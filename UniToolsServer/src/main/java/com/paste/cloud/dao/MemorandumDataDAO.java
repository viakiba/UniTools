package com.paste.cloud.dao;

import com.github.pagehelper.Page;
import com.paste.cloud.model.MemorandumData;

/**
 *
 */
public interface MemorandumDataDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link MemorandumData}
     */
    MemorandumData findById(String id);

    /**
     * 分页查询
     *
     * @return {@link MemorandumData}
     */
    Page<MemorandumData> findByPage();

    /**
     * 新增
     *
     * @param memorandumData
     */
    void insert(MemorandumData memorandumData);

    /**
     * 修改
     *
     * @param memorandumData
     */
    void update(MemorandumData memorandumData);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(String id);

}