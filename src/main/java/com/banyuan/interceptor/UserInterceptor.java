package com.banyuan.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.banyuan.bean.user.AuthorBean;
import com.banyuan.message.MessageData;
import com.banyuan.message.ResponseData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户拦截器,用于验证用户登录情况
 */


public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //检查session是否有登录对象
        AuthorBean bean = (AuthorBean) request.getSession().getAttribute("login");
        if (null == bean){
            ResponseData rd = new ResponseData();
            rd.setState(MessageData.NOT_LOGIN.getCode());
            rd.setMessage(MessageData.NOT_LOGIN.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(rd, SerializerFeature.WriteMapNullValue));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
