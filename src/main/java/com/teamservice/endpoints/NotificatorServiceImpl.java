package com.teamservice.endpoints;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.models.User;
import com.teamservice.services.GroupService;
import com.teamservice.services.UserService;
import com.teamservice.services.UserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.servlet.ServletContext;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import lombok.NoArgsConstructor;

@WebService(endpointInterface = "com.teamservice.endpoints.NotificatorService",
        portName = "notificatorPort",
        serviceName = "notificatorService")
@NoArgsConstructor
public class NotificatorServiceImpl implements NotificatorService{
    private UserService userService;
    @Resource
    private WebServiceContext context;

    @Override
    public ExpiredUsers[] teamLeadExpiration() {
        init();
        return userService.expiredTeamLead().stream().toArray(ExpiredUsers[]::new);
    }

    @Override
    public ExpiredUsers lectorExpiration() {
        init();
        return userService.expiredLector();
    }

    private void init(){
        if(userService == null){
            userService = (UserService) getContext().getAttribute("userService");
        }

    }

    private ServletContext getContext(){
        return (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
    }
}
