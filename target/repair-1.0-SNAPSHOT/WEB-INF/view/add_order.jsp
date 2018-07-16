<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <title>Reapir</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Main CSS-->
    <%@ include file="header.jsp" %>
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="/">Repair</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
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
        <li><a class="app-menu__item active" href="<c:url value="/orders " />"><i class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
        <li><a class="app-menu__item " href="<c:url value="/contractors" />"><i class="app-menu__icon fa fa-handshake-o"></i><span class="app-menu__label">Contractors</span></a></li>
        <security:authorize access="hasAuthority('view_role')">
            <li><a class="app-menu__item" href="<c:url value="/roles" />"><i class="app-menu__icon fa fa-id-card-o"></i><span class="app-menu__label">Rolles</span></a></li>
        </security:authorize>
    </ul>
</aside>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-plus-square"></i> Add Order</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item">Order</li>
            <li class="breadcrumb-item"><a href="#">Add Order</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <form class="form-horizontal" action="/add_order" method="post">
                        <div class="form-group row">
                            <label class="control-label col-3">Pin code:</label>
                            <div class="col-3">
                                <input class="form-control" type="text" maxlength="7" placeholder="" name="pin">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-3 ">Device:</label>
                            <div class="col-3">
                                <input class="form-control"  id="device" />
                                <input class="form-control" type="hidden" id="device_Id" name="device_Id"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-3 ">Problems:</label>
                            <div class="col-3 ">
                                <select id="multiselect" multiple="multiple" name="problems">
                                    <c:forEach var="items" items="${list}">
                                        <option value="${items.id}">${items.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br>
                            <div class="col-3">
                                <input class="form-control" type="text" placeholder="descriptions" name="info">
                            </div>

                        </div>
                        <div class="form-group row">
                            <label class="control-label col-3"></label>

                        </div>

                        <div class="tile-footer">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-3">
                                    <button class="btn btn-primary" type="submit"><i
                                            class="fa fa-fw fa-lg fa-check-circle"></i>Add
                                    </button>
                                    &nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="/orders" ><i
                                        class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
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

<%@include file="footer.jsp" %>

<!-- Page specific javascripts-->
<!-- Google analytics script-->
<script type="text/javascript">
    $(document).ready(function () {
        $('#multiselect').multiselect({
            buttonWidth: '290px'
        });

        var $input = $("#device");
        var $input_Id=$("#device_Id");
        var addOrder = '<c:url value="/get_devices" />';
        $input.autocomplete({
            // This shows the min length of charcters that must be typed before the autocomplete looks for a match.
            minLength: 1,
            enable: true,
            source: function (request, callback) {
                $input_Id.val(-1);
                $.ajax({
                    url: addOrder,
                    type: "POST",
                    data: {
                        device: $input.val()
                    },
                    success: function (data) {
                        console.log(data);
                        var elements = JSON.parse(data);
                        console.log(elements);
                        callback(elements);
                    }, error: function (data, status, er) {
                        console.log(data + "_" + status + "_" + er);
                    }
                });
            },
            // Once a value in the drop down list is selected, do the following:
            select: function (event, ui) {
                $input.val(ui.item.label);
                $input_Id.val(ui.item.value);
                return false;
            },
            focus: function (event, ui) {
                $input.val(ui.item.label);
                $input_Id.val(ui.item.value);
                return false;
            }
        });
    });
</script>


</body>
</html>