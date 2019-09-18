package com.teng.demomo.dao;

import com.teng.demomo.entity.BokeSet;
import com.teng.demomo.entity.BokeSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BokeSetMapper {
    int countByExample(BokeSetExample example);

    int deleteByExample(BokeSetExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(BokeSet record);

    int insertSelective(BokeSet record);

    List<BokeSet> selectByExample(BokeSetExample example);

    BokeSet selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") BokeSet record, @Param("example") BokeSetExample example);

    int updateByExample(@Param("record") BokeSet record, @Param("example") BokeSetExample example);

    int updateByPrimaryKeySelective(BokeSet record);

    int updateByPrimaryKey(BokeSet record);
}