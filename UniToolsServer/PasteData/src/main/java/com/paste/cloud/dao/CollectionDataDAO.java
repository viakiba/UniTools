package com.paste.cloud.dao;

import com.github.pagehelper.Page;
import com.paste.cloud.model.CollectionData;

/**
 *
 */
public interface CollectionDataDAO {

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
    Page<CollectionData> findByPage();

    /**
     * 新增
     *
     * @param collectionData
     */
    void insert(CollectionData collectionData);

    /**
     * 修改
     *
     * @param collectionData
     */
    void update(CollectionData collectionData);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(String id);

}