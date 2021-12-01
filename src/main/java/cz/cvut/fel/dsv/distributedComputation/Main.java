package cz.cvut.fel.dsv.distributedComputation;

import java.util.Scanner;

public class Main {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String... args) {
    var run = true;
    while (run) {
      switch (printMenu()) {
        case 0:
          break;

        case 1:
          break;

        case 2:
          break;

        case 3:
          break;

        case 4:
          break;

        case 6:
          break;

        case 7:
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
      System.out.println("  4 - Edit variable");
      System.out.println("  5 - Check token");
      System.out.println("  6 - Generate token");
      System.out.println("  7 - Terminate");
      System.out.println();
      System.out.print("Enter your option: ");

      try {
        var option = Integer.parseInt(scanner.nextLine());
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