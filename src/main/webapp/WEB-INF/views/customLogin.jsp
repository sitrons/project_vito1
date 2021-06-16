<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ include file="./includes/header.jsp" %> 

<html>
<head>
<title>vito. 로그인</title>
</head>
<body>



<br/>
<!-- ---------------------------- html --------------------------------------------------- -->

<br/>

<!-- ---------------------------- css --------------------------------------------------- -->
<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div><img src="/resources/img/login.jpg"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">
	                                        <c:out value="${error }" />
											<c:out value="${logout }" />
										</h1>
                                    </div>
             <form class="user" method='post' action='/login'><!-- ----------------------------------------------------------------------------- -->
                                        <div class="form-group">
                                            <input type="username" name='username' class="form-control form-control-user" id="username" aria-describedby="username" placeholder="Enter username...">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name='password' class="form-control form-control-user" id="password" placeholder="password">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label checkbox" name='remember-me' for="customCheck">Remember Me</label>
                                            </div>
                                        </div>
                                        <button type='submit' class="btn btn-success btn-user btn-block">
                                            Login
                                        </button>
                                        
         
                                        <input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' />	
                      </form><!-- ----------------------------------------------------------------------------- -->
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.html">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="register.html">Create an Account!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>


<!-- ---------------------------- js --------------------------------------------------- -->
<script src="/resources/vendor/jquery/jquery.min.js"></script> 
<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script> 
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="  crossorigin="anonymous"></script>
  
 <script>
  $(".btn-success").on("click", function(e){
  	e.preventDefault();
  	
  	$("form").submit();
  });
</script>



  
</body>
</html>


<%@ include file="./includes/footer.jsp" %>  