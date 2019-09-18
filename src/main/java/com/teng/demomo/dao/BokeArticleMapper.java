package com.teng.demomo.dao;

import com.teng.demomo.entity.BokeArticle;
import com.teng.demomo.entity.BokeArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BokeArticleMapper {
    int countByExample(BokeArticleExample example);

    int deleteByExample(BokeArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BokeArticle record);

    int insertSelective(BokeArticle record);

    List<BokeArticle> selectByExample(BokeArticleExample example);

    BokeArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BokeArticle record, @Param("example") BokeArticleExample example);

    int updateByExample(@Param("record") BokeArticle record, @Param("example") BokeArticleExample example);

    int updateByPrimaryKeySelective(BokeArticle record);

    int updateByPrimaryKey(BokeArticle record);
}