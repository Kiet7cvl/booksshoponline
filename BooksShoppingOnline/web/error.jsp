<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="">
<head>
    <title>Lỗi</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.css">
    
</head>
<body>
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.js"></script>
    <script>
        // Display error message using SweetAlert
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: '${errorMessage}'
        });
    </script>
</body>
</html>
