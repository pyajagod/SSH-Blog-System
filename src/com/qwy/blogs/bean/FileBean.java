package com.qwy.blogs.bean;

public class FileBean {
    private int id;
    private String fileName;
    private String fileUploader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUploader() {
        return fileUploader;
    }

    public void setFileUploader(String fileUploader) {
        this.fileUploader = fileUploader;
    }

    public FileBean() {
    }

    public FileBean(int id, String fileName, String fileUploader) {
        this.id = id;
        this.fileName = fileName;
        this.fileUploader = fileUploader;
    }

    @Override
    public String toString() {
//        return super.toString();
    return "[id = " +  id + ", fileName = " +fileName + ", fileUploader = " + fileUploader + "]";
    }
}
