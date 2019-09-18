package com.teng.demomo.controller;

import com.teng.demomo.entity.BokeArticle;
import com.teng.demomo.entity.BokeUser;
import com.teng.demomo.entity.Classify;
import com.teng.demomo.entity.Msg;
import com.teng.demomo.service.BokeArticleService;
import com.teng.demomo.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BokeArticleController {
    @Autowired
    private BokeArticleService articleService;
    @Autowired
    private ClassifyService classifyService;

    /**
     * 查找一条文章数据用于修改
     * @param request
     * @return
     */
    @GetMapping("/user/uploadtext")
    @ResponseBody
    public Msg putArticletoUpload(HttpServletRequest request){
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("aticle_id");
        BokeArticle article=articleService.findone(id);
        List<Classify> list=classifyService.findbyaid(article.getAid());
        return Msg.success().add("article",article).add("list",list);
    }

    /**
     *缓存文章id在session
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/user/putid/{aid}")
    @ResponseBody
    public Msg putid(@PathVariable("aid")Integer id,HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("aticle_id",id);
        return Msg.success();
    }

    /**
     * 用户分页查询自己的文章
     * 5条一分页，新到旧
     * @param pn
     * @return
     */
    @GetMapping("/user/allArticle/{pn}")
    @ResponseBody
    public Msg allArticle(@PathVariable("pn") Integer pn){
        BokeUser user=(BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int uid=user.getUid();
        return Msg.success().add("list",articleService.all(pn,uid));
    }

    /**
     * 删除一篇文章
     * @param id
     * @return
     */
    @DeleteMapping("/user/article/{id}")
    @ResponseBody
    public Msg deleteone(@PathVariable("id")Integer id){
        int i=articleService.delete(id);
        if(i>0)
            return Msg.success();
        else
            return Msg.fail();
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @PostMapping("/user/uploadArticle")
    @ResponseBody
    public Msg uploadArticle(BokeArticle article){
//        System.out.println(article);
        int i=articleService.upload(article);
        if(i>0)
            return Msg.success();
        else
            return Msg.fail();
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping("/user/addArticle")
    @ResponseBody
    public Msg addArticle(BokeArticle article){
        BokeUser user=(BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        article.setUid(user.getUid());
        System.out.println(article.getContent());
        int i=articleService.add(article);
        if(i>0)
            return Msg.success();
        else
            return Msg.fail();
    }

    /**
     * 返回用户的个人分类列表数据
     * 需用户权限
     * @return
     */
    @GetMapping("/user/persontype")
    @ResponseBody
    public Msg find_peopletype(){
        BokeUser user=(BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> plist=articleService.persontype(user.getUid());
        return Msg.success().add("plist",plist);
    }

    /**
     * 点击个人分类内容后刷新文章列表
     * @param type
     * @return
     */
    @GetMapping("/user/uploadlist/{type}")
    @ResponseBody
    public Msg upload_article_list(@PathVariable("type")String type){
        BokeUser user=(BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BokeArticle> alist=articleService.upload_peopletype_article(user.getUid(),type);
        return Msg.success().add("list",alist);
    }

    /**
     * 用户查看一篇文章内容，分类数据
     * @param request
     * @return
     */
    @GetMapping("/user/findonearticle")
    @ResponseBody
    public Msg findonearticle(HttpServletRequest request){
        HttpSession session=request.getSession();
        String aid=(String)session.getAttribute("aid");
        BokeArticle article=articleService.findByAid(aid);
        List<Classify> clist=classifyService.findbyaid(aid);
        BokeUser user=(BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Msg.success().add("article",article).add("clist",clist).add("username",user.getRealName());
    }
}
