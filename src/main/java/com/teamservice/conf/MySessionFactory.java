package com.teamservice.conf;

import org.hibernate.SessionFactory;

public interface MySessionFactory {

    public SessionFactory getSessionFactory();

    public void shutdownSessionFactory();
}