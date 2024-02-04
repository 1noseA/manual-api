package com.manualapi;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ManualsMapper {

	@Select("SELECT * FROM manuals")
    public List<Manuals> getList();

	@Select("SELECT * FROM manuals WHERE manual_id = #{manualId}")
    public Manuals getDetail(int manualId);

	@Insert("INSERT INTO manuals (user_id, display_order, title, start_date, end_date, content, link, created_by, created_at)"
			+ "VALUES (#{userId}, #{displayOrder}, #{title}, #{startDate}, #{endDate}, #{content}, #{link}, #{createdBy}, CURRENT_TIMESTAMP)")
    public boolean create(Manuals manual);

    @Update("UPDATE manuals SET"
    		+ "user_id =#{userId}, display_order = #{displayOrder}, title = #{title}, start_date = #{startDate}, end_date = #{endDate},"
    		+ "content = #{content}, link = #{link}, updated_by = #{updatedBy}, updated_at = CURRENT_TIMESTAMP"
    		+ "WHERE manual_id = #{manualId}")
    public boolean update(Manuals manual);

    @Delete("DELETE FROM manuals WHERE manual_id = #{manualId}")
    public boolean delete(int manualId);

}
