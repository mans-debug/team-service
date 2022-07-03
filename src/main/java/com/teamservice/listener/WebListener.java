package com.teamservice.listener;

import com.teamservice.repositories.GroupRepository;
import com.teamservice.repositories.GroupRepositoryImpl;
import com.teamservice.repositories.UserRepository;
import com.teamservice.repositories.UserRepositoryImpl;
import com.teamservice.services.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@jakarta.servlet.annotation.WebListener
public class WebListener implements ServletContextListener {
    private SessionFactory sessionFactory;

    public SessionFactory initSessionFactory(){
        return new Configuration()
                .configure()
                .buildSessionFactory();
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sessionFactory = initSessionFactory();

        UserRepository userRepository = new UserRepositoryImpl(sessionFactory);
        GroupRepository groupRepository = new GroupRepositoryImpl(sessionFactory);

        UserService userService = new UserServiceImpl(userRepository);
        GroupService groupService = new GroupServiceImpl(groupRepository, userRepository);

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("groupService", groupService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sessionFactory.close();
    }
}
