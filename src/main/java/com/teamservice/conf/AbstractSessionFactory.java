package com.teamservice.conf;

import org.hibernate.SessionFactory;

public abstract class AbstractSessionFactory implements MySessionFactory {

    protected SessionFactory sessionFactory;

    public AbstractSessionFactory() {
        buildSessionFactory();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void shutdownSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    protected abstract SessionFactory buildSessionFactory();
}