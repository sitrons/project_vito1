<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>  <!-- 상대경로 사용 -->



<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">Modify Product</h1>


<!-- ----- HTML ------------------------------------------------------------------------ --> 


<form role="form" class="form" action="/product/modify" method="post" enctype="multipart/form-data">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	<!-- 리스트 버튼 -->
	<div class="list">   
	  <button id="listBtn" class='btn btn-primary actionBtn' data-oper="listBtn" type="submit" style='float: right;'>리스트</button>
	</div>
	
	
    <div class="list">
 	   <div class="form-group col-md-4">
	      <label>셀러번호: </label> <input type="text" value="${product.sellerNo }" readonly="readonly" name="sellerNo" > <br/>	            
	      <label>상품번호: </label> <input type="text" value="${product.pno }" readonly="readonly" name="pno" id="pno"> <br/>
	   </div>    
    </div> 
    <div class="list">
       <div class="form-row">
	   <div class="form-group col-md-6">
	 	  <label for="Name">Name</label>
		  <input type="text" class="form-control" id="productName" name="productName" value='<c:out value="${product.productName }"/>' >
	   </div>
	   </div>
	   <div class="form-row">
	   <div class="form-group col-md-2">    
		  <label for="unitPrice">Price</label>
		  <input type="text" class="form-control" id="unitPrice" name="unitPrice" value='<c:out value="${product.unitPrice }"/>' >
	   </div>
	   <div class="form-group col-md-2">
	      <label for="stockAmount">Stock Amount</label>
	      <input type="text" class="form-control" id="stockAmount" name="stockAmount" value='<c:out value="${product.stockAmount }"/>' >
	   </div>
	   <div class="form-group col-md-2">    
	      <label for="amountSold">Amount Sold</label>
	      <input type="text" class="form-control" id="amountSold" name="amountSold" value='<c:out value="${product.amountSold }"/>' readonly="readonly">
	   </div>
    </div>  
    <div class="list">
       <div class="form-row">
	   <div class="form-group col-md-6">
	     <label>Description</label> 
	     <textarea class="form-control" rows="3" name="description"> <c:out value="${product.description }"/></textarea>
	   </div>
	   </div>
	</div> 
	
	<div class="form-row">
	   <div class="form-group col-md-2">    
	     <label for="upload">Num. of Files :</label>
	     <input type="text" class="form-control" id="upload" value='<c:out value="${product.fileNum }"/> ' readonly="readonly">
         <br/>	   

			<div class='row'>  
				<div class='col-lg-12'>
					<div class='panel panel-default'>
						<div class='panel-heading'>Files</div>
						<div class='panel-body'>
							<div class='form-group uploadDiv'>
								<input type='file' name='uploadFiles' multiple='multiple'>
							</div> 
							<div class='uploadResult'>    
								<ul>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div> 
	  </div>   
	  </div>  
	  <br/>
      <div class="form-row">
		<div class="form-group col-md-3">    
		   <label for="regDate">Registered Date</label>
		   <input type="text" class="form-control" id="regDate" value='<fmt:formatDate pattern = "yyyy-MM-dd" value="${product.regDate }"/>' readonly="readonly">
		</div>  
		<div class="form-group col-md-3">    
		   <label for="updateDate">Update Date</label>
		   <input type="text" class="form-control" id="updateDate" value='<fmt:formatDate pattern = "yyyy-MM-dd" value="${product.updateDate }"/>' readonly="readonly">
		</div>
      </div>
  </div> 
  
	<br/>      

	<div class="form-group col-md-2">   
	  <sec:authentication property="principal" var="pinfo" />
	  	<sec:authorize access="isAuthenticated()">
		  	<c:if test="${pinfo.member.userid eq product.userid }">
		    <button type="submit" data-oper="modify" class="btn btn-info actionBtn" id='modifyBtn' >수정</button>
			<button type="submit" data-oper="delete" class="btn btn-danger actionBtn" id='deleteBtn' >삭제</button>
			</c:if>
	 	</sec:authorize> 
	</div>

	<input type="hidden" name="page" value='<c:out value="${cri.page}"/>'>          
	<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>  
	<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}" />'>  
	<input type="hidden" name="type" value='<c:out value="${cri.type}" />'>  
</form> 




<div class="bigPictureWrapper"> 
	<div class='bigPicture'>
	
	</div>
</div>




<!-- ----- CSS ------------------------------------------------------------------------ -->

<style>

.uploadResult{
	width:100%;
	background-color: green;
	font-color: white;
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
	width: 100px;
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









<!-- ----- JS ------------------------------------------------------------------------ -->

<script>

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


var pnoVal = '<c:out value="${product.pno}"/>';
var csrfHeaderName = "${_csrf.headerName }";
var csrfTokenValue = "${_csrf.token }";
	



//사진 보여주기

var pno = '<c:out value="${product.pno }"/>';
		
		$.getJSON("/product/getAttachList", {pno: pno}, function(arr){
			
			var str = "";
			$(arr).each(function(i, attach){

				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath +"/s_"+ attach.uuid +"_"+ attach.fileName);
					str += "<li data-uploadPath='"+attach.uploadPath+"'  data-uuid='"+attach.uuid+"' data-fileName='"+attach.fileName+"' ><div>";
					str += "<img src='/product/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str + "</li>";		  		
				} else{
					str += "<li data-uuid='"+attach.uuid+"' data-fileName='"+attach.fileName+"' ><div>";
					str += "<span>" + attach.fileName + "</span><br/>";
					str += "<img src='/resources/img/attach.png'>";
					str += "</div>";
					str + "</li>";
				}
			})
			$(".uploadResult ul").html(str);
		});

		


var regex = new RegExp("(.*?)\.(exe|sh|zip|alz|jar)$");
var maxSize = 5242880; <!-- 5MB -->

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
	



const _formObj = document.querySelector("form[role='form']");
const _uploadFiles = document.querySelector("input[name='uploadFiles']");
const _uploadResult = document.querySelectorAll(".uploadResult ul li");

document.querySelectorAll('.actionBtn').forEach(btn => {
	console.log(btn);
	
	btn.addEventListener("click", function(e){			
		e.preventDefault();
		e.stopPropagation();	
		
		//window.alert("1---------여기?-----");		
		
		const operation = btn.getAttribute("data-oper");		
		console.log('data-oper: ' + operation);
		
		if(operation === 'modify'){           
					
			//파일 ajax로 보내기
			const formData = new FormData();
			const files = _uploadFiles.files;
			
			let str = "";
			
			$(".uploadResult ul li").each(function(i, obj){
				var jobj = $(obj);
				
				console.log("기 업로드된 값-------------------------------------------------------------------------");
				console.dir(jobj);
								
				str += "<input type='hidden' name='attachList[" + i +"].uploadPath' value='"+jobj.data("path")+"'>";
				str += "<input type='hidden' name='attachList[" + i +"].uuid' value='"+jobj.data("uuid")+"'>";				
				str += "<input type='hidden' name='attachList[" + i +"].fileName' value='"+jobj.data("fileName")+"'>";
				str += "<input type='hidden' name='attachList[" + i +"].fileType' value='"+jobj.data("type")+"'>";
			});
							
			//_formObj.append(str).submit();
			
			//console.log("1 여기-------------------------------------");
			//console.dir(str);
			//console.log("1 여기-------------------------------------");
			//console.log(files.length);			
			
			
			if (files.length > 0) {
				
				for(let i=0; i<files.length; i++){
					formData.append("uploadFiles", files[i]);   <!-- upload controller 의 parameter name-->
				}
				
				//console.log("2 여기-------------------------------------");
							
				$.ajax({
					url: '/product/uploadAjaxAction',
					processData: false,
					contentType: false,
					data: formData,
					type: 'post',
					success: function(result){
						console.log("ajax file uploaded.. complete");
					}
				});<!--end ajax -->

				//console.log("3 여기-------------------------------------");
				
				_formObj.submit();
				
			} else {
				//console.log("4 여기-------------------------------------");
				//_formObj.setAttribute("action", "/product/modify") ;    
				_formObj.submit();		
				
				//console.log(_formObj);					
				//console.log("5 여기-------------------------------------");				
			}
			
		} else if (operation === 'delete'){
			_formObj.setAttribute("action", "/product/delete");
			console.log('del,,,.......');
			
		} else if (operation === 'listBtn'){			
			_formObj.setAttribute("action", "/product/list");
			_formObj.setAttribute("method", "get");
			console.log('listBtn,,,.......');	
		} <!-- end if -->
		
		_formObj.submit();
		
    }, false); 
});





</script>


<%@ include file="../includes/footer.jsp" %> 