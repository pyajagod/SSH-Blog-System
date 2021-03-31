package com.qwy.blogs.Interface;

import com.qwy.blogs.bean.FileBean;

import java.io.File;
import java.util.List;

public interface IFile {

    void addFile(FileBean fileBean);
    int fileCounts();
    int fileCountsById(String userId);
    FileBean findFile(int id);
    List<FileBean> selectFilesByPage(int curPage, int pageSize);
    List<FileBean> selectMyFiles(int curPage, int pageSize, String userId);
    int deleteFile(int id);
}
