<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <%--<li>id=<%=((Member)request.getAttribute("member")).getId()%></li>  --%>
    <li>id=${member.id}</li>    <!-- 프로퍼티 접근법 -->
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>    <!-- '/' 표시 있어야함. -->
</body>
</html>

