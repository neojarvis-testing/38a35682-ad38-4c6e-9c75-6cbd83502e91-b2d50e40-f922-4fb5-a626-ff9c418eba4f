

package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;

public class LoggerHandler {
    private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
    private static Date date = new Date();
    private static String logDirectoryPath = System.getProperty("user.dir") + "/logs/";
    private static String logFilePath = logDirectoryPath + File.separator + dateFormat.format(date) + ".log";

    public static final Logger log = Logger.getLogger(LoggerHandler.class.getName());

    static {
        try {
            LogManager.getLogManager().reset();

            // Ensure the log directory exists
            File logDirectory = new File(logDirectoryPath);
            if (!logDirectory.exists()) {
                if (logDirectory.mkdirs()) {
                    System.out.println("Log directory created: " + logDirectoryPath);
                } else {
                    System.err.println("Failed to create log directory: " + logDirectoryPath);
                }
            }

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new java.util.logging.SimpleFormatter());

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new java.util.logging.SimpleFormatter());

            log.addHandler(fileHandler);
            log.addHandler(consoleHandler);
            log.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            consoleHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Could not set up logger configuration", e);
        }
    }

    public static Logger getLogger() {
        return log;
    }

    public static void closeHandler() {
        Handler[] handlers = log.getHandlers();
        for (Handler handler : handlers) {
            handler.close();
        }
    }
}
