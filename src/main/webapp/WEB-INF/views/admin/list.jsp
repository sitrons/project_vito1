<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>  

<h1 class="h3 mb-4 text-gray-800">list page</h1>



<!-- ----- HTML ------------------------------------------------------------------------ -->

<script>
   setTimeout(function(){
      window.location.href = '/admin/main';
   }, 3000);
</script>

<p>Web page redirects after 3 seconds.</p>
<p>3초후 이동합니다.</p>


<!-- ----- JS ------------------------------------------------------------------------ -->







<%@ include file="../includes/footer.jsp" %> 