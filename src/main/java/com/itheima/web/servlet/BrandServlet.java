package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.impl.BrandServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet("/Brand/*")
public class BrandServlet extends BaseServlet{
        private BrandServiceImpl brandService=new BrandServiceImpl();
        public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //调用service查询
                List<Brand> brands=brandService.selectAll();
                //转为JSON
                String jsonString= JSON.toJSONString(brands);
                //写入数据到浏览器
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write(jsonString);
        }

        public void Add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //1. 接收品牌数据 （post请求）
                BufferedReader br = request.getReader();
                String params = br.readLine();//json字符串

                //转为Brand对象
                Brand brand = JSON.parseObject(params, Brand.class);

                //2. 调用service添加
                brandService.add(brand);

                //3. 响应成功的标识
                response.getWriter().write("success");
        }
        //批量删除
        public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //1. 接收品牌数据 （post请求）
                BufferedReader br = request.getReader();
                String params = br.readLine();//json字符串
                //2.转为int[]
                int[] ids=JSON.parseObject(params,int[].class);

                //2. 调用service添加
                brandService.delectById(ids);

                //3. 响应成功的标识
                response.getWriter().write("success");
        }
        //分页查询
        public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //接收 当前页码 和 每页显示条数 (get请求)  url?currentPage=1&pageSize=5
                String _currentPage=request.getParameter("currentPage");
                String _pageSize=request.getParameter("pageSize");
                int currentPage= Integer.parseInt(_currentPage);
                int PageSize=Integer.parseInt(_pageSize);
                //2. 调用service查询
                PageBean<Brand> pageBean=brandService.selectByPage(currentPage,PageSize);
                //转为JSON
                String jsonString= JSON.toJSONString(pageBean);
                //写入数据到浏览器
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write(jsonString);
        }
        //分页条件查询
        public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
                //接收 当前页码 和 每页显示条数 (get请求)  url?currentPage=1&pageSize=5
                String _currentPage=request.getParameter("currentPage");
                String _pageSize=request.getParameter("pageSize");
                int currentPage= Integer.parseInt(_currentPage);
                int PageSize=Integer.parseInt(_pageSize);
                // 接收查询条件对象（post请求）
                BufferedReader br = request.getReader();
                String params = br.readLine();//json字符串
                //转为Brand
                Brand brand=JSON.parseObject(params,Brand.class);


                //2. 调用service查询
                PageBean<Brand> pageBean=brandService.selectByPageAndCondition(currentPage,PageSize,brand);
                //转为JSON
                String jsonString= JSON.toJSONString(pageBean);
                //写入数据到浏览器
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write(jsonString);
        }
}
