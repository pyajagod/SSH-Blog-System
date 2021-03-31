package com.qwy.blogs.entity;

import com.qwy.blogs.Interface.IStudent;


import java.util.HashMap;
import java.util.Map;

public class PagingEntity {
    private static String source = "com/qwy/blogs/service/serviceContext.xml";
    private static IStudent studentService = (IStudent) ApplicationContextConf.getApplicationContext(source).getBean("studentService");

    public static Map<String, Integer> getPage(Integer curPage, int size, int totalRow){
//        int pageSize = size;
        if (curPage < 0 || curPage == null){
            curPage = 1;
        }
//        int totalRows = totalRow;
        int totalPages = totalRow / size;
        int left = totalRow % size;
        if (left > 0){
            totalPages += 1;
        }

        if (curPage > totalPages){
            curPage = totalPages;
        }
        Map<String, Integer> params = new HashMap<>();
        params.put("curPage", curPage);
        params.put("pageSize", size);
        params.put("totalPages", totalPages);
//        System.out.println(curPage + "=======" + pageSize + "=======" + totalPages);
        return params;
    }
}
