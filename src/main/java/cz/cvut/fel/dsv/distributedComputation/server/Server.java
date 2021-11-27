package cz.cvut.fel.dsv.distributedComputation.server;

import cz.cvut.fel.dsv.distributedComputation.model.Node;
import cz.cvut.fel.dsv.distributedComputation.model.NodeID;
import cz.cvut.fel.dsv.distributedComputation.rmi.StubImplementation;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

  public static final String DSV_REGISTRY_NAME = "DSV-RMI";

  private Scanner scanner;
  private Registry registry;

  private volatile boolean run = true;

  private List<Node> nodes = new ArrayList<>();

  public Server() throws RemoteException, UnknownHostException, NotBoundException {
    scanner = new Scanner(System.in);

    var implementation = new StubImplementation();
    registry = LocateRegistry.createRegistry(54321);
    registry.rebind(DSV_REGISTRY_NAME, implementation);

    while (true) {
      System.out.print("Is there any other node? [y/n]: ");
      var option = scanner.nextLine();
      if (option.equalsIgnoreCase("y")) {
        connectToNode();
        break;
      } else if (option.equalsIgnoreCase("n")) {
        break;
      }
      System.out.println("Unknown option!");
    }
    mainLoop();
  }

  private void connectToNode() throws UnknownHostException, NotBoundException, RemoteException {
    System.out.print("Remote IP address: ");
    var address = scanner.nextLine();
    int port;

    while (true) {
      System.out.print("Remote port: ");
      try {
        port = Integer.parseInt(scanner.nextLine());
        if (port < 1 || port > 65535) {
          throw new NumberFormatException("Port number not in range!");
        }
      } catch (NumberFormatException ex) {
        System.out.println("Not valid port number!");
        continue;
      }
      break;
    }
    Node node = Node.getNodeFromAddressAndPort(InetAddress.getByName(address), port);
    nodes.add(node);
    node.getRemote().connecting(new NodeID(InetAddress.getLocalHost(), 54321));
  }

  private void mainLoop() {
    while (run) {
      System.out.println("1 - Read value");
      System.out.println("2 - Write value");
      System.out.println("3 - Generate token");
      System.out.println("4 - Disconnect");
      System.out.println("5 - Drop");
      System.out.println();
      System.out.print("Select option: ");

      int option;

      try {
        option = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException ex) {
        System.out.println("Unknown option!");
        continue;
      }

      switch (option) {
        case 1:
          break;

        case 2:
          break;

        case 3:
          break;

        case 4:
          disconnect();

        case 5:
          run = false;
          break;

        default:
          System.out.println("Unknown option!");
      }
    }
  }

  private void disconnect() {

  }
}
