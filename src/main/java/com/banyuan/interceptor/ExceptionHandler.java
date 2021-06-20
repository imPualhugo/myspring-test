package com.banyuan.interceptor;

import com.banyuan.message.MessageData;
import com.banyuan.message.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o, Exception e) {

        e.printStackTrace();
        ResponseData rd = new ResponseData();
        rd.setState(MessageData.REQUEST_FAILED.getCode());
        rd.setMessage(MessageData.REQUEST_FAILED.getMessage());
        ModelAndView mv= new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        mv.setView(view);
        mv.addObject("state", rd.getState());
        mv.addObject("message", "服务器内部错误");

        mv.addObject("data", Arrays.toString(e.getStackTrace()));
        return mv;
    }
}
