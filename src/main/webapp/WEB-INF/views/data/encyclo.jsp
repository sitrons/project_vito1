<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>  

<!-- ----- HTML ------------------------------------------------------------------------ -->
<h4 class="h3 mb-4 text-gray-800">지식백과 </h4>
<h5 style="font-size: 13pt;">하루 하나씩, 기본 <b>건강상식</b> 배워보아요. :-)</h5>
<br/><br/>


<section class="categories spad">
	<div class="categories__post">
		<div class="container">
			<div class="row">
				<c:forEach items="${data}" var="data">	
				<!-- 아티클 하나----------------------------------------------------- -->		
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="categories__post__item">
					
						<div class="categories__post__item__pic small_item set-bg" data-setbg="/resources/img/bg.jpg"  style= 'background-image: url(" /resources/img/background.jpg "); height:100%;' >				
							<div class="post__meta set-bg">
								<h4><c:out value= "${data.date }" /></h4>
								<span><c:out value= "${data.month }" /></span>
							</div>
		
							<!-- 이미지 영역 ------------------------------------ -->							
							<c:set var="image-appearance" />								
								<c:if test='${data.imgUrl != null}'>
									<a id='move' href='<c:out value="${data.dno }"/>'><img src='${data.imgUrl.split(",")[0] }' class='image-item' > </a>
								</c:if>
								<c:if test='${data.imgUrl == null}'>
									<a id='move' href='<c:out value="${data.dno }"/>'><img src='/resources/img/empty.jpg' class='image-item' ></a>
								</c:if>		
							<!-- 이미지 영역 ------------------------------------ -->	
							
						    <div class="categories__post__item__text" >
						    	<p class="post post__label"> <span style="color: green;"><i><c:out value= "${data.category }" /></i></span><br/>
						    	<a id='move' href='<c:out value="${data.dno }"/>' style='color: grey; font-size: 1.3em;' > <b>" <c:out value= '${data.name }'/> "</b></a><br/>
						    	<span class="post post__widget" style="font-size: 0.8em; color: grey;"> [no. <c:out value= "${data.dno }" />] </span><br/>				    	
						    	<span class="post__article" style="font-size: 0.9em; color: grey;"> <c:out value= "${data.content }" /> </span>	
						    </div>
						</div>
					</div>
				</div>
				<!-- ------------------------------------------------------------- -->		
				</c:forEach>
			</div>
		</div>
	</div>  
</section>


<nav aria-label="Page navigation example">
    <div class="pull-right">
    	<ul class="pagination justify-content-center">    
    		<c:if test="${pageMaker.prev }">
    			<li class="page-item previous"><a class="page-link" href="${pageMaker.startPage -1 }">Previous</a></li>    			
    		</c:if>
    	
    		<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
    			<li class="page-item ${pageMaker.cri.page == num ? 'active':'' }">
    			<a class="page-link" href="${num }">${num }</a></li>
    		</c:forEach>
    		
    		<c:if test="${pageMaker.next }">
    			<li class="page-item next"><a class="page-link" href="${pageMaker.endPage +1 }">Next</a></li>
    		</c:if>    	
    	</ul>    
    </div>
</nav>     


    
<form class='actionForm' action="/data/encyclo" method="get">
	<input type="hidden" name="page" value='<c:out value="${pageMaker.cri.page}" />'>             
	<input type="hidden" name="amount" value='<c:out value="${pageMaker.cri.amount}" />'>  
</form>   

<!-- ----- CSS ------------------------------------------------------------------------ -->

<style>

.post__article { 
	display: inline-block; 
	width: 400px; 
	white-space: nowrap; 
	overflow: hidden; 
}

.post {
	text-align: center;
}

set-bg{
	height: 100%;
}

.image-item {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}


</style>

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




const arr = '';
const _actionForm = document.querySelector(".actionForm");


document.querySelectorAll("#move").forEach(
		(m) => {			
			let target = m;			
			let dno = target.getAttribute("href");
			
			console.log(dno);
			
			
			//2. 상세 페이지 이동
			target.addEventListener("click", function(e){					
				e.preventDefault();			
				
				_actionForm.setAttribute("action", "/data/pedia");
				_actionForm.innerHTML += "<input type='hidden' name='dno' value='"+dno+"'>"
				_actionForm.submit();	
			}, false);
});
			


//페이지네이션		 
document.querySelectorAll(".pagination li a").forEach(
		(p) => {			
			let page = p.getAttribute("href");
			
			console.log("page번호---------------------------------"+page);
			
			p.addEventListener("click", function(e){					
				e.preventDefault();	
			
				_actionForm.querySelector("input[name='page']").setAttribute("value", page);			
			    _actionForm.submit();	
		}, false);
});


</script>




<%@ include file="../includes/footer.jsp" %> 