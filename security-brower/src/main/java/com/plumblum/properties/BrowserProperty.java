package com.plumblum.properties;


import com.plumblum.controller.utils.LoginResultType;

/**
 * 设置跳转页面还是返回json
 */
public class BrowserProperty {

    /**
     * 指定默认的跳转页面
     */
    private String loginPage = "/login.html";
    /**
     * 指定返回的是json还是跳转页面；
     */
    private LoginResultType loginResultType = LoginResultType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResultType getLoginResultType() {
        return loginResultType;
    }

    public void setLoginResultType(LoginResultType loginResultType) {
        this.loginResultType = loginResultType;
    }
}
