package com.teamservice.endpoints;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.dto.ExpiredUsersArrayDto;
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

import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "com.teamservice.endpoints.NotificatorService",
        portName = "notificatorPort",
        serviceName = "notificatorService")
@NoArgsConstructor
public class NotificatorServiceImpl implements NotificatorService{
    private UserService userService;
    @Resource
    private WebServiceContext context;

    @Override
    public ExpiredUsersArrayDto teamLeadExpiration() {
        init();
        ExpiredUsersArrayDto send = new ExpiredUsersArrayDto();
        List<ExpiredUsers> expiredUsers = userService.expiredTeamLead();
        send.setExpiredUsersWithOwner(expiredUsers);
        return send;
    }

    @Override
    public ExpiredUsersArrayDto lectorExpiration() {
        init();
        ExpiredUsersArrayDto send = new ExpiredUsersArrayDto();
        ExpiredUsers expiredUsers = userService.expiredLector();
        send.setExpiredUsersWithOwner(List.of(expiredUsers));
        return send;
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
