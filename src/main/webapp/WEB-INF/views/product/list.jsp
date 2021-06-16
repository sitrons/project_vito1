<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>  


<h1 class="h3 mb-4 text-gray-800">Hello, vegan! <br/> </h1>


<!-- ----- HTML ------------------------------------------------------------------------ -->

<sec:authorize access="hasRole('ROLE_ADMIN')"> 
	<div class="newReg">    
	  <button id="regBtn" type="submit" class="btn btn-primary" style='float: right;'>신규상품 등록</button>
	</div>
</sec:authorize>


<form>
  <div class="form-row align-items-center">
    <div class="col-auto my-1">
     <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
      	    <option selected>스패셜푸드 셀러 리스트...</option> 
	      	<c:forEach items="${sellerList}" var="sellerList">            
			<option id="seller">[#${sellerList.sellerNo }]  ${sellerList.name } </option>   
	      	</c:forEach>
      </select>
    </div>
    
    <div class="col-auto my-1">
      <div class="custom-control custom-checkbox mr-sm-5">
        <input class="custom-control-input" name="remember" type="checkbox"><br/>
	    <button class="btn btn-success" id="submitBtn" type="submit">매장별 검색</button>
   	  </div>
    </div>
    
  </div>
</form>   



<br/>   




 <section class="section-margin calc-60px">
      <div class="container">
        <div class="section-intro pb-60px">
          <h2>Vegan <span class="section-intro__style">Special</span></h2>
        </div>
        <div class="row">
        <c:forEach items="${productList}" var="productList">	
          <div class="carrier col-md-6 col-lg-4 col-xl-3">
            <div class="card text-center card-product">
              <div class="uploadResult">   <!-- image insert -->             
                <ul class="card-product__imgOverlay ">
                
                </ul>
              </div>
              <div class="desc card-body">
                <p><i>${productList.category }</i></p>
                <h4 class="card-product card-title move"><a href="<c:out value='${productList.pno }'/>"  style="font-size:18px;"> ${productList.productName }</a></h4>
                <p class="card-product" id="card-price" style="font-size:13px;">${productList.unitPrice } </p>
                <p class="card-product card-desc" style="font-size:13px;'" id="description"> ${productList.description } </p>	 	
			    <p class="card-product card-reply" style="font-size:10px;"> ♥댓글수: ${productList.replyCnt } </p>
              </div>
            </div>
          </div>
         </c:forEach>
        </div>
      </div>
    </section>

<br/> <br/>

   <div class='row'>
    <div class='col-lg-12'>    	
   	<form id='searchForm' action='/product/list' method='get'>
   	<select name ='type'>
   		<option value=" " <c:out value="${pageMaker.cri.type == null?'selected':'' }"/>>검색 조건을 선택하세요-------</option>
   		<option value="P" <c:out value="${pageMaker.cri.type eq 'P'?'selected':'' }"/>>▶ 상품명</option>
   		<option value="D" <c:out value="${pageMaker.cri.type eq 'D'?'selected':'' }"/>>▶ 상품 설명</option>
   		<option value="S" <c:out value="${pageMaker.cri.type eq 'S'?'selected':'' }"/>>▶ 셀러 번호</option>
   		<option value="PD" <c:out value="${pageMaker.cri.type eq 'PD'?'selected':'' }"/>>▶ 상품명 or 상품 설명</option>
   		<option value="PS" <c:out value="${pageMaker.cri.type eq 'PS'?'selected':'' }"/>>▶ 상품명 or 셀러 번호</option>
    		<option value="PSD" <c:out value="${pageMaker.cri.type eq 'PSD'?'selected':'' }"/>>▶ 상품명 or 상품 설명 or 셀러 번호</option>   		
   	</select>
   		<input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}" />'>
   		<input type='hidden' name='page' value='<c:out value="${pageMaker.cri.page}" />'>
   		<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}" />'>
   		<button class='btn btn-secondary' id="searchBtn">검색</button>
   		<button class='btn btn-secondary' id="listBtn">리스트</button>
   	</form>
     </div>    
   </div>
   <br/>
   
 
<nav aria-label="page navigation example">
    <div class="">
    	<ul class="pagination justify-content-center">    
    		<c:if test="${pageMaker.prev }">
    			<li class="page-item previous"><a class="page-link"  href="${pageMaker.startPage -1 }">Previous</a></li>    			
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
    




<form class='actionForm' action="/product/list" method="get">
	<input type="hidden" name="page" value='<c:out value="${pageMaker.cri.page}" />'>             
	<input type="hidden" name="amount" value='<c:out value="${pageMaker.cri.amount}" />'>  
	<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}" />'>  
	<input type="hidden" name="type" value='<c:out value="${cri.type}" />'>  
</form>   





<div class="modal fade" id="modalSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Success!</h4>
				<button type="button" class="close" data-dismiss="modal" id="xBtn" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body"> </div>
			<div class="modal-footer">			
		      <button class="btn btn-primary" type="submit" id="closeBtn" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>


<!-- ----- CSS ------------------------------------------------------------------------ -->


<style>

#description { 
	display: inline-block; 
	width: 200px; 
	white-space: nowrap; 
	overflow: hidden; 
}

#searchForm {
 	text-align: center;
}


.pagination {
	text-align: center;
}


.carrier {
	margin-bottom: 20px; 
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


const _regBtn = document.getElementById("regBtn");

if(_regBtn){
	_regBtn.onclick = function(){	
		location.href='/product/register';		
	};
}






var _actionForm = document.querySelector(".actionForm");

//pagination 
document.querySelectorAll(".page-item a").forEach(
	(atag) => {			
		var target = atag;
		
		//console.log("a tag------------------------"+target);
		
		target.addEventListener("click", function(e){					
			e.preventDefault();

			var value = target.getAttribute("href");				
			//console.log("href---------------------"+value);							
			_actionForm.querySelector("input[name='page']").setAttribute("value", value);				
			_actionForm.submit();				
		},false);				
	}			
);




//가격 한화 표시 -------------------------------------- 
const price = document.querySelectorAll("div #card-price");

price.forEach(
	(p) => {
	
		let priceTemp = new Number(p.innerText);
		
		//console.log("2 가격-------------"+ p.innerText);
		
		let obj = {
				style: "currency",
				currency: "KRW"
		}		
				
		//console.log("3 가격-------------"+ priceTemp.toLocaleString("ko-KR", obj));
		p.innerHTML = priceTemp.toLocaleString("ko-KR", obj);
	}
)



//---------------------------------------------------------
//1. 사진 보여주기
let index = 0;

<!-- 비동기에 따른 cashing issue -->
$.ajaxSetup({
    async: false
});		


document.querySelectorAll(".carrier a").forEach(
		(a) => {
			const findA = a;
			const pno = a.getAttribute("href");
	
			console.log("여기 pno값 ============================="+pno);
			
	$.getJSON("/product/getAttachList", {pno: pno}, function(arr){
		
		var str = "";
				
		$(arr).each(function(idx, attach){

			if(attach.fileType){			
				var filePath = encodeURIComponent(attach.uploadPath +"/s_"+ attach.uuid +"_"+ attach.fileName);
				str = "<img class='card-img' src='/product/display?fileName="+ filePath + "' style=' min-width:250px; height:320px;'>";
				
				//console.log("3----"+pno+"_____"+str);	
				
				$(".uploadResult").eq(index).html(str);		
				
			} else{
				str = "<img src='/resources/img/attach.png'>";
			}
		}) <!-- end arr -->	

		index++;	
		
	}); <!--end json -->



	
	
	//2. 상세 페이지 이동 
	findA.addEventListener('click', function(e){
		e.preventDefault();
		
		_actionForm.setAttribute("action", "/product/view");
		_actionForm.innerHTML += "<input type='hidden' name='pno' value='"+pno+"'>"
		_actionForm.submit();
	});
		
}, false); <!--end aTag -->


//---------------------------------------------------
	






var _searchForm = document.querySelector("#searchForm");
var _searchFormBtn = document.querySelector("#searchBtn");

_searchFormBtn.addEventListener('click', function(e){
	
	if(!_searchForm.find("option:selected").value){
		alert("검색 조건을 선택하세요.");
		return false;
	}
	if(!_searchForm.getElementsByName('keyword').value){
		alert("키워드를 입력하세요.");
		return false;
	}
	
	var search = _searchForm.getElementsByName('page').value;
	search.setAttribute('page', '1');
	
	e.preventDefault();
	e.stopPropagation();
  
	_searchForm.submit();
		
}, false); 





$(document).ready(function() {		

	var result = '<c:out value ="${result}"/>';                       
	const newPno = 	parseInt(result);
	
	checkModal(result); 
	history.replaceState({}, null, null);
	
	function checkModal(result){	
		if (result === '' || history.state){
			return;
		} 	
		if (newPno > 0) {		
			$(".modal-body").html("처리가 완료되었습니다. (게시글 "+ newPno +"번)");
			$("#modalSuccess").modal("show");
		} 		
	} <!-- end function -->
	
});	
	
	
//모달 팝업시, 종료 버튼 활성화	
var _modal = document.querySelector(".modal");
var _closeBtn = document.querySelector("#closeBtn");
var _xBtn = document.querySelector("#xBtn");


_closeBtn.addEventListener('click', function(e){
	e.preventDefault();
	e.stopPropagation();

	$(_modal).modal("hide");
}, false); 


_xBtn.addEventListener('click', function(e){
	e.preventDefault();
	e.stopPropagation();

	$(_modal).modal("hide");
}, false); 






</script>






<%@ include file="../includes/footer.jsp" %> 