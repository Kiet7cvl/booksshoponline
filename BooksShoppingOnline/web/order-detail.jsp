
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <%@include file="components/javascript.jsp" %>
        <style>
            body {
                min-height: 100vh;
                background-size: cover;
                font-family: 'Lato', sans-serif;
                color: rgba(116, 116, 116, 0.667);
                background: linear-gradient(140deg , #a75555 , 50% , #0089ff);

            }
            .container-fluid-detail {
                margin-top: 200px ;
            }

            p {
                font-size: 14px;
                margin-bottom: 7px;

            }

            .small {
                letter-spacing: 0.5px !important;
            }

            .card-1 {
                box-shadow: 2px 2px 10px 0px rgb(190, 108, 170);
            }

            hr {
                background-color: rgba(248, 248, 248, 0.667);
            }


            .bold {
                font-weight: 500;
            }

            .change-color {
                color: #AB47BC !important;
            }

            .card-2 {
                box-shadow: 1px 1px 3px 0px rgb(112, 115, 139);

            }

            .fa-circle.active {
                font-size: 8px;
                color: #AB47BC;
            }

            .fa-circle {
                font-size: 8px;
                color: #aaa;
            }

            .rounded {
                border-radius: 2.25rem !important;
            }


            .progress-bar {
                background-color: #AB47BC !important;
            }


            .progress {
                height: 5px !important;
                margin-bottom: 0;
            }

            .invoice {
                position: relative;
                top: -8px;
            }

            .Glasses {
                position: relative;
                top: -12px !important;
            }

            .card-footer {
                background-color: #6c757d;
                color: #fff;
            }

            .cart-footer h2 {
                color: rgb(78, 0, 92);
                letter-spacing: 2px !important;
            }

            .display-3 {
                font-weight: 500 !important;
            }

            @media (max-width: 479px) {
                .invoice {
                    position: relative;
                    top: 7px;
                }

                .border-line {
                    border-right: 0px solid rgb(226, 206, 226) !important;
                }

            }

            @media (max-width: 700px) {

                .cart-footer h2 {
                    color: rgb(78, 0, 92);
                    font-size: 17px;
                }

                .display-3 {
                    font-size: 28px;
                    font-weight: 500 !important;
                }
            }

            .card-footer small {
                letter-spacing: 7px !important;
                font-size: 12px;
            }

            .border-line {
                border-right: 1px solid rgb(226, 206, 226)
            }

            .cus-fontsize {
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <%@include file="components/header.jsp" %>
<!--<div style="display: flex;display: flex;
             flex-direction: row;
             align-content: stretch;
             justify-content: center;
             align-items: baseline;
             flex-wrap: nowrap;">-->
            <!-- Side widgets-->
            <div>
        <div class="container-fluid-detail my-5  d-flex  justify-content-center" >
            <div style="padding: 0;margin: 0;    padding-right: 32px;flex: 0 0 auto;
    width: 11.66666667%;margin-top: 100px;">
                <!-- Search widget-->
                <%@include file="components/sider.jsp" %>
            </div>
            <div class="card card-1">
                <div class="card-header bg-white">
                    <div class="media flex-sm-row flex-column-reverse justify-content-between  ">
                        <div class="col my-auto"> <h4 class="mb-0" style="display: flex; justify-content: center">Thanks for your orders</h4> </div>
                        <div class="col-auto text-center  my-auto pl-0 pt-sm-4">  <p class="mb-4 pt-0 Glasses"></p>  </div>
                        <!--                       <img class="img-fluid my-auto align-items-center mb-0 pt-3"  src="" width="115" height="115">-->
                    </div>
                </div>
                <div class="card-body">
                    <div class="row justify-content-between mb-3">
                        <!--                        <div class="col-auto"> <h6 class="color-1 mb-0 change-color">Receipt</h6> </div>-->
                        <!--                        <div class="col-auto  "> <small>Order Id: 10</small> </div>-->
                    </div>
                    <c:forEach items = "${Order_Detail}" var="s">
                        <div class="row">
                            <div class="col">
                                <div class="card card-2">
                                    <div class="card-body">
                                        <div class="media">
                                            <div class="sq align-self-center "> <img class="img-fluid  my-auto align-self-center mr-2 mr-md-4 pl-0 p-0 m-0" src="${s.product_image}" width="135" height="135" /> </div>
                                            <div class="media-body my-auto text-right">
                                                <div class="row  my-auto flex-column flex-md-row">
                                                    <div class="col my-auto"> <small class="mb-0">${s.product_name}</small>  </div>
<!--                                                    <div class="col my-auto"> <small class="mb-0">${s.product_image}</small>  </div>-->
                                                    <div class="col-auto my-auto"> <h6 class="mb-0">${s.category_name}</h6></div>
                                                    <div class="col my-auto"> <h6 class="mb-0">Giá: ${s.product_price}</h6></div>
                                                    <div class="col my-auto"> <h6 class="mb-0">Số lượng: ${s.quantity}</h6></div>
                                                    <div class="col my-auto"> <h6 class="mb-0">Tổng chi phí: ${s.product_price*s.quantity}</h6></div> 

                                                </div>
                                            </div>
                                        </div>
                                        <hr class="my-3 ">
                                        <div class="row">
                                            <div>
                                                <a href="list-detail?productId=${s.product_id}&categoryId=${s.category_id}" class="btn btn-primary btn-lg active" role="button" aria-pressed="true" style="width: 49%">Đánh giá</a>
                                                <a href="addcart?productId=${s.product_id}" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true" style="width: 49%">Mua lại</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <c:forEach items="${listMyOrderinDetail}" var="k">

                        
                        <div class="row invoice mt-4 " style="padding: 10px">

                            <div class="col">

                                <p class="mb-1 text-dark cus-fontsize"><b>Order Details</b></p>
                                <p class="mb-1 cus-fontsize"> OrderId: ${k.orderID}</p>
                                <p class="mb-1 cus-fontsize">Ngày mua hàng : ${k.date}</p>
                                <p class="mb-1 cus-fontsize">Trạng thái : ${k.status_order_name}</p>
                                <c:if test="${k.status_order_name eq 'Đang gửi'}">
                                        <div class="row">
                                            <a style="width: auto;margin-left: 12px;" href="cancel-order?order_id=${k.orderID}" class="btn btn-danger btn-lg active" role="button" aria-pressed="true" >Hủy</a>
                                        </div>

                                    </c:if></td>
                            </div>

                            <div class="col">
                                <p class="mb-1 cus-fontsize text-dark "><b>Thông tin Người nhận</b> </p>
                                <p class="mb-1 cus-fontsize">Họ tên : ${k.fullName}</p>
                                
                                <c:if test = "${k.gender ==1}">
                                    <p class="mb-1 cus-fontsize">Giới tính : Nam</p>
                                    </c:if>
                                <c:if test = "${k.gender ==0}">
                                    <p class="mb-1 cus-fontsize">Giới tính : Nữ</p>
                                    </c:if>
                                <p class="mb-1 cus-fontsize">Email : ${k.email}</p>
                                <p class="mb-1 cus-fontsize">Số điện thoại : ${k.mobile}</p>
                                <p class="mb-1 cus-fontsize">Địa chỉ : ${k.address}</p>
                            </div>
                        </div>



                    </div>
                    <div class="card-footer" >
                        <div class="jumbotron-fluid">
                            <div class="row justify-content-between ">
                                <div class="col-sm-auto col-auto my-auto"></div>
                                <!--<img class="img-fluid my-auto align-self-center " src="" width="115" height="115">-->
                                <div class="col-auto my-auto "><h2 class="mb-0 font-weight-bold">TOTAL PAID</h2></div>
                                <div class="col-auto my-auto ml-auto"><h1 class="display-3 ">${k.total_cost}đ</h1></div>
                            </div>
                            <div class="row mb-3 mt-3 mt-md-0">
                                <div > <small style="letter-spacing: 5px !important;font-size: 15px;" class="text-white">Cảm ơn vì đã ủng hộ chúng tôi<3</small></div>

                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
 </div>
        <%@include file="components/footer.jsp" %>
    </body>
</html>
