package FileControl;

import FileControl.errors.ErrorHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BaseFile {
    private final String fileName;
    private final String content;

    public BaseFile(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public void save(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(directory + File.separator + fileName, true)) {
            writer.write(content+ "\n");
        } catch (IOException e) {
            ErrorHandler.getInstance().logError("Failed to save file: " + fileName + " - " + e.getMessage());
        }
    }

    // Абстрактен метод за специфична логика на наследниците
    public abstract String getType();
}
