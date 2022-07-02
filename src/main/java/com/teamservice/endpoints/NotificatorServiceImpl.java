package com.teamservice.endpoints;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.models.User;
import com.teamservice.services.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.NoArgsConstructor;

@WebService(endpointInterface = "com.teamservice.endpoints.NotificatorService",
        portName = "notificatorPort",
        serviceName = "notificatorService")
@NoArgsConstructor
public class NotificatorServiceImpl implements NotificatorService{

    @Inject
    private UserServiceImpl userService;

    @Override
    public ExpiredUsers[] teamLeadExpiration() {
        return userService.expiredTeamLead().stream().toArray(ExpiredUsers[]::new);
    }

    @Override
    public ExpiredUsers lectorExpiration() {
        return userService.expiredLector();
    }
}
