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
        .podium {
            margin-top: 15px;
            display: flex;
            align-items: flex-end;
            justify-content: center;
        }
        .position {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-end;
            margin: 0 10px;
        }
        .position img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-bottom: 10px;
        }
        .position .name {
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            margin-top: 10px;
        }
        .position .number {
            font-size: 24px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 10px;
        }
        .first {
            height: 200px;
            width: 150px;
            background-color: gold;
        }
        .second {
            height: 150px;
            width: 150px;
            background-color: silver;
        }
        .third {
            height: 100px;
            width: 150px;
            background-color: #cd7f32;
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
                    <label for="surname" class="text-gray-700">Số lượng cầu thủ yêu cầu: *</label>
                    <input name="team_member_size" value="${USER_LEAGUE.teamMemberSize}" type="number" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                </div>

                <div>
                    <label for="surname" class="text-gray-700">Hình thức thi đấu *</label>
                    <select name="type" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  requried>
                        <c:if test="${USER_LEAGUE.type.equals('2')}">
                            <option value="1" >Đá theo bảng</option>
                        </c:if>
                        <c:if test="${USER_LEAGUE.type.equals('1')}">
                            <option value="1" >Đá xoay vòng</option>
                        </c:if>
                    </select>
                </div>


                <div>
                    <div style="margin: 30px 0px">
                        <a href="league?action=league-rank&leagueId=${USER_LEAGUE.id}" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                           style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Xem bảng xếp hạng</a>

                        <a href="ViewBlogLeagueListController?leagueId=${USER_LEAGUE.id}" class="btn btn-warning">Danh sách blog của giải</a>
                    </div>

                    <label for="surname" class="text-gray-700">Các đội đăng kí:</label>
                    <c:if test="${LEAGUE_TEAM.size() == 0}">
                        <h2>"Chưa có đội bóng nào đăng kí giải đấu này"</h2>
                    </c:if>

                    <c:forEach items="${GROUPS}" var="g">
                        <a class="btn ${g.id == groupId ? 'btn-warning' : 'btn-secondary'}" href="league?action=league-match&leagueId=${USER_LEAGUE.id}&groupId=${g.id}">${g.name}</a>
                    </c:forEach> 

                    <c:forEach items="${LEAGUE_TEAM}" var="team">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="row">
                                <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                    <div class="image">
                                        <img src="data:image/png;base64,${team.image}" alt="Profile picture"  style="width: 100%; height: 100px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="blog-details">
                                        <p class="card-text" style="color: #198754; font-weight: bold;">${league.name}</p>      
                                        <p class="card-text">Tên đội: <b>${team.teamName}</b></p>
                                        <p class="card-text">Đăng kí vào: ${team.registerAt}</p>
                                        <small class="text-muted">${league.type}</small>

                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4" style="">
                                    <div class="d-flex justify-content-between align-items-center" style="margin-top: 15px">
                                        <div class="btn-group">
                                            <a class="btn btn-sm btn-secondary" href="team?action=view-team&id=${team.teamId}" class="btn btn-sm" style="width: 100%">Xem thông tin đội</a>
                                        </div>
                                    </div>

                                    <div class="d-flex justify-content-between align-items-center" style="margin-top: 15px">
                                        <c:if test="${team.status == 0 && USER_LEAGUE.status == 2}">
                                            <div class="btn-group">
                                                <a class="btn btn-sm btn-success" href="league?action=accept-team&teamId=${team.teamId}&leagueId=${leagueId}" class="btn btn-sm" style="width: 100%">Chấp nhận</a>
                                            </div>  
                                        </c:if>
                                        <c:if test="${team.status == 1}">
                                            <div class="btn-group">
                                                <a disalb class="btn btn-sm btn-primary" href="#" class="btn btn-sm" style="width: 100%">Đã xác nhận</a>
                                            </div>  
                                        </c:if>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </c:forEach>

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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</html>