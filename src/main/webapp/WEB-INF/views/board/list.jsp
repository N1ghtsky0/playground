<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: jiwook
  Date: 2023-11-05
  Time: 오후 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <style>
    table, th, td {
      border: 1px solid black;
    }
  </style>
  <title>게시글 목록</title>
</head>
<body>
<h3>게시글 목록 페이지</h3>
<table id="boardListTable">
  <tr>
    <th>게시글 번호</th>
    <th>제목</th>
    <th>작성자</th>
    <th>작성 일자</th>
  </tr>
  <c:choose>
    <c:when test="${boardVOList.size() != 0}">
      <c:forEach items="${boardVOList}" var="boardVO">
        <tr>
          <td>${boardVO.seq}</td>
          <td>${boardVO.title}</td>
          <td>${boardVO.author}</td>
          <td>
            <fmt:parseDate value="${boardVO.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" />
          </td>
        </tr>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <tr>
        <td colspan="5">조회할 게시글이 없습니다.</td>
      </tr>
    </c:otherwise>
  </c:choose>
</table>
</body>
</html>
