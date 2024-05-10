<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h2>Please login here </h2>
<c:if test="${param.error }">
  Invalid Username or Password.
</c:if>
<form action="./login" method="post">
  <label for="fname">username:</label><br>
  <input type="text" id="fname" name="username"><br>
  <label for="lname">password:</label><br>
  <input type="password" id="password" name="password"><br><br>
  <input type="submit" value="LOG IN">
</form> 
</body>
</html>