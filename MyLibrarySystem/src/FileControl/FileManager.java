package FileControl;

public final class FileManager {
    public void createFile(String fileName, String content) {
        if (content.startsWith("Автор")) {
            String author = content.substring(6).trim(); // Извличане на автора
            MetadataFile metadataFile = new MetadataFile(fileName, author);
            metadataFile.save();
        } else {
            TextFile textFile = new TextFile(fileName, content);
            textFile.save();
        }
    }
}
