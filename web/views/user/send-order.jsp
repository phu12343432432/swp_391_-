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
            <div class="bg-white shadow-lg rounded-lg p-8 max-w-4xl w-full">
                <div class="flex flex-col md:flex-row justify-between items-start">
                    <div class="flex flex-col items-center text-center md:text-left md:items-start">
                        <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                            <i class="fas fa-user-circle mr-2"></i>Thông tin tài khoản
                        </h2>

                        <c:choose>
                            <c:when test="${USER.image != null}">
                                <img src="data:image/png;base64,${USER.image}" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                            </c:when>
                            <c:otherwise>
                                <img src="https://placehold.co/100x100" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                            </c:otherwise>
                        </c:choose>


                        <p class="text-gray-700 mb-1" style="margin: 0 auto">${sessionScope.account.userName}</p>
                        <a href="profile?action=view">
                            <button class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a); margin:10px auto">
                                Thông tin tài khoản
                            </button>
                        </a>
                        <a href="profile?action=changePassword">
                            <button class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a); margin:10px auto">
                                Nhấn để đổi mật khẩu
                            </button>
                        </a>
                        <a href="profile?action=wallet-history">
                            <button class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a); margin:10px auto">
                                Xem lịch sử giao dịch
                            </button>
                        </a>
                        <a href="auth">
                            <button class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a); ">
                                Trở về trang chủ 
                            </button>
                        </a>    
                        <div style="display: flex; width: 100%; justify-content: space-between; margin-top: 20px">
                            <a class="btn btn-sm btn-primary" href="league?action=user-league" class="btn btn-sm" style="width: 100%; padding: 10px 0">Giải đấu của bạn</a>
                            <a class="btn btn-sm btn-warning" href="league?action=list-registered" class="btn btn-sm" style="width: 100%;padding: 10px 0; margin-left: 15px">Lịch sử tham gia</a>
                        </div>

                        <div style="color: green; margin-top: 10px">${MESSAGE}</div>
                    </div>
                    <div class="mt-8 md:mt-0 md:ml-10 w-full max-w-lg">
                        <form action="SendOrderWalletController">
                            <div>
                                <label for="inputOne" class="text-gray-700">Số tiền bạn muốn nạp (Vnđ)</label>
                                <input placeholder="1000 vnđ" type="number" name="ammount"  id="inputOne"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>

                                <label for="inputTwo" class>Tiền trong ví (điểm) (1.000vnđ sẽ tương ứng với 1 điểm):</label>
                                <input  type="number" id="inputTwo" placeholder="1 điểm" readonly class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required"/>
                                <label for="surname" class="text-gray-700">Vui lòng khi chuyển tiền hãy ghi nội dụng phần giao dịch gồm</label>    
                                <div for="surname" class="text-gray-700">- Email - Sđt - Yêu cầu nạp tiền vào tài khoản</div>  
                                <button type="submit" class="w-full bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a); margin-top: 50px">Gửi yêu cầu</button>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
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

        document.getElementById('inputOne').addEventListener('input', function () {
            var inputOneValue = this.value;
            var resultValue = inputOneValue * 0.001; 
            document.getElementById('inputTwo').value = resultValue.toFixed(2); // Rounds the number to 2 decimal places
        });
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