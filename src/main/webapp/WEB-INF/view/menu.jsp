<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 16/06/2018
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="sidebar-box">
        <ul class="app-menu">
            <li><a class="app-menu__item active" href="<c:url value="index.jsp" />"><i class="app-menu__icon fa fa-building"></i><span class="app-menu__label">Branches</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="user.jsp" />"><i class="app-menu__icon fa fa-user"></i><span class="app-menu__label">Users</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="order.jsp" />"><i class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="contractor.jsp" />"><i class="app-menu__icon fa fa-handshake-o"></i><span class="app-menu__label">Contractors</span></a></li>
            <li><a class="app-menu__item" href="<c:url value="/roles" />"><i class="app-menu__icon fa fa-id-card-o"></i><span class="app-menu__label">Rolles</span></a></li>
        </ul>
    </div>
</aside>
