package cz.cvut.fel.dsv.distributedComputation;

import cz.cvut.fel.dsv.distributedComputation.rmi.DsvImplementation;
import cz.cvut.fel.dsv.distributedComputation.rmi.DsvStub;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static Scanner scanner = new Scanner(System.in);

  private static Main main;

  public static void main(String... args) {
    boolean run = true;
    while (run) {
      System.out.println("  1 - Start node");
      System.out.println("  2 - Exit");
      System.out.println();
      System.out.print("Enter option: ");
      var option = scanner.nextLine();
      if (option.equalsIgnoreCase("1")) {
        try {
          main = new Main();
          main = null;
        } catch (Exception e) {
          System.out.println(e.getMessage());
          e.printStackTrace();
        }
      } else if (option.equalsIgnoreCase("2")) {
        run = false;
      } else {
        System.out.println("Unknown option!");
      }
    }
  }

  private final List<Remote> remotes;

  private InetAddress address;

  private Main() throws Exception {
    remotes = new ArrayList<>();
    System.out.println();

    DsvImplementation impl = new DsvImplementation();
    LocateRegistry.createRegistry(54321);
    Naming.rebind("//localhost:54321/dsv", impl);

    while (true) {
      System.out.print("Is there any other node? [y/n]: ");
      var option = scanner.nextLine();
      if (option.equalsIgnoreCase("y")) {

        InetAddress remoteAddress;
        int remotePort;

        while (true) {
          System.out.print("Enter IP address of the other node: ");
          try {
            remoteAddress = InetAddress.getByName(scanner.nextLine());
            break;
          } catch (UnknownHostException ex) {
            System.out.println("Unknown address!");
          }
        }
        while (true) {
          System.out.print("Enter port number of the other node: ");
          try {
            remotePort = Integer.parseInt(scanner.nextLine());
            break;
          } catch (NumberFormatException ex) {
            System.out.println("Unknown address!");
          }
        }
        var remoteUrl = "rmi://" + remoteAddress.getHostAddress() + ":" + remotePort + "/dsv";
        System.out.println("Connecting to " + remoteUrl);
        var remote = new Remote();
        remote.setAddress(remoteAddress);
        remote.setPort(remotePort);
        remote.setRemote((DsvStub) Naming.lookup(remoteUrl));
        remote.getRemote().connecting(address, 54321);
        break;
      } else if (option.equalsIgnoreCase("n")) {
        break;
      }
      System.out.println("Unknown option!");
    }

    loop();
  }

  private void loop() {
    boolean run = true;
    while (run) {

    }
  }

  public void newNode(InetAddress address, int port) {
    System.out.println("New node on" + address.getHostAddress() + ":" + port);
  }

  public static Main getMain() {
    return main;
  }
}