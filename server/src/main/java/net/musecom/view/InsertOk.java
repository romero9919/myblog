package net.musecom.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.musecom.database.BlogDto;
import net.musecom.database.BlogImpl;
import net.musecom.database.FileDto;


@WebServlet("/insertok")
public class InsertOk extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		BlogImpl blogfile = new BlogImpl();
		BlogDto bDto = new BlogDto();
		FileDto fDto = new FileDto();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");		
		
		int imname = 0;
		if(session.getAttribute("imname") != null) imname = Integer.parseInt((String) session.getAttribute("imname"));
  	    bDto.setTitle(request.getParameter("title"));
  	    bDto.setContent(request.getParameter("content"));
  	    bDto.setWriter(request.getParameter("writer"));
  	    bDto.setCategorya(request.getParameter("categorya"));
  	    bDto.setCategoryb(request.getParameter("categoryb"));
  	    bDto.setImname(imname);
  	    int rs = blogfile.bInsert(bDto);
  	    
  	    fDto.setImname(imname);  
  	    fDto.setBlog_num(rs);  
  	    int rss = blogfile.fileUpdate(fDto); 
  	    session.invalidate();   
  	    
  	    
  	    
  	    out.print("insertok");
		doGet(request, response);
	}

}
