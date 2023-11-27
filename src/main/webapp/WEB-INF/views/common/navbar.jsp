<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-11-08
  Time: 오전 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- S: Responsive navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tab eq 'about'}">active</c:if>" href="#!">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tab eq 'contact'}">active</c:if>" href="#!">Contact</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tab eq 'blog'}">active</c:if>" href="/">Blog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tab eq 'singIn'}">active</c:if>" href="/login">sign In</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- E: Responsive navbar -->