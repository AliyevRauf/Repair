<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>

    <!-- Open Graph Meta-->
    <title>Conractor</title>
    <meta charset="utf-8">
    <!-- Main CSS-->
    <%@ include file="header.jsp"%>
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
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
        <li><a class="app-menu__item " href="<c:url value="/" />"><i class="app-menu__icon fa fa-building"></i><span class="app-menu__label">Branches</span></a></li>
        <li><a class="app-menu__item " href="<c:url value="/users" />"><i class="app-menu__icon fa fa-user"></i><span class="app-menu__label">Users</span></a></li>
        <li><a class="app-menu__item " href="<c:url value="/orders " />"><i class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
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
        </ul>
    </div>
    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body">
                </br>
                <form action="<c:url value="/add_conractor" />" method="get">
                    <input type="submit"  class="btn" value="Add"/>
                </form>
                </br>
                <table class="table table-hover table-bordered" id="sampleTable">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>&#160;</th>
                    </tr>
                    </thead>
                    <tbody class="hovered">
                    <c:forEach var="item" items="${list}">
                        <tr>
                            <td>${item.companyName}</td>
                            <td>${item.address}</td>
                            <td>${item.phone}</td>
                            <td>
                                <form action="edit_contractor/${item.id}" method="get">
                                    <input type="submit"  class="btn" value="Edit"/>
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
