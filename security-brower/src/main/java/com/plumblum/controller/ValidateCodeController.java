package com.plumblum.controller;

import com.plumblum.properties.SecurityProperty;
import com.plumblum.vaildate.ImageCode;
import com.plumblum.vaildate.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码
 */
@RestController
public class ValidateCodeController {

    public static final  String SESSION_KEY = "SESSION_KEY_IAMGE_CODE";

    /**
     * 用于操作session
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;


    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request , HttpServletResponse response){
        ImageCode imageCode = validateCodeGenerator.generate(new ServletWebRequest(request));
        //将验证码保存在对于的请求中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        try {
            //参数：图片流，图片格式，输出流
            ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
