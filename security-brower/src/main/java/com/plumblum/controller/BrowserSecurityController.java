package com.plumblum.controller;

import com.plumblum.controller.utils.SimpleResponse;
import com.plumblum.properties.SecurityProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于 判断springsecurity的请求是直接跳转html还是转为url跳转
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);

    /**
     * 用于获取访问的url，以及设置session
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 配置跳转请求。
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperty securityProperty;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)//设置返回的错误状态码。
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response){
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
//            logger.info("引发跳转的请求是："+redirectUrl);
            //请求是否已.html结尾
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")) {
                try {
                    redirectStrategy.sendRedirect(request, response, securityProperty.getBrowserProperty().getLoginPage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return  new SimpleResponse("访问服务器需要身份认证。");
    }

}
