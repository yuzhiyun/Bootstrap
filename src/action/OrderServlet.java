package action;

import dao.pojo.Order;
import service.IService.IOrderService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yuzhiyun on 2016-11-04.
 * 处理订单
 */
@WebServlet("*.order")
public class OrderServlet extends javax.servlet.http.HttpServlet {


    IOrderService iOrderService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.print("访问UserServlet成功" + request.getCharacterEncoding() + " " + response.getCharacterEncoding());
//        System.out.println("访问OrderServlet成功" +"username="+request.getParameter("username")+"  password="+request.getParameter("password"));
        System.out.println("访问OrderServlet成功");

        String temp = request.getServletPath().substring(1);
        String methodName = temp.substring(0, temp.length() - 6);

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
     * 支付
     * */
    public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String index=request.getParameter("index");
        int[] price={10,20,30,40,50,60};
        String[] description={
                "太阳公公在读诗歌",
                "左边一只大兔子，右边三只小兔子，蹲着在说话",
                "三只兔子赶跑了从烟囱进来的大灰狼，，开心的活蹦乱跳",
                "小女孩在糖果城堡跳舞",
                "小女孩突然变成蝴蝶，在花丛中翩翩起舞",
                "度蜜月的情侣在沙滩上尽情享受难得的二人世界"};
        iOrderService= ServiceFactory.getOrderService();
        int intIndex=Integer.parseInt(index);
        Order order=new Order(intIndex+1,price[intIndex],description[intIndex]);
        boolean falg=iOrderService.save(order);

        if(falg)
            response.getWriter().print("success");
        else
            response.getWriter().print("fail");

        System.out.println("支付"+price[intIndex]+"元");
    }


}
