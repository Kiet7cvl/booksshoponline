
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đơn hàng của tôi</title>
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <%@include file="components/javascript.jsp" %>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script src="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"></script>
        <style>
            .mtop {
                margin-top: 6%;
            }
            .title-order {
                display: flex;
                justify-content: center;
                color: red;
            }
        </style>

        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />

    </head>
    <body>
        <%@include file="components/header.jsp" %>
        <div style="display: flex;display: flex;
             flex-direction: row;
             align-content: stretch;
             justify-content: center;
             align-items: baseline;
             flex-wrap: nowrap;">
            <!-- Side widgets-->
            <div  style="padding: 0;margin: 0;    padding-right: 32px;flex: 0 0 auto;
                  width: 11.66666667%;margin-top: 100px;   position: relative;top: 66.5px;">
                <!-- Search widget-->
                <%@include file="components/sider.jsp" %>
            </div>

            <div class="container mtop" style="width:80%;padding: 0; margin: 0; ">
                <h2 class="mtop title-order" style="    margin-top: 100px;" >Danh sách các đơn hàng</h2>
                <div class="row-lg-2" style="    position: relative;z-index: 10;
    top: 42px;width: 40%">
                    <label for="recordLength">Hiển thị <input type="number" id="recordLength" min="1" max="100" value="10" style="    width: 45px;"> đơn hàng</label>
                    
                    <!--    <label for="startDate">Từ ngày:</label>
                        <input type="date" id="startDate">
                        <label for="endDate">Đến ngày:</label>
                        <input type="date" id="endDate">-->
                    <button id="applyBtn">Áp dụng</button>
                </div>

                <table class="table table-striped table-bordered" id="sortTable" style="display: flow">

                    <thead>
                        <tr>
                            <th>OrderID</th>
                            <th>Ngày&nbspmua&nbsphàng</th>
                            <th>Sản&nbspphẩm</th>
                            <th>Tổng&nbspchi&nbspphí</th>
                            <th>Tình&nbsptrạng</th>
<!--                            <th></th>-->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items ="${listMyOrder}" var="c">
                            <tr>
                                <td><a href="order-detail?orderId=${c.orderID}">
                                        ${c.orderID}</a></td>
                                <td>${c.date}</td>
                                <c:if test="${c.countProduct != 0}">
                                    <td>${c.fullNameFirstProduct} và ${c.countProduct} sản phẩm khác</td>
                                </c:if>
                                <c:if test="${c.countProduct == 0}">
                                    <td>${c.fullNameFirstProduct}</td>
                                </c:if>
                                <td>${c.total_cost}</td>
                                <td>${c.status_order_name}</td>
<!--                                <td>
                                    <c:if test="${c.status_order_name eq 'Đang gửi'}">
                                        <div class="row">
                                            <a style="width: auto;margin-left: 12px;" href="cancel-order?order_id=${c.orderID}" class="btn btn-danger btn-lg active" role="button" aria-pressed="true" >Hủy</a>
                                        </div>

                                    </c:if>
                                </td>-->
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="mtop"></div>
        <%@include file="components/footer.jsp" %>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>

        <script>
            $(document).ready(function () {
                var table = $('#sortTable').DataTable({
                    "language": {
                        "decimal": "",
                        "emptyTable": "Không có đơn hàng nào ",
                        "info": " _START_ đến _END_ của _TOTAL_ đơn hàng",
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
                    var startDate = $('#startDate').val();
                    var endDate = $('#endDate').val();

                    table
                            .columns(1)
                            .search(startDate, true, false)
                            .draw();

                    table
                            .columns(1)
                            .search(endDate, true, false)
                            .draw();
                });
            });

        </script>
    </body>
</html>
