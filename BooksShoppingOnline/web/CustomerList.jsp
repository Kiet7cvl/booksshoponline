<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Danh sách khách hàng</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
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

            .table-sortable th {
                cursor: pointer;
            }

            .table-sortable .th-sort-asc::after {
                content: "\25b4";
            }

            .table-sortable .th-sort-desc::after {
                content: "\25be";
            }

            .table-sortable .th-sort-asc::after,
            .table-sortable .th-sort-desc::after {
                margin-left: 5px;
            }

            .table-sortable .th-sort-asc,
            .table-sortable .th-sort-desc {
                background: rgba(0, 0, 0, 0.1);
            }

        </style>
         <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />
    </head>
    <body class="sb-nav-fixed">
        <!-- Notification Customer -->
        <c:if test="${notification !=null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert" style="position: fixed; z-index: 15 ; margin-left: 50%; margin-top: 4%">
                <strong>${notification}</strong>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <%@include file="components/account.jsp" %>
        <%@include file="components/manager-header.jsp" %>
        <div id="layoutSidenav">
            <%@include file="components/MKT-left-board.jsp" %>
            <div class="groundy" id="layoutSidenav_content">


                <main>
                    <div class="container-fluid rounded row" style="margin-top: 1% !important; margin-bottom: 1% !important">
                       <div class="col-md-1">
                            <a href="AddCustomer.jsp"><button type="button" class="btn btn-danger " style="">Thêm</button></a>
                        </div>
                        <div class="col-md-2">
                            <select class="dropdown-font-new float-left" style="width: 100%" aria-label="Default select example" onchange="location = this.value;"> Sắp xếp
                                <option value="customer-list?${historyKey}${historyStatus}&type=desc" ${type == "desc" ? "Selected" : ""}>
                                    Mới Nhất
                                </option>
                                <option value="customer-list?${historyKey}${historyStatus}" ${type == null ? "Selected" : ""}>
                                    Cũ Nhất
                                </option>
                                <option value="customer-list?${historyKey}${historyStatus}&type=desc&value=customer_name" ${type == "desc" && value == "customer_name" ? "Selected" : ""}>
                                    Họ tên giảm dần
                                </option>
                                <option value="customer-list?${historyKey}${historyStatus}&value=customer_name" ${type == null && value == "customer_name" ? "Selected" : ""}>
                                    Họ tên tăng dần
                                </option>
                                <option value="customer-list?${historyKey}${historyStatus}&type=desc&value=customer_email" ${type == "desc" && value == "customer_email" ? "Selected" : ""}>
                                    Email giảm dần
                                </option>
                                <option value="customer-list?${historyKey}${historyStatus}&value=customer_email" ${type == null && value == "customer_email" ? "Selected" : ""}>
                                    Email tăng dần
                                </option>
                                <option value="customer-list?${historyKey}${historyStatus}&type=desc&value=customer_mobile" ${type == "desc" && value == "customer_mobile" ? "Selected" : ""}>
                                    SĐT giảm dần
                                </option>
                                <option value="customer-list?${historyKey}${historyStatus}&value=customer_mobile" ${type == null && value == "customer_mobile" ? "Selected" : ""}>
                                    SĐT tăng dần
                                </option>
                            </select>
                        </div>
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-2">
                            <select class="dropdown-font-new float-right" aria-label="Default select example" onchange="location = this.value;"> Trạng thái
                                <option value="customer-list?${historyKey}${historyValue}" ${status == null ? "Selected" : ""}>
                                    Trạng thái
                                </option>
                                <option value="customer-list?${historyKey}${historyValue}&status=1" ${status == 1 ? "Selected" : ""}>
                                    Hiện
                                </option>
                                <option value="customer-list?${historyKey}${historyValue}&status=0" ${status == 0 ? "Selected" : ""}>
                                    Ẩn
                                </option>

                            </select>
                        </div>
                        <div class="col-md-2 text-center">
                            <form action="customer-list">
                                <input type="text" name="key" value="${key}" placeholder="Tìm kiếm khách hàng" class="filter-search__control" >
                                <button type="submit" class="btn btn-outline-danger" href="#" role="button">
                                    <i style='font-size:15px' class='fas'>&#xf002;</i>
                                </button>
                            </form>
                        </div>
                    </div>
                                
                    <div class="row-lg-2">
                        <label for="recordLength">Độ dài hiển thị bản ghi:</label>
                        <input type="number" id="recordLength" min="1" max="100" value="10">
                        <button id="applyBtn">Áp dụng</button>
                    </div>            
                    <div class="container rounded bg-white mt-5 mb-5">
                        <table class="table table-sortable" id="sortTable">
                            <thead  class="text-center">
                                  
                                    <th>ID</th>
                                    <th>Họ&nbsp;và&nbsp;tên</th>
                                    <th>Email</th>
                                    <th>Số&nbsp;điện&nbsp;thoại</th>
                                    
                                    <th>Trạng&nbsp;thái</th>
                                    <th>Tùy&nbsp;chọn&nbsp;</th>
                                
                            </thead>
                            <tbody>
                                <c:forEach items="${listCustomer}" var="c">
                                    <tr  class="text-center">
                                        <th scope="row">${c.customer_id}</th>
                                        <td>${c.customer_name}</td>
                                        <td>${c.customer_email}</td>
                                        <td>${c.customer_mobile}</td>
                                       
                                        <c:if test="${c.status == true}">
                                            <td><img class="circle" src="images/active.png"></td>
                                            </c:if>
                                            <c:if test="${c.status != true}">
                                            <td><img class="circle" src="images/inactive.png"></td>
                                            </c:if>

                                        <td style="width: 125px">
                                            <a class="btn btn-danger" href="customer-detail?cid=${c.customer_id}" role="button" style='font-size:10px'>
                                                <i style='font-size:10px' class='fas'>&#xf044;</i>
                                            </a>
                                            <c:if test="${c.status == true}">
                                                <a class="btn btn-dark" href="change-status-customer?status=0&customerId=${c.customer_id}" role="button" style='font-size:10px'>
                                                    <i style='font-size:10px' class='fas far fa-eye-slash'>&#xf070;</i>
                                                </a>
                                            </c:if>
                                            <c:if test="${c.status != true}">
                                                <a class="btn btn-dark" href="change-status-customer?status=1&customerId=${c.customer_id}" role="button" style='font-size:10px'>
                                                    <i style='font-size:10px' class='fas'>&#xf06e;</i>
                                                </a>
                                            </c:if>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
<!--        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>-->
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    
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
            });

        </script>
        
    </body>
</html>


