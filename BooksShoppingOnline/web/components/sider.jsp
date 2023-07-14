
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card mb-3" style="border-radius: 25px;     top: 10.5px;">
    <form action="list">
        <div class="input-group">
            <input class="form-control" name="key" value="${key}"  type="text" placeholder="Tìm kiếm" aria-label="Enter search term..." aria-describedby="button-search" style="border-radius: 25px 0px 0px 25px"/>
            <button class="btn btn-dark"  type="submit" style="border-radius: 0px 25px 25px 0px">Tìm</button>
        </div>
    </form>
</div>
<!-- Categories widget-->
<div class="card mb-4" style="border-radius: 25px;">
    <div class="list-group  bg-dark" style="border-radius: 25px; padding-bottom: 18px">
        <div href="#" class="list-group-item list-group-item-action bg-dark btn-dark active"  style="border-radius: 25px 25px 0px 0px">
            Danh mục sản phẩm
        </div>


<select class="list-group-item list-group-item-action" aria-label="Default select example" onchange="redirectToPage(this)">
  <option value="">Thể Loại</option>
  <option value="http://localhost:9999/BooksShoppingOnline/list?&categoryId=1">Light Novel</option>
  <option value="http://localhost:9999/BooksShoppingOnline/list?&categoryId=2">Kĩ năng sống</option>
  <option value="http://localhost:9999/BooksShoppingOnline/list?&categoryId=3">Manga - Comic</option>
  <option value="http://localhost:9999/BooksShoppingOnline/list?&categoryId=4">Nghệ thuật - Giải trí</option>
  
</select>
<a class="list-group-item list-group-item-action" aria-label="Default select example" href="http://localhost:9999/BooksShoppingOnline/list?&type=desc" >Sản phẩm mới nhất</a>



    </div>
</div>
                        
                        <script>
function redirectToPage(selectElement) {
  var selectedOption = selectElement.options[selectElement.selectedIndex];
  var pageUrl = selectedOption.value;
  
  if (pageUrl !== "") {
    window.location.href = pageUrl;
  }
}
</script>