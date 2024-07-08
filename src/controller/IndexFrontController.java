//package controller;
//
//import config.action.ActionTo;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class IndexFrontController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String requestURI = req.getRequestURI(); // /myrealtrip/index.jsp
//        String contextPath = req.getContextPath(); // myrealtrip
//        String command = requestURI.substring(contextPath.length());
//        ActionTo actionTo = null;
//
//        if (command.equals("index")) {
//            try {
////                actionTo = new
//            }
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        doGet(req, resp);
//    }
//}
