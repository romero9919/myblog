package net.musecom.util;

public class Pagination {
   private int listSize; //한 번에 보일 목록의 갯수
   private int rangeSize;  //한 번에 보여질 페이지 갯수
   private int page;
   private int range;
   private int totalCnt; //전체 컬럼 수
   private int pageCnt; //페이지 수 (전체컬럼 / listSize)
   private int startPage; 
   private int endPage;
   private int startList;
   
   private boolean prev;
   private boolean next;
   
   
   public Pagination(int page, int range, int totalCnt, int listSize, int rangeSize) {  //생성자
	   this.listSize = listSize;
	   this.rangeSize = rangeSize;
	   this.page = page;
	   this.range= range;
	   this.totalCnt = totalCnt;
	   //전체 페이지
	   this.pageCnt = (int) Math.ceil(totalCnt / listSize);
	   
	   //시작 페이지
	   this.startPage = (range - 1) * rangeSize + 1;
	   
	   //끝
	   this.endPage = range * rangeSize;
	   
	   //시작 번호
	   this.startList = (page -1) * listSize;
	   
	   //이전 버튼 있기 없기
	   this.prev = range == 1 ? false : true;
	   
	   //다음 버튼 있기 없기
	   this.next = endPage > pageCnt ? false :true;
	   if(this.endPage > this.pageCnt) {
		   this.endPage = this.pageCnt;
		   this.next = false;
	   }
   }

   public int getRange() {
	return range;
   }

   public void setRange(int range) {
	this.range = range;
   }

	public int getListSize() {
		return listSize;
	}
	
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	
	public int getRangeSize() {
		return rangeSize;
	}
	
	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotalCnt() {
		return totalCnt;
	}
	
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	
	public int getPageCnt() {
		return pageCnt;
	}
	
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getStartList() {
		return startList;
	}
	
	public void setStartList(int startList) {
		this.startList = startList;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public void setNext(boolean next) {
		this.next = next;
	}
	   	   
}
