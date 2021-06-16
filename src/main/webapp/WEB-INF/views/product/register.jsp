<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<%@ include file="../includes/header.jsp" %>  <!-- 상대경로 사용 -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>


<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">Register</h1>
<button class="btn btn-primary" data-oper="list" onclick="location.href='/product/list'"  style="float:right;">리스트</button>
<br/>


<!-- ----- HTML ------------------------------------------------------------------------ --> 


<form role="form" class="registrationForm" action="/product/register" method='post' enctype="multipart/form-data">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
<sec:csrfInput />
  <div class="form-col">
	<div class="form-group col-md-2">
		<label>Writer ID : <input class='form-control' name='userid' value='<sec:authentication property="principal.member.userid"/>' readonly="readonly"></label> 
		<label>Writer Name : <input class='form-control' name='username' value='<sec:authentication property="principal.member.username"/>' readonly="readonly"></label> 
	</div>
	<br/>
   <div class="form-col">
   	  <div class="form-group col-md-4">
	    <label for="sellerNo">Seller No</label>
	    <input type="text" class="form-control" id="sellerNo" name="sellerNo" placeholder="판매자 번호">
	  </div>  
	  <div class="form-group col-md-4">
	    <label for="category">Category</label>
	    <input type="text" class="form-control" id="category" name="category" placeholder="카테고리명을 입력하세요.">
	  </div>
	  <div class="form-group col-md-6">
	    <label for="productName">Product Name</label>
	    <input type="text" class="form-control" id="productName" name="productName" placeholder="상품명을 입력하세요.">
	  </div>
	   <div class="form-group col-md-6">
	    <label for="description">Description</label>
	    <textarea type="text" class="form-control" rows="3" id="description" name="description" placeholder="제품 상세설명 (최대 3줄)"></textarea>
	  </div>
   </div>
   <div class="form-row">
	   <div class="form-group col-md-2">
	 	    <label for="unitPrice">Unit Price</label>
		    <div class="col">
		      <input type="text" class="form-control" id="unitPrice" name="unitPrice" placeholder="판매 단가">
		    </div>
	   </div>		    
	   <div class="form-group col-md-2">    
		    <label for="stockAmount">Stock Amount</label>
		    <div class="col">
		      <input type="text" class="form-control" id="stockAmount" name="stockAmount" placeholder="재고 수량">
		    </div>
	   </div>
    </div>
    
    
<div class='row'>  
	<div class='col-lg-12'>
		<div class='panel panel-default'>
			<div class='panel-heading'>File Attach</div>
			<div class='panel-body'>
				<div class='form-group uploadDiv'>
					<input type='file' name='uploadFiles' multiple>     
				</div>
				
				<div class='uploadResult'>  
					<ul>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="bigPictureWrapper"> 
	<div class='bigPicture'></div>
</div>

<br/>

  	<sec:authorize access="isAuthenticated()">   
  		<c:if test="${pinfo.member.userid eq product.userid }">
		<button class="btn btn-success" type='submit' id="submitBtn">등록</button>
		</c:if> 
	</sec:authorize>
</form>





<!-- ----- css ------------------------------------------------------------------------ --> 
<style>

.uploadResult{
	width: 100%;
	background-color: gray;
}
.uploadResult ul{
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}
.uploadResult ul li{
	list-style: none;
	padding: 10px;
	align-content: center;
	text-align: center;
}
.uploadResult ul li img{
	width: 200px;
}
.uploadResult ul li span{
	color: white;
}
.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background: rgba(255,255,255,0.5);
}
.bigPicture{
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}
.bigPicture img{
	width: 600px;
}
</style>



<!-- ----- SCRIPT ------------------------------------------------------------------------ -->
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
  
<script type="text/javascript">


authentication();

function authentication(authen){	

	if (authen === null){
		console.log("사용자 미인증");
		return;
	} 	
	if (authen !== null){
	 	console.log("사용자 인증 완료");
		checkLoginUser();
	} 		
} 

var csrfHeaderName = "${_csrf.headerName }";
var csrfTokenValue = "${_csrf.token }";




var regex = new RegExp("(.*?)\.(exe|sh|zip|alz|jar)$");
var maxSize = 5242880; 

function checkExtension(fileName, fileSize){
	if (fileSize >= maxSize){
		alert("업로드 파일 사이즈가 초과되었습니다.");
		return false;
	}
	if (regex.test(fileName)){
		alert("해당 종류의 파일은 업로드 불가합니다.");
		return false;
	}
	return true;
} <!-- end function -->



//1. 신규상품 등록
const _formObj = document.querySelector("form[role='form']");
const _inputValue = document.querySelectorAll("input[type='text']"); 
const _file = document.querySelector("input[type='file']"); 
const _uploadFiles = document.querySelector("input[name='uploadFiles']"); 

_formObj.querySelector("#submitBtn").addEventListener("click", function(e){
	e.preventDefault();
	
	console.log("파일명 -------------------------------");
	console.dir(_uploadFiles);
	console.log("파일명 -------------------------------");
	
	//파일 ajax로 보내기 
	const formData = new FormData();	
	const upFiles = _uploadFiles.files;
	
	console.log("업로드 파일-------------------------------");
	console.dir(upFiles);
		
	
	for(let i = 0; i < upFiles.length; i++){
		formData.append("uploadFile", upFiles[i]);    
	}
	
	$.ajax({
		url: '/product/uploadAjaxAction',
		processData: false,
		contentType: false,
		data: formData,
		type: 'post',
		success: function(result){
			console.log("ajax file uploaded.. complete");
		}
	});<!--end aja -->


	_formObj.submit();
	window.alert("신규 상품이 등록되었습니다.");	
}, false);	 


	
	
	
	
	
	

$('.uploadResult').on('click', 'button', function(e){ 123
	console.log("delete file");
		
	var targetFile = $(this).data("file");
	var type = $(this).data('type');
		
	var targetLi = $(this).closest('li');
	
	$.ajax({
		url: '/deleteFile',
		data: {fileName: targetFile, type: type},
		beforeSend: function(xhr){ xhr.setRequestHeader(csrfHeaderName, csrfTokenValue); },
		type: 'POST',
		dataType: 'text',
		success: function(result){
			alert(result);
			targetLi.remove();
		}					
	}); <!-- end ajax -->
	
		
}); <!-- end event --> 
	


</script>




<%@ include file="../includes/footer.jsp" %>  