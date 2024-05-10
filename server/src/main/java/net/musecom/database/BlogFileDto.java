package net.musecom.database;

public class BlogFileDto {
   private BlogDto blog;
   private FileDto file;
   
   public BlogFileDto() {}
   
	public BlogFileDto(BlogDto blog, FileDto file) {
	this.blog = blog;
	this.file = file;
    }
	
	public BlogDto getBlog() {
		return blog;
	}
	public void setBlog(BlogDto blog) {
		this.blog = blog;
	}
	public FileDto getFile() {
		return file;
	}
	public void setFile(FileDto file) {
		this.file = file;
	}
	   
    
}
