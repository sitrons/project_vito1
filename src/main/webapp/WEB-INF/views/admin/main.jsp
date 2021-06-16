<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>  

<br/>

<!-- 
<h1 class="h3 mb-4 text-gray-800">스패셜티 푸드 전문점 vito.</h1>
<font size="10px" color="grey">건강한 식습관은 vito와 함께<br/> vegan, lacto-free, halal 같은 관리가 필요한 당신을 위해 준비한 푸드 딜리버리 전문점 </font>
 -->
<img src="/resources/img/main_top.jpg" class="card-img-top" alt="specialty food delivery, vito.">
<br/>
<br/>

<!-- ----- HTML ------------------------------------------------------------------------ -->
<div class="row">
	<div class="col-sm-3">
		<div class="card" style="width: 18rem;">
		  <img src="/resources/img/main1.jpg" class="card-img-top" alt="let's eat fresh">
		  <div class="card-body">
		    <h5 class="card-title">Eat Fresh!</h5>
		    <p class="card-text" style="font-size:10px">특별한 당신을 위해<br/> vito에서 엄선한 신선한 식품으로 최고의 맛을 전합니다. </p>

		    <a href="/product/list" class="btn btn-secondary">구경가기 ▷</a>
		  </div>
		</div>
	</div>
	<div class="col-sm-3">
		<div class="card" style="width: 18rem;">
		  <img src="/resources/img/main2.jpg" class="card-img-top" alt="let's eat fresh">
		  <div class="card-body">
		    <h5 class="card-title">Encyclopedia</h5>
		    <p class="card-text" style="font-size:10px">이제는 똑똑하게 먹자. <br/> 각양각색 식품에 대해 상식백과에서 확인하자.</p>
		    <a href="/data/encyclo" class="btn btn-secondary">상식백과 ▷</a>
		  </div>
		</div>
	</div>
	<div class="col-sm-3">
		<div class="card" style="width: 18rem;">
		  <img src="/resources/img/main3.jpg" class="card-img-top" alt="let's eat fresh">
		  <div class="card-body">
		    <h5 class="card-title">Recipe Book</h5>
		    <p class="card-text" style="font-size:10px">레시피 블로그. <br/>오늘 먹은 건강한 식단을 기록하고 공유합니다.  </p>
		    <a href="/calorie/calorie" class="btn btn-secondary">식단관리 ▷</a>
		  </div>
		</div>
	</div>
	<div class="col-sm-3">
		<div class="card" style="width: 18rem;">
		  <img src="/resources/img/main4.jpg" class="card-img-top" alt="let's eat fresh">
		  <div class="card-body">
		    <h5 class="card-title">Store lists</h5>
		    <p class="card-text" style="font-size:10px">인근 비건 매장 찾기. <br/>비건, 할랄, 락토 등 인근 스페셜티 매장 알아보자.  </p>
		    <a href="/store/storelist" class="btn btn-secondary">매장찾기 ▷</a>
		  </div>
		</div>
	</div>
</div>


<br/>
<br/>
    
<!-- ----- CSS ------------------------------------------------------------------------ -->



<!-- ----- JS ------------------------------------------------------------------------ -->

<script type="text/javascript">


authentication();

function authentication(authen){	

	if (authen === null){
//		console.log("사용자 미인증");
		return;
	} 	
	if (authen !== null){
//	 	console.log("사용자 인증 완료");
		checkLoginUser();
	} 		
} 


</script>



<%@ include file="../includes/footer.jsp" %> 