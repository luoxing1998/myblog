package com.ustc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luoxing
 * @create 2021-02-08 20:01
 * 异常处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHangler(HttpServletRequest request, Exception e) throws Exception {
        //控制台输出异常信息
        logger.error("Request URL : {},Exception : {}",request.getRequestURL(),e);
        //有状态的异常抛出，不做处理
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");//返回error目录下的error页面
        return mv;

    }


}
