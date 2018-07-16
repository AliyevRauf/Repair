<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reapir</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <%@ include file="header.jsp"%>

</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="/">Repair</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->

    <ul class="app-nav">
        <li class="app-nav__item"><i class="fa fa-user fa-lg "></i>&#160; <b>${username}</b></li>
        <li><a class="app-nav__item" href="/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
    </ul>
</header>
<!-- Sidebar menu-->
<%--<%@ include file="menu.jsp"%>--%>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="sidebar-box">
        <ul class="app-menu">
            <li><a class="app-menu__item active" href="<c:url value="/" />"><i class="app-menu__icon fa fa-building"></i><span class="app-menu__label">Branches</span></a></li>
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
            <h1><i class="fa fa-building"></i> Branches</h1>

        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">Branches</a></li>
        </ul>
    </div>
    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body">
                <%
                    String message = request.getParameter("title");
                    if (message != null && message.equals("error")) {
                %>

                <div class="alert alert-danger">
                    Error
                </div>
                <%
                }else if(message != null && message.equals("success")){
                %>
                <div class="alert alert-success">
                    Saved
                </div>
                <% } %>
                </br>

                <security:authorize access="hasAuthority('create_branch')">
                    <form action="/add_branch" method="get">
                        <input type="submit"  class="btn" value="Add"/>
                    </form>
                </security:authorize>
                </br>
                <table class="table table-hover table-bordered" id="sampleTable">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>&#160;</th>
                        <th>&#160;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="items" items="${list}">

                        <tr>
                            <td>${items.name}</td>
                            <td>${items.location}</td>
                            <td>${items.email}</td>
                            <td>${items.address}</td>
                            <td>
                                <form action="view_users/${items.branchId}" method="get">
                                    <input type="submit"  class="btn"
                                           value="View Users"/>
                                </form>
                            </td>
                            <td>
                                <form action="edit_branch/${items.branchId}" method="get">
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
<!-- Essential javascripts for application to work-->


<%@include file="footer.jsp"%>



</body>
</html>