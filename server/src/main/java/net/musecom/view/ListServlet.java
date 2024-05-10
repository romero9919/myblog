package net.musecom.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.musecom.database.BlogDto;
import net.musecom.database.BlogImpl;
import net.musecom.database.FileDto;
import net.musecom.util.Pagination;

@WebServlet("/list")
public class ListServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

      
	   
	   String links = "C:\\youjae\\react\\blog-me\\public\\data\\img\\"; 
	   String fdata;
	   String flink;
	   String page = req.getParameter("page");
	   int pg = 1;
	   if(page != null) {
		  pg = Integer.parseInt(page);   
	   }	   
	   
	   res.setContentType("text/plan;charset=UTF-8");
	   PrintWriter out = res.getWriter();
	
	   BlogImpl blog = new BlogImpl();
	   
	   List<FileDto> flists = blog.fileList(0);
	   if(flists.size() > 0) {
		  for(FileDto flist : flists) {
			  
			 fdata = flist.getNewname();
			 flink = links + fdata;
			// System.out.println(flink);
			// System.out.println(flist.getNum());
			 
			 File file = new File(flink);
			 if(file.exists()) {
				 file.delete();
				 System.out.println(flink);
			 }
			 blog.fileDelete(flist.getNum());
			 System.out.println("db list");
		  }
	   }  
		
	   List<BlogDto> lists = blog.bList(pg);

	   String content = null;

	   for(BlogDto list : lists) {
		   try {
			  content = removeHtmlTag(list.getContent());
			  list.setContent(content);
			  
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   
		   List<FileDto> fdto = blog.fileList(list.getNum());
		   if(fdto.size() > 0) {
			   String filename = fdto.get(0).getNewname();
			   String fileExt = fdto.get(0).getExt();
			   int fileSize = (int) fdto.get(0).getFilesize();
			   
			   list.setFileName(filename);
			   list.setFileSize(fileSize);
			   list.setFileExt(fileExt);
		   }
	   }

	   Pagination pagination = Pagination(pg, 15, blog.bListCount(), 15, 15);
	   
	   RequestDispatcher requestDispatcher = req.getRequestDispatcher("/list.jsp");   
	   req.setAttribute("list", lists);
	   req.setAttribute("pages", pagination);
	   
	   requestDispatcher.forward(req, res);
	}


	private Pagination Pagination(int pg, int i, int bListCount, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String removeHtmlTag(String html) throws Exception {
		return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
	
}
