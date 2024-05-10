package net.musecom.database;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.musecom.util.Pagination;

public class BlogDao implements BlogConfig, FileConfig {

	private static BlogDao dao;
	private BlogDao() {}
	private Pagination pagination;
	
	public static BlogDao getInterface() {
		if(dao == null) dao = new BlogDao();
		return dao;
	}
	
	//SQL 세션 열기
	SqlSessionFactory sft = SqlMapSessionFactory.getSqlSessionFactory(); 
	
	@Override
	public int bListCount() {
		SqlSession session = sft.openSession();
		int rs = session.selectOne("net.musecom.database.blogMapper.blogCount");
		session.close();
		return rs;
	}

	@Override
	public List<BlogDto> bList(int page) {
		int totalCount = bListCount();
		pagination = new Pagination(page, 15, totalCount, 15, 15);
        SqlSession session = sft.openSession();
        List<BlogDto> dto = session.selectList("net.musecom.database.blogMapper.blogList", pagination);
        session.close();
		return dto;
	}

	@Override
	public BlogDto bView(int num) {
		SqlSession session = sft.openSession();
		BlogDto dto = session.selectOne("net.musecom.database.blogMapper.blogView", num);
		session.close();
		return dto;
	}

	@Override
	public int bUpdate(BlogDto blogDto) {
		SqlSession session = sft.openSession();
		int rs = session.update("net.musecom.database.blogMapper.blogUpdate", blogDto);
		session.commit();
		session.close();
		return rs;
	}

	@Override
	public int bInsert(BlogDto blogDto) {
		SqlSession session = sft.openSession();
		session.insert("net.musecom.database.blogMapper.blogInsert", blogDto);
		session.commit();
		session.close();
		return blogDto.getNum();
	}

	@Override
	public int bDelete(int num) {
		SqlSession session = sft.openSession();
		int rs = session.delete("net.musecom.database.blogMapper.blogDelete", num);
		session.commit();
		session.close();
		return rs;
	}

	@Override
	public int fileInsert(FileDto fileDto) {
		SqlSession session = sft.openSession();
		session.insert("net.musecom.database.blogMapper.insertFile", fileDto);
		session.commit();
		session.close();
		return fileDto.getNum();
	}

	@Override
	public int fileUpdate(FileDto fileDto) {
		SqlSession session = sft.openSession();
		if(fileDto.getBlog_num() > 0) {
			session.insert("net.musecom.database.fileMapper.insertAfterUpdateFile", fileDto);
		}else {
			session.insert("net.musecom.database.fileMapper.updateFile", fileDto);
		}
		session.commit();
		session.close();
		return fileDto.getNum();
	}

	@Override
	public int fileDelete(int num) {
	    SqlSession session = sft.openSession();
	    int rs = session.delete("net.musecom.database.fileMapper.deleteFile", num);
	    session.commit();
	    session.close();
		return rs;
	}

	@Override
	public List<FileDto> fileList(int blognum) {
        SqlSession session = sft.openSession();
        List<FileDto> fdto = session.selectList("net.musecom.database.fileMapper.listFile", blognum);
        session.close();
		return fdto;
	}

	@Override
	public FileDto fileOneList(int blognum) {
		SqlSession session = sft.openSession();
		List<FileDto> list = session.selectList("net.musecom.database.fileMapper.listOneFile", blognum);
		 if (!list.isEmpty()) {
	            return list.get(0); // 리스트의 첫 번째 항목을 반환
	        }
	     return null; // 결과가 없을 경우 null 반환
	}

}
