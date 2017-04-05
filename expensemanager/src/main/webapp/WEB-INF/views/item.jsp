<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>Expense Manager</title>
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/css/sb-admin.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/font-awesome/css/font-awesome.min.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/css/plugins/morris.css' />" rel="stylesheet"></link>
    <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
          rel="stylesheet">

    <!-- Expense Manager CSS -->
    <link href="<c:url value='/css/expensemanager.css' />" rel="stylesheet"></link>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<c:url value="/app/" />">Expense Manager</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
                    class="caret"></b></a>
            <ul class="dropdown-menu message-dropdown">
                <li class="message-preview">
                    <a href="#">
                        <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>

                            <div class="media-body">
                                <h5 class="media-heading"><strong>John Smith</strong>
                                </h5>

                                <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>

                                <p>Lorem ipsum dolor sit amet, consectetur...</p>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-preview">
                    <a href="#">
                        <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>

                            <div class="media-body">
                                <h5 class="media-heading"><strong>John Smith</strong>
                                </h5>

                                <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>

                                <p>Lorem ipsum dolor sit amet, consectetur...</p>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-preview">
                    <a href="#">
                        <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>

                            <div class="media-body">
                                <h5 class="media-heading"><strong>John Smith</strong>
                                </h5>

                                <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>

                                <p>Lorem ipsum dolor sit amet, consectetur...</p>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-footer">
                    <a href="#">Read All New Messages</a>
                </li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b
                    class="caret"></b></a>
            <ul class="dropdown-menu alert-dropdown">
                <li>
                    <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">View All</a>
                </li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                ${sessionScope.userSession.user.displayName} <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                </li>
                <li>
                     <a href="<c:url value="/app/changepassword" />"><i class="fa fa-fw fa-user"></i>Change Password</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="<c:url value="/logout" />"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <li>
                <a href="<c:url value="/app/" />"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
            </li>
            <li class="active">
                <a href="<c:url value="/app/item/" />"><i class="fa fa-fw fa-edit"></i> Add Expense</a>
            </li>
            <li>
                <a href="<c:url value="/app/rent/" />"><i class="fa fa-fw fa-edit"></i> Pay Rent</a>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-table"></i>
                    Reports <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="demo" class="collapse">
                    <li>
                        <a href="<c:url value="/app/report/rent" />">Rent</a>
                    </li>
                    <li>
                        <a href="<c:url value="/app/report/expense" />">Expense</a>
                    </li>
                    <li>
                        <a href="<c:url value="/app/report/account" />">Account Summary</a>
                    </li>
                </ul>
            </li>
            <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#admin"><i class="fa fa-fw fa-dashboard"></i>
                                Admin <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="admin" class="collapse">
                                <li>
                                    <a href="<c:url value="/admin/user/" />">Customise User</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/admin/account/" />">Generate Monthly Bill</a>
                                </li>
                            </ul>
                        </li>


        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>

<div id="page-wrapper">

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    Expense
                </h2>
                <ol class="breadcrumb">
                    <li>
                        <i class="fa fa-dashboard"></i> <a href="<c:url value="/app/" />">Dashboard</a>
                    </li>
                    <li class="active">
                        <i class="fa fa-edit"></i> Expense
                    </li>
                </ol>
            </div>
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-lg-6">

                <form role="form" name="itemForm">

                    <div id="successDiv" class="alert alert-success">
                        <p id="success"></p>
                    </div>

                    <div id="errorDiv" class="alert alert-danger">
                        <p id="error"></p>
                    </div>
                    <input id="hiddenField" type="hidden" value=""/>

                    <div class="form-group">
                        <label>Item Name</label>
                        <input name="itemName" id="itemName" class="form-control" placeholder="Enter item">
                    </div>

                    <div class="form-group">
                        <label>Amount Paid</label>
                        <input name="price" id="price" class="form-control" placeholder="Enter price">
                    </div>

                    <div class="form-group">
                        <label>Select Category</label>
                        <select class="form-control" name="category" id="category">
                            <!--<option>Food</option>
                            <option>Electricity</option>
                            <option>Internet</option>
                            <option>Gas</option>
                            <option>Water</option>
                            <option>Newspaper</option>
                            <option>Maintanence</option>
                            <option>New Things</option>
                            <option>Other</option>-->
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Date</label>
                        <input name="purchasedDate" class="form-control" id="datepicker">
                    </div>
                    <div class="form-group">
                        <label>Comments</label>
                        <input class="form-control" placeholder="Enter comment" name="comment" id="comment">
                    </div>
                    <button type="submit" class="btn btn-primary" id="submitButton" disabled>Submit</button>
                    <button type="reset" class="btn btn-primary" id="resetbutton">Reset</button>
                    <br></br>

                </form>

            </div>

            <div class="col-lg-6">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped" id="itemTable">
                        <thead>
                        <tr>
                            <th>Sl No</th>
                            <th>Item Name</th>
                            <th>Amount</th>
                            <th>Paid Date</th>
                            <th>Comment</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>

            </div>

        </div>
    </div>
    <!-- /.row -->

</div>
<!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->
<nav class=" navbar-inverse navbar-bottom" style="line-height: 0.9;font-size:10px;color:#9d9d9d">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-footer">
        <br>
        <center>
            <strong>&copy; 2008 - 2017, Expense Manager . All rights reserved.</strong>
        </center>
    </div>
</nav>
</div>
<!-- /#wrapper -->

<!-- loading image -->

<div class="modal"><!-- Place at bottom of page --></div>

<!-- jQuery -->
<script src="<c:url value='/js/jquery.js' />"></script>


<!-- Bootstrap Core JavaScript -->
<script src="<c:url value='/js/bootstrap.min.js' />"></script>

<!-- Morris Charts JavaScript -->
<script src="<c:url value='/js/plugins/morris/raphael.min.js' />"></script>
<script src="<c:url value='/js/plugins/morris/morris.min.js' />"></script>
<script src="<c:url value='/js/plugins/morris/morris-data.js' />"></script>

<!-- Date Picker-->


<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<script src="<c:url value='/js/item.js' />"></script>
<script src="<c:url value='/js/expensemanager.js' />"></script>
<script src="<c:url value='/js/jquery.validate.min.js' />"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


</body>

</html>
