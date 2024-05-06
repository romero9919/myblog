package net.musecom.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.musecom.database.BlogDto;
import net.musecom.database.BlogFileDto;
import net.musecom.database.BlogImpl;
import net.musecom.database.FileDto;

@WebServlet("/blist")
public class BlogList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BlogList() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		  String page = req.getParameter("page");
		   int pg = 1;
		   if(page != null) {
			  pg = Integer.parseInt(page);   
		   }
		   
		res.setContentType("text/plan;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		BlogImpl blog = new BlogImpl();
		List<BlogDto> dtos = blog.bList(pg);
	    List<BlogFileDto> combinedDtos = new ArrayList<>();
		
		for(BlogDto dto : dtos) {
			FileDto fdto = blog.fileOneList(dto.getNum());
			combinedDtos.add(new BlogFileDto(dto, fdto));
		}
		
		String gson = new Gson().toJson(combinedDtos);  //gson�� �̿��� json Ÿ������ ��ȯ
		//��ǥ�̹��� ��������
		out.println(gson);
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
