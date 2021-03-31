package com.qwy.blogs.service;

import com.qwy.blogs.Interface.IFile;
import com.qwy.blogs.bean.FileBean;
import com.qwy.blogs.entity.ApplicationContextConf;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("fileService")
public class FileService implements IFile {
    String source = "com/qwy/blogs/dao/daoContext.xml";
    IFile fileDao = (IFile) ApplicationContextConf.getApplicationContext(source).getBean("fileDao");
    @Override
    public void addFile(FileBean fileBean) {
        fileDao.addFile(fileBean);
    }

    @Override
    public int fileCounts() {
        return fileDao.fileCounts();
    }

    @Override
    public int fileCountsById(String userId) {
        return fileDao.fileCountsById(userId);
    }

    @Override
    public List<FileBean> selectFilesByPage(int curPage, int pageSize) {
        return fileDao.selectFilesByPage(curPage, pageSize);
    }

    @Override
    public List<FileBean> selectMyFiles(int curPage, int pageSize, String userId) {
        return fileDao.selectMyFiles(curPage, pageSize, userId);
    }

    @Override
    public FileBean findFile(int id) {
        return fileDao.findFile(id);
    }

    @Override
    public int deleteFile(int id) {
        return fileDao.deleteFile(id);
    }
}
