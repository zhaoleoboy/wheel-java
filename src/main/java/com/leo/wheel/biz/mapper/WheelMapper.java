package com.leo.wheel.biz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.leo.wheel.entity.biz.WheelInfo;

@Mapper
public interface WheelMapper {

	public WheelInfo getDataById(@Param("id") String id);

	public WheelInfo getDataByCondition(WheelInfo info);
}
