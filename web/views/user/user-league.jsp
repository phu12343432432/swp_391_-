<%@ page import="java.net.URLEncoder" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông tin tài khoản</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <!-- Favicon -->
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700;800&family=Rubik:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <style>

        .input-bordered {
            border: 1px solid #cccccc; /* Màu viền xám nhạt */
            padding: 12px 15px; /* Đệm để nhập liệu không bị sát viền */
            margin: 8px 0; /* Khoảng cách giữa các trường nhập */
            width: 100%; /* Chiều rộng tối đa */
            background-color: #eee; /* Màu nền nhạt cho trường nhập */
            border-radius: 4px; /* Bán kính bo góc cho viền */
        }
        body {
            font-family: 'Inter', sans-serif;
        }
        .radio-button:checked + .radio-label {
            background-color: #f97316;
            border-color: #f97316;
        }
        body{
            background:#eee;
        }

        p {
            font-size: 14px;
            color: #777;
        }

        .blog .image {
            height: 250px;
            overflow: hidden;
            border-radius: 3px 0 0 3px;
        }

        .blog .image img {
            width: 100%;
            height: auto;
        }

        .blog .date {
            top: -10px;
            z-index: 99;
            width: 65px;
            right: -10px;
            height: 65px;
            padding: 10px;
            position: absolute;
            color:#FFFFFF;
            font-weight:bold;
            background: #5bc0de;
            border-radius: 999px;
        }

        .blog .blog-details {
            padding: 0 20px 0 0;
        }

        .blog {
            padding: 0;
        }

        .well {
            border: 0;
            padding: 20px;
            min-height: 63px;
            background: #fff;
            box-shadow: none;
            border-radius: 3px;
            position: relative;
            max-height: 100000px;
            border-bottom: 2px solid #ccc;
        }

        .blog .blog-details h2 {
            margin-bottom: 10px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }

        .blog .date .blog-number {
            color: #fff;
            display: block;
            font-size: 24px;
            text-align: center;
        }


    </style>

    <body>
        <jsp:include page="header.jsp"/>

        <div class="container bootstrap snippets bootdey" style="min-height: 800px">
            <hr>
            <ol class="breadcrumb">
                <li><a href="profile?action=view">Trở lại</a>/<a href="#">Giải đấu của bạn</a></li>
                <li class="pull-right"><a href="" class="text-muted">
                        <i class="fa fa-refresh"></i></a>
                </li>
            </ol>
            <div class="row">
                <form action="league" class="d-flex" style="margin-bottom: 15px;">
                    <input type="hidden" name="action" value="user-league"/>
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${search}">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                <c:if test="${USER_LEAGUE.size() == 0}">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="well blog">
                            <div class="row">
                                <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
                                    <div class="blog-details">
                                        <h2>"Bạn chưa có giải đấu nào"</h2>
                                    </div>
                                    <a class="btn btn-sm btn-warning" href="league?action=create" class="btn btn-sm" style="width: 30%;padding: 5px 0; margin-left: 15px">Tạo giải đấu</a>
                                </div>
                            </div>
                            <img src="${pageContext.request.contextPath}/img/anh4.jpg" style="height: 505px; width: 100%; margin-top: 35px"/>
                            </a>
                        </div>
                    </div>
                </c:if>
                <c:forEach items="${USER_LEAGUE}" var="league">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="well blog">
                            <div class="row">
                                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                    <div class="image">
                                        <img src="data:image/png;base64,${league.image}"alt="">
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
                                    <div class="blog-details">
                                        <h2>${league.name} </h2>
                                        <p>
                                            ${league.description}
                                        </p>
                                        <p>
                                            Thời gian bắt đầu: ${league.startDate}
                                        </p>
                                        <p>
                                            Thời gian kết thúc: ${league.endDate}
                                        </p>
                                    </div>
                                    <a class="btn btn-sm btn-warning" href="league?action=detail&leagueId=${league.id}" class="btn btn-sm" style="width: 30%;padding: 5px 0; margin-left: 15px">Xem chi tiết</a>
                                </div>
                            </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <nav aria-label="Page navigation example" style="display: flex; justify-content:center;margin-top: 15px;">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test ="${selectedPage - 1 < 1}">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">«</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="league?action=user-league&search=${search}&index=${selectedPage-1}">«</a></li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="i" begin="1" end="${endP}">
                            <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="league?action=user-league&search=${search}&index=${i}">${i}</a> <li>
                            </c:forEach>
                            <c:choose>
                                <c:when test ="${selectedPage >= endP}">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">»</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="league?action=user-league&search=${search}&index=${selectedPage+1}">»</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </nav>
            </div>
        </div>
    </body>

    <jsp:include page="footer.jsp"/>
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/wow/wow.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/waypoints/waypoints.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/counterup/counterup.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script>
        const profilePicture = document.getElementById('profile-picture');
        const imageInput = document.getElementById('image-input');

        profilePicture.addEventListener('click', () => {
            imageInput.disabled = false;
            imageInput.click();
        });
        const updateAvatar = false;
        imageInput.addEventListener('change', () => {
            imageInput.disabled = false;
            const file = imageInput.files[0];
            const formData = new FormData();
            formData.append('image', file);

            const reader = new FileReader();
            reader.onload = () => {
                profilePicture.src = reader.result;
                profilePicture.width = '100%';
            };
            reader.readAsDataURL(file);
            updateAvatar = true;
        });
        if (!updateAvatar && imageInput.value != null) {
            imageInput.disabled = true;
        }
        document.addEventListener("DOMContentLoaded", function () {
            const phoneInput = document.getElementById("Phone");

            phoneInput.addEventListener("input", function () {
                const regex = /^\d{0,10}$/;
                if (!regex.test(phoneInput.value)) {
                    // If validation fails, show a custom error message
                    phoneInput.setCustomValidity("Số điện thoại phải gồm 10 chữ số và không chứa ký tự đặc biệt.");
                } else {
                    // Clear custom error message
                    phoneInput.setCustomValidity("");
                }
            });
        });

    </script>
</html>