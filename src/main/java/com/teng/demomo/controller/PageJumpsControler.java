package com.teng.demomo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 页面跳转控制
 */
@Controller
public class PageJumpsControler {

    /**
     *登录页面
     */
    @GetMapping("/mylogin")
    public String login(HttpServletResponse response) throws Exception{
        return "login";
    }

    /**
     * 文本编辑页面
     * @return
     */
    @GetMapping("/user/text.html")
    public String textEditing(){
        return "text";
    }

    /**
     * 重新编辑文本页面
     * @return
     */
    @GetMapping("/user/upload.html")
    public String textEditingAgain(){
        return "upload";
    }

    /**
     * 文章列表页面
     * @return
     */
    @GetMapping("/user/article.html")
    public String article(){
        return "article";
    }

    /**
     * 文章页面
     * @param aid
     * @param request
     * @return
     */
    @GetMapping("/user/read.html/{aid}")
    public String see_article(@PathVariable("aid")String aid,HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("aid",aid);
        System.out.println(session.getAttribute("aid"));
        return "read";
    }
}
