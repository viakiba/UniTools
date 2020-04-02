package com.paste.cloud.service;

import com.github.pagehelper.PageInfo;
import com.paste.cloud.dto.ListCollectionDTO;
import com.paste.cloud.model.CollectionData;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 *
 */
public interface CollectionDataService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link CollectionData}
     */
    CollectionData findById(String id);

    /**
     * 分页查询
     *
     * @return {@link CollectionData}
     */
    JSONObject findByPage(ListCollectionDTO listCollectionDTO);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(String id);

}