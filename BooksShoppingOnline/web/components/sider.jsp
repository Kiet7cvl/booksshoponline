<%-- 
    Document   : sider
    Created on : Jun 24, 2023, 8:46:36 PM
    Author     : MSI Bravo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card mb-4" style="border-radius: 25px;">
    <form action="list">
        <div class="input-group">
            <input class="form-control" name="key" value="${key}"  type="text" placeholder="Tìm kiếm" aria-label="Enter search term..." aria-describedby="button-search" style="border-radius: 25px 0px 0px 25px"/>
            <button class="btn btn-dark"  type="submit" style="border-radius: 0px 25px 25px 0px">Tìm kiếm</button>
        </div>
    </form>
</div>
<!-- Categories widget-->
<div class="card mb-4" style="border-radius: 25px;">
    <div class="list-group  bg-dark" style="border-radius: 25px; padding-bottom: 18px">
        <div href="#" class="list-group-item list-group-item-action bg-dark btn-dark active"  style="border-radius: 25px 25px 0px 0px">
            Danh mục sản phẩm
        </div>
        <select class="list-group-item list-group-item-action" aria-label="Default select example" onchange="location = this.value;" name="Sắp xếp">
            <option value="list?${historyKey}${historyValue}${historyType}"${categoryId == 1 ? "Selected" : ""}>Thể loại</option>        
            <option value="list?${historyKey}${historyValue}${historyType}" ${categoryId == 0 ? "Selected" : ""}>
                        Tất Cả
                    </option>
                    <c:forEach items="${sessionScope.listCategories}" var="c">
                        <option value="list?${historyKey}&categoryId=${c.id}${historyValue}${historyType}" ${categoryId == c.id ? "Selected" : ""}>
                            ${c.name}
                        </option>
                    </c:forEach>
                </select>
            <select class="list-group-item list-group-item-action" aria-label="Default select example" onchange="location = this.value;">
                    <option value="list?${historyKey}${historyCategoryId}&type=desc" ${type eq "desc" ? "Selected" : ""}>
                        Cũ Nhất
                    </option>
                    <option value="list?${historyKey}${historyCategoryId}" ${type == null ? "Selected" : ""}>
                        Mới Nhất
                    </option> 
                    <option value="list?${historyKey}${historyCategoryId}&value=original_prices" ${value eq "original_prices" ? "Selected" : ""}>
                        Giá tăng dần
                    </option>
                    <option value="list?${historyKey}${historyCategoryId}&value=original_prices&type=desc" ${value eq "original_prices" && type eq "desc" ? "Selected" : ""}>
                        Giá giảm dần
                    </option> 
                </select>
    </div>
</div>