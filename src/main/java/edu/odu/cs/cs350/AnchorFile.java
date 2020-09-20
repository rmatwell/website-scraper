package edu.odu.cs.cs350;

public class AnchorFile {

    private String path;

    private String size;

    public AnchorFile() {
        path = "";
        size = "";
    }

    public AnchorFile(String path, String size) {
        this.path = path;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
