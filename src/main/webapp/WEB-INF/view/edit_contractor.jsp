<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <!-- Open Graph Meta-->
    <title>Add Contractor</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <%@ include file="header.jsp"%>
</head>
<body class="app sidebar-mini rtl">
<header class="app-header"><a class="app-header__logo" href="<c:url value="/" />">Reapir</a>
    <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <ul class="app-nav">
        <li><a class="app-nav__item" href="/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <ul class="app-menu">
        <li><a class="app-menu__item" href="<c:url value="/" />"><i class="app-menu__icon fa fa-building"></i><span class="app-menu__label">Branches</span></a></li>
        <li><a class="app-menu__item " href="/users"><i class="app-menu__icon fa fa-user"></i><span class="app-menu__label">Users</span></a></li>
        <li><a class="app-menu__item " href="/orders"><i class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
        <li><a class="app-menu__item active" href="<c:url value="/contractors" />"><i class="app-menu__icon fa fa-handshake-o"></i><span class="app-menu__label">Contractors</span></a></li>
        <security:authorize access="hasAuthority('view_role')">
            <li><a class="app-menu__item" href="<c:url value="/roles" />"><i class="app-menu__icon fa fa-id-card-o"></i><span class="app-menu__label">Rolles</span></a></li>
        </security:authorize>
    </ul>
</aside>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-handshake-o"></i> Contractor</h1>

        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-handshake-o fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#"> Contractor</a></li>
            <li class="breadcrumb-item"><a href="#">Add Contractor</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <div class="col"></div>
                    <form class="form-horizontal" onsubmit=" return validateForm(this)" action="/edit_contractor/${list.id}" method="post" >
                        <div class="form-group row">
                            <label class="control-label col-md-3 d-inline-block">Company name:</label>
                            <div class="col-md-3">
                                <input class="form-control" type="text" placeholder="Company name" name="company_name" id="company_name" value="${list.companyName}">
                                <span class="error" style="color: red; font-size: 16px ; display: none" id="error_contractor">This field is required</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Address:</label>
                            <div class="col-md-4">
                                <textarea class="form-control" rows="4" placeholder="" name="address">${list.address}</textarea>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="control-label col-md-3" >Telefon:</label>
                            <div class="col-md-3">
                                <input type="tel" class="text-input form-control glyphicon-phone" name="phone"  id="phone_login" value="${list.phone}" maxlength="17"/>
                                <span class="error" style="color: red; font-size: 16px ; display: none" id="error_phone">This field is required</span>
                            </div>
                        </div>
                        <div class="tile-footer">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-3">
                                    <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>Edit</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="/contractors"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>

    </div>
</main>
<!-- Essential javascripts for application to work-->
<%@include file="footer.jsp"%>

<script>
    function validateForm() {
        var flag = true;

        if($("#company_name").val() == ''){
            $("span#error_contractor").show();
            $("#company_name").focus();
            $("#company_name").css("border-color","red");

            flag = false;
        }

        if($("#phone_login").val().length < 5 ){
            $("span#error_phone").show();
            $("#phone_login").focus();
            $("#phone_login").css("border-color","red");

            flag = false;
        }

        var phoneTrim = $('#phone_login').val().replace(/\s/g, '');
        if(!/^(\+994)(50|55|51|70|77|60)(\d{7})$/.test(phoneTrim)){

            $("span#error_phone").show();
            $("#phone_login").focus();
            $("#phone_login").css("border-color","red");
            flag = false;
        }

        return flag;
    }

    $("#phone_login").val('+994').bind('keydown', function (e) {

        $("#phone_login").css("border-color","#ced4da");
        $("span#error_phone").hide();

        if ($(this).val().length <= 4) {
            $(this).val('+994 ');

            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                (e.keyCode >= 35 && e.keyCode <= 40)) {

                return;

            } else {
                if ($(this).val().length == 7 || $(this).val().length == 11 || $(this).val().length == 14) {
                    $(this).val($(this).val() + ' ');
                }
            }
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        } else {
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                (e.keyCode >= 35 && e.keyCode <= 40)) {

                return;

            } else {
                if ($(this).val().length == 7 || $(this).val().length == 11 || $(this).val().length == 14) {
                    $(this).val($(this).val() + ' ');
                }
            }
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        }
    });

    $("#company_name").keydown(function(){
        $("span#error_contractor").hide();
        $("#company_name").css("border-color","#ced4da");
    });

</script>

</body>
</html>
