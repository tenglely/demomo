package com.teng.demomo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.teng.demomo.dao.BokeArticleMapper;
import com.teng.demomo.entity.BokeArticle;
import com.teng.demomo.entity.BokeArticleExample;
import com.teng.demomo.entity.Classify;
import com.teng.demomo.entity.ClassifyExample;
import com.teng.demomo.util.Select_List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BokeArticleService {
    @Autowired
    private BokeArticleMapper bokeArticleMapper;
    @Autowired
    private ClassifyService classifyService;

    /**
     * 添加文章
     * @param bokeArticle
     * @return
     */
    public int add(BokeArticle bokeArticle){
        int i=bokeArticleMapper.insert(bokeArticle);
        return i;
    }

    /**
     * 分页查询10条文章数据
     * @param pn
     * @param uid
     * @return
     */
    public PageInfo all(int pn, int uid){
        BokeArticleExample articleExample=new BokeArticleExample();
        articleExample.setOrderByClause("id DESC");
        BokeArticleExample.Criteria criteria=articleExample.createCriteria();
        criteria.andUidEqualTo(uid);
        PageHelper.startPage(pn, 10);
        List<BokeArticle> list=bokeArticleMapper.selectByExample(articleExample);
        PageInfo page=new PageInfo(list,5);
        return page;
    }

    /**
     * 删除一个文章
     * @param id
     * @return
     */
    public int delete(Integer id) {
        BokeArticle article=bokeArticleMapper.selectByPrimaryKey(id);
        classifyService.deleteall(article.getAid());
        int i=bokeArticleMapper.deleteByPrimaryKey(id);
        return i;
    }

    /**
     * 根据文章id查找一篇文章
     * @param id
     * @return
     */
    public BokeArticle findone(int id) {
        BokeArticle article=bokeArticleMapper.selectByPrimaryKey(id);
        return article;
    }

    /**
     * 修改一篇文章
     * @param article
     * @return
     */
    public int upload(BokeArticle article) {
        int i=bokeArticleMapper.updateByPrimaryKey(article);
        return i;
    }

    /**
     * 根据用户id查找出其所有文章id,再用文章id去查找其个人分类
     * 最后将个人分类的所有分类筛选后传递
     * @param uid
     * @return
     */
    public List<String> persontype(Integer uid){
        BokeArticleExample articleExample=new BokeArticleExample();
        BokeArticleExample.Criteria criteria=articleExample.createCriteria();
        criteria.andUidEqualTo(uid);
        //查找该用户所有的文章
        List<BokeArticle> alist= bokeArticleMapper.selectByExample(articleExample);
        //用来存储个人分类
        List<String> plist=new ArrayList<>();
        for(BokeArticle article:alist){
            ClassifyExample classifyExample=new ClassifyExample();
            ClassifyExample.Criteria cc=classifyExample.createCriteria();
            String persontype=classifyService.doctypeANDaid(article.getAid(),"个人分类").getType();
            if(plist.isEmpty())
                plist.add(persontype);
            //plist中没有则添加进来
            if(!new Select_List().find_list_false(plist,persontype))
                plist.add(persontype);
        }
        return plist;
    }

    /**
     * 根据个人分类内容  刷新文章列表
     * @param uid
     * @param type
     * @return
     */
    public List<BokeArticle> upload_peopletype_article(Integer uid, String type){
        List<Classify> clist=classifyService.fingByType(uid,type);
        List<BokeArticle> alist=new ArrayList<>();
        for(Classify c:clist){
            BokeArticleExample articleExample=new BokeArticleExample();
            BokeArticleExample.Criteria criteria=articleExample.createCriteria();
            criteria.andAidEqualTo(c.getAid());
            BokeArticle article=bokeArticleMapper.selectByExample(articleExample).get(0);
            alist.add(article);
        }
        return alist;
    }

    /**
     * 根据aid查找一条数据
     * 并且number+1
     * @param aid
     * @return
     */
    public BokeArticle findByAid(String aid) {
        BokeArticleExample articleExample=new BokeArticleExample();
        BokeArticleExample.Criteria criteria=articleExample.createCriteria();
        criteria.andAidEqualTo(aid);
        BokeArticle article=bokeArticleMapper.selectByExample(articleExample).get(0);
        article.setNumber(article.getNumber()+1);
        int i=bokeArticleMapper.updateByPrimaryKey(article);
        return article;
    }
}
