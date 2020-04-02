package com.paste.cloud.controller;

import com.paste.cloud.compoent.JSONResult;
import com.paste.cloud.dto.ListPasteDTO;
import com.paste.cloud.dto.PasteDataDTO;
import com.paste.cloud.service.PasteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;


/**
 *
 */
@RestController
@RequestMapping("/pasteDatas")
public class PasteDataController {
    @Autowired
    private PasteDataService pasteDataService;

    @PostMapping("/list")
    public JSONObject list(@RequestBody ListPasteDTO listPasteDTO) {
        listPasteDTO.setPageNum(listPasteDTO.getPageNum()+listPasteDTO.getPageSize());
        return pasteDataService.findByPage(listPasteDTO);
    }

    @PostMapping("/add")
    public JSONObject insert(@RequestBody PasteDataDTO pasteDataDTO, @RequestAttribute("email") String email,
                             @RequestAttribute("uid") String uid) {
        pasteDataService.insert(pasteDataDTO, email, uid);
        return JSONResult.success_result;
    }

    @DeleteMapping("/del")
    public JSONObject deleteById(  @RequestParam("id") String id) {
        pasteDataService.deleteById(id);
        return JSONResult.success_result;
    }

    @GetMapping("/collection")
    public JSONObject inCollection(@RequestParam("uuid") String uuid, @RequestAttribute("email") String email,
                             @RequestAttribute("uid") String uid) {
        pasteDataService.inCollection(uuid, email, uid);
        return JSONResult.success_result;
    }
}
