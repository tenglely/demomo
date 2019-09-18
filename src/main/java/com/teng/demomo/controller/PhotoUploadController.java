package com.teng.demomo.controller;

import com.teng.demomo.entity.BokeUser;
import com.teng.demomo.entity.ImgInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
@Controller
public class PhotoUploadController {

    @RequestMapping("/user/upload")
    @ResponseBody
    public ImgInfo setImgUrl(MultipartFile file, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
       System.out.println(bytes.length);
        BokeUser user=(BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int i=user.getUsername().indexOf("@");
        String path = request.getServletContext().getRealPath("/image/"+user.getUsername().substring(0,i)+"/");
        File imgFile = new File(path);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }
        String fileName = file.getOriginalFilename();// 文件名称
        System.out.println(path + fileName);

        try (FileOutputStream fos = new FileOutputStream(new File(path + fileName))) {
            int len = 0;
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String value = "/image/"+user.getUsername().substring(0,i)+"/" + fileName;
        String[] values = { value };

        ImgInfo imgInfo = new ImgInfo();
        imgInfo.setError(0);
        imgInfo.setUrl(values);
        System.out.println("成功");
        System.out.println(imgInfo.toString());
        return imgInfo;
    }

}
