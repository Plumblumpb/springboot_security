package com.plumblum.vaildate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器接口
 */
public interface ValidateCodeGenerator {
    public ImageCode generate(ServletWebRequest request);
}
