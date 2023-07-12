
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/fonts/themify-icons/themify-icons.css">
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <style>
            * {
                margin: 0px;
                padding: 0px;
                box-sizing: border-box;
            }
            body{
                background: #E9EBEE;
                padding-top: 100px;
                background-image: url(https://c3nguyentatthanhhp.edu.vn/wp-content/uploads/2023/04/177-Background-Book-Background-Sach-dep-nhat-scaled.jpg);
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                min-height: 100vh;
                font-family: 'Roboto', sans-serif;
                overflow: hidden;
            }
            .class1{

                border: 2px solid #9164649e;
                border-radius: 55px;
                width: 30%;
                margin: auto;
                font-family: verdana, Helvetica, sans-serif;
                height: 50%;
                background: #a9a9a9;
                font-weight: bolder;
            }
            .rsp{
                color: #FFFFFF;
                text-align: center;
                font-weight: bolder;
                font-size: 30px;
                padding: 20px 20px 0px 20px;
            }
            .class2{
                padding-top: 50px;
            }
            .edit{
                margin: 20px;
            }
            input[type = password] {
                font-family: verdana, Helvetica, sans-serif;
                width: 100%;
                height: 40px;
                border-radius: 20px;
                padding: 5px 20px 5px 20px;
                font-size: 15px;
                border-color: #E9EBEE;
            }

            input[type = text] {
                font-family: verdana, Helvetica, sans-serif;
                width: 100%;
                height: 40px;
                border-radius: 20px;
                padding: 5px 20px 5px 20px;
                font-size: 15px;
                border-color: #E9EBEE;
            }
            .sb1{
                font-family: verdana, Helvetica, sans-serif;
                height: 40px;
                width: 30%;
                font-weight: bolder;
                background: #1356d2;
                border-color: #1356d2;
                color: #FFFFFF;
                font-weight: bolder;
                border-radius: 20px;
                padding: 5px 20px 5px 20px;
                margin-left: 20%;
            }
            .sb2{
                font-family: verdana, Helvetica, sans-serif;
                height: 40px;
                width: 30%;
                font-weight: bolder;
                background: #4e4e4e;
                border-color: #4e4e4e;
                border-radius: 20px;
                padding: 5px 20px 5px 20px;
                color: #FFFFFF;
                font-weight: bolder;
                font-size: 20px;
            }
            .text-danger{
                color: #FFFFFF;
                font-size: 20px;
            }
            @media (max-width: 768px) {
                .class1 {
                    width: 90%;
                }
            }

            .custom-control-label::before {
                background-color: #146EB4;
                border: #dee2e6
            }


        </style>
        <title>New Password</title>
    </head>
    <body>
        <div style="text-align:center">
        <c:if test="${notification !=null}">
    <div class="alert alert-warning alert-dismissible fade show " role="alert" style="display: inline-block; z-index: 15 ; margin: 0">
        <strong>${notification}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if></div>
        <div class="class2">
            <div class="class1">
                <div class="rsp">Đặt lại mật khẩu</div>
                <form action="newpass" method="post">
                    <input type="hidden" name="user_Id" value="${sessionScope.uss.user_Id}"/>

                    <div class="edit">
                        <input type="password" placeholder=" Nhập mật khẩu mới(*)" name="pass" required>
                    </div> 
                    <div class="edit">
                        <input type="password"  placeholder=" Xác nhận mật khẩu mới(*)" name="pass2" required>
                    </div> 
<!--                    <div>
                        <div style="display: none">
                            <input style="display: none" name="email" value="${email} ">${email}           
                        </div>
                    </div>-->
                    <div class="edit">
                        <button type="submit" id="login-btn" class="sb2" value="Reset"> Lưu</button>
                    </div>
                    <p style="color: red; display: none;" id="messforgotpassword" tilte="${mess}">${mess}</p>
                </form>
            </div>
        </div>
        <script>
            var mess = document.getElementById('messforgotpassword');
            console.log(mess.innerText === '');

            if (mess.innerText !== '') {
                alert('Your Password less than 8 character or more than 32 characters ');
            }

        </script>
    </body>
</html>

