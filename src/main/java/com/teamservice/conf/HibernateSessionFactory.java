package com.teamservice.conf;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@ORMSessionFactory(ORMSessionFactory.ORMType.HIBERNATE)
public class HibernateSessionFactory extends AbstractSessionFactory {

    @Override
    protected SessionFactory buildSessionFactory() {
        try {

            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry sr = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(sr);

            return sessionFactory;

        } catch (Exception ex) {
            throw ex;
        }
    }
}