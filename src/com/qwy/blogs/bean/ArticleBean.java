package com.qwy.blogs.bean;

import java.util.Date;

public class ArticleBean {
    private int id;
    private String blogTitle;
    private String blogLabel;
    private Date blogUploadTime;
    private int blogReadTimes;
    private String blogIntroduction;
    private String blogContent;
    private String blogUploader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogLabel() {
        return blogLabel;
    }

    public void setBlogLabel(String blogLabel) {
        this.blogLabel = blogLabel;
    }

    public Date getBlogUploadTime() {
        return blogUploadTime;
    }

    public void setBlogUploadTime(Date blogUploadTime) {
        this.blogUploadTime = blogUploadTime;
    }

    public int getBlogReadTimes() {
        return blogReadTimes;
    }

    public void setBlogReadTimes(int blogReadTimes) {
        this.blogReadTimes = blogReadTimes;
    }

    public String getBlogIntroduction() {
        return blogIntroduction;
    }

    public void setBlogIntroduction(String blogIntroduction) {
        this.blogIntroduction = blogIntroduction;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogUploader() {
        return blogUploader;
    }

    public void setBlogUploader(String blogUploader) {
        this.blogUploader = blogUploader;
    }

    public ArticleBean() {
    }

    public ArticleBean(String blogTitle, String blogLabel, Date blogUploadTime, int blogReadTimes, String blogContent, String blogUploader) {
        this.blogTitle = blogTitle;
        this.blogLabel = blogLabel;
        this.blogUploadTime = blogUploadTime;
        this.blogReadTimes = blogReadTimes;
        this.blogContent = blogContent;
        this.blogUploader = blogUploader;
    }

    public ArticleBean(int id, String blogTitle, String blogLabel, Date blogUploadTime, int blogReadTimes, String blogContent, String blogUploader) {
        this.id = id;
        this.blogTitle = blogTitle;
        this.blogLabel = blogLabel;
        this.blogUploadTime = blogUploadTime;
        this.blogReadTimes = blogReadTimes;
        this.blogContent = blogContent;
        this.blogUploader = blogUploader;
    }

    @Override
    public String toString() {
        return "[ blogTitle = " + blogTitle + ", blogLabel = " + blogLabel + ", blogUploadTime = " + blogUploadTime + ", blogReadTimes = " + blogReadTimes + ", blogIntroduction = " + blogIntroduction + ", blogContent = " + blogContent + ", blogUploader = " + blogUploader + "]";
    }
}
