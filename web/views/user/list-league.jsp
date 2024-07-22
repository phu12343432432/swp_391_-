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
        <input type="hidden" id="error-team" name="error" value="${TEAMERROR}"/>
        <jsp:include page="header.jsp"/>
        <main>
            <section class="py-5 text-center container">
                <div class="row py-lg-5">
                    <div class="col-lg-6 col-md-8 mx-auto">
                        <h1 class="fw-light">Danh sách giải đấu</h1>
                        <!--<p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>-->
                        <p>
                        <form class="d-flex" action="league">
                            <input type="hidden" name="action" value="search-by-name"/>
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${search}">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                        </p>
                    </div>
                </div>
            </section>

            <div class="album py-5 bg-light">
                <div class="container">
                    <input type="hidden" id="error" name="ERROR" value="${ERROR}"/>              
                    <input type="hidden" id="success" name="MESSAGE" value="${MESSAGE}"/>



                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach items="${LEAGUE}" var="league">
                            <div class="col">
                                <div class="card shadow-sm">
                                    <img src="data:image/png;base64,${league.image}" alt="Profile picture"  style="width: 100%; height: 250px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                    <div class="card-body" style="text-align: center">
                                        <p class="card-text" style="color: #198754; font-weight: bold;">${league.name}</p>      
                                        <p class="card-text">Bắt đầu: ${league.startDate}</p>
                                        <p class="card-text">Kết thúc: ${league.endDate}</p>

                                        <p class="card-text">Hạn đăng kí giải: ${league.dateRegister}</p>

                                        <c:if test="${league.type.equals('1')}">
                                            <small class="text-muted">
                                                Đá xoay vòng
                                            </small>
                                        </c:if>
                                        <c:if test="${league.type.equals('2')}">
                                            Thi đấu theo bảng
                                        </c:if>


                                        <div class="d-flex justify-content-between align-items-center" style="margin-top: 15px">
                                            <div class="btn-group">
                                                <a class="btn btn-sm btn-secondary" href="league?action=view-league&leagueId=${league.id}" class="btn btn-sm" style="width: 100px">Xem chi tiết</a>
                                            </div>
                                            <c:choose>
                                                <c:when test="${USER.id == league.userId}">
                                                    <a class="btn btn-primary" class="btn btn-sm" style="width: 200px">Giải đấu của bạn</a>
                                                </c:when>   
                                                <c:otherwise>
                                                    <c:if test="${league.status == 0}">
                                                        <div style="display: flex">
                                                            <a class="btn  btn-warning" href="league?action=register&leagueId=${league.id}" style="width: 200px">Đăng kí ngay</a>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${league.status == 2}">
                                                        <div style="display: flex">
                                                            <a class="btn  btn-primary" style="width: 200px">Sắp diễn ra</a>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${league.status == 4}">
                                                        <div style="display: flex">
                                                            <a class="btn  btn-warning" style="width: 200px">Đang diễn ra</a>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${league.status == 5}">
                                                        <div style="display: flex">
                                                            <a class="btn btn-danger" style="width: 200px">Đã kết thúc</a>
                                                        </div>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <nav aria-label="Page navigation example" style="display: flex; justify-content:center;">
                <ul class="pagination">
                    <c:choose>
                        <c:when test ="${selectedPage - 1 < 1}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">«</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="league?action=listLeague&search=${search}&index=${selectedPage-1}">«</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="i" begin="1" end="${endP}">
                        <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="league?action=listLeague&search=${search}&index=${i}">${i}</a> <li>
                        </c:forEach>
                        <c:choose>
                            <c:when test ="${selectedPage >= endP}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">»</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="league?action=listLeague&search=${search}&index=${selectedPage+1}">»</a></li>
                            </c:otherwise>
                        </c:choose>
                </ul>
            </nav>
        </main>

        <jsp:include page="footer.jsp"/>

    </body>


    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        var error = document.getElementById('error');
        var message = document.getElementById('success');
        var errorTeam = document.getElementById('error-team');

        if (errorTeam.value) {
            Swal.fire({
                title: errorTeam.value,
                icon: "info",
                showCancelButton: true,
                confirmButtonText: "Tạo team",
            }).then((result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {
                    window.location.href = 'team';
                }
            });
        }
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