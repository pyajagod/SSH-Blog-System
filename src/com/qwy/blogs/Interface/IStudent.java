package com.qwy.blogs.Interface;


import com.qwy.blogs.bean.UserBean;

import java.util.List;

public interface IStudent {
    boolean checkUser(String userId);
    int userCount();
    UserBean getUser(String userId);
    List<UserBean> getAllUsersByPage(int curPage, int pageSize);
    int addUser(UserBean user);
    int selectUser(String userId, String UserPwd);

    int changeProsition(String userId, String userProsition);
    int changeInfomation(int id, String userId, String userName, String userPwd, String userSex);
}
