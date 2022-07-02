package com.teamservice.endpoints;

import com.teamservice.dto.ExpiredUsers;
import com.teamservice.models.User;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
             use = SOAPBinding.Use.ENCODED,
             parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface NotificatorService {


    ExpiredUsers[] teamLeadExpiration();


    ExpiredUsers lectorExpiration();
}
