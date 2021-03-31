package com.qwy.blogs.Interface;

import com.qwy.blogs.bean.ArticleBean;

import java.util.List;

public interface IArticle {

    int articleCount();
    int articleCountById(String userId);

    int addArticle(ArticleBean article);

    List<ArticleBean> showArticles();
    List<Integer> selectArticlesByUserId(String userId);

    int selectArticlesByWordCount(String word);
    List<ArticleBean> selectArticleByWord(String word, int curPage, int pageSize);

    List<ArticleBean> showArticlesByPage(int curPage, int pageSize);

    List<ArticleBean> showMyArticles(int curPage, int pageSize, String userId);

    ArticleBean selectArticle(int id);

    void updateCount(int id);

    int updateUploader(List<Integer> ids, String userid);

    int deleteArticle(int id);



}
