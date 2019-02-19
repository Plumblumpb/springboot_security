package com.plumblum.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

//读取配置项以“”开头的配置文件
@ConfigurationProperties(prefix = "security.property")
public class SecurityProperty {
    private BrowserProperty browserProperty = new BrowserProperty();

    private ImageCodeProperty imageCodeProperty = new ImageCodeProperty();

    public ImageCodeProperty getImageCodeProperty() {
        return imageCodeProperty;
    }

    public void setImageCodeProperty(ImageCodeProperty imageCodeProperty) {
        this.imageCodeProperty = imageCodeProperty;
    }

    public BrowserProperty getBrowserProperty() {
        return browserProperty;
    }

    public void setBrowserProperty(BrowserProperty browserProperty) {
        this.browserProperty = browserProperty;
    }


}
