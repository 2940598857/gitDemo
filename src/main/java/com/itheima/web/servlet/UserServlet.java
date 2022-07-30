package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/User/*")
public class UserServlet extends BaseServlet{
        public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                System.out.println("User查所有");
        }

        public void Add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                System.out.println("User增加");
        }
}
