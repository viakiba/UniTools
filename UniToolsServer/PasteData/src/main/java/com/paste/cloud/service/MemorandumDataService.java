package com.paste.cloud.service;

import com.github.pagehelper.PageInfo;
import com.paste.cloud.model.MemorandumData;

/**
 *
 */
public interface MemorandumDataService {

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
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link MemorandumData}
     */
    PageInfo<MemorandumData> findByPage(int pageNum, int pageSize);

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