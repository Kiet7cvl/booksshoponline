<%-- 
    Document   : header
    Created on : May 30, 2022, 2:32:51 PM
    Author     : son22
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand me-5 ti-joomla" href="home">KingBooks</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse ms-5" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item me-4">
                        <a class="nav-link active" aria-current="page" href="home">Trang chủ</a>
                    </li>
                    <li class="nav-item me-4">
                        <a class="nav-link" href="list">Sản Phẩm</a>
                    </li>
                    <li class="nav-item me-4">
                        <a class="nav-link" href="blog">Blog</a>
                    </li>
                    <li class="nav-item me-4">
                        <a class="nav-link" href="#">Liên hệ</a>
                    </li>


                </ul>
                <form class="d-flex me-5" action="list">
                    <input class="form-control me-2" type="text" name ="key" placeholder="Tìm kiếm sản phẩm..." aria-label="Search" value="${key}" id="" required class="form-control">
                    <button class="btn btn-outline-danger" type="submit">Tìm</button>
                </form>




                <ul class="navbar-nav mb-2 mb-lg-0">
                    <c:if test="${sessionScope.us != null}">
                        <div class="btn-group">
                           <button type="button" style="border-radius: 4px" class="btn btn-outline-dark py-2 px-4" data-toggle="dropdown" aria-expanded="false">
                                <c:if test="${sessionScope.us.avatar != null && sessionScope.us.avatar ne ''}">
                                    <img class="rounded-circle " width="30px" height="30px" src="data:image/png;base64,${sessionScope.us.base64Image}">
                                    <span class="font-weight-bold">${sessionScope.us.full_Name}</span>
                                    </c:if>
                                    <c:if test="${sessionScope.us.avatar == null || sessionScope.us.avatar eq ''}">
                                    <img class="rounded-circle " width="30px" height="30px" src="data:image/png;base64,${sessionScope.us.avatar}">
                                        <span class="font-weight-bold">${sessionScope.us.full_Name} ${sessionScope.us.base64Image}</span>
                                </c:if>
                            </button>
                            <ul class="dropdown-menu menuScroll">
                                <li><a class="dropdown-item" type="button" data-toggle="modal"  data-target="#userProfileModal">Thông tin cá nhân</a></li>
                                    <c:if test="${sessionScope.us.role_Id == 1}">
                                    <li><a class="dropdown-item" href="myorder">Danh sách đơn hàng</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.us.role_Id == 2}">
                                    <li><a class="dropdown-item" href="mkt-dashboard">Quản lý tiếp thị</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.us.role_Id == 3 || sessionScope.us.role_Id == 4}">
                                    <li><a class="dropdown-item" href="sale-dashboard">Quản lý bán hàng</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.us.role_Id == 5}">
                                    <li><a class="dropdown-item" href="admin-dashboard">Quản lý hệ thống</a></li>
                                    </c:if>  
                                <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.us == null}">
                        <li class="nav-item">
                            <a><i type="button" class="ti-user btn btn-icon py-2 px-4" data-toggle="modal"  data-target="#loginModal"></i></a>
                        </li>
                    </c:if>
                        
                    <!-- begin icon header -->
                    <c:if test="${sessionScope.us.role_Id == 1 || sessionScope.us == null}">
                        <li class="nav-item">
                            <a class="nav-link btn btn-icon py-2 px-4" href="carts" tabindex="-1" aria-disabled="true"><i class="ti-shopping-cart"></i></a>
                        </li>
                    </c:if>
                </ul>

            </div>
        </div>
    </nav>
</div>

