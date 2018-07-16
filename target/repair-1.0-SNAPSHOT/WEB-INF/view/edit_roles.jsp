<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 23:28
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
    <title>New Roles</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="../css/main.css">

    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="../index.html">Repair</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
        <!--<li class="app-search">-->
        <!--<input class="app-search__input" type="search" placeholder="Search">-->
        <!--<button class="app-search__button"><i class="fa fa-search"></i></button>-->
        <!--</li>-->
        <%@ include file="header.jsp"%>
        <!-- User Menu-->
        <li><a class="app-nav__item" href="/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="sidebar-box">
        <ul class="app-menu">
            <li><a class="app-menu__item" href="<c:url value="/" />"><i class="app-menu__icon fa fa-building"></i><span class="app-menu__label">Branches</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="/users" />"><i class="app-menu__icon fa fa-user"></i><span class="app-menu__label">Users</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="/orders" />"><i class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="/contractors" />"><i class="app-menu__icon fa fa-handshake-o"></i><span class="app-menu__label">Contractors</span></a></li>
            <security:authorize access="hasAuthority('view_role')">
                <li><a class="app-menu__item" href="<c:url value="/roles" />"><i class="app-menu__icon fa fa-id-card-o"></i><span class="app-menu__label">Rolles</span></a></li>
            </security:authorize>
        </ul>
    </div>
</aside>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-id-card-o"></i> Add Roles</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-id-card-o"></i></li>
            <li class="breadcrumb-item">Roles</li>
            <li class="breadcrumb-item"><a href="#">Add Roles</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <c:if test="${saved == 'success'}">
                        <div class="alert alert-success">
                            Saved
                        </div>
                    </c:if>
                    <c:if test="${saved == 'error'}">
                        <div class="alert alert-danger">
                            Error
                        </div>
                    </c:if>

                    <form class="form-horizontal" action="/edit_role/${role.id}" method="post" name="role_form" id="role_form" onsubmit="return validateform()">
                        <div class="form-group row">
                            <div class="col-12">
                                <label class="control-label" style="margin-right: 43px;">Role adı: </label>
                                <input class="form-control d-inline-block col-3" type="text" placeholder="" id="role_name" name="role_name" value="${role.roleName}">
                                <span class="error" style="color: red; font-size: 16px ; display: none" id="error_role">This field is required</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-12">
                                <label class="control-label" style="margin-right: 21px;">Create User: </label>
                                <select class="form-control d-inline-block col-3" id="combo_role2" name="create_user">
                                    <option value="own_branch" <c:if test="${role.creatUser == 'own_branch' }">selected</c:if> >Öz filalı</option>
                                    <option value="all_branch" <c:if test="${role.creatUser == 'all_branch' }">selected</c:if> >Bütün filallar</option>
                                    <option value=""  <c:if test="${role.creatUser == 'Nothing' }">selected</c:if> >Heç biri</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group row">
                            <div class="custom-checkbox col-12">
                                <label class="control-label">Create Branch:</label>
                                <input type="checkbox" name="create_branch" class="form-control d-inline-block col-1"  <c:if test="${role.createBranch=='true_branch'}"> checked="checked"</c:if>/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="custom-checkbox col-12">
                                <label class="control-label" style="margin-right: 6px;">Create Order: </label>
                                <input type="checkbox" name="create_order" class="form-control d-inline-block col-1" <c:if test="${role.createOrder == 'true_order'}">checked="checked" </c:if> />
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12">
                                <label class="control-label" style="margin-right: 6px;">Users Orders: </label>
                                <select class="form-control d-inline-block col-3" id="combo_role1" name="view_orders">
                                    <option value="own_order" <c:if test="${role.usersOrders == 'own_order' }"> selected</c:if> >Özünü</option>
                                    <option value="other_order" <c:if test="${role.usersOrders == 'other_order' }"> selected</c:if> >Filaldakı əməkdaşlar</option>
                                    <option value="all_order" <c:if test="${role.usersOrders == 'all_order' }"> selected</c:if>>Bütün filaldakı əməkdaşlar</option>
                                </select>
                            </div>
                        </div>
                        <div class="tile-footer">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-3">
                                    <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>Edit</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="/roles"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</main>

<%@include file="footer.jsp"%>
<script type="text/javascript">
//    $("form#role_form").submit(function( event ) {
//
//        if ( $( "#role_name" ).val() == '' ) {
//            $( "span#error_role" ).show();
//           $( "#role_name" ).css("border-color","red");
//            event.preventDefault();
//        }else{
//            $( "span#error_role" ).hide();
//        }
//
//    });

function validateform(){

    if ($( "#role_name" ).val() == ''){
        $( "span#error_role" ).show();
          $( "#role_name" ).css("border-color","red");
        return false;
    }
    return true;
}

$(function () {

});

</script>
</body>
</html>