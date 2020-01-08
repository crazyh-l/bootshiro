package com.shirotest.bootshiro.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseHeaderAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
        ServletServerHttpResponse servletResponse = (ServletServerHttpResponse) serverHttpResponse;
        if(servletRequest == null || servletResponse == null
                || servletRequest.getServletRequest() == null || servletResponse.getServletResponse() == null) {
            return o;
        }

        //对于未添加跨域消息头的响应进行处理
        HttpServletRequest request = servletRequest.getServletRequest();
        HttpServletResponse response = servletResponse.getServletResponse();
        String originHeader = "Access-Control-Allow-Origin";
        if (!response.containsHeader(originHeader)) {
            String origin = request.getHeader("Origin");
            if (origin == null) {
                String referer = request.getHeader("Referer");
                if (referer != null) {
                    origin = referer.substring(0,referer.indexOf(7));
                }
            }
            response.setHeader("Access-Control-Allow-Origin",origin);
        }

        String allowHeaders = "Access-Control-Allow-Headers";
        if (!response.containsHeader(allowHeaders)) {
            response.setHeader(allowHeaders,request.getHeader(allowHeaders));
        }

        String allowMethods = "Access-Control-Allow-Methods";
        if (!response.containsHeader(allowMethods)) {
            response.setHeader(allowMethods,"GET,POST,OPTIONS,HEAD");
        }

        String exposeHeader = "access-control-expose-headers";
        if (!response.containsHeader(exposeHeader)) {
            response.setHeader(exposeHeader,"x-auth-token");
        }
        return o;
    }
}
