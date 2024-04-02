package com.manualapi;

import com.manualapi.validation.ValidGroup1;
import com.manualapi.validation.ValidGroup2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ManualsForm {

    // 社員ID
    private int userId;

    // 社員番号
    private String userNumber;

    // 表示順
    @NotNull(groups = ValidGroup1.class, message = "入力してください")
    private Integer displayOrder;

    // タイトル
    @NotBlank(groups = ValidGroup1.class, message = "入力してください")
    @Size(max = 256, groups = ValidGroup2.class, message = "256文字以内で入力してください")
    private String title;

    // 掲載開始日_年
    private String startYear;

    // 掲載開始日_月
    private String startMonth;

    // 掲載開始日_日
    private String startDay;

    // 掲載終了日_年
    private String endYear;

    // 掲載終了日_月
    private String endMonth;

    // 掲載終了日_日
    private String endDay;

    // 内容
    @NotBlank(groups = ValidGroup1.class, message = "入力してください")
    private String content;

    // リンク
    private String link;
}
