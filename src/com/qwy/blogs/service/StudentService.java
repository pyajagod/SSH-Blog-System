package com.qwy.blogs.service;

import com.qwy.blogs.Interface.IStudent;
import com.qwy.blogs.bean.UserBean;
import com.qwy.blogs.entity.ApplicationContextConf;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("studentService")
public class StudentService implements IStudent {
    String source = "com/qwy/blogs/dao/daoContext.xml";
    IStudent studentDao = (IStudent) ApplicationContextConf.getApplicationContext(source).getBean("studentInDao");

    @Override
    public boolean checkUser(String userId) {
        if (studentDao.checkUser(userId)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int userCount() {
        return studentDao.userCount();
    }

    @Override
    public UserBean getUser(String userId) {
        UserBean userBean = studentDao.getUser(userId);
        return userBean;
    }

    @Override
    public List<UserBean> getAllUsersByPage(int curPage, int pageSize) {
        return studentDao.getAllUsersByPage(curPage, pageSize);
    }

    @Override
    public int addUser(UserBean user) {
        int count = studentDao.addUser(user);
        return count;
    }

    @Override
    public int selectUser(String userId, String UserPwd) {
        int count = studentDao.selectUser(userId, UserPwd);
        return count;
    }

    @Override
    public int changeProsition(String userId, String userProsition) {
        return studentDao.changeProsition(userId, userProsition);
    }

    @Override
    public int changeInfomation(int id, String userId, String userName, String userPwd, String userSex) {
        return studentDao.changeInfomation(id, userId, userName, userPwd, userSex);
    }
}
