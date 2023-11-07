<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-07
  Time: 오후 4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세조회</title>
</head>
<body>
<button name="callBackBtn" id="callBackBtn" onclick="history.back()">뒤로 가기</button>
<h3>${BoardVO.title}</h3>
<br>
<p>작성자: ${BoardVO.author}</p>
<p>작성일자: ${BoardVO.createdAt}</p>
<br>
<p>${BoardVO.content}</p>
</body>
</html>
