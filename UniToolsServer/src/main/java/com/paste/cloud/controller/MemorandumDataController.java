package com.paste.cloud.controller;

import com.github.pagehelper.PageInfo;
import com.paste.cloud.model.MemorandumData;
import com.paste.cloud.service.MemorandumDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/memorandumDatas")
public class MemorandumDataController {
    @Autowired
    private MemorandumDataService memorandumDataService;

    @GetMapping("/{id}")
    public MemorandumData findById( @PathVariable("id") String id) {
        return memorandumDataService.findById(id);
    }

    @GetMapping
    public PageInfo<MemorandumData> findByPage( @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "10") Integer pageSize) {
        return memorandumDataService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    public void insert(@RequestBody MemorandumData memorandumData) {
        memorandumDataService.insert(memorandumData);
    }

    @PutMapping
    public void update(@RequestBody MemorandumData memorandumData) {
        memorandumDataService.update(memorandumData);
    }

    @DeleteMapping("/{id}")
    public void deleteById( @PathVariable("id") String id) {
        memorandumDataService.deleteById(id);
    }
}
