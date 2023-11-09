<%--
  Created by IntelliJ IDEA.
  User: jiwook
  Date: 2023-11-05
  Time: 오후 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Blog Post - Start Bootstrap Template</title>
  <jsp:include page="../common/header.jsp" />
</head>
<body>
<jsp:include page="../common/navbar.jsp"/>
<!-- Page header with logo and tagline-->
<header class="py-5 bg-light border-bottom mb-4">
  <div class="container">
    <div class="text-center my-5">
      <h1 class="fw-bolder">Welcome to Blog Home!</h1>
      <p class="lead mb-0">A Bootstrap 5 starter layout for your next blog homepage</p>
    </div>
  </div>
</header>
<!-- Page content-->
<div class="container" id="wrapper">
  <div class="row">
    <!-- Blog entries-->
    <div class="col-lg-8">
      <!-- Nested row for non-featured blog posts-->
      <div class="row">
        <c:choose>
          <c:when test="${boardVOList.size() > 0}">
            <c:forEach items="${boardVOList}" var="boardVO">
              <div class="col-lg-6">
                <!-- Blog post-->
                <div class="card mb-4">
                  <a href="#!"><img class="card-img-top" src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg" alt="..." /></a>
                  <div class="card-body">
                    <div class="small text-muted">
                      <fmt:parseDate value="${boardVO.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                      <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" />
                    </div>
                    <h2 class="card-title h4">${boardVO.title}</h2>
                    <p class="card-text">${boardVO.content}</p>
                    <a class="btn btn-primary" href="/board/detail/${boardVO.seq}">Read more →</a>
                  </div>
                </div>
              </div>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="py-5 mb-4">
              <div class="container">
                <div class="text-center my-5">
                  <h2 class="fw-bolder">작성된 글이 없습니다.</h2>
                  <a href="/board/insert" class="btn btn-primary">글 작성하러 가기 →</a>
                </div>
              </div>
            </div>
          </c:otherwise>
        </c:choose>
      </div>
      <!-- Pagination-->
      <div class="row">
        <hr class="my-0" />
        <nav aria-label="Pagination" class="col-10">
          <ul class="pagination justify-content-center my-4">
            <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">Newer</a></li>
            <li class="page-item active" aria-current="page"><a class="page-link" href="#!">1</a></li>
            <li class="page-item"><a class="page-link" href="#!">2</a></li>
            <li class="page-item"><a class="page-link" href="#!">3</a></li>
            <li class="page-item disabled"><a class="page-link" href="#!">...</a></li>
            <li class="page-item"><a class="page-link" href="#!">15</a></li>
            <li class="page-item"><a class="page-link" href="#!">Older</a></li>
          </ul>
        </nav>
        <div class="col-auto my-4">
          <a class="btn btn-primary" href="/board/insert">글 쓰기</a>
        </div>
      </div>
    </div>
    <jsp:include page="../common/sidewidget.jsp"/>
  </div>
</div>
<jsp:include page="../common/footer.jsp" />
</body>
</html>
