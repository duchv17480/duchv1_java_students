package com.hybird.interceptor;

import com.hybird.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    private SubjectService subjectService;

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        request.setAttribute("subjects",subjectService.findAll());
    }
}
