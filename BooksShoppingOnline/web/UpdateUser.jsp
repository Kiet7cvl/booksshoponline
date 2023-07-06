<%-- 
    Document   : UpdateUser
    Created on : Jul 1, 2023, 7:18:18 AM
    Author     : MSI Bravo
--%>


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
        <title>Chi tiết USER</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>


        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

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
    </head>
    <body>
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
                        <div class="container">
                            <h4 class="mb-5 text-secondary">Chi tiết USER</h4>
                            <div class="row">

                                <form action="UpUser" method="post">
                                    <div class="mb-3 col-md-12">
                                        <label for="fullname">Avatar<span class="text-danger"></span></label>
                                        <div class="sq align-self-center "> <img class="img-fluid  my-auto align-self-center mr-2 mr-md-4 pl-0 p-0 m-0" src="${us.avatar}" width="135" height="135" /> </div>

                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="user_Id">ID<span class="text-danger"></span></label>
                                        <input value="${us.user_Id}"  type="text" name="user_Id" class="form-control" placeholder="id" required readonly>
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="fullname">Họ tên<span class="text-danger"></span></label>
                                        <input value="${us.full_Name}"  type="text" name="full_Name" class="form-control" placeholder="Nhập họ tên" required >
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="pass">Mật khẩu<span class="text-danger"></span></label>
                                        <input value="${us.password}"  type="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required >
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="Email">Email<span class="text-danger"></span></label>
                                        <input value="${us.email}"  type="text" name="email" class="form-control" placeholder="Nhập email" required >
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="mobile">Số điện thoại<span class="text-danger"></span></label>
                                        <input value="${us.mobile}" type="text" name="mobile" class="form-control" placeholder="Nhập số điện thoại" required >
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="address">Địa chỉ<span class="text-danger"></span></label>
                                        <input value="${us.address}" type="text" name="address" class="form-control" placeholder="Nhập địa chỉ" required>
                                    </div>



                                    <div class="mb-3 col-md-12 input-group" style="margin-top: 35px;">
                                        <label class="input-group-text" for="inputGroupSelect1" style="">Giới tính</label>
                                        <select name="sex_id" class="form-select" id="inputGroupSelect1">
                                            <option value="1" ${us.gender == "1" ? "selected" : ""}>Nam</option>
                                            <option value="0" ${us.gender == "0" ? "selected" : ""}>Nữ</option>
                                        </select>
                                    </div>
                                    <div class="mb-3 col-md-12 input-group" style="margin-top: 35px;">
                                        <label class="input-group-text" for="inputGroupSelect01" style="">Trạng thái</label>
                                        <select name="status" class="form-select" id="inputGroupSelect01">
                                            <option value="0" ${us.status == "0" ? "selected" : ""}>Ẩn</option>
                                            <option value="1" ${us.status == "1" ? "selected" : ""}>Hiện</option>
                                        </select>
                                    </div>



                                    <div class="mb-3 col-md-12 input-group" style="margin-top: 35px;">
                                        <label class="input-group-text" for="inputGroupSelect02">Vai trò</label>
                                        <select name="role_Id" class="form-select" id="inputGroupSelect02">
                                            <option value="1" ${us.role_Id == 1 ? 'selected' : ''}>customer</option>
                                            <option value="2" ${us.role_Id == 2 ? 'selected' : ''}>marketing</option>
                                            <option value="3" ${us.role_Id == 3 ? 'selected' : ''}>sale</option>
                                            <option value="4" ${us.role_Id == 4 ? 'selected' : ''}>sale manager</option>
                                            <option value="5" ${us.role_Id == 5 ? 'selected' : ''}>admin</option>
                                        </select>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary">Lưu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>
            </div>


        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    </body>
</html>
