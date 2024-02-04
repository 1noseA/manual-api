package com.manualapi;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ManualsForm {

	// マニュアルID
	private int manualId;

	// 社員ID
	private int userId;

	// 社員番号
	private String userNumber;

	// 表示順
	private String displayOrder;

	// タイトル
	private String title;

	// 掲載開始日
	private LocalDate startDate;

	// 掲載終了日
	private LocalDate endDate;

	// 内容
	private String content;

	// リンク
	private String link;
}
