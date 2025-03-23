package FileControl;

public final class TextFile extends BaseFile {
    public TextFile(String fileName, String content) {
        super(fileName, content);
    }

    @Override
    public String getType() {
        return "Text File";
    }

    public void save() {
        super.save(FileConstants.BASE_DIR);
    }
}
