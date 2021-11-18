package cz.cvut.fel.dsv.distributedComputation;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/** This is the main class of the Distributed Computation. */
public class Main {

  /** Logger for logging messages into the console. */
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  /** Static initializer to initialise the logger. */
  static {
    LOGGER.setUseParentHandlers(false);
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(
        new Formatter() {
          //  The format is: YYYY-MM-DD HH:MM:SS: [ _x_ ] _message_
          private static final String format = "%1$tF %1$tT: %2$s %n";

          @Override
          public String format(LogRecord record) {
            return String.format(format, new Date(record.getMillis()), record.getMessage());
          }
        });
    LOGGER.addHandler(handler);
  }

  /**
   * Main method of the program for Distributed Computing.
   *
   * @param args
   */
  public static void main(String... args) {
    LOGGER.info(() -> "[ ↺ ] Distributed Computation is starting ...");
  }
}
