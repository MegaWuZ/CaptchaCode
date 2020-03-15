package com.wuzheyi.code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //需要获取到浏览器的输出流对象
        PrintWriter out = response.getWriter();
        //获取用户传过来的验证码
        String code = request.getParameter("code");
        //获取验证码框产生的验证码
        String sessionCode = (String) request.getSession().getAttribute("code");
        if(code != null && sessionCode != null){
            if(code.equalsIgnoreCase(sessionCode)){
                out.print("success");
            }else {
                out.print("fail");
            }
        }else {
            out.print("fail");
        }



        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
