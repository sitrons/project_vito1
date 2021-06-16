<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<%@ include file="../includes/header.jsp" %>


<body>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">logout PAGE</h1>


<form method='post' action='/security/customLogout'>
	<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />
	<button>logout</button>
</form>





<script>

checkLoginUser();

</script>


</body>
</html>




<%@ include file="../includes/footer.jsp" %> 