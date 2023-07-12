
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
        <title>Add - User</title>
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

                    <div class="container-fluid rounded row" style="margin-top: 3% !important; margin-bottom: 1% !important">
                        <div class="container">
                            <h4 class="mb-5 text-secondary">Thêm Người Dùng</h4>
                            <div class="row">

                                <form action="create-user" method="post">
                                    <div class="mb-3 col-md-12">
                                        <label for="fullname">Họ tên<span class="text-danger">*</span></label>
                                        <input  type="text" name="fullName" class="form-control" placeholder="Nhập họ tên" required>
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="pass">Mật khẩu<span class="text-danger">*</span></label>
                                        <input  type="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="Email">Email<span class="text-danger">*</span></label>
                                        <input  type="text" name="email" class="form-control" placeholder="Nhập email" required>
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="phone">Số điện thoại<span class="text-danger">*</span></label>
                                        <input  type="text" name="mobile" class="form-control" placeholder="Nhập số điện thoại" required>
                                    </div>
                                    <div class="mb-3 col-md-12">
                                        <label for="address" >Địa chỉ<span class="text-danger">*</span></label>
                                        <input type="text" name="address" class="form-control" placeholder="Nhập địa chỉ" required>
                                    </div>


                                    <div class="mb-3 col-md-12 input-group " style="margin-top: 35px">
                                        <label class="input-group-text" for="inputGroupSelect01" style="">Giới tính</label>
                                        <select name="sex_id" class="form-select" id="inputGroupSelect01">
                                            <option value="True">Nam</option>
                                            <option value="False">Nữ</option>
                                        </select>
                                    </div>



                                    <div class="mb-3 col-md-12 input-group "  style="margin-top: 35px">

                                        <!--                                        Vai trò
                                                                                <input class="" name="role_id" type="radio" value="1" required/> customer
                                                                                <input class="" name="role_id" type="radio" value="2" required/> marketing
                                                                                <input class="" name="role_id" type="radio" value="3" required/> sale
                                                                                <input class="" name="role_id" type="radio" value="4" required/> sale manager
                                                                                <input class="" name="role_id" type="radio" value="5" required/> admin-->
                                        <label class="input-group-text" for="inputGroupSelect02">Vai trò</label>
                                        <select name="role_id" class="form-select" id="inputGroupSelect02">
                                            <option value="1">customer</option>
                                            <option value="2">marketing</option>
                                            <option value="3">sale</option>
                                            <option value="4">sale manager</option>
                                            <option value="5">admin</option>


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
