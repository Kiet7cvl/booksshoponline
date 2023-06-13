
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách Slider</title>
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <%@include file="components/javascript.jsp" %>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script src="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"></script>

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
            .mtop {
                margin-top: 6%;
            }
            .title-order {
                display: flex;
                justify-content: center;
                color: red;
            }
        </style>
    </head>
    <body  class="sb-nav-fixed">
        <%@include file="components/account.jsp" %>
        <%@include file="components/manager-header.jsp" %>
        <div id="layoutSidenav">
            <div class="groundy" id="layoutSidenav_content">
                <%@include file="components/MKT-left-board.jsp" %>
                <h2 class="mtop title-order" >Danh sách Slider</h2>
                <div class="container mtop" style="width:80%">
                    <div class="col-md-2 text-center">
                                <select class="dropdown-font-new" aria-label="Default select example" onchange="location = this.value;">
                                    <option value="slider-list?" ${categoryId == 0 ? "Selected" : ""}>
                                        Trạng thái
                                    </option>
                                    <option value="slider-list?status=1" ${status eq "1" ? "Selected" : ""}>
                                        Hiện
                                    </option>
                                    <option value="slider-list?status=0" ${status eq "0" ? "Selected" : ""}>
                                        Ẩn
                                    </option>
                                </select>
                    </div>  
                    <table class="table table-striped table-bordered" id="sortTable">
                        <thead>
                            <tr>
                                <th>Slider_ID</th>
                                <th>Slider_Title</th>
                                <th>Slider_Image</th>
                                <th>Backlink</th>
                                <th>Status</th>
                                <th>Update_Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items ="${SliderList}" var="c">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>${c.slider_title}</td>

                                    <td><img  src="${c.slider_image}" width="200px"></td></td>


                                    <td>${c.backlink}</td>
                                    <c:if test="${c.status == true}">
                                        <td class="text-center"><img class="circle" src="images/active.png"></td>
                                        </c:if>
                                        <c:if test="${c.status == false}">
                                        <td class="text-center"><img class="circle" src="images/inactive.png"></td>
                                        </c:if>
                                    <td class="text-center" style="width: 125px">
                                        <a class="btn btn-danger" href="slider-detail?sliderId=${c.id}" role="button" style='font-size:10px'>
                                            <i style='font-size:10px' class='fas'>&#xf044;</i>
                                        </a>

                                        <c:if test="${c.status eq 'True'}">
                                            <a href="change-status-slider?status=0&sliderid=${c.id}" class="btn btn-dark " role="button" aria-pressed="true" style="font-size: 10px">
                                                <i style='font-size:10px' class='fas far fa-eye-slash'>&#xf070;</i></a>
                                        </c:if>

                                        <c:if test="${c.status eq 'False'}">
                                            <a href="change-status-slider?status=1&sliderid=${c.id}" class="btn btn-dark" role="button" aria-pressed="true" style="font-size: 10px">
                                                <i style='font-size:10px' class='fas'>&#xf06e;</i></a>
                                        </c:if>
                                    </td>


                                </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="mtop"></div>
            </div>
        </div>

    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#sortTable').DataTable({
                "language": {
                    "decimal": "",
                    "emptyTable": "No data available in table",
                    "info": " _START_ đến _END_ của _TOTAL_ bản ghi",
                    "infoEmpty": "HIển thị 0 bản ghi",
                    "infoFiltered": "(kết quả từ _MAX_ tổng số bản ghi)",
                    "infoPostFix": "",
                    "thousands": ",",
                    "lengthMenu": "Hiển thị _MENU_ bản ghi",
                    "loadingRecords": "Loading...",
                    "processing": "",
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
                },
                "paging": false
               
            });
        });
    </script>

</body>
</html>

