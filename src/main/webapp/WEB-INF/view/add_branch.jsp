<%--
  Created by IntelliJ IDEA.
  User: Rauf
  Date: 12/06/2018
  Time: 23:29
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
    <title>Reapir</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <%@ include file="header.jsp"%>
    <script type="text/javascript">


        function deleteRow(row) {
            var i = row.parentNode.parentNode.rowIndex;
            document.getElementById('sampleTable').deleteRow(i);

            var user = row.parentNode.parentNode.attributes[0]
            console.log(user);
            var userId=parseInt(user.nodeValue);

            usersIdArray.pop(userId);


        }
        function changeRole(row) {
            var i = row.parentNode.parentNode;
            i.setAttribute("roleid", row.value);
        }



    </script>
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
        <li><a class="app-nav__item" href="/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
    </ul>
</header>
<!-- Sidebar menu-->
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
            <h1><i class="fa fa-plus-square"></i> Add Branch</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item">Branches</li>
            <li class="breadcrumb-item"><a href="#">Add Branch</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <form class="form-horizontal">
                        <div class="form-group row">
                            <label class="control-label col-md-3">Branch name:</label>
                            <div class="col-md-3">
                                <input class="form-control" id="branchNameId" type="text" placeholder="Branch name">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Email</label>
                            <div class="col-md-3">
                                <input class="form-control" type="email" id="emailId" placeholder="Email address">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3" >City</label>
                            <div class="col-md-3">
                                <input class="form-control" type="text" placeholder="city" id="cityId">
                                <input class="form-control" type="hidden" id="city_hidden_Id" name="city_hidden_Id"/>
                            </div>
                        </div>
                        <div class="form-group row"  hidden="hidden" id="region">
                            <label class="control-label col-md-3">Region</label>
                            <div class="col-md-3 d-block">
                                <input class="form-control" type="text" placeholder="Region" id="regionId">
                                <input class="form-control" type="hidden" id="region_hidden_Id" name="region_hidden_Id" value="0"/>
                            </div>

                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Address</label>
                            <div class="col-md-4">
                                <textarea class="form-control" rows="4" placeholder="Address" id="addressId"></textarea>
                            </div>
                        </div>

                        <div class="form-group" style="margin-bottom: 0px;">
                            <div class="col-md-4 offset-8" style="padding-right: 0px;">
                                <input class="form-control col-md-8 d-inline" style="margin-left: 77px;" type="text" placeholder="users"  id="userId">
                                <input class="form-control col-md-8 d-inline"  style="margin-left: 77px;" type="hidden" placeholder="users"  id="userIdHidden">
                                <button   id="userBtnId" type="button" class="btn btn-default" style="margin-bottom: 6px"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <table class="table table-hover table-bordered col-md-6" id="sampleTable">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Role</th>
                                <th>&#160;</th>
                            </tr>
                            </thead>
                            <tbody>


                            </tbody>
                        </table>
                        <div class="tile-footer">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-3">
                                    <button class="btn btn-primary" id="branchBtnId" type="button"><i class="fa fa-fw fa-lg fa-check-circle"></i>Add</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="/"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
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
<script type="text/javascript">
    var branchName='';
    var email='';
    var cityId;
    var regionId;
    var address='';
    var usersIdArray =[];
    $(function () {
        $('#branchBtnId').click(function () {
             branchName=$('#branchNameId').val();
             email=$('#emailId').val();
             city=$('#city_hidden_Id').val();
             regionId=$('#region_hidden_Id').val();
             address=$('#addressId').val();

//            $('#sampleTable >tbody>tr').each(function(){
//                var tr =$(this)[0];
//                var cells=tr.cells;
////                data["userId"]=cells[0];
//            });


        });



//
//        function myCallBack(call) {
//            $.ajax({
//                url: "/get_roles_combo",
//                type: "POST",
//                success: function (data) {
//                    call(data);
//                },
//                error: function (data, status, er) {
//                    console.log(data + "_" + status + "_" + er);
//                }
//            });
//        }

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

        $('#userBtnId').click(function () {
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
                var user = $('#userId').val();
                var userArray = user.split(' ');
                var name = userArray[0];
                var lastName = userArray[1];
                var userId = $('#userIdHidden').val();
                var tdName = '<td>' + name + '</td>';
                var tdLastName = '<td>' + lastName + '</td>';
                var tdRole = '<td>' + myselect + '</td>';
                var tdDelete = '<td>' + '<input type="button" name="delete" class="btn btnDelete"  value="Delete" onclick="deleteRow(this)">' + '</td>';
                var tr = "<tr userId=" + userId + "  roleId = " + defaultRoleId + " branchUserID ='-1' >" + tdName + tdLastName + tdRole + tdDelete + '</tr>';

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
        })


        $('#branchBtnId').click(function () {
            var x = document.getElementById("sampleTable").rows;
            var roles = '';
            var users = '';

            for (var i = 1; i < x.length; i++) {
                var c = '--';
                if (users == '') {
                    c = '';
                }
                roles = roles + c + x[i].getAttribute('roleId');
                users = users + c + x[i].getAttribute('userId');
            }
            $.ajax({
                url:  "/add_branch",
                type: "POST",
                data: {
                    users:users,
                    roles:roles,
                    branchNameId:branchName,
                    emailId:email,
                    city_hidden_Id:city,
                    region_hidden_Id:regionId,
                    addressId:address
                },
                success: function (data) {

                console.log("sent");

                    location.href="/?code=" + data.code + "&title=" + data.title;


                }, error: function (data, status, er) {
                    console.log(data + "_" + status + "_" + er);
                }
            });



        })



    });
$(document ).ready(function(){

    var $input =  $("#userId");
    var $input_Id= $("#userIdHidden");
    var getUser = '<c:url value="/get_users" />';
    $input.autocomplete({
        // This shows the min length of charcters that must be typed before the autocomplete looks for a match.
        minLength: 1,
        enable: true,
        source: function (request, callback) {
           $input_Id.val(-1);
            $.ajax({
                url: getUser,
                type: "POST",
                data: {
                    userId: $input.val()
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

    var $inputCity =  $("#cityId");
    var $inputCityId= $("#city_hidden_Id");
    var getCity = '<c:url value="/get_city" />';
    $inputCity.autocomplete({
        // This shows the min length of charcters that must be typed before the autocomplete looks for a match.
        minLength: 1,
        enable: true,
        source: function (request, callback) {
           $input_Id.val(-1);
            $.ajax({
                url: getCity,
                type: "POST",
                data: {
                    cityId: $inputCity.val()
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

            $inputCity.val(ui.item.label);
            $inputCityId.val(ui.item.value);
            return false;
        },
        focus: function (event, ui) {

            $inputCity.val(ui.item.label);
            $inputCityId.val(ui.item.value);
            return false;
        }
    });

 var $inputRegion =  $("#regionId");
    var $inputRegionId= $("#region_hidden_Id");
    var getRegion = '<c:url value="/get_region" />';
    $inputRegion.autocomplete({
        // This shows the min length of charcters that must be typed before the autocomplete looks for a match.
        minLength: 1,
        enable: true,
        source: function (request, callback) {
            $inputRegionId.val("0");
            $.ajax({
                url: getRegion,
                type: "POST",
                data: {
                    regionId: $inputRegion.val()
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

            $inputRegion.val(ui.item.label);
            $inputRegionId.val(ui.item.value);
            return false;
        },
        focus: function (event, ui) {

            $inputRegion.val(ui.item.label);
            $inputRegionId.val(ui.item.value);
            return false;
        }
    });

    $("#cityId").val('');
    $("#regionId").val('');

    $("#cityId").keyup(function(){
        if($("#cityId").val() == 'Baki'){
            $("#region").removeAttr("hidden","hidden");
        }else{
            $("#regionId").val('');
            $("#region_hidden_Id").val(0);
            $("#region").attr("hidden","hidden");
        }

    });


});

</script>

</html>
