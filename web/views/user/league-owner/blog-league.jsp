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

        .blog-content {
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
            display: -webkit-box;
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
                        <c:choose>
                            <c:when test="${USER_LEAGUE.image != null}">
                                <img src="data:image/png;base64,${USER_LEAGUE.image}" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                            </c:when>
                            <c:otherwise>
                                <img src="https://placehold.co/100x100" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                            </c:otherwise>
                        </c:choose>
                        <div id="success" style="color: green; margin-top: 10px">${MESSAGE}</div>        
                        <div id="success" style="color: red; margin-top: 10px">${ERROR}</div>

                    </div>
                    <div class="mt-8 md:mt-0 md:ml-10 w-full max-w-lg">
                        <!--<form class="space-y-4" action="team" method="POST" >-->                       
                        <div class="space-y-4" >
                            <input type="hidden" name="action" value="update" />         
                            <input type="file" id="image-input" name="image" style="display: none;" value="${TEAM.image}">
                            <input type="hidden" name="leagueId" value="${USER_LEAGUE.id}" />
                            <div>
                                <label for="surname" class="text-gray-700">Tên giải đấu *</label>
                                <input name="name" value="${USER_LEAGUE.name}" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                            </div>
                            <div style="display: flex">
                                <div>
                                    <label for="surname" class="text-gray-700">Ngày bắt đầu *</label>
                                    <input name="start_date" value="${USER_LEAGUE.startDate}" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                                </div>
                                <div style="margin-left: 10px">
                                    <label for="Phone" class="text-gray-700">Ngày kết thúc *</label>
                                    <input name="end_date" value="${USER_LEAGUE.endDate}" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  required>
                                </div>
                            </div>
                            <div>
                                <label for="surname" class="text-gray-700">Địa điểm thi đấu *</label>
                                <input name="address" value="${USER_LEAGUE.address}" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                            </div>

                        </div>
                    </div>
                </div>
                </br>
                <hr>
                </br>
                <div>
                    <label for="desc" class="text-gray-700">Mô tả về giải đấu *:</label>
                    <textarea name="desc" type="email" id="Email" placeholder="Mô tả về team của bạn" 
                              class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
                              pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Email phải chứa ký tự '@' và không được chứa các ký tự đặc biệt không hợp lệ." required>${USER_LEAGUE.description}</textarea>
                </div>
                <div>
                    <label for="surname" class="text-gray-700">Số lượng đội tham gia: *</label>
                    <input name="teamsize" value="${USER_LEAGUE.teamSize}" type="number" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                </div>

                <div>
                    <label for="surname" class="text-gray-700">Hình thức thi đấu *</label>
                    <select name="type" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  requried>
                        <option value="Đá xoay vòng" >Đá xoay vòng</option>
                        <option value="Thi đấu theo bảng">Thi đấu theo bảng</option>
                    </select>
                </div>
                <div>
                    <div style="margin: 30px 0px">
                        <a href="${pageContext.request.contextPath}/CreateBlogLeagueController?leagueId=${USER_LEAGUE.id}" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                           style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Thêm blog mới</a>
                    </div>

                </div>
                <div>
                    Danh sách các bài blog
                </div>
                <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <th scope="col" class="px-6 py-3">
                                #
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Image
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Title
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Content
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Action
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${LIST_BLOG}" var="blog" varStatus="status">
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th scope="row" class="px-6 py-4 ">
                                    ${status.count}
                                </th>
                                <th scope="row" class="px-6 py-4 ">
                                    <img src="data:image/png;base64,${blog.image}" alt="Blog Image" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 100px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                </th>
                                <td class="px-6 py-4 px-6 py-4 font-medium text-gray-900 whitespace-wrap">
                                    ${blog.title}
                                </td>
                                <td class="px-6 py-4 blog-content" style="
                                    overflow: hidden;
                                    height: 80px;
                                    ">
                                    ${blog.description}
                                </td>
                                <td class="px-6 py-4">
                                    <a href="blog-league?action=view&blogId=${blog.id}&leagueId=${blog.leagueId}" class="btn btn-primary">Xem</a>
                                    <a href="DeleteBlogController?blogId=${blog.id}&leagueId=${blog.leagueId}" class="btn btn-danger">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>



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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        var error = document.getElementById('error');
        var message = document.getElementById('success');

        if (error.value) {
            Swal.fire({
                title: error.value,
                icon: "info",
                showCancelButton: true,
                confirmButtonText: "Xác nhận",
            })
        }

        if (message.value) {
            Swal.fire({
                title: message.value,
                icon: "success",
                showCancelButton: true,
                confirmButtonText: "Xác nhận",
            })
        }
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