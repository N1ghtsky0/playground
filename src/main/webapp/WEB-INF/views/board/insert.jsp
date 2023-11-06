<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-06
  Time: 오후 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>게시글 작성</title>
</head>
<body>
<h1>게시글 작성</h1>
<form method="post" name="frm" id="frm">
    <label for="title">제목: </label><input type="text" name="title" id="title">
    <br>
    <label for="content">내용: </label><input type="text" name="content" id="content">
    <br>
    <label for="author">작가: </label><input type="text" name="author" id="author">
    <br>
    <button type="submit" name="summitBtn" id="summitBtn" disabled>저장</button>
</form>
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
