package com.manualapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManualController {

    @Autowired
    ManualsMapper manualsMapper;

    @GetMapping("/manual-api")
    public List<Manuals> getList() {
        List<Manuals> manualList = manualsMapper.getList();
        return manualList;
    }

    @GetMapping("/manual-api/{manualId}")
    public Manuals getDetail(@PathVariable int manualId) {
        Manuals manual = manualsMapper.getDetail(manualId);
        return manual;
    }

    @PostMapping("/manual-api")
    public ResponseEntity<Manuals> create(@RequestBody Manuals manual) {
        // 登録
        manualsMapper.create(manual);
        return ResponseEntity.ok(manual);
    }

    @PatchMapping("/manual-api/{manualId}")
    public ResponseEntity<Manuals> update(@PathVariable int manualId, @RequestBody Manuals manual) {
        // 更新
        manualsMapper.update(manual);
        return ResponseEntity.ok(manual);
    }

    @DeleteMapping("/manual-api/{manualId}")
    public ResponseEntity<Void> delete(@PathVariable int manualId) {
        manualsMapper.delete(manualId);
        return ResponseEntity.noContent().build();
    }
}
