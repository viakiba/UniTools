package com.paste.cloud.dao;

import com.github.pagehelper.Page;
import com.paste.cloud.dto.ListPasteDTO;
import com.paste.cloud.model.PasteData;

import java.util.List;

/**
 *
 */
public interface PasteDataDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link PasteData}
     */
    PasteData findById(String id);

    /**
     * 分页查询
     *
     * @return {@link PasteData}
     */
    List<PasteData> findByPage(ListPasteDTO listPasteDTO);

    /**
     * 新增
     *
     * @param pasteData
     */
    void insert(PasteData pasteData);

    /**
     * 修改
     *
     * @param pasteData
     */
    void update(PasteData pasteData);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(String id);

}