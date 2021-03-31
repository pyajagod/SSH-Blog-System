package com.qwy.blogs.action;
import com.opensymphony.xwork2.ActionSupport;
import com.qwy.blogs.Interface.IFile;
import com.qwy.blogs.bean.FileBean;
import com.qwy.blogs.entity.ApplicationContextConf;
import com.qwy.blogs.entity.PagingEntity;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class FileAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 1L;
    private int id;
    private File upfile;
    private String userId;
    private String uploaderId;
    private int totalPages;
    private int curPage;
    private String fileName;
    private InputStream currInputStream;
    private String upfileFileName;
    private String upfileContentType;
    private List<FileBean> fileBeans;
    private Map<String, Object> request;
    String source = "com/qwy/blogs/service/serviceContext.xml";
    IFile filService = (IFile) ApplicationContextConf.getApplicationContext(source).getBean("fileService");


    public List<FileBean> getFileBeans() {
        return fileBeans;
    }

    public void setFileBeans(List<FileBean> fileBeans) {
        this.fileBeans = fileBeans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getFileName() {
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public void setFileName(String fileName) {
        try {
            fileName = new String(fileName.getBytes(), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.fileName = fileName;
    }

    public InputStream getCurrInputStream() {
        currInputStream = ServletActionContext.getServletContext().getResourceAsStream("/file/"  + fileName);
        return currInputStream;
    }

    public void setCurrInputStream(InputStream currInputStream) {
        this.currInputStream = currInputStream;
    }

    public File getUpfile() {
        return upfile;
    }

    public void setUpfile(File upfile) {
        this.upfile = upfile;
    }

    public String getUpfileFileName() {
        return upfileFileName;
    }

    public void setUpfileFileName(String upfileFileName) {
        this.upfileFileName = upfileFileName;
    }

    public String getUpfileContentType() {
        return upfileContentType;
    }

    public void setUpfileContentType(String upfileContentType) {
        this.upfileContentType = upfileContentType;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public String uploadFile() throws IOException {
        FileBean files = new FileBean();
        System.out.println("in UploadAction method uploadFile()");
        String path = ServletActionContext.getServletContext().getRealPath("/file/" + upfileFileName);

        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            files.setFileUploader(uploaderId);
            files.setFileName(upfileFileName);
            filService.addFile(files);

            FileUtils.copyFile(upfile, file);
        }
        return "success";
    }

    public String manageFile(){
        int totalRows = filService.fileCountsById(userId);
        Map<String, Integer> params = PagingEntity.getPage(curPage, 10, totalRows);
        int curPage1 = params.get("curPage");
        int pageSize = params.get("pageSize");
        curPage = curPage1;
        totalPages = params.get("totalPages");
        fileBeans = filService.selectMyFiles(curPage1, pageSize, userId);
        return "success";
    }

    public String downloadFile(){
        int totalRows = filService.fileCounts();
        System.out.println("in FileListAction method execute()");
        Map<String, Integer> params = PagingEntity.getPage(curPage, 10, totalRows);
        int curPage1 = params.get("curPage");
        int pageSize = params.get("pageSize");
        curPage = curPage1;
        totalPages = params.get("totalPages");

        fileBeans = filService.selectFilesByPage(curPage1, pageSize);
//        String path = ServletActionContext.getServletContext().getRealPath("/file");
//        System.out.println("文件列表路径");
//        File file = new File(path);
//        String[] fileNames = file.list();
//        for (String fileName : fileNames){
//            System.out.println(fileName);
//        }
//        request.put("fileNames", fileNames);
        return "success";
    }

    public String download(){
        System.out.println("inDownLoadAction method execute()");
        System.out.println(fileName);
        return "success";
    }

    public String deleteFile(){
        FileBean fileBean = filService.findFile(id);
        String path = ServletActionContext.getServletContext().getRealPath("/file/" + fileBean.getFileName());
        File file = new File(path);
        if (file.exists()){
            file.delete();
            int count = filService.deleteFile(id);
        }
        uploaderId = fileBean.getFileUploader();
//        file.delete();

        return "success";
    }
}
