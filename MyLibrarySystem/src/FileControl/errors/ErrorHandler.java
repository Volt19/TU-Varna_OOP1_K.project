package FileControl.errors;

import bg.tu_varna.sit.FileConstants;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public final class ErrorHandler {
    private static ErrorHandler instance;

    private ErrorHandler() {
    }

    public static ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }

    public void logError(String errorMessage) {
        try (FileWriter writer = new FileWriter(FileConstants.ERROR_FILE, true)) {
            writer.write(LocalDateTime.now() + " - " + errorMessage + "\n");
        } catch (IOException e) {
            System.err.println("Failed to log error: " + e.getMessage());
        }
    }
}
