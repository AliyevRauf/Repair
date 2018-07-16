<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 23:27
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
    <title>Reapir</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <%@ include file="header.jsp" %>

    <script type="text/javascript">

        var deletedItems = [];

        function deleteRow(row) {
            var i = row.parentNode.parentNode.rowIndex;
            var x = row.parentNode.parentNode;
            document.getElementById('sampleTable').deleteRow(i);
            var branch = row.parentNode.parentNode.attributes[0];
            var branchId = parseInt(branch.nodeValue);
            branchIdArray.pop(branch);

            var buID = x.getAttribute("branchuserid");

            if (buID != -1) {
                deletedItems.push({buid: buID});
            }
        }

        function changeRole(row) {
            var i = row.parentNode.parentNode;
            i.setAttribute("roleid", row.value);
        }

    </script>
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
    <div class="sidebar-box">
        <ul class="app-menu">
            <li><a class="app-menu__item " href="<c:url value="/" />"><i class="app-menu__icon fa fa-building"></i><span
                    class="app-menu__label">Branches</span></a></li>
            <li><a class="app-menu__item active" href="<c:url value="/users" />"><i
                    class="app-menu__icon fa fa-user"></i><span class="app-menu__label">Users</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="/orders " />"><i
                    class="app-menu__icon fa fa-cart-plus"></i><span class="app-menu__label">Orders</span></a></li>
            <li><a class="app-menu__item " href="<c:url value="/contractors" />"><i
                    class="app-menu__icon fa fa-handshake-o"></i><span class="app-menu__label">Contractors</span></a>
            </li>
            <security:authorize access="hasAuthority('view_role')">
                <li><a class="app-menu__item" href="<c:url value="/roles" />"><i
                        class="app-menu__icon fa fa-id-card-o"></i><span class="app-menu__label">Rolles</span></a></li>
            </security:authorize>
        </ul>
    </div>
</aside>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-plus-square"></i> Edit User</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item">Users</li>
            <li class="breadcrumb-item"><a href="#">Edit User</a></li>
        </ul>
    </div>
    <div class="row">

        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <form class="form-horizontal">
                        <div class="form-group row">
                            <label class="control-label col-md-3">Name:</label>
                            <div class="col-md-3">
                                <input class="form-control" type="text" id="name" value="${users.name}">
                                <input class="form-control" type="hidden" id="userID" value="${users.userId}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Surname</label>
                            <div class="col-md-3">
                                <input class="form-control" type="text" id="surname" value="${users.surname}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Email</label>
                            <div class="col-md-3">
                                <input class="form-control" type="email" id="email" value="${users.email}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Telefon nömrəsi</label>
                            <div class="col-md-3">
                                <input class="form-control" type="text" id="tel" maxlength="17"
                                       value="${users.tel}">
                                <input id="j_phone" name="phone" type="hidden"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Password</label>
                            <div class="col-md-3">
                                <input class="form-control" type="password" placeholder="Password" id="password">
                            </div>
                        </div>
                        <div class="form-group" style="margin-bottom: 0px;">
                            <div class="col-md-4 offset-8" style="padding-right: 0px;">
                                <input id="branchName" class="form-control col-md-8 d-inline" style="margin-left: 77px;"
                                       type="text" placeholder="branches">
                                <input id="branchId" class="form-control col-md-8 d-inline" style="margin-left: 77px;"
                                       type="hidden" placeholder="branches">
                                <button id="addBranchId" type="button" class="btn btn-default"
                                        style="margin-bottom: 6px"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <table class="table table-hover table-bordered col-md-6" id="sampleTable">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Role</th>
                                <th>&#160;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="items" items="${users.branches}">

                                <tr branchId="${items.branchId}" roleId="${items.roleId}"
                                    branchUserID="${items.branchUserId}">
                                    <td>${items.name}</td>
                                    <td>
                                        <select class="form-control" id="combo_role1" autofocus
                                                onchange="changeRole(this)">
                                            <c:forEach var="item" items="${roleList}">

                                                <option value="${item.roleId}"  <c:if
                                                        test="${item.roleId == items.roleId }"> selected
                                                </c:if> >${item.roleName}
                                                </option>
                                            </c:forEach>

                                        </select>
                                    </td>
                                    <td>

                                        <input type="submit" class="btn" value="Delete" onclick="deleteRow(this)"/>
                                    </td>
                                    </td>
                                </tr>

                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="tile-footer">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-3">
                                    <button id="btnAddUser" class="btn btn-primary" type="button"><i
                                            class="fa fa-fw fa-lg fa-check-circle"></i>Edit
                                    </button>
                                    &nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="/users"><i
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

<script>

    var name = '';
    var surName = '';
    var email;
    var tel;
    var password = '';
    var userId='';
    var branchIdArray = [];
    $(document).ready(function () {

        var $input = $("#branchName");
        var $input_Id = $("#branchId");
        var getBranch = '<c:url value="/get_branch" />';
        $input.autocomplete({
            // This shows the min length of charcters that must be typed before the autocomplete looks for a match.
            minLength: 1,
            enable: true,
            source: function (request, callback) {
                $input_Id.val(-1);
                $.ajax({
                    url: getBranch,
                    type: "POST",
                    data: {
                        branchName: $input.val()
                    },
                    success: function (data) {
                        //var elements = JSON.parse(data);
                        console.log(data);
                        callback(data);
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

        $("#tel").val('+994').bind('keydown', function (e) {
            var value = $(this).val().replace(/\s/g, '');
            $("#j_phone").val(value);
            console.log($("#j_phone").val(value));
            $("#tel").css("border-color", "#ced4da");
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

        function myCallBack(call) {
            $.ajax({
                url: "/get_roles_combo",
                type: "POST",
                success: function (data) {
                    call(data);
                },
                error: function (data, status, er) {
                    console.log(data + "_" + status + "_" + er);
                }
            });
        }

        $('#addBranchId').click(function () {

            myCallBack(function (data) {
                var myOptions = '';
                var defaultRoleId = 0;
                $.each(data, function (index, value) {
                    if (index == 0) {
                        defaultRoleId = value.roleId;
                    }
                    myOptions = myOptions + '<option value="' + value.roleId + '" >' + value.roleName + '</option>';
                })
                var myselect = '<select class="form-control" id="combo_role1" autofocus="" onchange="changeRole(this)">';

                myselect = myselect + myOptions + '</select>';

                var branchName = $('#branchName').val()
                var branchId = $('#branchId').val();

                var tdName = '<td>' + branchName + '</td>';
                var tdRole = '<td>' + myselect + '</td>';
                var tdDelete = '<td>' + '<input type="button" name="delete" class="btn btnDelete"  value="Delete" onclick="deleteRow(this)">' + '</td>';
                var tr = "<tr branchId=" + branchId + " roleId = " + defaultRoleId + " branchUserID ='-1' >" + tdName + tdRole + tdDelete + '</tr>';

                for (var i = 0; i < branchIdArray.length; i++) {
                    if (branchIdArray[i] == branchId) {
                        $('#branchName').css("border-color", "red");
                        setTimeout(function () {
                            $('#branchName').css("border-color", "")
                        }, 3000);
                        return
                    }
                }

                if ($('#branchName').val() != "") {
                    $('#sampleTable').append(tr);
                    branchIdArray.push(branchId);
                    $('#branchName').val("");
                }

                for (var i = 0; i < usersIdArray.length; i++) {
                    if (usersIdArray[i] == userId) {
                        $('#userId').css("border-color", "red");
                        setTimeout(function () {
                            $('#userId').css("border-color", "")
                        }, 3000);
                        return
                    }
                }

                if ($('#userId').val() != "") {
                    $('#sampleTable').append(tr);
                    usersIdArray.push(userId);
                    $('#userId').val("");
                }
            });

        });


        $('#btnAddUser').click(function () {
            name = $('#name').val();
            surName = $('#surname').val();
            email = $('#email').val();
            tel = $('#j_phone').val();
            password = $('#password').val();
            userId=$('#userID').val();

            var x = document.getElementById("sampleTable").rows;
            var branches = '';
            var roles = '';
            var buid = '';
            var deleted = '';


            for (var i = 0; i < deletedItems.length; i++) {
                var c = 'aa';
                if (deleted == '') {
                    c = '';
                }
                deleted = deleted + c + deletedItems[i].buid;
            }


            for (var i = 1; i < x.length; i++) {
                var c = 'aa';
                if (buid == '') {
                    c = '';
                }
                buid = buid + c + x[i].getAttribute('branchuserid');
                roles = roles + c + x[i].getAttribute('roleId');
                branches = branches + c + x[i].getAttribute('branchId');
            }

            $.ajax({
                url: "/edit_user",
                type: "POST",
                data: {
                    userId:userId,
                    branches: branches,
                    roles: roles,
                    buid:buid,
                    deleted: deleted,
                    name: name,
                    surname: surName,
                    email: email,
                    j_phone: tel,
                    password: password
                },
                success: function (data) {

                    console.log("sent");

                    location.href = "/users?code=" + data.code + "&title=" + data.title;


                }, error: function (data, status, er) {
                    console.log(data + "_" + status + "_" + er);
                }
            });


        })


    });


</script>

</body>
</html>
