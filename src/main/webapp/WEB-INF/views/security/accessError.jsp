<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<%@ include file="../includes/header.jsp" %>  

<body>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">/accessError denied PAGE</h1>

<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage() }" /></h2>
<h2><c:out value="${msg }" /></h2>

</body>
</html>




<%@ include file="../includes/footer.jsp" %>