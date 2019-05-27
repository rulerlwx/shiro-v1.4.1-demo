package cn.wolf.shiro.web;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet  extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		String username =  req.getParameter("username");
//		String password =  req.getParameter("password");
//		if("zhangsan".equals(username)&&"666".equals(password)){
//			req.setAttribute("userName",username);
//			//登陆成功
//			req.getRequestDispatcher("/main").forward(req, resp);
//		}else{
//			if(username!=null)
//				req.setAttribute("errorMsg", "账号密码有误");
//			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
//		}

		//如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		//根据shiro返回的异常类路径判断，抛出指定异常信息
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				//最终会抛给异常处理器
				req.setAttribute("errorMsg","账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				req.setAttribute("errorMsg","用户名/密码错误");
			} else {
				req.setAttribute("errorMsg","其他异常信息");//最终在异常处理器生成未知错误
			}
		}
		//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		//登陆失败还到login页面
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
}
