package com.qwy.blogs.dao;

import com.qwy.blogs.Interface.IFile;
import com.qwy.blogs.bean.FileBean;
import com.qwy.blogs.entity.HibernateConnection;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("fileDao")
public class FileDao implements IFile {
    private SessionFactory factory = null;
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public void addFile(FileBean fileBean) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        session.save(fileBean);
        transaction.commit();
        session.close();
        factory.close();
    }

    @Override
    public FileBean findFile(int id) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "from FileBean f where f.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        List<FileBean> file = query.list();
        FileBean fileBean = null;
        if (file.size() != 0){
            fileBean = file.get(0);
        }
        return fileBean;
    }

    @Override
    public int fileCounts() {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "select count(*) from FileBean";
        Query query = session.createQuery(hql);
        Long count = (Long) query.list().get(0);

        transaction.commit();
        session.close();
        factory.close();
        return Math.toIntExact(count);
    }

    @Override
    public int fileCountsById(String userId) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "select count(*) from FileBean f where f.fileUploader = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        Long count = (Long) query.list().get(0);

        transaction.commit();
        session.close();
        factory.close();
        return Math.toIntExact(count);
    }

    @Override
    public List<FileBean> selectFilesByPage(int curPage, int pageSize) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from FileBean";
        Query query = session.createQuery(hql);
        query.setFirstResult((curPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<FileBean> fileBeans = query.list();

        transaction.commit();
        session.close();
        factory.close();

        return fileBeans;
    }

    @Override
    public List<FileBean> selectMyFiles(int curPage, int pageSize, String userId) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "from FileBean u where u.fileUploader = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, userId);

        query.setFirstResult((curPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<FileBean> fileBeans = query.list();

        transaction.commit();
        session.close();
        factory.close();

        return fileBeans;
    }

    @Override
    public int deleteFile(int id) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "delete from FileBean u where u.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        int count = query.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();
        return count;
    }
}
