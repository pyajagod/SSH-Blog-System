package com.qwy.blogs.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qwy.blogs.Interface.IArticle;
import com.qwy.blogs.Interface.IStudent;
import com.qwy.blogs.bean.ArticleBean;
import com.qwy.blogs.entity.ApplicationContextConf;
import com.qwy.blogs.entity.PagingEntity;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ArticleAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private int id;
    private int idd;
    private int totalPages;
    private int curPage;
    private String word;
    private File file;
    private String uploader;
    private String title;
    private String label;
    private String userId;
    private String userIdd;
    private Date blogUploadTime;
    private int blogReadTimes;
    private String introduction;
    private String content1;
    private String result;
    private List<ArticleBean> articles;
    private List<String> ids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUserIdd() {
        return userIdd;
    }

    public void setUserIdd(String userIdd) {
        this.userIdd = userIdd;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPages() {
        return totalPages;
    }


    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBean> articles) {
        this.articles = articles;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }


    String source = "com/qwy/blogs/service/serviceContext.xml";
    IArticle articleService = (IArticle) ApplicationContextConf.getApplicationContext(source).getBean("articleService");
    IStudent iStudent = (IStudent) ApplicationContextConf.getApplicationContext(source).getBean("studentService");

    public String addArticle(){
        ArticleBean articleBean = new ArticleBean();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            blogUploadTime = df.parse(df.format(new Date(System.currentTimeMillis())));
            System.out.println(blogUploadTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        articleBean.setBlogTitle(title);
        articleBean.setBlogLabel(label);
        articleBean.setBlogUploader(uploader);
        articleBean.setBlogContent(content1);
        articleBean.setBlogIntroduction(introduction);
//        System.out.println(introduction);
//        articleBean.setBlogReadTimes(0);
        articleBean.setBlogUploadTime(blogUploadTime);
//        System.out.println(articleBean);
        int count = articleService.addArticle(articleBean);
//        System.out.println(count);
        ActionContext.getContext().getSession().put("userId", articleBean.getBlogUploader());
//        request.setAttribute("userId", articleBean.getBlogUploader());
        return "input";
    }

    public String selectArticle(){

        int totalRows = articleService.articleCount();
//        System.out.println(totalRow);
        Map<String, Integer> params = PagingEntity.getPage(curPage, 4, totalRows);
        int curPage1 = params.get("curPage");
        int pageSize = params.get("pageSize");
        curPage = curPage1;
        totalPages = params.get("totalPages");
        ids = new ArrayList<>();

        articles = articleService.showArticlesByPage(curPage1, pageSize);
        for (ArticleBean article : articles){
            String username = iStudent.getUser(article.getBlogUploader()).getUserName();
            ids.add(username);
//            System.out.println("文章的信息：" + article);
        }

        return "input";
    }

    public String readArticle(){
        System.out.println(id);
        ArticleBean article = articleService.selectArticle(id);
        articleService.updateCount(id);
        String username = iStudent.getUser(article.getBlogUploader()).getUserName();

        ActionContext.getContext().getSession().put("username", username);

        title = article.getBlogTitle();
        blogUploadTime = article.getBlogUploadTime();
        blogReadTimes = article.getBlogReadTimes();
        content1 = article.getBlogContent();

        return "input";
    }

    public String selectArticles(){
        int totalRow = articleService.articleCountById(userId);
//        System.out.println(totalRow);
        Map<String, Integer> params = PagingEntity.getPage(curPage, 6, totalRow);
        int curPage1 = params.get("curPage");
        int pageSize = params.get("pageSize");
        curPage = curPage1;
        totalPages = params.get("totalPages");

        ids = new ArrayList<>();
        articles = articleService.showMyArticles(curPage1, pageSize, userId);

        for (ArticleBean article : articles){
            String username = iStudent.getUser(article.getBlogUploader()).getUserName();
            ids.add(username);
        }
        return "input";
    }

    public String deleteArticle(){
        int count = articleService.deleteArticle(idd);
        ActionContext.getContext().getSession().put("userId", userIdd);
        return count > 0 ? "input" : "error";
    }

    public String searchArticleByWord(){
        int totalRow = articleService.selectArticlesByWordCount(word);
        System.out.println(totalRow);
        Map<String, Integer> params = PagingEntity.getPage(curPage, 6, totalRow);
        int curPage1 = params.get("curPage");
        int pageSize = params.get("pageSize");
        curPage = curPage1;
        totalPages = params.get("totalPages");

        System.out.println(curPage1 + "===============" + pageSize);
        ids = new ArrayList<>();
        articles = articleService.selectArticleByWord(word, curPage1, pageSize);
        curPage = curPage1;
        for (ArticleBean article : articles){
            String username = iStudent.getUser(article.getBlogUploader()).getUserName();
            ids.add(username);
        }
        return "input";
    }
}
