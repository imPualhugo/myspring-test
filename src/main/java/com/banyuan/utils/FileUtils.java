package com.banyuan.utils;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hyz
 */
public class FileUtils {

    private FileUtils() {
    }


    /**
     * 将接收文件的Http request请求转化为List型
     * @param request 输入的请求
     * @return FileItem的List
     * @throws FileUploadException 可能产生的异常
     */
    public static  List<FileItem> parseUploadFiles(HttpServletRequest request) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        return upload.parseRequest(new ServletRequestContext(request));
    }
}
