<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Login</title>
</head>
<body>
<section class="material-half-bg">
    <div class="cover"></div>
</section>
<section class="login-content">
    <div class="logo">
        <h1>Repair</h1>
    </div>
    <div class="login-box">
        <c:if test="${param.fail != null}">
            <div class="alert alert-danger" role="alert">
               FAILED
            </div>
        </c:if>

        <form class="login-form" action="/login" method="post" id="signInForm">

            <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>SIGN IN</h3>

            <div class="form-group">
                <label class="control-label">BRANCH:</label>
                <div class="col-13">
                    <select class="form-control" id="combo_role1" autofocus>
                        <option selected>Seçin</option>
                       <c:forEach items="${branchList}" var="item">
                           <option value="${item.branchId}">${item.name}</option>
                       </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">USERNAME</label>
                <input class="form-control" id="email" type="text" placeholder="Email">
                <input type="hidden" name="username" id="username">
            </div>
            <div class="form-group">
                <label class="control-label">PASSWORD</label>
                <input class="form-control" name="password" type="password" placeholder="Password">
            </div>
            </br>
            </br>
            <!--<div class="form-group">-->
            <!--<div class="utility">-->
            <!--<p class="semibold-text mb-2"><a href="#" data-toggle="flip">Forgot Password ?</a></p>-->
            <!--</div>-->
            <!--</div>-->
            <div class="form-group btn-container">
                <button id="loginBtn" class="btn btn-primary btn-block"><i class="fa fa-sign-in fa-lg fa-fw"></i>SIGN IN</button>
            </div>

        </form>
        <form class="forget-form" action="index.jsp">
            <h3 class="login-head"><i class="fa fa-lg fa-fw fa-lock"></i>Forgot Password ?</h3>
            <div class="form-group">
                <label class="control-label">EMAIL</label>
                <input class="form-control" type="text" placeholder="Email">
            </div>
            <div class="form-group btn-container">
                <button class="btn btn-primary btn-block"><i class="fa fa-unlock fa-lg fa-fw"></i>RESET</button>
            </div>
            <div class="form-group mt-3">
                <p class="semibold-text mb-0"><a href="#" data-toggle="flip"><i class="fa fa-angle-left fa-fw"></i> Back to Login</a></p>
            </div>
        </form>
    </div>
</section>
<%@include file="footer.jsp"%>

<script type="text/javascript">
    // Login Page Flipbox control
    $('.login-content [data-toggle="flip"]').click(function() {
        $('.login-box').toggleClass('flipped');
        return false;
    });

    var combo_role1 = $("#combo_role1");
    var email = $("#email");

    var username = $("#username");

    $("#loginBtn").on("click", function () {
        username.val(combo_role1.val() + "," +email.val());
        $("#signInForm").submit();
    });

</script>
</body>
</html>