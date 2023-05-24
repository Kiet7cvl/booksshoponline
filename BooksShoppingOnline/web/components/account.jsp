<%-- 
    Document   : account
    Created on : Jun 3, 2022, 6:30:16 PM
    Author     : son22
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Notification User -->
<c:if test="${notification !=null}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert" style="position: fixed; z-index: 15 ; margin-left: 40%">
        <strong>${notification}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>


<!-- Login -->
<div class="modal fade col-md-12" role="dialog" id="loginModal">
    <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 10px; margin-top: 258px;">
            <div class="modal-header">
                <h2 class="" id="loginModal" style="text-align:center; font-family: Arial"><b style="padding-left: 150px;">Đăng Nhập</b></h2><br>
            </div>
            <div class="modal-body">
                <form action="login" method="post">
                    <div class="form-group">
                        <input name="email" type="email" class="form-control" placeholder="Email của bạn"style="border-radius: 100px;" required="">
                    </div>
                    <div class="form-group">
                        <input name="password" type="password" class="form-control" placeholder="Mật khẩu" style="border-radius: 100px;" required>
                    </div>
                    <center><button type="submit" class="btn btn-dark" style="padding-right: 193px;padding-left: 193px; border-radius: 100px;">Đăng nhập</button></center>
                </form>
                <br><br>
                <a type="button" data-toggle="modal" data-dismiss="modal" data-target="#ResetPasswordModal" style="float:right; text-decoration: none; border-radius: 100px;">Quên Mật Khẩu</a>
                <a type="button" data-toggle="modal" data-dismiss="modal" data-target="#registerModal" style="float:left; text-decoration: none; border-radius: 100px;">Đăng ký tài khoản mới</a>
            </div>
        </div>
    </div>
</div>


<!-- ResetPassword -->
<div class="modal fade col-md-12" role="dialog" id="ResetPasswordModal">
    <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 10px; margin-top: 150px;">
            <div class="modal-header">
                <h1 style="text-align: center ; margin-left: 70px;">Cấp lại mật khẩu</h1>
            </div>
            <div class="modal-body">
                <form action="resetpassword" method="post">
                    <div class="form-group">
                        <input name="email" type="email" class="form-control" placeholder="Email của bạn"style="border-radius: 100px;" required>
                    </div>
                    <br>
                    <center>
                        <button type="submit" class="btn btn-dark" style="padding-right: 200px;
                                padding-left: 200px;
                                border-radius: 100px;
                                margin-bottom: -40px;
                                height: 50px;">Kiểm tra
                        </button>
                    </center>
                </form>
                <br><br>
                <a type="button" data-toggle="modal" data-dismiss="modal" data-target="#loginModal" style="padding-left: 170px; text-decoration: none; border-radius: 100px;">Quay lại Đăng nhập</a> 
            </div>
        </div>
    </div>
</div>