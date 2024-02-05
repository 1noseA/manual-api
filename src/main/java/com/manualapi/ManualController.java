package com.manualapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public ResponseEntity<Manuals> create(@RequestBody ManualsForm manualForm) {
    	// 入力値を設定
    	Manuals manual = setEntity(manualForm);
    	// レコード登録者
    	manual.setCreatedBy(manualForm.getUserNumber());
    	// 登録
    	manualsMapper.create(manual);
        return ResponseEntity.ok(manual);
    }

    @PatchMapping("/manual-api/{manualId}")
    public ResponseEntity<Manuals> update(@PathVariable int manualId, @RequestBody ManualsForm manualForm) {
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

    // 例外処理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseBody
    public Map<String, Object> handleError400() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "リクエストが正しくありません。");
        errorMap.put("status", HttpStatus.BAD_REQUEST);
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NotFoundException.class })
    @ResponseBody
    public Map<String, Object> handleError404() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "指定されたマニュアルは存在しません。");
        errorMap.put("status", HttpStatus.NOT_FOUND);
        return errorMap;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    @ResponseBody
    public Map<String, Object> handleError405() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "許可されていないメソッドでアクセスされました。");
        errorMap.put("status", HttpStatus.METHOD_NOT_ALLOWED);
        return errorMap;
    }
}
