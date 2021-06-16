<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %> 

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">Product Specification</h1>


<!-- ----- HTML ------------------------------------------------------------------------ --> 


<div class="listBtn">   
  <button id="listBtn" type="submit" class="btn btn-primary" style='float: right;'>리스트</button>
</div>


<form role="form" action="/product/modify" method="get">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
<!-- <input type="hidden" name="zzz" value="sitron" /> -->
    <div class="form-group">
    <div class="form-row">
 	   <div class="form-group col-md-2">
	      <label >셀러번호: </label> <input type="text" class="form-control" value="${product.sellerNo }" name="sellerNo" readonly="readonly"> <br/>	 
	   </div>    
	   <div class="form-group col-md-2">    
		  <label>작 성 자 : </label> <input type="text" class="form-control" value="${product.userid }" name="userid" readonly="readonly" > <br/>   
	   </div>  
	   <div class="form-group col-md-2">  	           
	      <label>상품번호: </label> <input type="text"  class="form-control" value="${product.pno }" name="pno" readonly="readonly" > <br/>   
	   </div>
    </div>   
    </div> 
    <div class="form-group">
       <div class="form-row">
	   <div class="form-group col-md-6">
	 	  <label for="Name">Name</label>
		  <input type="text" class="form-control" id="name" value='<c:out value="${product.productName }"/>' readonly="readonly">
	   </div>
	   </div>
	   <div class="form-row">
	   <div class="form-group col-md-2">    
		  <label for="unitPrice">Price</label>
		  <input type="text" class="form-control" id="unitPrice" value='<c:out value="${product.unitPrice }"/>' readonly="readonly">
	   </div>
	   <div class="form-group col-md-2">
	    <label for="stockAmount">Stock Amount</label>
	    <input type="text" class="form-control" id="stockAmount" value='<c:out value="${product.stockAmount }"/>' readonly="readonly">
	   </div>
	   	  <div class="form-group col-md-2">    
	   <label for="amountSold">Amount Sold</label>
	   <input type="text" class="form-control" id="amountSold" value='<c:out value="${product.amountSold }"/>' readonly="readonly">
	  </div>
    </div>  
    <div class="form-group">
      <div class="form-row">
	  <div class="form-group col-md-6">
	    <label>Description</label> 
	    <textarea class="form-control" rows="3" readonly="readonly"> <c:out value="${product.description }"/></textarea>
	  </div>
	  </div>
	</div> 
	  <div class="form-row">
	  <div class="form-group col-md-2">    
	   <label for="upload">Uploaded Files</label>
	   <input type="text" class="form-control" id="upload" value='<c:out value="${product.fileNum }"/>' readonly="readonly">
	  </div>
	<div class="form-group col-md-2">    
	   <label for="regDate">Registered Date</label>
	   <input type="text" class="form-control" id="regDate" value='<fmt:formatDate pattern = "yyyy-MM-dd" value="${product.regDate }"/>' readonly="readonly">
	</div>
	<div class="form-group col-md-2">    
	   <label for="updateDate">Update Date</label>
	   <input type="text" class="form-control" id="updateDate" value='<fmt:formatDate pattern = "yyyy-MM-dd" value="${product.updateDate }"/>' readonly="readonly">
	</div>
	
  </div>
 </div>

 <div class="form-row">
  <div class="form-group col-md-2">    
  <sec:authentication  property="principal" var="pinfo" />
<!-- security  보안............. -->
  <%--  <c:if test="${pinfo != null} "> --%>
    
  	<sec:authorize access="isAuthenticated()">   
  		<c:if test="${pinfo.member.userid eq product.userid }">
		<button type="submit" data-oper="modify" class="btn btn-info operBtn" id='modifyBtn' >수정</button>    
		<button type="submit" data-oper="delete" class="btn btn-danger operBtn" id='deleteBtn' >삭제</button> 
		</c:if> 
	</sec:authorize>
  </div>  
</div> 

	<input type="hidden" id="pno" name="pno" value='<c:out value="${product.pno}"/>'>  
	<input type="hidden" name="page" value='<c:out value="${cri.page}"/>'>          
	<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>  
</form> 




<div class="bigPictureWrapper"> 
	<div class='bigPicture'></div>
</div>

<div class='row'>  
	<div class='col-lg-12'>
		<div class='panel panel-default'>
			<div class='panel-heading'>    </div>
			<div class='panel-body'>
				<div class='uploadResult'>  upload files >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					<ul>
					</ul>
				</div>
			</div>
		</div>
		<div class='panel-foot'>   </div>
	</div>
</div> 

<br/>
<br/>


<div class="row">
	<div class="col-lg-12">
	
		<div class="panel panel-default">
			<div class="panel-heading">
			<i class="fa fa-comments fa-fw"></i> 댓글 리스트
	
			  <sec:authorize access="isAuthenticated()">
				<button id ='addReplyBtn' class='btn btn-success btn-xs pull-right'>신규 등록</button>
			  </sec:authorize> 
			</div>
		</div>		

		<div class='panel-body'>
			<ul class='chat'>
			</ul>
		</div>	
		<div class='panel-footer'> 
		</div>
	</div>
</div>





<div class='modal fade' id='myModal' tabindex='-1' role='dialog' area-labelledby='myModalLabel' area-hidden='true' >
	<div class='modal-dialog'>
		<div class='modal-content'>
			<div class='modal-header'>                                   
				<p class='modal-title' id='myModalLael' style='float: right;'>Review</p> 
				<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>
			</div>
			<div class='modal-body'>			
				<div class='form-group'>
					<label>No.</label>
					<input class='form-control' name='rno' type="text" placeholder="reply number" readonly="readonly">
				</div>
				<div class='form-group'>
					<label>댓글 내용</label>
					<input class='form-control' name='reply' type="text" placeholder="write a new reply here" >
				</div>
				<div class='form-group'>
					<label>작성자</label>
					<input class='form-control' name='replyer' type="text" placeholder="write your name"  readonly="readonly">
				</div>
				<div class='form-group'>
					<label>Reply Date</label>
					<input class='form-control' name='replyDate' value='<fmt:formatDate pattern = "yyyy-MM-dd" value=""/>' readonly="readonly">
				</div>				
			</div>
			<div class='modal-footer'>
				<button id='modalModBtn' type='button' class='btn btn-warning' data-dismiss='modal'>수정</button>
				<button id='modalRemoveBtn' type='button' class='btn btn-danger' data-dismiss='modal'>삭제</button>	
				<button id='modalRegisterBtn' type='button' class='btn btn-default' data-dismiss='modal'>등록</button>	
				<button id='modalCloseBtn' type='button' class='btn btn-default' data-dismiss='modal'>종료</button>			
			</div>
		</div> 
	</div> 
</div> 



<!-- ----- CSS ------------------------------------------------------------------------ -->



<style>


.uploadResult{
	width: 50%;
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




<!-- ----- SCRIPT ------------------------------------------------------------------------ -->

<script type="text/javascript" src="/resources/js/reply.js"></script>



<script>

//시큐리티
authentication(); 

function authentication(authen){	

	if (authen === null){
	// 	console.log("사용자 미인증");
		return;
	} 	
	if (authen !== null){	
	// 	console.log("사용자 인증 완료");
		checkLoginUser();
	} 		
} 




// 버튼 이벤트 : 수정, 삭제
let formObj = document.querySelector("form[role='form']");

document.querySelectorAll('.operBtn').forEach(btn => {
	
	btn.addEventListener("click", function(e){
		e.preventDefault();
		
		const operation = btn.getAttribute("data-oper");
		
		//console.log('★data-oper: ' + operation);
		
		if(operation === 'modify'){                                                   
			formObj.setAttribute("action", "/product/modify") 
		} else if (operation === 'delete'){
			formObj.setAttribute("action", "/product/delete")
			formObj.setAttribute("method", "post");
		}
		formObj.submit();
	}, false);
});



//back to list
document.getElementById("listBtn").onclick = function(){
	location.href='/product/list';		
} 




// 사진 보여주기
	
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


var pnoVal = '<c:out value="${product.pno}"/>';
var _replyUL = document.querySelector(".chat");

var csrfHeaderName = "${_csrf.headerName }";
var csrfTokenValue = "${_csrf.token }";





showList(1);

function showList(page){	

	replyService.getList( {pno:pnoVal, page:page||1}, function(replyCnt, list){
		
		
		console.log("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBAAAAAAAAAAAAAAAAAAAAA");
		
		console.log("replyCnt: " + replyCnt);
		console.log("list: " + list);
		
		if(page == -1){
			pageNum = Math.ceil(replyCnt / 5.0);
			showList(pageNum);   <!-- 해당 댓글이 있는 리뷰페이지 보여주기 -->
			return;
		}
		
		var str = "";
		if(list == null || list.length == 0){
			return;
		}
			
		for(var i=0, len = list.length||0; i< len; i++) {
			str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
			str += " <div><div class='header'><strong class='primary-font'>(#" + list[i].rno + ") " + list[i].replyer + "</strong>";
			str += " <small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
			str += " <p>" + list[i].reply + "</p></div></li>";
		}
		
		_replyUL.innerHTML = str;
			
		showReplyPage(replyCnt);
		//console.log("replyCnt :" + replyCnt);		
	});
}
	
		
var pageNum = 1;
var _replyPageFooter = document.querySelector('.panel-footer');

function showReplyPage(replyCnt) {
		

	var endNum = Math.ceil(pageNum / 5.0) * 5;
	var startNum = endNum - 4
	var prev = startNum != 1;
	var next = false;
	
	if(endNum * 5 >= replyCnt){
		endNum = Math.ceil(replyCnt / 5.0);
	}
	if(endNum * 5 < replyCnt){
		next = true;
	}
			
	var str = "<ul class='pagination pull-right'>";
	
	if(prev){
		str += "<li class='page-item'><a class='page-link' href='"+(startNum - 1)+"'>Previous</a></li>";
	}

	for(var i= startNum; i <= endNum; i++){
		var active = pageNum == i ? "active" : "" ;
		str += "<li class='page-item "+ active +"'><a class='page-link' href='"+ i +"'>"+ i +"</a></li>";
	}
	
	if(next){
		str += "<li class='page-item'><a class='page-link' href='"+ (endNum + 1) +"'>Next</a></li>";
	}
	
	str += "</ul></div>";
	//console.log("str.......:" + str);
		
	_replyPageFooter.innerHTML = str;


		
};


	



</script>


<%@ include file="../includes/footer.jsp" %> 
