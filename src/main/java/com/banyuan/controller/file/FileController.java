package com.banyuan.controller.file;


import com.banyuan.message.ResponseData;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.banyuan.message.MessageData.success;
import static java.util.UUID.randomUUID;


@Controller

@RequestMapping("/file")
public class FileController {

    @RequestMapping("/getImage")
    public void getImage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String imageName = req.getParameter("imageName");
//        resp.setHeader("content-disposition", "attachment;filename=" + imageName);
        IOUtils.copy(new FileInputStream("/Users/edz/Desktop/photos/" + imageName), resp.getOutputStream());
    }

    @PostMapping("/upload")
    public ResponseData upload(MultipartFile source, HttpSession session) throws IOException {
        String filename = source.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String uname = UUID.randomUUID() + suffix;
        IOUtils.copy(source.getInputStream(), new FileOutputStream("/Users/edz/Desktop/photos" + uname));
        return success(uname);
    }
}
