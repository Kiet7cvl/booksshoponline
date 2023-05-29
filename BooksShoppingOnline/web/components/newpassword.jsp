<%-- 
    Document   : newpassword
    Created on : Feb 13, 2023, 3:44:16 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


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
        <div class="class2">
            <div class="class1">
                <div class="rsp">Reset Password</div>
                <form action="newpass" method="post">
                    <div class="edit">
                        <input type="text" placeholder=" Enter New Password(*)" name="pass">
                    </div> 
                    <div class="edit">
                        <input type="password"  placeholder=" Confirm New Password(*)" name="pass2">
                    </div> 
                    <div>
                        <div style="display: none">
                            <input style="display: none" name="email" value="${email} ">${email}           
                        </div>
                    </div>
                    <div class="edit">
                        <button type="submit" id="login-btn" class="sb2" value="Reset"> Reset</button>
                    </div>
                    <p style="color: red; display: none;" id="messforgotpassword" tilte="${mess}">${mess}</p>
                </form>
            </div>
        </div>
        <script>
            var mess = document.getElementById('messforgotpassword');
            console.log(mess.innerText === '');

            if (mess.innerText !== '') {
                alert('Mật khẩu chứa ít nhất 8 chứ kí tự ');
            }

        </script>
    </body>
</html>

