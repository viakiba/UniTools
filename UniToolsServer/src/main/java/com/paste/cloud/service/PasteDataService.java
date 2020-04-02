package com.paste.cloud.service;

import com.paste.cloud.dto.ListPasteDTO;
import com.paste.cloud.dto.PasteDataDTO;
import com.paste.cloud.model.PasteData;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 *
 */
public interface PasteDataService {

    /**
     * 分页查询
     *
     * @return {@link PasteData}
     */
    JSONObject findByPage(ListPasteDTO pageNum);

    /**
     * 新增
     *
     * @param pasteData
     */
    void insert(PasteDataDTO pasteData,String email,String uid);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(String id);

    void inCollection(String uuid, String email, String uid);
}