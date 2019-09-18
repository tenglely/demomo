package com.teng.demomo.controller;

import com.teng.demomo.entity.BokeUser;
import com.teng.demomo.entity.Classify;
import com.teng.demomo.entity.Msg;
import com.teng.demomo.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章分类
 */
@Controller
public class ClassifyController {
    @Autowired
    ClassifyService classifyService;

    /**
     * 修改文章分类数据
     * @param classify
     * @return
     */
    @PostMapping("/user/uploadClassify")
    @ResponseBody
    public Msg uploadClassify(Classify classify){
        int i=classifyService.upload(classify);
        System.out.println(classify);
        if(i>0)
            return Msg.success();
        else
            return Msg.fail();
    }

    /**
     * 添加文章分类数据
     * @param classify
     * @return
     */
    @PostMapping("/user/addClassify")
    @ResponseBody
    public Msg addClassify(Classify classify){
        BokeUser user=(BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        classify.setUid(user.getUid());
        int i=classifyService.add(classify);
        if(i>0)
            return Msg.success();
        else
            return Msg.fail();
    }
}
