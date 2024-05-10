package net.musecom.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/", "/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if("admin".equals(username) && "123".equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", username);
            resp.sendRedirect("list"); //this page should be only acccessed after login
        }else{
            resp.sendRedirect("login?error=true");
        }
    }

}
