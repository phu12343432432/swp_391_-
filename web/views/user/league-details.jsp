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
            border: 1px solid #2563eb; /* Viền xanh dương */
            padding: 12px 15px;
            margin: 8px 0;
            width: 100%;
            background-color: #eee;
            border-radius: 4px;
        }

        body {
            font-family: 'Inter', sans-serif;
        }

        .radio-button:checked + .radio-label {
            background-color: #2563eb; /* Màu xanh dương */
            border-color: #2563eb; /* Màu xanh dương */
        }

        .custom-btn {
            background-color: #2563eb; /* Màu nền xanh dương */
            border: none;
            color: white;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .custom-btn:hover {
            background-color: #1e3a8a; /* Màu nền khi hover */
        }

        .custom-form {
            width: 900px;
            height: 900px;
        }

        .card {
            border: 1px solid #2563eb; /* Viền xanh dương */
            border-radius: 10px;
            overflow: hidden;
        }

        .card-title {
            font-size: 1.25rem;
        }

        .card-text b {
            color: #2563eb; /* Màu xanh dương */
        }

        .team-card {
            display: flex;
            border: 1px solid #2563eb; /* Viền xanh dương */
            border-radius: 10px;
            overflow: hidden;
            margin-bottom: 15px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .team-card img {
            height: 100px;
            width: 100px;
            object-fit: cover;
        }

        .team-card-body {
            padding: 10px;
            flex-grow: 1;
        }

        .team-card-title {
            color: #2563eb; /* Màu xanh dương */
            font-weight: bold;
        }

        .team-card-text {
            margin: 5px 0;
        }

        .view-team-btn {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 10px;
            background-color: #f1f1f1;
            cursor: pointer;
        }

        .view-team-btn:hover {
            background-color: #ddd;
        }

        .bg-primary {
            background-color: #2563eb; /* Màu nền xanh dương */
        }

        .text-primary {
            color: #2563eb; /* Màu chữ xanh dương */
        }

        .focus\:ring-primary:focus {
            outline: 2px solid transparent;
            outline-offset: 2px;
            box-shadow: 0 0 0 2px rgba(37, 99, 235, 0.5);
        }

        .focus\:border-primary:focus {
            border-color: #2563eb; /* Viền xanh dương khi focus */
        }
    </style>
</head>

<body>

    <jsp:include page="header.jsp" />

    <div class="min-h-screen bg-gray-100 flex items-center justify-center px-4">
        <form class="bg-white shadow-lg rounded-lg p-4 md:p-4 lg:p-8 custom-form" action="league" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="action" value="update" />
            <div class="flex flex-col md:flex-row justify-between items-start">
                <div class="flex flex-col items-center text-center md:text-left md:items-start">
                    <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                        <i class="fas fa-user-circle mr-2"></i>Thông tin giải đấu
                    </h2>
                    <c:choose>
                        <c:when test="${USER_LEAGUE.image != null}">
                            <img src="data:image/png;base64,${USER_LEAGUE.image}" alt="Profile picture" id="profile-picture" class="border-3 border-blue-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto; border: 2px solid #2563eb">
                        </c:when>
                        <c:otherwise>
                            <img src="https://placehold.co/100x100" alt="Profile picture" id="profile-picture" class="border-3 border-blue-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto; border: 2px solid #2563eb">
                        </c:otherwise>
                    </c:choose>
                    <div style="color: #2563eb; margin-top: 10px">${MESSAGE}</div>
                </div>
                <div class="mt-8 md:mt-0 md:ml-10 w-full max-w-lg">
                    <div class="space-y-4">
                        <input type="hidden" name="action" value="update" />
                        <input type="file" id="image-input" name="image" style="display: none;" value="${TEAM.image}">
                        <input type="hidden" name="leagueId" value="${USER_LEAGUE.id}" />
                        <div>
                            <label for="surname" class="text-gray-700">Tên giải đấu *</label>
                            <input name="name" value="${USER_LEAGUE.name}" type="text" id="surname" class="input-bordered focus:ring-primary focus:border-transparent" readonly>
                        </div>
                        <div class="flex space-x-4">
                            <div>
                                <label for="start_date" class="text-gray-700">Ngày bắt đầu *</label>
                                <input name="start_date" value="${USER_LEAGUE.startDate}" type="datetime-local" class="input-bordered focus:ring-primary focus:border-transparent" readonly>
                            </div>
                            <div>
                                <label for="end_date" class="text-gray-500">Ngày kết thúc *</label>
                                <input name="end_date" value="${USER_LEAGUE.endDate}" type="datetime-local" class="input-bordered focus:ring-primary focus:border-transparent" readonly>
                            </div>
                        </div>
                        <div>
                            <label for="address" class="text-gray-700">Địa điểm thi đấu *</label>
                            <input name="address" value="${USER_LEAGUE.address}" type="text" id="address" class="input-bordered focus:ring-primary focus:border-transparent" readonly>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mt-8">
                <label for="desc" class="text-gray-700">Mô tả về giải đấu *:</label>
                <textarea name="desc" id="desc" class="input-bordered focus:ring-primary focus:border-transparent" readonly>${USER_LEAGUE.description}</textarea>
            </div>
            <div class="mt-4">
                <label for="teamsize" class="text-gray-700">Số lượng đội tham gia: *</label>
                <input name="teamsize" value="${USER_LEAGUE.teamSize}" type="number" id="teamsize" class="input-bordered focus:ring-primary focus:border-transparent" readonly>
            </div>
            <div class="mt-4">
                <label for="type" class="text-gray-700">Hình thức thi đấu *</label>
                <select name="type" id="type" class="input-bordered focus:ring-primary focus:border-transparent" readonly>
                    <option value="Đá xoay vòng">Đá xoay vòng</option>
                    <option value="Thi đấu theo bảng">Thi đấu theo bảng</option>
                </select>
            </div>
        </form>
    </div>

    <div class="container my-8 bg-white shadow-lg rounded-lg p-4 md:p-4 lg:p-8">
        <label for="teams" class="text-gray-700 h4 mb-4">Các đội đăng ký:</label>
        <c:if test="${LEAGUE_TEAM.size() == 0}">
            <div class="text-center">
                <h2 class="text-primary">Chưa có đội bóng nào đăng ký giải đấu này</h2>
                <a class="btn btn-warning btn-lg custom-btn mt-3" href="league?action=register&leagueId=${leagueId}">Đăng ký ngay</a>
            </div>
        </c:if>
        <c:forEach items="${LEAGUE_TEAM}" var="team">
            <div class="team-card">
                <img src="data:image/png;base64,${team.image}" alt="Profile picture">
                <div class="team-card-body">
                    <h5 class="team-card-title">${league.name}</h5>
                    <p class="team-card-text">Tên đội: <b>${team.teamName}</b></p>
                    <p class="team-card-text">Đăng ký vào: ${team.registerAt}</p>
                    <p class="team-card-text"><small class="text-muted">${league.type}</small></p>
                </div>
                <div class="view-team-btn">
                    <a class="btn btn-sm btn-secondary" href="team?action=view-team&id=${team.teamId}">Xem thông tin đội</a>
                </div>
            </div>
        </c:forEach>
    </div>





                <!--                <div style="display: flex; justify-content: center; margin-top: 15px">
                                    <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                                            style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Lưu</button>
                                </div>-->
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