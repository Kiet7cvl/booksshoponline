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

<!-- Register -->

<div class="modal fade col-md-12" role="dialog" id="registerModal" style="padding-right: 18px" >
    <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 10px; margin-top: 180px;">
            <div class="modal-header">
                <h2 class="" id="registerModal" style="text-align:center; font-family: Arial"><b style="padding-left: 100px;">Đăng ký tài khoản</b></h2><br>
            </div>

            <div class="modal-body">
                <section>
                    <div class="container">
                        <form action="register" method="POST">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" placeholder="Họ và tên" name="fullName" style="border-radius: 100px;" required>
                                    </div></div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="email" placeholder="Email" name="email" style="border-radius: 100px;" required>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" placeholder="Số điện thoại" name="mobile" style="border-radius: 100px;" required>
                                    </div></div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="pwd" placeholder="Mật khẩu" name="password" style="border-radius: 100px;" required>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="pwd" placeholder="Nhập lại mật khẩu" name="repassword" style="border-radius: 100px;" required>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group" required>
                                        Giới tính
                                        <input class="" name="gender" type="radio" value="1" required/> Nam
                                        
                                        <input class="" name="gender" type="radio" value="0" required/> Nữ
                                    </div>
                                </div>
                                <br><br><center><button type="submit" class="btn btn-dark" style="padding-right: 190px;padding-left: 190px; border-radius: 100px;">Đăng ký</button></center><br><br>
                            </div>
                        </form>
                        <a type="button" data-toggle="modal" data-dismiss="modal" data-target="#loginModal" style="padding-left: 150px; text-decoration: none; border-radius: 100px;">Quay lại đăng nhập</a> 
                    </div>    
                </section>
            </div>
        </div>
    </div>
</div> 
