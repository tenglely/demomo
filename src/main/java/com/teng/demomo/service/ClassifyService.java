package com.teng.demomo.service;

import com.teng.demomo.dao.ClassifyMapper;
import com.teng.demomo.entity.Classify;
import com.teng.demomo.entity.ClassifyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章分类
 */
@Service
public class ClassifyService {
    @Autowired
    private ClassifyMapper classifyMapper;

    /**
     * 根据文章的aid删除其所有的分类对象
     * @param aid
     */
    public void deleteall(String aid){
        ClassifyExample classifyExample=new ClassifyExample();
        ClassifyExample.Criteria criteria=classifyExample.createCriteria();
        criteria.andAidEqualTo(aid);
        List<Classify> list=classifyMapper.selectByExample(classifyExample);
        for(Classify c: list){
            int i=classifyMapper.deleteByPrimaryKey(c.getId());
            if(i>0)
                System.out.println("文章分类:"+c+"已被删除!");
            else
                System.out.println("文章分类:"+c+"删除失败!");
        }
    }

    /**
     * 添加分类
     * @param classify
     * @return
     */
    public int add(Classify classify){
        int i=classifyMapper.insert(classify);
        return i;
    }

    /**
     * 根据文章的aid查找所有的分类数据
     * @param aid
     * @return
     */
    public List<Classify> findbyaid(String aid){
        ClassifyExample classifyExample=new ClassifyExample();
        ClassifyExample.Criteria criteria=classifyExample.createCriteria();
        criteria.andAidEqualTo(aid);
        List<Classify> list=classifyMapper.selectByExample(classifyExample);
        if(list.isEmpty()){
            return null;
        }else{
            return list;
        }
    }

    /**
     * 修改文章分类数据
     * @param classify
     * @return
     */
    public int upload(Classify classify) {
        int i=classifyMapper.updateByPrimaryKeySelective(classify);
        return i;
    }

    /**
     * 根据doctype和aid查找分类数据
     * @param aid
     * @param doctype
     * @return
     */
    public Classify doctypeANDaid(String aid,String doctype){
        ClassifyExample classifyExample=new ClassifyExample();
        ClassifyExample.Criteria criteria=classifyExample.createCriteria();
        criteria.andDoctypeEqualTo(doctype);
        criteria.andAidEqualTo(aid);
//        System.out.println(aid+" "+doctype);
        List<Classify> list=classifyMapper.selectByExample(classifyExample);
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

    /**
     * 根据uid和type来查找分类
     * @param uid
     * @param type
     * @return
     */
    public List<Classify> fingByType(Integer uid,String type) {
        ClassifyExample classifyExample=new ClassifyExample();
        ClassifyExample.Criteria criteria=classifyExample.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andTypeEqualTo(type);
        List<Classify> clist=classifyMapper.selectByExample(classifyExample);
        return clist;
    }
}
