package com.paste.cloud.model;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class PasteData {
    /**
     * 唯一ID
     */
    private String uid;
    /**
     * 用户ID
     */
    private String uuid;
    /**
     * 内容
     */
    private Object content;
    /**
     * hash值 用来索引值
     */
    private String hashSecret;
    /**
     * 产生时间
     */
    private Date createTime;
    /**
     * 是否置顶
     */
    private Integer topIs;
}