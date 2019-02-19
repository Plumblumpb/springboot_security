package com.plumblum.config;

import com.plumblum.properties.SecurityProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 加载配置自定义配置文件
 */
@Configuration
//使自定义的配置器生效
@EnableConfigurationProperties(SecurityProperty.class)
public class SecurityPropertyConfig {
}
