package com.plumblum.vaildate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

    /**
     * 图片
     */
    private BufferedImage image;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间点
     */
    private LocalDateTime localDateTime;
    /**
     * 过期时间间隔
     */
    private int expireTime;

    public ImageCode(BufferedImage image , String code ,LocalDateTime localDateTime){
        this.image = image ;
        this.code  = code;
        this.localDateTime = localDateTime;
    }

    public ImageCode(BufferedImage image ,String code ,int expireTime){
        this.image = image;
        this.code = code;
        this.localDateTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
}
