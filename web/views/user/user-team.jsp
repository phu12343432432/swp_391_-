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
                <div class="flex flex-col md:flex-row justify-between items-start" style="display: flex; flex-direction: column">
                    <div style="display: flex;">
                        <div class="flex flex-col items-center text-center md:text-left md:items-start">
                            <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                                <i class="fas fa-user-circle mr-2"></i>Thông tin đội bóng
                            </h2>
                            <c:choose>
                                <c:when test="${TEAM.image != null}">
                                    <img src="data:image/png;base64,${TEAM.image}" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                </c:when>
                                <c:otherwise>
                                    <img src="https://placehold.co/100x100" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                </c:otherwise>
                            </c:choose>


                            <p class="text-gray-700 mb-1" style="margin: 0 auto">${sessionScope.account.userName}</p>
                            <a href="league?action=list-registered">
                                <button class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a); margin:10px auto">
                                    Lịch sử tham gia giải đấu
                                </button>
                            </a>
                            <a href="auth">
                                <button class="bg-green-500 text-white text-lg px-6 py-2 rounded-full shadow-md hover:shadow-lg transition-shadow duration-300 ease-in-out focus:outline-none focus:ring-2 focus:ring-green-700 focus:ring-opacity-50" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a); ">
                                    Trở về trang chủ 
                                </button>
                            </a>    
                            <div style="color: green; margin-top: 10px">${MESSAGE}</div>        
                            <div style="color: red; margin-top: 10px">${ERROR}</div>

                        </div>
                        <div class="mt-8 md:mt-0 md:ml-10 w-full max-w-lg">
                            <!--<form class="space-y-4" action="team" method="POST" >-->                       
                            <form class="space-y-4" action="team" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="update" />         
                                <input type="file" id="image-input" name="image" style="display: none;" value="${TEAM.image}">
                                <input type="hidden" name="teamId" value="${TEAM.id}" />
                                <div>
                                    <label for="surname" class="text-gray-700">Tên đội</label>
                                    <input name="teamName" value="${TEAM.name}" type="text" id="surname" placeholder="Tên đội của bạn" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent">
                                </div>
                                <div>
                                    <label for="surname" class="text-gray-700">Tên viết tắt</label>
                                    <input name="shortName" value="${TEAM.shortName}" type="text" id="surname" placeholder="Tên viết tắt, ví du: BAR, ARG" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" >
                                </div>
                                <div>
                                    <label for="Phone" class="text-gray-700">Số lượng thành viên</label>
                                    <input name="teamsize" value="${TEAM.teamSize}" type="int" id="Phone" placeholder="Số lượng sẽ tự cập nhật với số lượng thành viên" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  required readonly>
                                </div>
                                <div>
                                    <label for="desc" class="text-gray-700">Mô tả về đội của bạn:</label>
                                    <textarea name="desc" id="desc" type="email" id="Email" placeholder="Mô tả về team của bạn" 
                                              class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
                                              pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Email phải chứa ký tự '@' và không được chứa các ký tự đặc biệt không hợp lệ." required>${TEAM.description}</textarea>
                                </div>
                                <button type="submit" class="w-full bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Lưu</button>
                            </form>
                        </div>
                    </div>

                    <div class="container">
                        <form action="team" class="d-flex" style="margin-bottom: 15px;">
                            <!--<input type="hidden" name="" />-->
                            <input class="form-control me-2" type="search" placeholder="Tìm theo tên cầu thủ" aria-label="Search" name="search" value="${search}">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                        <div class="row">
                            <c:if test="${TEAM_MEMBERS ==  null}">
                                <h3>Bạn chưa có thành viên nào trong team</h3>
                            </c:if>
                            <form action="team" method="POST" style="display: flex; justify-content: space-between; align-items: center"  enctype="multipart/form-data">
                                <input type="hidden" name="action" value="add-team-member"/>      
                                <input type="hidden" name="teamId" value="${TEAM.id}" />
                                <div style="width: 20%">
                                    <label for="surname" class="text-gray-700">Hình ảnh</label>
                                    <input type="file" name="file" required>
                                </div>
                                <div>
                                    <label for="surname" class="text-gray-700">Tên</label>
                                    <input name="name" required="" value="" type="text" id="surname" placeholder="Tên cầu thủ" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent">
                                </div>
                                <div>
                                    <label for="surname" class="text-gray-700">Số áo</label>
                                    <input name="number"required value="" type="number" id="surname" placeholder="Số áo cầu của" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" >
                                </div>
                                <button type="submit" class="btn btn-success" style="width: 150px; height: 50px;">Thêm</button>
                            </form>


                            <c:forEach var="member" items="${TEAM_MEMBERS}" varStatus="status">
                                <hr>
                                <div style="display: flex; justify-content: space-between; align-items: center">
                                    <div>
                                        <img src="data:image/png;base64,${member.image}" style="width: 100px; hegiht: 100px"/>
                                    </div>
                                    <div>
                                        <label for="surname" class="text-gray-700">Tên: ${member.name}</label>
                                    </div>
                                    <div>
                                        <label for="surname" class="text-gray-700">Số áo: ${member.number}</label>
                                    </div>
                                    <a data-bs-toggle="modal" data-bs-target="#teamMember-${member.id}" class="btn btn-primary" style="width: 150px; height: 50px;">Xem</a>
                                </div>

                                <!-- Button trigger modal -->
                                <!-- Modal -->
                                <div class="modal fade" id="teamMember-${member.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Thông tin cầu thủ</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form action="team" method="POST"  enctype="multipart/form-data">
                                                <div class="modal-body">
                                                    <div  method="POST" style="display: flex; justify-content: space-between; align-items: center">
                                                        <input type="hidden" name="action" value="update-team-member"/>      
                                                        <input type="hidden" name="id" value="${member.id}" />
                                                        <div>
                                                            <label for="surname" class="text-gray-700">Tên</label>
                                                            <input name="name" required="" value="${member.name}" type="text" id="surname" placeholder="Tên cầu thủ" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent">
                                                        </div>
                                                        <div    style="margin-left: 15px;">
                                                            <label for="surname" class="text-gray-700">Số áo</label>
                                                            <input name="number"required value="${member.number}" type="number" id="surname" placeholder="Số áo cầu của" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" >
                                                        </div>
                                                        <div>
                                                        </div>
                                                      
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <a  href="team?action=delete-team-member&id=${member.id}" class="btn btn-danger">Xóa</a>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>



                        <nav aria-label="Page navigation example" style="display: flex; justify-content:center;margin-top: 15px;">
                            <ul class="pagination">
                                <c:choose>
                                    <c:when test ="${selectedPage - 1 < 1}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#">«</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="team?search=${search}&index=${selectedPage-1}">«</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="i" begin="1" end="${endP}">
                                    <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="team?search=${search}&index=${i}">${i}</a> <li>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test ="${selectedPage >= endP}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#">»</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="team?search=${search}&index=${selectedPage+1}">»</a></li>
                                        </c:otherwise>
                                    </c:choose>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>



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