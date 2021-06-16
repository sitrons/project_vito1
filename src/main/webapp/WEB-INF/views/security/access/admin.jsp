<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<%@ include file="../../includes/header.jsp" %> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"> /access/admin PAGE </h1>

<p>principal : <sec:authentication property='principal'/> </p>
<p>MemberVO : <sec:authentication property='principal.member'/> </p>
<p>사용자 아이디 userid : <sec:authentication property='principal.member.userid'/> </p>
<p>사용자 이름  username : <sec:authentication property='principal.member.username'/> </p>
<p>사용자 닉네임  charName : <sec:authentication property='principal.member.charName'/> </p>
<p>사용자 권한 리스트 : <sec:authentication property='principal.member.authList'/> </p>


<a href='/security/customLogout'>logout</a>


</body>
</html>




<%@ include file="../../includes/footer.jsp" %>  