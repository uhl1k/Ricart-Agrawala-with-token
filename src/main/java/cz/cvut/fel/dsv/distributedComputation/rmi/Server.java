package cz.cvut.fel.dsv.distributedComputation.rmi;

import cz.cvut.fel.dsv.distributedComputation.exceptions.AlreadyStartedException;
import cz.cvut.fel.dsv.distributedComputation.exceptions.AlreadyStoppedException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

  public static final int PORT = 2010;

  public static final String NAME = "dsv";

  private static final Server INSTANCE = new Server();

  private Scanner scanner;

  private boolean running;

  private List<Remote> remotes;

  private Registry registry;

  private InetAddress address;

  private Server() {
    running = false;
    remotes = new ArrayList<>();
    scanner = new Scanner(System.in);
  }

  public static Server getInstance() {
    return INSTANCE;
  }

  public void start() throws AlreadyStartedException, RemoteException, NotBoundException, MalformedURLException, AlreadyBoundException {
    if (running) {
      throw new AlreadyStartedException();
    }
    System.out.println("Attempting to start ...");
    running = true;

    while (true) {
      System.out.print("Enter my IP address: ");
      try {
        address = InetAddress.getByName(scanner.nextLine());
      } catch (UnknownHostException ex) {
        System.out.println("Not a valid IP address!");
        continue;
      }
      System.out.println("Moje ip " + address.getHostAddress());
      break;
    }

    System.setProperty("java.rmi.server.hostname", address.getHostAddress());
    registry = LocateRegistry.createRegistry(PORT);
    registry.rebind(NAME, new DsvImplementation());

    while (true) {
      System.out.print("Connect to another node? [y/n]: ");
      String option = scanner.nextLine();
      if (option.equalsIgnoreCase("y")) {
        connectToOtherNode();
        break;
      } else if (option.equalsIgnoreCase("n")) {
        break;
      }
      System.out.println("Not valid option! Please enter 'y' or 'n'.");
    }
  }

  private void connectToOtherNode() throws NotBoundException, RemoteException, MalformedURLException {
    InetAddress connectTo;
    while (true) {
      System.out.print("Enter node's IP address: ");
      try {
        connectTo = InetAddress.getByName(scanner.nextLine());
      } catch (UnknownHostException ex) {
        System.out.println("Not a valid IP address!");
        continue;
      }
      break;
    }
    Remote remote = new Remote();
    remote.setAddress(connectTo);
    Registry registry = LocateRegistry.getRegistry(connectTo.getHostAddress(), PORT);
    remote.setRemote((DsvStub) registry.lookup(NAME));
    remote.getRemote().connecting(address);
    remotes.add(remote);
  }

  public void stop() throws AlreadyStoppedException {
    if (!running) {
      throw new AlreadyStoppedException();
    }
    running = false;
  }

  public void drop() throws AlreadyStoppedException {
    if (!running) {
      throw new AlreadyStoppedException();
    }
    running = false;
  }

  public boolean isRunning() {
    return running;
  }

  public List<Remote> getRemotes() {
    return remotes;
  }

  public void addRemote(Remote remote) {
    remotes.add(remote);
  }
}
