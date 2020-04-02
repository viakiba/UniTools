package com.paste.cloud.model;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class MemorandumData {
    /**
     * 唯一ID
     */
    private String uid;
    /**
     * 内容
     */
    private Object content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否置顶
     */
    private Integer topIs;
}