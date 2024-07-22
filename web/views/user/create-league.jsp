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
                <input type="hidden" name="action" value="create"/>
                <div class="flex flex-col md:flex-row justify-between items-start">
                    <div class="flex flex-col items-center text-center md:text-left md:items-start">
                        <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                            <i class="fas fa-user-circle mr-2"></i>Tạo giải đấu
                        </h2>
                        <img src="https://placehold.co/100x100" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">

                        <div style="color: green; margin-top: 10px">${MESSAGE}</div>  
                        <div style="color: red; margin-top: 10px">${ERRORMESSAGE}</div>

                    </div>
                    <div class="mt-8 md:mt-0 md:ml-10 w-full max-w-lg">
                        <!--<form class="space-y-4" action="team" method="POST" >-->                       
                        <div class="space-y-4" >
                            <input type="hidden" name="action" value="update" />         
                            <input type="file" id="image-input" name="image" style="display: none;" value="${TEAM.image}">
                            <input type="hidden" name="leagueId" />
                            <div>
                                <label for="surname" class="text-gray-700">Tên giải đấu *</label>
                                <input name="name" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                            </div>
                            <div style="display: flex">
                                <div>
                                    <label for="surname" class="text-gray-700">Ngày bắt đầu *</label>
                                    <input name="start_date" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                                </div>
                                <div style="margin-left: 10px; width: 80%">
                                    <label for="Phone" class="text-gray-700">Ngày kết thúc *</label>
                                    <input name="end_date" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" style="width: 90%"  required>
                                </div>
                            </div>
                            <div><b>Thời gian bắt đầu giải đấu</b> cũng sẽ là thời gian diễn ra trận <b>đấu đầu tiên</b>, bạn hãy cân nhắc chọn thời gian phù hợp.</div>
                            <div>
                                <label for="surname" class="text-gray-700">Địa điểm thi đấu *</label>
                                <input name="address" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                            </div>

                        </div>
                    </div>
                </div>
                </br>
                <hr>
                </br>
                <div>
                    <label for="desc" class="text-gray-700">Mô tả về giải đấu *:</label>
                    <textarea name="desc" type="email" id="Email" placeholder="Mô tả về giải đấu của bạn" 
                              class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
                              pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Email phải chứa ký tự '@' và không được chứa các ký tự đặc biệt không hợp lệ." required></textarea>
                </div>

                <div>
                    <label for="surname" class="text-gray-700">Số lượng cầu thủ mỗi đội: *</label>
                    <select name="team_member_size" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  requried>
                        <option value="5" >5</option>    
                        <option value="7" >7</option>
                        <option value="11" >11</option>
                    </select>
                </div>
                <div>
                    <label for="surname" class="text-gray-700">Ngày hết hạn đăng kí *</label>
                    <input name="date_register" type="date" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                </div>


                <div>
                    <label for="surname" class="text-gray-700">Hình thức thi đấu *</label>
                    <select name="type" id="type-select" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  requried>
                        <option value="1" >Đá xoay vòng</option>
                        <option value="2">Thi đấu theo bảng</option>
                    </select>
                </div>

                <div id="team-size-container">
                    <label for="surname" class="text-gray-700">Số lượng đội tham gia: *</label>
                    <input name="teamsize" type="number" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                </div>
                <div>
                    Nếu giải theo hình thức chia bảng. Giải đấu sẽ được chia 4 đội trên 1 bảng.
                </div>

                <hr>

                <div>
                    Hệ thống sẽ thu phí là <b>10 điểm</b> trên 1 lần đăng giải đấu, số tiền sẽ được trừ sau khi quản trị viên phê duyệt yêu cầu của bạn.
                </div>
                <div>
                    <input type="checkbox" name="checkbox" id="checkbox_id" value="value" required>
                    <label for="checkbox_id">Bạn đã chắc muốn tạo giải đấu này ?</label>
                </div>
                <div style="display: flex; justify-content: center; margin-top: 15px">
                    <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                            style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Tạo giải</button>
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
            };
            reader.readAsDataURL(file);
            updateAvatar = true;
        });
        if (!updateAvatar && imageInput.value != null) {
            imageInput.disabled = true;
        }

        document.getElementById('type-select').addEventListener('change', function () {
            const container = document.getElementById('team-size-container');
            const selectedType = this.value;
            container.innerHTML = '';  // Clear previous content
            if (selectedType === '2') {
                // Create a select element for team sizes
                container.innerHTML = `
            <label for="teamsize-select" class="text-gray-700">Số lượng đội tham gia: *</label>
            <select name="teamsize" id="teamsize-select" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                <option value="8">8 đội</option>
                <option value="16">16 đội</option>
                <option value="32">32 đội</option>
            </select>`;
            } else {
                // Create an input element for team sizes
                container.innerHTML = `
            <label for="teamsize" class="text-gray-700">Số lượng đội tham gia: *</label>
            <input name="teamsize" type="number" id="teamsize" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>`;
            }
        });

    </script>
</html>