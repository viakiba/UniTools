package com.paste.cloud.dto;

import lombok.Data;

@Data
public class PasteDataDTO {

    /**
     * 内容
     */
    private Object content;
    /**
     * hash值 用来索引值
     */
    private String hashSecret;

}
