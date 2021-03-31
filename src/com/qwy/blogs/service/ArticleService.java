package com.qwy.blogs.service;

import com.qwy.blogs.Interface.IArticle;
import com.qwy.blogs.bean.ArticleBean;
import com.qwy.blogs.entity.ApplicationContextConf;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("articleService")
public class ArticleService implements IArticle {
    String source = "com/qwy/blogs/dao/daoContext.xml";
    IArticle articleDao = (IArticle) ApplicationContextConf.getApplicationContext(source).getBean("articleDao");


    @Override
    public int articleCount() {
        return articleDao.articleCount();
    }

    @Override
    public int articleCountById(String userId) {
        return articleDao.articleCountById(userId);
    }

    @Override
    public int addArticle(ArticleBean article) {
        return articleDao.addArticle(article);
    }

    @Override
    public List<ArticleBean> showArticles() {
        return articleDao.showArticles();
    }

    @Override
    public ArticleBean selectArticle(int id) {
        return articleDao.selectArticle(id);
    }

    @Override
    public int selectArticlesByWordCount(String word) {
        return articleDao.selectArticlesByWordCount(word);
    }

    @Override
    public List<ArticleBean> selectArticleByWord(String word, int curPage, int pageSize) {
        return articleDao.selectArticleByWord(word, curPage, pageSize);
    }

    @Override
    public List<ArticleBean> showArticlesByPage(int curPage, int pageSize) {
        return articleDao.showArticlesByPage(curPage, pageSize);
    }

    @Override
    public List<Integer> selectArticlesByUserId(String userId) {
        return articleDao.selectArticlesByUserId(userId);
    }

    @Override
    public List<ArticleBean> showMyArticles(int curPage, int pageSize, String userId) {
        return articleDao.showMyArticles(curPage, pageSize, userId);
    }

    @Override
    public void updateCount(int id) {
        articleDao.updateCount(id);
    }

    @Override
    public int updateUploader(List<Integer> ids, String userid) {
        return articleDao.updateUploader(ids, userid);
    }

    @Override
    public int deleteArticle(int id) {
        return articleDao.deleteArticle(id);
    }
}
