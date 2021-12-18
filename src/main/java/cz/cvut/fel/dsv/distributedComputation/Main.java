package cz.cvut.fel.dsv.distributedComputation;

import cz.cvut.fel.dsv.distributedComputation.exceptions.AlreadyStartedException;
import cz.cvut.fel.dsv.distributedComputation.rmi.Server;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String... args) {
    boolean run = true;
    while (run) {
      switch (printMenu()) {
        case 0:
          try {
            Server.getInstance().start();
            System.out.println("Server has started.");
          } catch (AlreadyStartedException ex) {
            System.out.println("The server cannot be started because it is already running!");
          } catch (RemoteException | NotBoundException | MalformedURLException | AlreadyBoundException ex) {
            System.out.println("Server could not be started. Cause: " + ex.getClass().getName() + ": " + ex.getMessage());
          }
          break;

        case 1:
          break;

        case 2:
          break;

        case 3:
          System.out.println("Server is " + (Server.getInstance().isRunning()?"running":"stopped") + ".");
          break;

        case 4:
          System.out.println("Remotes [" + Server.getInstance().getRemotes().size() + "]: ");
          Server.getInstance().getRemotes().forEach(r -> System.out.println("  " + r));
          break;

        case 6:
          break;

        case 7:
          break;

        case 8:
          run = false;
          break;

        default:
          System.out.println("Unknown number!");
      }
    }
  }

  private static int printMenu() {
    while (true) {
      System.out.println();
      System.out.println("Menu:");
      System.out.println();
      System.out.println("  0 - Start");
      System.out.println("  1 - Stop");
      System.out.println("  2 - Drop");
      System.out.println("  3 - Check server state");
      System.out.println("  4 - Print remotes");
      System.out.println("  5 - Edit variable");
      System.out.println("  6 - Check token");
      System.out.println("  7 - Generate token");
      System.out.println("  8 - Terminate");
      System.out.println();
      System.out.print("Enter your option: ");

      try {
        int option = Integer.parseInt(scanner.nextLine());
        if (option < 0 || option > 7) {
          throw new NumberFormatException();
        }
        return option;
      } catch (NumberFormatException ex) {
        System.out.println("Not valid number or not in range!");
      }
    }
  }
}