package cz.cvut.fel.dsv.distributedComputation;

import cz.cvut.fel.dsv.distributedComputation.server.Server;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/** This is the main class of the Distributed Computation. */
public class Main {

  /**
   * Main method of the program for Distributed Computing.
   *
   * @param args
   */
  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);
    boolean run = true;

    while (run) {
      System.out.println();
      System.out.println("1 - Start node");
      System.out.println("2 - Exit");
      System.out.println();
      System.out.print("Select option: ");

      int option = 0;

      try {
        option = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException ex) {
        System.out.println("Enter a number! (" + ex.getMessage() + ")");
        continue;
      }

      switch (option) {
        case 1:
          try {
            new Server();
          } catch (RemoteException e) {
            e.printStackTrace();
          } catch (UnknownHostException e) {
            e.printStackTrace();
          } catch (NotBoundException e) {
            e.printStackTrace();
          }
          break;

        case 2:
          run = false;
          break;

        default:
          System.out.println("Unknown option!");
          break;
      }
    }
    System.out.println("Good bye.");
  }
}
