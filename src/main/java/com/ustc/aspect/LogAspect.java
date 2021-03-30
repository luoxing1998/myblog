package com.ustc.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author luoxing
 * @create 2021-02-23 14:06
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.ustc.controller.*.*(..))")//拦截controller下面所有的类
    public void log(){

    }

    @Before("log()")
    public void doBeFore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String classMethod =
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                        joinPoint.getSignature().getName();
        RequestLog reqeustLog = new RequestLog(
                request.getRequestURL().toString(),
                request.getRemoteAddr(),
                classMethod,
                joinPoint.getArgs()
        );
        logger.info("request...." + reqeustLog);
    }

    @After("log()")
    public void doAfter(){
        //logger.info("after...");
    }

    @AfterReturning(pointcut = "log()",returning = "result")
    public void doAfterReturn(Object result){
        logger.info("afterreturn..."+result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
