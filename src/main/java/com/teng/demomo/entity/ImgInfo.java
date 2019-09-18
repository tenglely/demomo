package com.teng.demomo.entity;

import java.util.Arrays;

/**
 * wangeditor 图片上传后信息返回类
 */
public class ImgInfo {

    private Integer error;
    private String[] url;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImgInfo [error=" + error + ", url=" + Arrays.toString(url) + "]";
    }

}
