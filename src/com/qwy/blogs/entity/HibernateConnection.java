package com.qwy.blogs.entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
    private static Configuration configuration = null;
    static {
        configuration = new Configuration();
        configuration.configure("./hibernate.cfg.xml");
    }

    public static SessionFactory getFactory(){

        return configuration.buildSessionFactory();
    }

}
