

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!DOCTYPE html>
    <html lang="zxx">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <link rel="stylesheet" href="./assets/css/style.css">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">


            <!-- Google Font -->
            <link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">

            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <link rel="stylesheet" href="./assets/css/style.css">
            <!-- Css Styles -->
            <link rel="stylesheet" href="do1/css/bootstrap.min.css" type="text/css">
            <link rel="stylesheet" href="do1/css/font-awesome.min.css" type="text/css">
            <link rel="stylesheet" href="assets/fonts/themify-icons/themify-icons.css">
            <link rel="stylesheet" href="do1/css/elegant-icons.css" type="text/css">
            <link rel="stylesheet" href="do1/css/owl.carousel.min.css" type="text/css">
            <link rel="stylesheet" href="do1/css/nice-select.css" type="text/css">
            <link rel="stylesheet" href="do1/css/jquery-ui.min.css" type="text/css">
            <link rel="stylesheet" href="do1/css/slicknav.min.css" type="text/css">
            <link rel="stylesheet" href="do1/css/style.css" type="text/css">
            <!-- Core theme CSS (includes Bootstrap)-->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
            <%@include file="components/javascript.jsp" %>
            <style>
                .return-productlist {
                    margin-top: -40px;
                }
                .return-productlist button:hover{
                    background-color: #e9e9e9;
                }
                .return-productlist button {
                    margin-left:90px;
                    border-radius: 5px;
                    border: 1px solid #e9e9e9;
                    background-color: white;
                  
                }
                .delete a:hover{
                    background-color: white;

                }
                .delete ion-icon {
                    color: black;
                    font-size: 24px;
                    margin-bottom: -5px
                }
            </style>
        </head>

        <body>
            <%@include file="components/header.jsp" %>
            <%@include file="components/account.jsp" %>
            <!-- Shopping Cart Section Begin -->
            <section class="shopping-cart spad mt-5">
                <div>
                    <div class="return-productlist">
                        <button>
                            <a href="list" style="font-size: 20px;font-weight: bold;color: black;text-transform: uppercase;text-decoration: none;"> Tiếp tục mua hàng</a>
                        </button>
                    </div>                  
                </div>
                <br/>
                <div class="container" style="max-width: 90%">
                    <div class="row">
                        <div class="col-lg-12" >
                            <div class="cart-table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th class="p-name">Tên sản phẩm</th>
                                            <th>Giá tiền</th>
                                            <th>Số lượng</th>
                                            <th>Tổng tiền</th>
                                            <th>Xóa Sản Phẩm</th>  
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listCart}" var="c">
                                            <tr>
                                                <td class="p-price first-row">${c.product_id}</td>
                                                <td class="cart-title first-row">
                                                    <h5>${c.product_name}</h5>
                                                </td>
                                                <td class="p-price first-row">${c.product_price}đ</td>
                                                <td class="qua-col first-row">
                                                    <div class="quantity">                                                
                                                        <form action="update-cart">                                                            
                                                            <input id="quantityInput" class="form-control form-control-sm" type="number" name="quantity" value="${c.quantity}" data-product-id="${c.product_id}" data-cart-id="${c.cart_id}" min="1" max="${sessionScope.productQuantity}" style="width: 150px"readonly="">
<input type="hidden" name="productId" value="${c.product_id}"/>
<input type="hidden" name="cartId" value="${c.cart_id}"/>
<button onclick="decreaseQuantity()">-</button>
<button onclick="increaseQuantity()">+</button>

                                                        </form>
                                                    </div>
                                                </td>
                                                <td class="total-price first-row">${c.total_cost}đ</td>
                                                <td class="close-td first-row">
                                                    <a onclick="showMess('${c.product_id}', '${c.user_id}')" href="#" class="delete">
                                                        <ion-icon name="trash"></ion-icon></ion-icon></a>
                                                    </a>
                                                </td>                           
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 offset-lg-4">
                                    <div class="proceed-checkout">
                                        <ul>
                                            <li class="cart-total">Tổng tiền các sản phẩm <span>${sum}đ</span></li>
                                        </ul>
                                        <div class="proceed-btn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">

                                            <a href="cart-contact" style="color: white">Mua hàng</a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section style="background-color: #f4f4f4;">
                <div class="content-section background-product text-center" ><br/><br/>
                    <!-- Base Product -->
                    <div>
                    <h2 class="section-heading" >CÓ THỂ BẠN CŨNG THÍCH</h2>
                    </div>
                    <br/><br/><br/>
                    <div  class="container-fluid">
                        <div class="row">
                            <c:forEach items="${sessionScope.listproduct}" var="p">
                                <div class="col-md-2 product-down">
                                    <div class="row">
                                        <div class="product-item">
                                            <div class="product-top">
                                                <div class="reviews-rating">

                                                    <c:forEach var="i" begin="0" end="4">
                                                        <c:if test="${(p.rated_star - i) >= 1}">
                                                            <div class="reviews-rating__star is-active"></div> 
                                                        </c:if>
                                                        <c:if test="${(p.rated_star - i) < 1 && (p.rated_star - i) > 0}">
                                                            <div class="reviews-rating__star is-active is-half"></div> 
                                                        </c:if>
                                                        <c:if test="${(p.rated_star - i) <= 0}">
                                                            <div class="reviews-rating__star"></div> 
                                                        </c:if>

                                                    </c:forEach>

                                                </div>
                                                <a href="" class="product-thumb">
                                                    <a href="list-detail?productId=${p.id}&categoryId=${p.category_id}">
                                                        <img src="${p.image}" height="365px" width="230px" alt="">
                                                    </a>

                                                </a>
                                                <a href="addcart?productId=${p.id}" class="buy-now" >Mua ngay</a>

                                            </div>
                                            <div class="product-infor">
                                                <diV style="width: 226px; height: 90px">
                                                <a href="" class="product-name">${p.name}</a> 
                                                </div>
                                                <div class="product-price">
                                                    <c:if test="${p.sale_price != 0}">
                                                        ${p.sale_price}đ
                                                        <del>${p.original_price}đ</del>
                                                    </c:if>
                                                    <c:if test="${p.sale_price == 0}">
                                                        ${p.original_price}đ
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </section>
            <div id="footer">              
                <div class="map">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d7448.27256631781!2d105.53745830000003!3d21.027232300000005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1svi!2s!4v1653919437201!5m2!1svi!2s" 
                            width=90% height="400" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>

                <div class="info-footer">
                    <div class="content-footer">
                        <h2 style="color: white">Địa chỉ liên hệ</h2>
                        <p class="mt-40" style="color: white">khu công nghệ cao, huyện Thạch Thất, TP. Hà Nội</p>
                    </div>

                    <div class="feedback-footer">
                        <h2 class="mt-40" style="color: white">KingsBooks lắng nghe bạn!</h2>
                        <p style="color: white">Chúng tôi luôn trân trọng và mong đợi nhận được mọi ý kiến đóng góp từ khách hàng để có thể nâng cấp trải nghiệm dịch vụ và sản phẩm tốt hơn nữa</p>
                        <br>
                        <br>
                        <br>
                        <div class="contact-info">
                            <p style="color: white"<i class="ti-location-pin" style="color: white"></i>Tran Duy Hung, Ha Noi</p>
                            <p style="color: white"><i class="ti-mobile" style="color: white"></i>Phone:+0356111214</p>
                            <p style="color: white"><i class="ti-email" style="color: white"></i>dotunglam200xx@gmail.com</p>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <!-- Js Plugins -->
            <script src="do1/js/jquery-3.3.1.min.js"></script>
            <script src="do1/js/bootstrap.min.js"></script>
            <script src="do1/js/jquery-ui.min.js"></script>
            <script src="do1/js/jquery.countdown.min.js"></script>
            <script src="do1/js/jquery.nice-select.min.js"></script>
            <script src="do1/js/jquery.zoom.min.js"></script>
            <script src="do1/js/jquery.dd.min.js"></script>
            <script src="do1/js/jquery.slicknav.js"></script>
            <script src="do1/js/owl.carousel.min.js"></script>
            <script src="do1/js/main.js"></script>
            <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
            <script>
                                                        function updateCartItem(input) {
                                                            var quantity = input.value;
                                                            var productId = input.getAttribute("data-product-id");
                                                            var cartId = input.getAttribute("data-cart-id");

                                                            // Gửi yêu cầu AJAX đến servlet để cập nhật số lượng sản phẩm
                                                            $.ajax({
                                                                url: "update-cart",
                                                                method: "POST",
                                                                data: {
                                                                    quantity: quantity,
                                                                    productId: productId,
                                                                    cartId: cartId
                                                                },
                                                                success: function (response) {
                                                                    // Cập nhật số tiền và tổng tiền tương ứng trên giao diện
                                                                    var totalCostElement = input.closest("tr").querySelector(".total-price");
                                                                    totalCostElement.textContent = response.totalCost + "đ";
                                                                    var cartTotalElement = document.querySelector(".cart-total span");
                                                                    cartTotalElement.textContent = response.cartTotal + "đ";
                                                                },
                                                                error: function (xhr, status, error) {
                                                                    console.log(error); // Xử lý lỗi nếu có
                                                                }
                                                            });
                                                        }
            </script>
            <script>
                function showMess(productId, userId) {
                    var option = confirm('Bạn có chắc chắn muốn xóa?');
                    if (option === true) {
                        window.location.href = 'delete-cart?productId=' + productId + '&userId=' + userId;
                    }
                }
            </script>
            <script>
                function decreaseQuantity() {
  var quantityInput = document.getElementById("quantityInput");
  var currentQuantity = parseInt(quantityInput.value);
  if (currentQuantity > 1) {
    quantityInput.value = currentQuantity - 1;
  }
}

function increaseQuantity() {
  var quantityInput = document.getElementById("quantityInput");
  var currentQuantity = parseInt(quantityInput.value);
  var maxQuantity = parseInt(quantityInput.getAttribute("max"));
  if (currentQuantity < maxQuantity) {
    quantityInput.value = currentQuantity + 1;
  }
}

// Ngăn người dùng nhập số trực tiếp
var quantityInput = document.getElementById("quantityInput");
quantityInput.addEventListener("keydown", function (e) {
  if (!isNumericInput(e)) {
    e.preventDefault();
  }
});

function isNumericInput(event) {
  var key = event.keyCode || event.which;
  return (
    (key >= 48 && key <= 57) || // Số từ 0 đến 9
    key === 8 || // Phím backspace
    key === 9 || // Phím tab
    key === 37 || // Phím mũi tên trái
    key === 39 || // Phím mũi tên phải
    key === 46 || // Phím delete
    (key >= 96 && key <= 105) // Số từ bàn phím số
  );
}

            </script>
        </body>

    </html>
</html>
