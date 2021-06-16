<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>  


<head>
	<meta charset="utf-8"/>
	<title>매장별 리스트</title>
</head>

<body>
<!-- ----- HTML ----------------   ------------------------------------------------------- -->

<h1>매장 리스트</h1>
    

    
<div class="container-fluid">
	<div class="row body">
		<div class="col" id="map" style="width:500px;height:400px;"> 
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9d761a75095524bd7cc5bc806c014e08"></script>
		</div>
		
		<div class="col card" >
		<br/>
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col" style="width: 2%">#</th>
			      <th scope="col" style="width: 15%">상호명</th>
			      <th scope="col" style="width: 12%">주요 품목</th>
			      <th scope="col" style="width: 30%">주소</th>
			    </tr>
			  </thead>
			  <tbody>
				<c:forEach items="${storeList}" var="store">	   
					<tr class ='carrier'>
				      <th scope="row"> <c:out value="${store.sno }"/> </th>
				      <td class="storename" data-lng="<c:out value="${store.lng }"/>" data-name="<c:out value="${store.storeName }"/>"  data-lat="<c:out value="${store.lat }"/>" style="color:green;"> <b><c:out value="${store.storeName }"/></b> </td>
				      <td> <c:out value="${store.storeDescription }"/> </td>
				      <td> <c:out value="${store.address }"/> </td>
				    </tr>
			    </c:forEach>
			   </tbody>
			 </table>
			  
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
			
		</div> <!-- .card  -->		
	</div>	
</div>



<form class='actionForm' action="/store/storelist" method="get">
	<input type="hidden" name="page" value='<c:out value="${pageMaker.cri.page}" />'>             
	<input type="hidden" name="amount" value='<c:out value="${pageMaker.cri.amount}" />'>  
</form>   



<br/>
<br/>	



<!-- ----- JS ------------------------------------------------------------------------ -->
<script type="text/javascript" src="/resources/js/map.js"></script>
<script type="text/javascript">


authentication();

function authentication(authen){	
	if (authen === null){
		return;
	} 	
	if (authen !== null){
		checkLoginUser();
	} 		
} 


//pagination 
var _actionForm = document.querySelector(".actionForm");

document.querySelectorAll(".page-item a").forEach(
	(atag) => {			
		var target = atag;
			
		target.addEventListener("click", function(e){					
			e.preventDefault();

			var value = target.getAttribute("href");				
				
			_actionForm.querySelector("input[name='page']").setAttribute("value", value);				
			_actionForm.submit();				
		},false);				
	}			
);


//로케이션 ajax 값
showLocation(1);

function showLocation(page){	

	
}






// 지도 ----------------------------------------------------------------------------------------------------------


var bitcamp = new kakao.maps.LatLng(37.503199, 127.0245861);

var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	mapOption = { 
	    center: bitcamp, // 지도의 중심좌표
	    level: 8 // 지도의 확대 레벨
	};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


const jsonList = JSON.parse('${jsonStr}');
//console.log(jsonList);

const _storeNames = document.querySelectorAll(".storename");
//console.log(_storeNames);


<!-- 마커 생성 ---------------------------------------------------------------------- -->
for (var i=0; i < jsonList.length; i++) {	
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    map: map, // 마커를 표시할 지도
	    //position: new kakao.maps.LatLng(jsonList[k].lat, jsonList[k].lng)
	});

	// 마커에 표시할 인포윈도우를 생성합니다 
	var infowindow = new kakao.maps.InfoWindow({
		content: jsonList[i].storeName // 인포윈도우에 표시할 내용
	});

	// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	// 이벤트 리스너로는 클로저를 만들어 등록합니다 
	// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	
	
	<!-- 매장 클릭 이벤트 ---------------------------------------------------------------- -->
	for(let j = 0; j < _storeNames.length; j++){
		
		const target = _storeNames[j];
		//console.log(target)
		
		target.addEventListener("click", function() { 
			
			const lat = target.getAttribute("data-lat");
			const lng = target.getAttribute("data-lng");
			const name = target.getAttribute("data-name");
			console.log("이름------------------------"+name);
			
			const targetPosition = new kakao.maps.LatLng(lat, lng);	
			
			
			//1. 기존 마커 다 삭제...
	        //marker.position(null);
			//map.setMarkers(null); 
			//marker.setMap(null);	
			
			
			//2. 클릭한 곳만 마커 보여주기(기존 마커 다 삭제...)
			
			
			
			//3. 마커 생성 및 이동
	
			map.panTo(targetPosition);
			
			//마커 생성
			var marker = new kakao.maps.Marker({
			    map: map, // 마커를 표시할 지도
			    position: targetPosition
			});

			
			//인포윈도우 
			var infowindow = new kakao.maps.InfoWindow({
				content: name
			});
			
			
		}, false);	
	} <!-- end for -->
	<!-- ---------------------------------------------------------------- -->


} <!-- end for -->




//인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
	return function() {
	    infowindow.open(map, marker);
	};
}

//인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
	return function() {
	    infowindow.close();
	};
}











</script>

</body>


<!-- ----- CSS ------------------------------------------------------------------------ -->

<style>
	.table {
	  font-size: 12px;
	}
</style>



<%@ include file="../includes/footer.jsp" %> 