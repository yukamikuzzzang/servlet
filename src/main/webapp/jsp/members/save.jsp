<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%-- 임포트 되는 부분은 꼭 추가해주자. --%>

<%-- ... web.servlet.MemberSaveServlet.java의 로직을 따름. --%>
<%
    //request, response는 그냥 사용 가능.
    //JSP도 자동으로 서블릿으로 전환되기 때문.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");     //보낸 데이터를 읽을 수 있도록..
    int age = Integer.parseInt(request.getParameter("age"));//request.getParameter()의 반환형은 무조건 문자

    //멤버 객체 생성
    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>             <!-- like System.out.print(); -->
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>    <!-- '/' 표시 있어야함. -->
</body>
</html>
