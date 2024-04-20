package com.manualapi;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManualsMapper {

    // 全件取得
    public List<Manuals> getList();

    // 1件取得
    public Manuals getDetail(int manualId);

    // 登録
    public void create(Manuals manual);

    // 更新
    public void update(Manuals manual);

    // 削除
    public void delete(int manualId);
}
