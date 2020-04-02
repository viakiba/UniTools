package com.paste.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.paste.cloud.dao.MemorandumDataDAO;
import com.paste.cloud.model.MemorandumData;
import com.paste.cloud.service.MemorandumDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemorandumDataServiceImpl implements MemorandumDataService {

    @Autowired
    private MemorandumDataDAO memorandumDataDAO;

    @Transactional(readOnly = true)
    @Override
    public MemorandumData findById(String id) {
        return memorandumDataDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<MemorandumData> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(memorandumDataDAO.findByPage());
    }

    @Override
    public void insert(MemorandumData memorandumData) {
        memorandumDataDAO.insert(memorandumData);
    }

    @Override
    public void update(MemorandumData memorandumData) {
        memorandumDataDAO.update(memorandumData);
    }

    @Override
    public void deleteById(String id) {
        memorandumDataDAO.deleteById(id);
    }

}