<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Danh sách người dùng - Admin</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/fonts/themify-icons/themify-icons.css">
        <%@include file="components/javascript.jsp" %>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
        <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
        <style>
            .payment-method__item-name {
                font-size: 20px;
                padding-left: 20px;
            }

            .payment-method__item {
                display: flex;
                align-items: center;
                border: 1px solid #D9D9D9;
                border-radius: 16px;
                padding: 15px 20px;
                margin-bottom: 1rem;
            }



            .payment-method__item-icon-wrapper img {
                min-width: 100px;
                max-height: 100px;
                max-width: 100px;
                padding-left: 40px;
                image-rendering: -webkit-optimize-contrast;
            }
            .body_cartCompletion {
                font-family: sans-serif;
                background: linear-gradient(110deg, #fdfdbe 60%, #f9f86c 60%);
            }
            .groundy{
                font-family: sans-serif;
                background: linear-gradient(110deg, #fdfdbe 60%, #f9f86c 60%);
            }

            .circle {
                height: 10px;
                width: 10px;
                border: 50%;
            }
        </style>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />

    </head>

    <body class="sb-nav-fixed">
        <%@include file="components/account.jsp" %>
        <%@include file="components/manager-header.jsp" %>



        <div id="layoutSidenav">
            <%@include file="components/admin-left-dashboard.jsp" %>
            <div class="groundy" id="layoutSidenav_content">
                <main>
                    <div class="col-md-1">
                        <a href="AddUser.jsp"><button type="button" class="btn btn-danger " style="">Thêm</button></a>
                    </div>
                    <div class="container-fluid rounded row" style="margin-top: 3% !important; margin-bottom: 1% !important">
                        <div class="row-lg-2">
                            <label for="recordLength">Hiển thị <input type="number" id="recordLength" min="1" max="100" value="10" style="width: 55px"> bản ghi | </label>
                            <label for="genderFilter"> | Giới Tính:</label>
                            <select id="genderFilter">
                                <option value="">Tất cả</option>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                            </select>
                            <label for="roleFilter"> | Vai Trò:</label>
                            <select id="roleFilter">
                                <option value="">Tất cả</option>
                                <option value="Customer">Customer</option>
                                <option value="Marketing">Marketing</option>
                                <option value="Sale">Sale</option>
                                <option value="Sale manager">Sale manager</option>
                                <option value="admin">Admin</option>
                            </select>
                            <label for="statusFilter"> | Trạng thái:</label>
                            <select id="statusFilter">
                                <option value="">Tất cả</option>
                                <option value="Ẩn">Ẩn</option>
                                <option value="Hiện">Hiện</option>
                            </select>



                            <button id="applyBtn">Áp dụng</button>
                        </div>
                        <table class="table table-striped table-bordered" id="sortTable"  style="margin-top: 4%; background: #FFFFE0; ">
                            <thead  class="text-center">
                            <th>ID</th>
                            <th>Họ tên </th>
                            <th>Giới tính</th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                            <th>Vai trò</th>
                            <th>Trạng&nbsp;thái</th>
                            <th style="width: 12%">Tùy&nbsp;chọn&nbsp;</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${listUsers}" var="U">
                                    <tr class="text-center">
                                        <th scope="row">${U.user_Id}</th>
                                        <td>${U.full_Name}</td>

                                        <!--giới tính-->
                                        <c:if test="${U.gender == true}">
                                            <td style="text-align: center">Nam</td>
                                        </c:if>
                                        <c:if test="${U.gender == false}">
                                            <td style="text-align: center">Nữ</td>
                                        </c:if>


                                        <td>${U.email}</td>
                                        <td>${U.mobile}</td>
                                        <!--role-->
                                        <c:if test="${U.role_Id == 1}">
                                            <td style="text-align: center">Customer</td>
                                        </c:if>
                                        <c:if test="${U.role_Id == 2}">
                                            <td style="text-align: center">Marketing</td>
                                        </c:if>
                                        <c:if test="${U.role_Id == 3}">
                                            <td style="text-align: center">Sale</td>
                                        </c:if>
                                        <c:if test="${U.role_Id == 4}">
                                            <td style="text-align: center">Sale manager</td>
                                        </c:if>
                                        <c:if test="${U.role_Id == 5}">
                                            <td style="text-align: center">admin</td>
                                        </c:if>
                                        <c:if test="${U.status == true}">
                                            <td><img class="circle" src="images/active.png"><div hidden="">hiện</div></td>

                                        </c:if>
                                        <c:if test="${U.status != true}">
                                            <td><img class="circle" src="images/inactive.png"><div hidden="">ẩn</div></td>

                                        </c:if>
                                        <td style="width: 125px">
                                            <c:if test="${U.status == true}">
                                                <a class="btn btn-dark" href="update-status-user?status=0&userId=${U.user_Id}" role="button" title="Ẩn" style='font-size:10px'>
                                                    <i style='font-size:10px' class='fas far fa-eye-slash'>&#xf070;</i>
                                                </a>
                                            </c:if>
                                            <c:if test="${U.status != true}">
                                                <a class="btn btn-dark" href="update-status-user?status=1&userId=${U.user_Id}" role="button" title="Hiện" style='font-size:10px'>
                                                    <i style='font-size:10px' class='fas'>&#xf06e;</i>
                                                </a>
                                            </c:if>
                                            <a class="btn btn-dark" href="UpUser?user_Id=${U.user_Id}" role="button" title="Chi tiết" style='font-size:10px'>
                                                <i style='font-size:10px' class='fas'>&#xf044;</i>
                                            </a>
                                        </td>
<!--                                        <td style="width: 125px">

                                            <a href="UpUser?user_Id=${U.user_Id}" class="delete" title="Xóa" ><i
class="material-icons">&#xf06e;</i></a>
                                        </td>-->


                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </main>
                <!-- Footer-->
            </div>
        </div>
        <script>
            $(document).ready(function () {
                var table = $('#sortTable').DataTable({
                    "language": {
                        "decimal": "",
                        "emptyTable": "No data available in table",
                        "info": " _START_ đến _END_ của _TOTAL_ bản ghi",
                        "infoEmpty": "HIển thị 0 to 0 of 0 bản ghi",
                        "infoFiltered": "(kết quả từ _MAX_ tổng số bản ghi)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "",
                        "loadingRecords": "Loading...",
                        "processing": "",
                        "search": "Tìm kiếm:",
                        "zeroRecords": "Không tìm thấy kết quả nào",
                        "paginate": {
                            "first": "F",
                            "last": "L",
                            "next": "Sau",
                            "previous": "Trước"
                        },
                        "aria": {
                            "sortAscending": ": activate to sort column ascending",
                            "sortDescending": ": activate to sort column descending"
                        }
                    }
                });
                $('#applyBtn').on('click', function () {
                    var recordLength = parseInt($('#recordLength').val());
                    table.page.len(recordLength).draw();
                });
                $('#applyBtn').on('click', function () {
                    var recordLength = parseInt($('#recordLength').val());
                    table.page.len(recordLength).draw();

                    var genderFilter = $('#genderFilter').val();
                    table
                            .columns(2)
                            .search(genderFilter)
                            .draw();

                    var roleFilter = $('#roleFilter').val();
                    table
                            .columns(5)
                            .search(roleFilter)
                            .draw();
                    var statusFilter = $('#statusFilter').val();
                    table
                            .columns(6)
                            .search(statusFilter)
                            .draw();
                });

            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>


