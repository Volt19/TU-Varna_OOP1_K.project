package FileControl;

public final class MetadataFile extends BaseFile {
    public MetadataFile(String fileName, String content) {
        super(fileName, "Автор: " + content);
    }

    @Override
    public String getType() {
        return "Metadata File";
    }

    public void save() {
        super.save(FileConstants.META_DIR);
    }
}