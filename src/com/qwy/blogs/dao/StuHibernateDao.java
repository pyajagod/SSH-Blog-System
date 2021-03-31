package com.qwy.blogs.dao;

import com.qwy.blogs.Interface.IStudent;
import com.qwy.blogs.bean.UserBean;
import com.qwy.blogs.entity.HibernateConnection;
import com.sun.jna.platform.win32.Netapi32Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;


import java.util.List;

//@Repository
@Component("studentInDao")
public class StuHibernateDao implements IStudent {
    private SessionFactory factory = null;
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public boolean checkUser(String userId) {
        if (this.getUser(userId) == null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public int userCount() {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();

        transaction = session.beginTransaction();

        String hql = "select count(*) from UserBean";

        Query query = session.createQuery(hql);
        Long count = (Long) query.list().get(0);

        transaction.commit();
        session.close();
        factory.close();
        return Math.toIntExact(count);
    }

    @Override
    public UserBean getUser(String userId) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "from  UserBean as u where u.userId = ?";

        Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        UserBean userBean = null;
        List<UserBean> user = query.list();
        if (user.size() != 0) {
            userBean = user.get(0);
        }
        transaction.commit();
        session.close();
        factory.close();
//        System.out.println(userBean);
        return userBean;
    }


    @Override
    public List<UserBean> getAllUsersByPage(int curPage, int pageSize) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();

        transaction = session.beginTransaction();

        String hql = "from UserBean as u order by u.id";

        Query query = session.createQuery(hql);

        query.setFirstResult((curPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<UserBean> users = query.list();

        transaction.commit();
        session.close();
        factory.close();

        return users;
    }

    @Override
    public int addUser(UserBean user) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();

        transaction = session.beginTransaction();

        session.save(user);

        transaction.commit();
        session.close();
        factory.close();

        return 1;
    }

    @Override
    public int selectUser(String userId, String UserPwd) {

        factory = HibernateConnection.getFactory();
        session = factory.openSession();

        transaction = session.beginTransaction();
        String hql = "from  UserBean as u where u.userId = ? and u.userPwd = ?";

        Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, UserPwd);
        List<UserBean> list = query.list();
        transaction.commit();
        session.close();
        factory.close();
        if (list.isEmpty()){
            return 0;
        }else {
            return 1;
        }

    }

    @Override
    public int changeProsition(String userId ,String userProsition) {
        String hql;
        hql = "update UserBean as u set u.userProsition=? where u.userId = ?";

        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();


        Query query = session.createQuery(hql);
        query.setParameter(0, userProsition);
        query.setParameter(1, userId);

        int count = query.executeUpdate();

        transaction.commit();
        session.close();
        factory.close();

        return count;
    }

    @Override
    public int changeInfomation(int id, String userId, String userName, String userPwd, String userSex) {
        factory = HibernateConnection.getFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();

        String hql = "update UserBean as u set u.userId = ?, u.userName = ?, u.userPwd = ?, u.userSex = ?  where u.id = ?";
        Query query = session.createQuery(hql);
//        query.setParameter(0, id);
        query.setParameter(0, userId);
        query.setParameter(1, userName);
        query.setParameter(2, userPwd);
        query.setParameter(3, userSex);
        query.setParameter(4, id);

        int count = query.executeUpdate();
        System.out.println(count);
        transaction.commit();
        session.close();
        factory.close();
        return count;
    }
}
