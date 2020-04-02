package com.paste.cloud.controller;

import com.paste.cloud.dto.ListCollectionDTO;
import com.paste.cloud.model.CollectionData;
import com.paste.cloud.service.CollectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/collectionDatas")
public class CollectionDataController {
    @Autowired
    private CollectionDataService collectionDataService;

    @GetMapping("/list")
    public JSONObject list(ListCollectionDTO listCollectionDTO) throws JSONException {
        JSONObject byPage = collectionDataService.findByPage(listCollectionDTO);
        byPage.put("das","dsa");
        return byPage;
    }

    @PostMapping("/top")
    public void top(@RequestBody CollectionData collectionData) {
//        collectionDataService.insert(collectionData);
    }


    @DeleteMapping("/del")
    public void del(@RequestParam("id") String id) {
        collectionDataService.deleteById(id);
    }

}
