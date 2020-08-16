package kafka.micronaut.app.enums;

public enum FileType {
    TXT("txt"), PDF("pdf"), DOC("doc");

    private String type;

    FileType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
