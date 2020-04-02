package com.paste.cloud.service.impl;

import com.paste.cloud.compoent.JSONResult;
import com.paste.cloud.dao.CollectionDataDAO;
import com.paste.cloud.dao.PasteDataDAO;
import com.paste.cloud.dto.ListPasteDTO;
import com.paste.cloud.dto.PasteDataDTO;
import com.paste.cloud.model.CollectionData;
import com.paste.cloud.model.PasteData;
import com.paste.cloud.service.PasteDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PasteDataServiceImpl implements PasteDataService {

    @Autowired
    private PasteDataDAO pasteDataDAO;
    @Autowired
    private CollectionDataDAO collectionDataDAO;

    @Override
    public JSONObject findByPage(ListPasteDTO listPasteDTO) {
        List<PasteData> byPage = pasteDataDAO.findByPage(listPasteDTO);
        JSONObject jsonObject = new JSONObject();
        BeanUtils.copyProperties(JSONResult.success_result,jsonObject);
        try {
            jsonObject.put("result",byPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void insert(PasteDataDTO pasteDataDTO,String email,String uid) {
        PasteData pasteData = new PasteData();
        pasteData.setUuid(UUID.randomUUID().toString().replace("-",""));
        pasteData.setCreateTime(new Date());
        pasteData.setHashSecret(pasteDataDTO.getHashSecret());
        pasteData.setContent(pasteDataDTO.getContent());
        pasteData.setUid(uid);
        pasteData.setTopIs(0);
        pasteDataDAO.insert(pasteData);
    }

    @Override
    public void deleteById(String id) {
        pasteDataDAO.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void inCollection(String uuid, String email, String uid) {
        PasteData byId = pasteDataDAO.findById(uuid);
        if(!byId.getUid().equalsIgnoreCase(uid)){
            return;
        }
        //删除粘贴板数据
        pasteDataDAO.deleteById(byId.getUuid());
        //添加收藏表数据
        CollectionData collectionData = new CollectionData();
        collectionData.setUuid(UUID.randomUUID().toString().replace("-",""));
        collectionData.setCreateTime(new Date());
        collectionData.setHashSecret(byId.getHashSecret());
        collectionData.setContent(byId.getContent());
        collectionData.setUid(uid);
        collectionData.setTopIs(0);
        collectionDataDAO.insert(collectionData);
    }
}