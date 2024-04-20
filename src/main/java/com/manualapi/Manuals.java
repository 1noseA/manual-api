package com.manualapi;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Manuals {

    // マニュアルID
    private int manualId;

    // 社員ID
    private int userId;

    // 表示順
    private int displayOrder;

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

    // レコード登録者
    private String createdBy;

    // レコード登録日
    private LocalDateTime createdAt;

    // レコード更新者
    private String updatedBy;

    // レコード更新日
    private LocalDateTime updatedAt;
}
