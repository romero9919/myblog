<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/reset.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
   <h1 class="text-center title">목록보기</h1>
   <p class="text-center titlep">관리자 모드</p>
   <div class="container">
       <a href="./insert.jsp">글올리기</a>
       <ul class="row">
       
       <c:forEach var="row" items="${list }">
          <li class="col-6">
              <a href="#" class="box">
                 <img src="http://localhost:3000/data/img/${row.fileName }" alt="${row.fileSize }" />
                 <article class="content">
                    <h3>${fn: substring(row.title, 0, 28)}</h3>
                    <span>${row.categorya}</span><span>${row.categoryb}</span>
                    <p>
                       ${fn: substring(row.content, 0, 100) } </p>
                    <div class="wdate">
                       <span>${fn: substring(row.wdate, 0, 10) }</span>
                       <span>${fn: substring(row.wdate, 11, 19) }</span>
                    </div>
                 </article>
              </a>             
           </li>
       </c:forEach>
       
       	     <ul class="pagination justify-content-center">
		        <c:if test="${pages.prev }">     
		         <li class="page-item disabled">
		             <a href="list${pages.makeQuery(pages.startPage + 1 )}" class="page-link">이전</a>
		         </li>
		       </c:if>
		       
		       <c:forEach begin="${pages.startPage }" end="${pages.endPage }" var="idx">
		       
		          <c:choose>
		            <c:when test ="${idx eq pg }">
		               <li class="page-item active">
		            </c:when>
		            <c:when test="${param.page eq null && idx eq 1}">
		              <li class="page-item active">
		            </c:when>
		            <c:otherwise>
		               <li class="page-item">
		            </c:otherwise>
		          </c:choose>  
		          
		           <a href="list${pages.makeQuery(idx)}"  class="page-link">${idx }</a>
		        
		       </c:forEach>  
		       
               <c:if test="${pages.next && pages.endPage > 0 }">
		         <li class="page-item">
		             <a href="list${pages.makeQuery(pages.endPage + 1)}" class="page-link">다음</a>
		         </li>          
		       </c:if>  
		     </ul>
              
       </ul>
   </div>
</body>
</html>