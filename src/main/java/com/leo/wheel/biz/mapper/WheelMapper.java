package com.leo.wheel.biz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.leo.wheel.entity.biz.WheelInfo;

@Mapper
public interface WheelMapper {

	@Select("SELECT * FROM wheel WHERE id = #{id}")
	public WheelInfo getDataById(@Param("id") String id);

	@Select("SELECT * FROM wheel WHERE id = #{id}")
	public WheelInfo getDataByCondition(@Param("id") String id);
}
