package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径URI
        String uri=req.getRequestURI();
        //2.获取最后一段路径方法名
        String methodName=uri.substring(uri.lastIndexOf("/")+1);
        //3.执行方法
        //3.1获取BrandServlet 字节码对象Class
        Class<? extends BaseServlet> cls= this.getClass();


        //3.2获取方法的Method对象
        try {
            Method method= cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //3.3执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
