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


    </style>
    <body>

        <jsp:include page="header.jsp"/>

        <div class="min-h-screen bg-gray-100 flex items-center justify-center px-4">
            <form class="bg-white shadow-lg rounded-lg p-8 max-w-4xl w-full" action="league" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="action" value="update"/>
                <div class="flex flex-col md:flex-row justify-between items-start">
                    <div class="flex flex-col items-center text-center md:text-left md:items-start">
                        <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                            <i class="fas fa-user-circle mr-2"></i>Thông tin giải đấu
                        </h2>
                        <div style="color: green; margin-top: 10px">${MESSAGE}</div>
                    </div>
                </div>
                </br>
                <hr>
                </br>
                <div>
                    <label for="surname" class="text-gray-700">Các đội đăng kí:</label>
                    <c:if test="${LEAGUE_TEAM.size() == 0}">
                        <h2>"Chưa có đội bóng nào đăng kí giải đấu này"</h2>
                        <a class="btn btn-sm btn-warning" href="league?action=register&leagueId=${leagueId}" class="btn btn-sm" style="width: 200px">Đăng kí ngay</a>
                    </c:if>
                    <c:forEach items="${LIST_MATCH}" var="team">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="row">
                                <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                    <div class="image">
                                        <img src="data:image/png;base64,${team.hometeamImage}" alt="Profile picture"  style="width: 100%; height: 100px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                                    <div class="blog-details">
                                        <p class="card-text" style="color: #198754; font-weight: bold;"></p>      
                                        <p class="card-text">${team.hometeamName}</b></p>
                                        <small class="text-muted">${team.hometeamShortName}</small>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4" style="">
                                    <div class="d-flex justify-content-center align-items-center" style="margin-top: 15px">
                                        <div class="btn-group">
                                            <c:if test="${team.status == 2}">
                                                <a href="league?action=match-detail&matchId=${team.id}" class="btn btn-sm btn-secondary" class="btn btn-sm" style="width: 100%">
                                                    Sắp diễn ra
                                                </a>
                                            </c:if>
                                            <c:if test="${team.status == 4}">

                                                <a href="league?action=match-detail?matchId=${team.id}" class="btn btn-sm btn-danger" class="btn btn-sm" style="width: 100%">
                                                    Đã diễn ra 
                                                </a>
                                            </c:if>
                                            <c:if test="${team.status == 5}">

                                                <a href="league?action=match-detail?matchId=${team.id}" class="btn btn-sm btn-danger" class="btn btn-sm" style="width: 100%">
                                                    Đã kết thúc - xem kết quả
                                                </a>
                                            </c:if>
                                            <c:if test="${team.status == 6}">

                                                <a href="league?action=match-detail?matchId=${team.id}" class="btn btn-sm btn-danger" class="btn btn-sm" style="width: 100%">
                                                    Vòng knock out
                                                </a>
                                            </c:if>

                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center align-items-center" style="margin-top: 15px">
                                        <div class="btn-group">
                                            <c:if test="${team.status == 4}">
                                                <b>${team.scoreHome} - ${team.scoreAway}</b>
                                            </c:if>
                                            <c:if test="${team.status == 2}">
                                                <b> __ - __</b>
                                            </c:if>
                                        </div>

                                    </div>
                                    <div class="d-flex justify-content-center align-items-center" >
                                        ${team.startAt.substring(11, 16)} - ${team.endAt.substring(11, 16)}
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                                    <div class="blog-details">
                                        <p class="card-text" style="color: #198754; font-weight: bold;"></p>      
                                        <p class="card-text">${team.awayteamName}</b></p>
                                        <small class="text-muted">${team.awayteamShortName}</small>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                    <div class="image">
                                        <img src="data:image/png;base64,${team.awayteamImage}" alt="Profile picture"  style="width: 100%; height: 100px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                    </div>
                                </div>
                            </div>
                        </div>
                        </hr>
                    </c:forEach>
                    <div style="display: flex; justify-content: center; margin-top: 15px">
                        <a href="league?action=finish-league&leagueId=${USER_LEAGUE.id}" class="btn btn-danger" >Trở lại</a>
                    </div>

                </div>        
        </div>
    </form>
</div>



<jsp:include page="footer.jsp"/>
<!-- Footer End -->


<!-- Back to Top -->
<a href="timsan_nhat.jsp" class="btn btn-lg btn-primary btn-lg-square rounded back-to-top"><i class="bi bi-arrow-up"></i></a>

</body>


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