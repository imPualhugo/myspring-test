package com.banyuan.aop;

import com.banyuan.bean.user.AuthorBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.banyuan.message.MessageData.notLoggedIn;

@Aspect
//@Component
public class UserLoginInfoAdd {


    @Pointcut("execution(* com.banyuan.controller.book.*.*(..))")
    public void pc() {
    }

    @Around("pc()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        AuthorBean bean = (AuthorBean) request.getSession().getAttribute("login");
        if (null == bean) {
            return notLoggedIn();
        }
        return joinPoint.proceed();
    }
}
