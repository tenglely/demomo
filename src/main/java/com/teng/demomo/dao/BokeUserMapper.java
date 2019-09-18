package com.teng.demomo.dao;

import com.teng.demomo.entity.BokeUser;
import com.teng.demomo.entity.BokeUserExample;
import java.util.List;

import com.teng.demomo.entity.Role;
import com.teng.demomo.entity.UserRole;
import org.apache.ibatis.annotations.Param;

public interface BokeUserMapper {
    BokeUser loadUserByUsername(String username);

    List<Role> getUserRolesByUid(Integer id);

    int countByExample(BokeUserExample example);

    int deleteByExample(BokeUserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insertUserRole(UserRole userRole);

    int insert(BokeUser record);

    int insertSelective(BokeUser record);

    List<BokeUser> selectByExample(BokeUserExample example);

    BokeUser selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") BokeUser record, @Param("example") BokeUserExample example);

    int updateByExample(@Param("record") BokeUser record, @Param("example") BokeUserExample example);

    int updateByPrimaryKeySelective(BokeUser record);

    int updateByPrimaryKey(BokeUser record);
}