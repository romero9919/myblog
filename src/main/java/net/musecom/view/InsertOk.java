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
  	    int rs = blogfile.bInsert(bDto);  //������ ����ϰ� auto_increment�� Ű���� rs�� ��ȯ�Ѵ�.
  	    
  	    fDto.setImname(imname);  //���ǰ����� ������ imname�� ���� ���̺��� imname�� ���� ���̴�. 
  	    fDto.setBlog_num(rs);  //blog_num �� ���� ��ȣ�� �ִ´�. (�ܷ�Ű)
  	    int rss = blogfile.fileUpdate(fDto);  //imname�� ���� ���̺���� ������Ʈ �Ѵ�.
  	    session.invalidate();   //���� �ƿ�
  	    
  	    //�̹��� if else �̿��ؼ� �ٲ�� ó�� 
  	    //������ ��� ��� , ���κ��� 
  	    //������ ��忡 �α��� ...
  	    
  	    out.print("����߼�");
		doGet(request, response);
	}

}
