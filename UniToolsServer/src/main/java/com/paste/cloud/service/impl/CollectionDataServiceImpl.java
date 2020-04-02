package com.paste.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.paste.cloud.dao.CollectionDataDAO;
import com.paste.cloud.dto.ListCollectionDTO;
import com.paste.cloud.model.CollectionData;
import com.paste.cloud.service.CollectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectionDataServiceImpl implements CollectionDataService {

    @Autowired
    private CollectionDataDAO collectionDataDAO;

    @Override
    public CollectionData findById(String id) {
        return collectionDataDAO.findById(id);
    }

    @Override
    public JSONObject findByPage(ListCollectionDTO listCollectionDTO) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        CollectionData byId = collectionDataDAO.findById(id);
        collectionDataDAO.deleteById(id);
    }

}