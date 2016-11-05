package action;

import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yuzhiyun on 2016-11-04.
 */
@WebServlet("*.user")
public class UserServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.print("访问UserServlet成功" + request.getCharacterEncoding() + " " + response.getCharacterEncoding());
        System.out.println("访问UserServlet成功" +"username="+request.getParameter("username")+"  password="+request.getParameter("password"));

        String temp = request.getServletPath().substring(1);
        String methodName = temp.substring(0, temp.length() - 5);

        Method method = null;
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     * */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username=request.getParameter("username");
        String password=request.getParameter("password");


        if("yuzhiyun".equals(username) && "123".equals(password)){
//            重定向到主页面
            response.sendRedirect("index.html");
        }else{
            response.sendRedirect("error.html");
        }

        System.out.println("登录用户名"+request.getParameter("username"));
    }

    public void register(HttpServletRequest request, HttpServletResponse response) {

    }
}
