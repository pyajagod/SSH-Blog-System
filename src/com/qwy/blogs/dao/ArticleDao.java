package com.qwy.blogs.dao;

import com.qwy.blogs.Interface.IArticle;
import com.qwy.blogs.bean.ArticleBean;
import com.qwy.blogs.entity.HibernateConnection;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("articleDao")
public class ArticleDao implements IArticle {
    private SessionFactory factory = null;
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public int articleCount() {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();

        transaction = session.beginTransaction();

        String hql = "select count(*) from ArticleBean ";

        Query query = session.createQuery(hql);
        Long count = (Long) query.list().get(0);

        transaction.commit();
        session.close();
        factory.close();
        return Math.toIntExact(count);
    }

    @Override
    public int articleCountById(String userId) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();

        transaction = session.beginTransaction();

        String hql = "select count(*) from ArticleBean a where a.blogUploader = ?";

        Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        Long count = (Long) query.list().get(0);

        transaction.commit();
        session.close();
        factory.close();
        return Math.toIntExact(count);
    }

    @Override
    public int addArticle(ArticleBean article) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        session.save(article);
        transaction.commit();
        session.close();
        factory.close();

        return 1;
    }

    @Override
    public List<ArticleBean> showArticles() {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from ArticleBean";
        Query query = session.createQuery(hql);
        List<ArticleBean> articles = query.list();

        transaction.commit();
        session.close();
        factory.close();

        return articles;
    }

    @Override
    public List<ArticleBean> showArticlesByPage(int curPage, int pageSize) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from ArticleBean";
        Query query = session.createQuery(hql);
        query.setFirstResult((curPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<ArticleBean> articles = query.list();

        transaction.commit();
        session.close();
        factory.close();

        return articles;
    }

    @Override
    public List<ArticleBean> showMyArticles(int curPage, int pageSize, String userId) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from ArticleBean a where a.blogUploader = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setFirstResult((curPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<ArticleBean> articles = query.list();

        transaction.commit();
        session.close();
        factory.close();

        return articles;
    }

    @Override
    public ArticleBean selectArticle(int id) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from ArticleBean where id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, id);

        List<ArticleBean> articles = query.list();

        ArticleBean article = articles.get(0);

        return article;
    }

    @Override
    public int selectArticlesByWordCount(String word) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "select count(*) from ArticleBean a where a.blogTitle like:word1 or a.blogIntroduction like:word2";
        Query query = session.createQuery(hql);
        query.setString("word1", "%"+word+"%");
        query.setString("word2", "%"+word+"%");
        Long count  = (Long) query.list().get(0);
        System.out.println("======================" + count + "=====================");
        transaction.commit();
        session.close();
        factory.close();
        return Math.toIntExact(count);
    }

    @Override
    public List<ArticleBean> selectArticleByWord(String word, int curPage, int pageSize) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from ArticleBean a where a.blogTitle like:word1 or a.blogIntroduction like:word2";
        Query query = session.createQuery(hql);
        query.setString("word1", "%"+word+"%");
        query.setString("word2", "%"+word+"%");

        query.setFirstResult((curPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<ArticleBean> articles = query.list();

        transaction.commit();
        session.close();
        factory.close();

        return articles;
    }

    @Override
    public List<Integer> selectArticlesByUserId(String userId) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from ArticleBean a where a.blogUploader = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, userId);

        List<ArticleBean> articles = query.list();
        List<Integer> ids = new ArrayList<>();
        for (ArticleBean a : articles){
//            System.out.println(a.getId());
            ids.add(a.getId());
        }

        return ids;
    }

    @Override
    public void updateCount(int id) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "update ArticleBean a set a.blogReadTimes = a.blogReadTimes + 1 where id = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, id);

        query.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();

    }

    @Override
    public int updateUploader(List<Integer> ids, String userid) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        int sum=0;
        for (Integer id : ids){
            String hql = "update ArticleBean a set a.blogUploader = ? where a.id = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, userid);
            query.setParameter(1, (int)id);
//            System.out.println(userid + "=======" + id );
            query.executeUpdate();
            sum++;
        }
        transaction.commit();
        session.close();
        factory.close();

        return sum;
    }

    @Override
    public int deleteArticle(int id) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "delete from ArticleBean a where a.id = ?";

        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        int ret = query.executeUpdate();

        transaction.commit();
        session.close();
        factory.close();
        return ret;
    }
}
