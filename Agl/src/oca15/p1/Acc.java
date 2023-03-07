package oca15.p1;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Acc {
    private static final Logger LOGGER = Logger.getLogger(Acc.class.getName());
    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("application.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            // some code
        } catch (IOException e) {
            LOGGER.severe("Error occurred while setting up file logger: " + e);
        }
    }
}
