package com.qwy.blogs.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qwy.blogs.Interface.IArticle;
import com.qwy.blogs.Interface.IStudent;
import com.qwy.blogs.bean.UserBean;
import com.qwy.blogs.entity.ApplicationContextConf;
import com.qwy.blogs.entity.PagingEntity;

import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {
    private int curPage;
    private int totalPages;
    private String userId;
    private String userid;
    private String userProsition;
    private String securityCode;
    private UserBean user;
    private List<UserBean> userBeans;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserProsition() {
        return userProsition;
    }

    public void setUserProsition(String userProsition) {
        this.userProsition = userProsition;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<UserBean> getUserBeans() {
        return userBeans;
    }

    public void setUserBeans(List<UserBean> userBeans) {
        this.userBeans = userBeans;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    String source = "com/qwy/blogs/service/serviceContext.xml";
    IStudent studentService = (IStudent) ApplicationContextConf.getApplicationContext(source).getBean("studentService");
//    String source1 = "com/qwy/blogs/service/serviceContext.xml";
    IArticle articleService = (IArticle) ApplicationContextConf.getApplicationContext(source).getBean("articleService");

    public String register(){

        if (studentService.checkUser(user.getUserId())){
            addActionError("该用户已存在");
            return ERROR;
        }else {
            int i = studentService.addUser(user);
            if (i > 0) {
//                System.out.println(user.getUserName());
                return SUCCESS;
            } else {
                addActionError("注册失败");
                return ERROR;
            }
        }
    }

    public String login(){
        String securityCheck = (String) ActionContext.getContext().getSession().get("SESSION_SECURITY_CODE");
//        System.out.println(securityCheck + "===" + securityCode);
        if (!securityCheck.equals(securityCode)){
            return "error";
        }else {
            if (studentService.checkUser(user.getUserId())) {
                UserBean userBean = studentService.getUser(user.getUserId());
//            System.out.println(userBean);
                int count = studentService.selectUser(user.getUserId(), user.getUserPwd());
                if (count > 0) {
                    ActionContext.getContext().getSession().put("user", userBean);
                    return "success";
                } else {
//                addActionError("用户名或密码错误");
                    return "error";
                }
            } else {
//            addActionError("该用户不存在");
                return "error";
            }
        }
    }

    public String showUsers(){
        int totalRows = studentService.userCount();
        Map<String, Integer> params = PagingEntity.getPage(curPage, 10, totalRows);
        int curPage1 = params.get("curPage");
        int pageSize = params.get("pageSize");
        curPage = curPage1;
        totalPages = params.get("totalPages");
        userBeans = studentService.getAllUsersByPage(curPage1, pageSize);
        return "input";
    }

    public String changeProsition(){
        int count = 0;
        if ("管理员".equals(userProsition)){
            count = studentService.changeProsition(userId, "普通用户");
        }else {
            count = studentService.changeProsition(userId, "管理员");
        }
        if (count > 0){
            return "input";
        }else {
            return "error";
        }

    }

    public String changeInformation(){
        UserBean user = studentService.getUser(userId);
        ActionContext.getContext().getSession().put("user", user);
        System.out.println(user);
        return "input";
    }

    public String changeInfo(){
//        System.out.println(user);
        int count = studentService.changeInfomation(user.getId(), user.getUserId(), user.getUserName(), user.getUserPwd() ,user.getUserSex());
        UserBean userBean = studentService.getUser(user.getUserId());
        List<Integer> ids = articleService.selectArticlesByUserId(userid);
//        System.out.println(user.getUserId() + "=====" + userid);
        int count1 = articleService.updateUploader(ids, user.getUserId());
//        System.out.println("========" + count1 + "=========");
        ActionContext.getContext().getSession().put("user", userBean);
        return (count >0) ?  "input" : "error";
    }
}
