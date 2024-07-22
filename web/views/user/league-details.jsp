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

        <div class="min-h-screen bg-gray-100 flex items-center justify-center px-4" style="
             align-items: flex-start;
             ">
            <%--<c:if test="${TOP_SOCCER.playerName}">--%>
            <div class="league-dashboard " style="margin-right: 40px">
                <div class="top-player-goal" style="background-color: #f97316; padding: 30px 0; margin-bottom: 25px; color: white; border-radius: 10px">
                    <div style="margin: 10px">
                        <div style="display: flex">
                            <h3>VUA PHÁ LƯỚI</h3>
                            <img style="width: 50px; margin-left: 10px;" src="${pageContext.request.contextPath}/img/one.png"/>  
                        </div>
                        <h5>${TOP_SOCCER.playerName}</h5>
                        <div>${TOP_SOCCER.totalGoals} goals</div>
                        <div>${TOP_SOCCER.teamName}</div>
                    </div>

                </div>
                <div class="top-player-goal" style="background-color: #30901c; padding: 30px 0; margin-bottom: 25px; color: white; border-radius: 10px">

                    <div style="margin: 10px">
                        <div style="display: flex">
                            <h3>VUA BÀN THẮNG</h3>
                            <img style="width: 50px; margin-left: 10px;" src="${pageContext.request.contextPath}/img/partners.png"/>  
                        </div>
                        ${TOP_TEAM_GOAL.teamName} - <b>${TOP_TEAM_GOAL.totalGoals}  goals</b>
                    </div>

                </div>

                <div class="top-player-goal" style="background-color: #009CFF; padding: 30px 0; margin-bottom: 25px; color: white; border-radius: 10px">

                    <div style="margin: 10px">
                        <div style="display: flex">
                            <h3>ĐỘI FAIR PLAY</h3>
                            <img style="width: 50px; margin-left: 10px;" src="${pageContext.request.contextPath}/img/fair-play.png"/>  
                        </div>
                        ${TOP_TEAM_FL.teamName} - <b>${TOP_TEAM_FL.totalCards} thẻ</b> 
                    </div>

                </div>

            </div> 
            <%--</c:if>--%>

            <div class="main-content">
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
                            <div style="color: green; margin-top: 10px">${MESSAGE}</div>
                        </div>
                        <div class="mt-8 md:mt-0 md:ml-10 w-full max-w-lg">
                            <!--<form class="space-y-4" action="team" method="POST" >-->                       
                            <div class="space-y-4" >
                                <input type="hidden" name="action" value="update" />         
                                <input type="file" id="image-input" name="image" style="display: none;" value="${TEAM.image}">
                                <input type="hidden" name="leagueId" value="${USER_LEAGUE.id}" />
                                <div>
                                    <label for="surname" class="text-gray-700">Tên giải đấu *</label>
                                    <input name="name" value="${USER_LEAGUE.name}" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                                </div>
                                <div style="display: flex">
                                    <div>
                                        <label for="surname" class="text-gray-700">Ngày bắt đầu *</label>
                                        <input name="start_date" value="${USER_LEAGUE.startDate}" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                                    </div>
                                    <div style="margin-left: 10px">
                                        <label for="Phone" class="text-gray-700">Ngày kết thúc *</label>
                                        <input name="end_date" value="${USER_LEAGUE.endDate}" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  readonly>
                                    </div>
                                </div>
                                <div>
                                    <label for="surname" class="text-gray-700">Địa điểm thi đấu *</label>
                                    <input name="address" value="${USER_LEAGUE.address}" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
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
                                  pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Email phải chứa ký tự '@' và không được chứa các ký tự đặc biệt không hợp lệ." readonly>${USER_LEAGUE.description}</textarea>
                    </div>
                    <div>
                        <label for="surname" class="text-gray-700">Số lượng đội tham gia: *</label>
                        <input name="teamsize" value="${USER_LEAGUE.teamSize}" type="number" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
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

                    <c:if test="${USER_LEAGUE.type.equals('2') && USER_LEAGUE.status == 5}">
                    <div>
                        <div class="podium">
                            <div class="position second">
                                <img src="${pageContext.request.contextPath}/img/match-result/huychuongbac.jpg" alt="Second Place">
                                <div class="name">[${TEAM2.name}]</div>     
                                <div class="number">2</div>
                            </div>
                            <div class="position first">
                                <img src="${pageContext.request.contextPath}/img/match-result/huychuongvang.png" alt="First Place">
                                <div class="name">[${TEAM1.name}]</div>
                                <div class="number">1</div>
                            </div>
                            <div class="position third">
                                <img src="${pageContext.request.contextPath}/img/match-result/huychuongdong.jpg" alt="Third Place">
                                <div class="name">
                                    <c:forEach items="${TEAM3}" var="team">
                                        [${team.name}]
                                    </c:forEach>
                                </div>
                                <div class="number">3</div>
                            </div>
                        </div>
                    </div>
                </c:if>


                    <div>
                        <div style="margin: 30px 0px">
                            <a href="league?action=league-rank&leagueId=${USER_LEAGUE.id}" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                               style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Xem bảng xếp hạng</a>
                        </div>

                        <label for="surname" class="text-gray-700">Các đội đăng kí:</label>
                        <c:choose>
                            <c:when test="${REGISTERED}">
                                <c:if test="${USER_LEAGUE.status != 5 && USER_LEAGUE.status != 4 }">
                                    <div></div>
                                    <button class="btn btn-sm btn-primary" class="btn btn-sm" style="width: 200px">Đang chờ phê duyệt</button>
                                </c:if>

                            </c:when>
                            <c:otherwise>
                                <c:if test="${LEAGUE_TEAM.size() == 0}">
                                    <br/>
                                    <h2>"Chưa có đội bóng nào đăng kí giải đấu này"</h2>
                                </c:if>
                                <%-- Cho nay a chua nghi ra cach ok ho --%>
                                <c:if test="${USER_LEAGUE.status != 5 && USER_LEAGUE.status != 4 && USER_LEAGUE.status != 6 && USER_LEAGUE.status != 8}">
                                    <a class="btn btn-sm btn-warning" href="league?action=register&leagueId=${leagueId}" class="btn btn-sm" style="width: 200px">Đăng kí ngay</a>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

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
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <div style="display: flex; justify-content: center; margin-top: 15px">
                        <c:if test="${USER_LEAGUE.status == 4}">
                            <a href="league?action=league-match&leagueId=${USER_LEAGUE.id}&groupId=${groupId}"  class="btn btn-primary" >Đang diễn ra - xem chi tiết</a>
                        </c:if> 
                        <c:if test="${USER_LEAGUE.status == 5}">
                            <a  href="league?action=league-match&leagueId=${USER_LEAGUE.id}&groupId=${groupId}"  class="btn btn-danger">Đã kết thúc - xem chi tiết</a>
                        </c:if> 
                        <c:if test="${USER_LEAGUE.status == 6}">
                            <div>
                                <a class="btn btn-danger" href="league?action=knockout-stage&leagueId=${USER_LEAGUE.id}&groupId=${groupId}">Vòng knock out</a>
                            </div> 
                        </c:if>
                        <c:if test="${USER_LEAGUE.status == 8}">
                            <div>
                                <a class="btn btn-danger" href="league?action=view-final&leagueId=${USER_LEAGUE.id}&groupId=${groupId}">Trận chung kết</a>
                            </div> 
                        </c:if>
                    </div>
                </form>

                <c:forEach items="${COMMENTS}" var="comment">
                    <div class="row p-3" id="faq">
                        <div class="col-md-1">
                            <c:choose>
                                <c:when test="${comment.avatar != null}">
                                    <img  src="data:image/png;base64,${comment.avatar}" alt="" width="100"  class="rounded-circle reviews" />
                                </c:when>
                                <c:otherwise>
                                    <img src="https://placehold.co/100x100" width="50" >
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-11">
                            <p class="heading-md">name: ${comment.userName}</p>
                            <span style="color: gray">${comment.createAt}</span>
                            <p style="padding-top: 10px; font-size: 18px">
                                ${comment.content}
                            </p>
                        </div>
                    </div>
                </c:forEach>

                <nav aria-label="Page navigation example" style="display: flex; justify-content:center;margin-top: 15px;">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test ="${selectedPage - 1 < 1}">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#"><<</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="league?action=view-league&leagueId=${USER_LEAGUE.id}&index=${selectedPage-1}"><<</a></li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="i" begin="1" end="${endP}">
                            <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="league?action=view-league&leagueId=${USER_LEAGUE.id}&index=${i}">${i}</a> <li>
                            </c:forEach>
                            <c:choose>
                                <c:when test ="${selectedPage >= endP}">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">>></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="league?action=view-league&leagueId=${USER_LEAGUE.id}&index=${selectedPage+1}">>></a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </nav>
                <div class=" p-3">
                    <c:choose>
                        <c:when test="${REGISTERED}">
                            <form action="LeagueCommentController" method="POST">
                                <div class="form-group">
                                    <input type="hidden" name="leagueId" value="${USER_LEAGUE.id}"/>
                                    <label for="exampleFormControlTextarea1">Your Feedback</label >
                                    <textarea required=""
                                              class="form-control"
                                              id="feedback" name="feedback"
                                              rows="3"
                                              ></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary mt-2" >
                                    <div style="dislay: flex">
                                        <!--                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                                                                        <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576zm6.787-8.201L1.591 6.602l4.339 2.76z"></path>
                                                                        </svg>-->
                                        <div>
                                            Send
                                        </div>
                                    </div>
                                </button>
                            </form>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>


                </div>   
            </div>
            <div class="blog-leauge" style="margin-left: 40px; width: 30%">
                <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    </thead>
                    <tbody>
                        <c:forEach items="${BLOG_LEAGUE}" var="blog" varStatus="status">


                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                                <th scope="row" class="px-6 py-4 ">
                                    <a href="blog-league?action=view&blogId=${blog.id}&leagueId=${blog.leagueId}" style="text-decoration: none">
                                        <img src="data:image/png;base64,${blog.image}" alt="Profile picture" id="profile-picture"  style="width: 100px;  cursor: pointer">
                                    </a>

                                </th>
                                <td class="px-6 py-4 px-6 py-4 font-medium text-gray-900 whitespace-wrap">
                                    ${blog.title}
                                </td>
                                <td class="px-6 py-4 blog-content" style="
                                    overflow: hidden;
                                    height: 80px;
                                    ">>
                                    ${blog.description}
                                </td>

                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
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