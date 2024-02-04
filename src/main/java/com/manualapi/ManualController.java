package com.manualapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        return manualsMapper.getList();
    }

    @GetMapping("/manual-api/{manualId}")
    public Manuals getDetail(@PathVariable int manualId) {
        return manualsMapper.getDetail(manualId);
    }

    @PostMapping("/manual-api")
    public boolean create(@RequestBody ManualsForm manualForm) {
    	Manuals manual = setEntity(manualForm);
        return manualsMapper.create(manual);
    }

    @PatchMapping("/manual-api/{manualId}")
    public boolean update(@PathVariable int manualId, @RequestBody ManualsForm manualForm) {
    	Manuals manual = setEntity(manualForm);
        return manualsMapper.update(manual);
    }

    @DeleteMapping("/manual-api/{id}")
    public boolean delete(@PathVariable int manualId) {
        return manualsMapper.delete(manualId);
    }

    // 入力値をエンティティに設定
    private Manuals setEntity(ManualsForm manualForm) {
    	Manuals manual = new Manuals();
    	// 社員ID
    	manual.setUserId(manualForm.getUserId());
    	// 表示順
    	manual.setDisplayOrder(manualForm.getDisplayOrder());
    	// タイトル
    	manual.setTitle(manualForm.getTitle());
    	// 掲載開始日
    	manual.setStartDate(manualForm.getStartDate());
    	// 掲載終了日
    	manual.setEndDate(manualForm.getEndDate());
    	// 内容
    	manual.setContent(manualForm.getContent());
    	// リンク
    	manual.setLink(manualForm.getLink());

    	if (manualForm.getManualId() == 0) {
    		// レコード登録者
        	manual.setCreatedBy(manualForm.getUserNumber());
    	} else {
    		// レコード更新者
        	manual.setUpdatedBy(manualForm.getUserNumber());
    	}

    	return manual;
    }
}
