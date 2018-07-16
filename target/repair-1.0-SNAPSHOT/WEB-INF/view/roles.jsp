<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>

    <title>Roles</title>
    <%@ include file="header.jsp"%>
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="index.html">Repair</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
        <!--<li class="app-search">-->
        <!--<input class="app-search__input" type="search" placeholder="Search">-->
        <!--<button class="app-search__button"><i class="fa fa-search"></i></button>-->
        <!--</li>-->
        <!-- User Menu-->
        <!--<li><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>-->
        <!--<ul class="dropdown-menu settings-menu dropdown-menu-right">-->
        <li><a class="app-nav__item" href="/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
        <!--</ul>-->
        <!--</li>-->
    </ul>
</header>
<!-- Sidebar menu-->
<%--<%@ include file="menu.jsp"%>--%>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="sidebar-box">
        <ul class="app-menu">
            <li><a class="app-menu__item " href="<c:url value="/" />"><i class="app-menu__icon fa fa-building"></i><span class="app-menu__label">Branches</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="/users" />"><i class="app-menu__icon fa fa-user"></i><span class="app-menu__label">Users</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="/orders " />"><i class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
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
            <li class="breadcrumb-item"><a href="#">Roles</a></li>
            <li class="breadcrumb-item"><a href="#">Add Roles</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                </br>
                <form action="/add_roles" method="get">
                    <input type="submit"  class="btn" value="Add"/>
                </form>
                </br>
                <table class="table table-hover table-bordered" id="sampleTable">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Create User</th>
                        <th>Create Branch</th>
                        <th>Create Order</th>
                        <th>Users Orders</th>
                        <th>&#160;</th>
                    </tr>
                    </thead>
                    <tbody class="table-hover">

                    <c:forEach var="item" items="${list}" varStatus="loop">
                        <tr>
                        <td>${item.roleName}</td>
                        <td>${item.creatUser}</td>
                        <td>${item.createBranch}</td>
                        <td>${item.createOrder}</td>
                        <td>${item.usersOrders}</td>
                        <td>
                            <form action="edit_role/${item.id}" method="get">
                                <input type="submit"  class="btn"
                                       value="Edit"/>
                            </form>
                        </td>
                    </tr>
                   </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>

</body>
</html>
