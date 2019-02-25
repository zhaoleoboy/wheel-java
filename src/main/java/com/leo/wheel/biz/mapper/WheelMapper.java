package com.leo.wheel.biz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.leo.wheel.entity.biz.WheelInfo;

@Mapper
public interface WheelMapper {

	public WheelInfo getDataById(@Param("id") String id);

	public List<WheelInfo> getDataByCondition(Map<String, Object> params);
}
