<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-06
  Time: 오후 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Blog Post - Start Bootstrap Template</title>
    <jsp:include page="../common/header.jsp" />
</head>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container-md h-75 my-5" id="wrapper">
    <main class="w-100 m-auto card">
        <h1 class="h3 mb-3 fw-normal card-header">게시글 작성</h1>
        <form method="post" name="frm" id="frm" class="card-body">
            <div class="mb-3">
                <label for="title" class="form-label">제목: </label>
                <input class="form-control" type="text" name="title" id="title" placeholder="제목을 입력하세요.">
            </div>
            <div class="mb-3">
                <label class="form-label" for="content">내용: </label>
                <textarea class="form-control" name="content" id="content" placeholder="내용을 입력하세요." rows="5" ></textarea>
            </div>
            <div class="mb-3 row">
                <label for="author" class="col-sm-1 col-form-label">작성자: </label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="author" value="anonymous">
                </div>
                <div class="col-sm-1" align="right">
                    <button type="submit" class="btn btn-primary col-auto" name="summitBtn" id="summitBtn" disabled>저장</button>
                </div>
            </div>
        </form>
    </main>
</div>
<jsp:include page="../common/footer.jsp" />
</body>
<script type="text/javascript">
    $("#title").keyup(function () {
        if ($("#title").val().length !== 0 && $("#content").val().length !== 0) {
            $("#summitBtn").removeAttr("disabled");
        }
    })

    $("#content").keyup(function () {
        if ($("#title").val().length !== 0 && $("#content").val().length !== 0) {
            $("#summitBtn").removeAttr("disabled");
        }
    })

    $("#summitBtn").click(function () {
        const confirm_msg = "글을 등록하시겠습니까?";
        if (confirm(confirm_msg)) {
            $("#frm").attr("action", "/board/insertProcess").submit();
        }
    })
</script>
</html>
