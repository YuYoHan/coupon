package controller;

import config.ActionTo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserFrontController extends HttpServlet{
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  req.setCharacterEncoding("UTF-8");
	  doGet(req, resp);
  	}
  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  	String requestURI = req.getRequestURI();	// /board_mvc2/UserJoin.us
		String contextPath = req.getContextPath(); // /board_mvc2
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		ActionTo acto = null;
		
		switch (command) {
		case "/user/sign_up.us":
//			그냥 회원가입하는 페이지로 이동시키는 거임
			try {
				acto = new UserSignUpAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원 가입 페이지 이동 실패 : " + e);
			}
			break;
		case "/user/sign_in.us":
//			로그인 하는 페이지로 이동시키는 거임
			try {
				acto = new UserSignInAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원 가입 페이지 이동 실패 : " + e);
			}
			break;
			
		case "/user/sign_upOk.us":
	         try {
	            acto = new Sign_UpOkAction().execute(req,resp);
	         } catch (Exception e) {
	            System.out.println("/Sign_UpOk : "+e);
	         }
	         break;
		case "/user/sign_inOk.us":
			try {
				// 행동을 하고 페이지 이동
	            new Sign_InOkAction().execute(req,resp);
	         } catch (Exception e) {
	            System.out.println("/Sign_inOk : "+e);
	         }
			break;
		case "/user/CheckIdOk.us":
			try {
				// 페이지 이동이 아니니까 ActionTo에 담아둘 필요가 없다.
				new CheckIdOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/CheckIdOK : "+e);
			}
			break;	
		case "/user/log_out.us":
			try {
				acto = new Sign_OutAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/CheckIdOK : "+e);
			}
			break;	
		case "/user/pwSearch.us":
			try {
				acto = new PwSearchAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/pwSearch : "+e);
			}
			break;
		case "/user/pwSearchOk.us":
			try {
				acto = new PwSearchOKAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/pwSearchOk : "+e);
			}
			break;
		case "/user/changePw.us":
			try {
				acto = new ChangePwAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/changePw : "+e);
			}
			break;
			
		case "/user/changePwOk.us":
			try {
				acto = new ChangePwOKAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/changePwOk : "+e);
			}
			break;
		}
		if(acto != null) {
			if(acto.isRedirect()) {
				resp.sendRedirect(acto.getPath());
			}
			else {
				RequestDispatcher disp = req.getRequestDispatcher(acto.getPath());
				disp.forward(req, resp);
			}
		}
	}
}
