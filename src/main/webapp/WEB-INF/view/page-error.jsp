<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta name="description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">

    <title>Error Page</title>
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
        <li><a class="app-nav__item" href="page-login.html"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
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
            <li><a class="app-menu__item" href="<c:url value="/" />"><i class="app-menu__icon fa fa-building"></i><span class="app-menu__label">Branches</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="user.jsp" />"><i class="app-menu__icon fa fa-user"></i><span class="app-menu__label">Users</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="order.jsp" />"><i class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="contractor.jsp" />"><i class="app-menu__icon fa fa-handshake-o"></i><span class="app-menu__label">Contractors</span></a></li>
            <li><a class="app-menu__item active" href="<c:url value="/roles" />"><i class="app-menu__icon fa fa-id-card-o"></i><span class="app-menu__label">Rolles</span></a></li>
        </ul>
    </div>
</aside>
<main class="app-content">
    <div class="page-error tile">
        <h1><i class="fa fa-exclamation-circle"></i> Error 404: Page not found</h1>
        <p>The page you have requested is not found.</p>
        <p><a class="btn btn-primary" href="javascript:window.history.back();">Go Back</a></p>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>
</html>