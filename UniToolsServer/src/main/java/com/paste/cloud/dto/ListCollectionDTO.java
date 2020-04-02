package com.paste.cloud.dto;

import lombok.Data;

@Data
public class ListCollectionDTO {

   private Integer pageNum;

   private Integer pageSize;

   private String email;

   private String uid;

   private String secretHash;

   private Integer top;
}
