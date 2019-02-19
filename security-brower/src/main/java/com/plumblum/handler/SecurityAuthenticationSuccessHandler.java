package com.plumblum.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plumblum.controller.utils.LoginResultType;
import com.plumblum.properties.BrowserProperty;
import com.plumblum.properties.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证成功处理Handler
 */
//implements AuthenticationSuccessHandler
@Component
/**
 * 默认登录成功处理器
 */
public class SecurityAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * 将数据封装成json
     */
    @Autowired
    private ObjectMapper objectMapper ;

    @Autowired
    private SecurityProperty securityProperty;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (securityProperty.getBrowserProperty().getLoginResultType().equals(LoginResultType.JSON)) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
