<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<%@ include file="../../includes/header.jsp" %>  


<body>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">(/access/all) 모두 확인가능 PAGE</h1>


<sec:authorize access="isAnonymous()">
<a href="/security/customLogin">로그인</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<a href="/security/customLogout">로그아웃</a>
</sec:authorize>


</body>
</html>



<script>

$.getJSON("/product/getAttachList?pno=38",function(data){
	
	
	alert("DATA");
	console.log(data);
}); 

</script>



<%@ include file="../../includes/footer.jsp" %> 