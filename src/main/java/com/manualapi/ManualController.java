package com.manualapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manualapi.validation.GroupOrder;

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
    public ResponseEntity<Manuals> create(@Validated(GroupOrder.class) @RequestBody ManualsForm manualForm) {
    	// 入力値を設定
    	Manuals manual = setEntity(manualForm);
    	// レコード登録者
    	manual.setCreatedBy(manualForm.getUserNumber());
    	// 登録
    	manualsMapper.create(manual);
        return ResponseEntity.ok(manual);
    }

    @PatchMapping("/manual-api/{manualId}")
    public ResponseEntity<Manuals> update(@PathVariable int manualId, @Validated(GroupOrder.class) @RequestBody ManualsForm manualForm) {
    	// 入力値を設定
    	Manuals manual = setEntity(manualForm);
    	// マニュアルID
    	manual.setManualId(manualId);
    	// レコード更新者
    	manual.setUpdatedBy(manualForm.getUserNumber());
    	// 更新
    	manualsMapper.update(manual);
        return ResponseEntity.ok(manual);
    }

    @DeleteMapping("/manual-api/{manualId}")
    public ResponseEntity<Void> delete(@PathVariable int manualId) {
    	manualsMapper.delete(manualId);
        return ResponseEntity.noContent().build();
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

    	return manual;
    }
}
