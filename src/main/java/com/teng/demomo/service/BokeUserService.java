package com.teng.demomo.service;

import com.teng.demomo.dao.BokeUserMapper;
import com.teng.demomo.entity.BokeUser;
import com.teng.demomo.entity.BokeUserExample;
import com.teng.demomo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BokeUserService implements UserDetailsService {

    @Autowired
    private BokeUserMapper bokeUserMapper;

    @Autowired
    RedisTemplate redisTemplate;

    public List<BokeUser> alluser(){
        return bokeUserMapper.selectByExample(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //redis里有就不执行查询，直接使用
        ValueOperations op=redisTemplate.opsForValue();
        BokeUser user=(BokeUser) op.get(username);
        if(user==null){
            user=bokeUserMapper.loadUserByUsername(username);
            op.set(username,user);
            System.out.println("redis添加了:"+user);
        }
        //BokeUser user=bokeUserMapper.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(bokeUserMapper.getUserRolesByUid(user.getUid()));
        return user;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int register(BokeUser user) {
        int i=bokeUserMapper.insert(user);
        return i;
    }

    /**
     * 根据username查找用户
     * @param username
     * @return
     */
    public BokeUser selectByUserName(String username) {
        BokeUserExample userExample=new BokeUserExample();
        BokeUserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<BokeUser> list= bokeUserMapper.selectByExample(userExample);
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

    /**
     * 添加用户角色
     * @param uid
     * @param rid
     */
    public void addUserRole(int uid, int rid) {
        UserRole userRole=new UserRole();
        userRole.setUid(uid);
        userRole.setRid(rid);
        bokeUserMapper.insertUserRole(userRole);
    }
}
