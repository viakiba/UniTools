package com.paste.cloud.dao;

import com.github.pagehelper.Page;
import com.paste.cloud.model.User;

/**
 *
 */
public interface UserDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link User}
     */
    User findById(String id);

    /**
     * 分页查询
     *
     * @return {@link User}
     */
    Page<User> findByPage();

    /**
     * 新增
     *
     * @param user
     */
    void insert(User user);

    /**
     * 修改
     *
     * @param user
     */
    void update(User user);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(String id);

    User findByEmail(String email);
}