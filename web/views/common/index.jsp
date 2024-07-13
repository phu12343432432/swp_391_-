<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Sân Bóng FLeague</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

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
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <style>
                .blog-content {
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
            display: -webkit-box;
        }
        </style>
    </head>

    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner"></div>
        </div>
        <!-- Spinner End -->


        <!-- Topbar Start -->
        <div class="container-fluid bg-dark px-5 d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-8 text-center text-lg-start mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center" style="height: 45px;">
                        <small class="me-3 text-light"><i class="fa fa-map-marker-alt me-2"></i>78 Bình Kỳ, Ngũ Hành Sơn, Đà Nẵng</small>
                        <small class="me-3 text-light"><i class="fa fa-phone-alt me-2"></i>+012 345 6789</small>
                        <small class="text-light"><i class="fa fa-envelope-open me-2"></i>FLeague@gmail.com</small>
                    </div>
                </div>
                <div class="col-lg-4 text-center text-lg-end">
                    <div class="d-inline-flex align-items-center" style="height: 45px;">
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-twitter fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-facebook-f fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-linkedin-in fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-instagram fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle" href=""><i class="fab fa-youtube fw-normal"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Navbar & Carousel Start -->
        <div class="container-fluid position-relative p-0">
            <nav class="navbar navbar-expand-lg navbar-dark px-5 py-3 py-lg-0">
                <a href="index.jsp" class="navbar-brand p-0">
                    <h1 class="m-0"><i class="fa fa-user-tie me-2"></i>FLeague</h1>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto py-0">
                        <a href="index.jsp" class="nav-item nav-link active">Trang Chủ</a>
                        <a href="danhsachtimsan" class="nav-item nav-link">Tìm Sân</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Tìm Đối Thủ</a>
                            <div class="dropdown-menu m-0">
                                <a href="blog.html" class="dropdown-item">Đăng ký làm đối thủ</a>
                                <a href="detail.html" class="dropdown-item">Tìm kiếm đối thủ</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Giải Đấu</a>
                            <div class="dropdown-menu m-0">
                                <a href="price.html" class="dropdown-item">Thông tin các giải đấu</a>
                                <a href="feature.html" class="dropdown-item">Đăng ký tham gia giải đấu</a>
                                <a href="team.html" class="dropdown-item">Team Members</a>
                                <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                <a href="quote.html" class="dropdown-item">Free Quote</a>
                            </div>
                        </div>
                        <a href="service.html" class="nav-item nav-link">Khóa Học Bóng Đá</a>
                        <a href="contact.html" class="nav-item nav-link">Liên Hệ</a>
                    </div >
                    <a href="lichsuhoadon" class="icon-button">
                        <span class="material-icons">shopping_cart</span>
                        <!--                        <span class="icon-button__badge"></span>-->
                    </a>

                    <h1 class="m-4 ">
                        <div class="d-none d-lg-block ">
                            <c:if test="${sessionScope.USER == null}">
                                <a href="${pageContext.request.contextPath}/auth?action=login" class="bi-person custom-icon me-3">Login!</a>
                            </c:if>  
                            <c:if test="${sessionScope.USER != null }">
                                <div class="dropdown" style="width: 250px">
                                    <button type="button" id="dropdownMenuButton1" class="btn dropdown-toggle " data-bs-toggle="dropdown" aria-expanded="false"style="color: #06A3DA;
                                            font-size: 20px;">
                                        ${sessionScope.USER.firstName} ${sessionScope.USER.lastName}
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton1" style="width: 150px">
                                        <a class="dropdown-item " href="profile?action=view">Profile</a>
                                        <a class="dropdown-item " href="profile?action=send-order">
                                            <div style="display: flex">
                                                <div>
                                                    Ví tiền: <fmt:formatNumber value="${WALLET}" pattern="#"/>
                                                </div>
                                                <a class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#addWallet" aria-hidden="true" >+</a> 
                                            </div>
                                        </a>
                                        <a class="dropdown-item " href="auth?action=logout">Logout</a>
                                    </div>
                                </div>
                            </c:if> 
                        </div>
                    </h1>
                </div>
            </nav>

            <div id="header-carousel" class="carousel slide carousel-fade" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="w-100" src="${pageContext.request.contextPath}/img/anh3.jpg" alt="Image">
                        <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                            <div class="p-3" style="max-width: 900px;">
                                <h5 class="text-white text-uppercase mb-3 animated slideInDown">Chuyên Nghiệp & Chất Lượng</h5>
                                <h1 class="display-1 text-white mb-md-4 animated zoomIn">Cập nhật những giải đấu mới nhất</h1>
                                <a href="league?action=listLeague" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Tham gia giải đấu</a>
                                <a href="team" class="btn btn-outline-light py-md-3 px-md-5 animated slideInRight">Team của bạn</a>
                                </br>
                                </br>
                                <a href="league?action=create" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Tạo giải đấu</a>
                                <a href="#lienlac" class="btn btn-outline-light py-md-3 px-md-5 animated slideInRight">Liên lạc</a>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#header-carousel"
                        data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#header-carousel"
                        data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        <!-- Navbar & Carousel End -->


        <!-- Full Screen Search Start -->
        <div class="modal fade" id="searchModal" tabindex="-1">
            <div class="modal-dialog modal-fullscreen">
                <div class="modal-content" style="background: rgba(9, 30, 62, .7);">
                    <div class="modal-header border-0">
                        <button type="button" class="btn bg-white btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body d-flex align-items-center justify-content-center">
                        <div class="input-group" style="max-width: 600px;">
                            <input type="text" class="form-control bg-transparent border-primary p-3" placeholder="Type search keyword">
                            <button class="btn btn-primary px-4"><i class="bi bi-search"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Full Screen Search End -->


        <!-- Facts Start -->
        <div class="container-fluid facts py-5 pt-lg-0">
            <div class="container py-5 pt-lg-0">
                <div class="row gx-0">
                    <div class="col-lg-4 wow zoomIn" data-wow-delay="0.1s">
                        <div class="bg-primary shadow d-flex align-items-center justify-content-center p-4" style="height: 150px;">
                            <div class="bg-white d-flex align-items-center justify-content-center rounded mb-2" style="width: 60px; height: 60px;">
                                <i class="fa fa-users text-primary"></i>
                            </div>
                            <div class="ps-4">
                                <h5 class="text-white mb-0">Lượng người chơi hàng ngày</h5>
                                <h1 class="text-white mb-0" data-toggle="counter-up">500</h1>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow zoomIn" data-wow-delay="0.3s">
                        <div class="bg-light shadow d-flex align-items-center justify-content-center p-4" style="height: 150px;">
                            <div class="bg-primary d-flex align-items-center justify-content-center rounded mb-2" style="width: 60px; height: 60px;">
                                <i class="fa fa-check text-white"></i>
                            </div>
                            <div class="ps-4">
                                <h5 class="text-primary mb-0">Giải đấu đã tổ chức</h5>
                                <h1 class="mb-0" data-toggle="counter-up">696</h1>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow zoomIn" data-wow-delay="0.6s">
                        <div class="bg-primary shadow d-flex align-items-center justify-content-center p-4" style="height: 150px;">
                            <div class="bg-white d-flex align-items-center justify-content-center rounded mb-2" style="width: 60px; height: 60px;">
                                <i class="fa fa-award text-primary"></i>
                            </div>
                            <div class="ps-4">
                                <h5 class="text-white mb-0">Giải thưởng về chất lượng</h5>
                                <h1 class="text-white mb-0" data-toggle="counter-up">35</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Facts Start -->


        <!-- About Start -->
        <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-7">
                        <div class="section-title position-relative pb-3 mb-5">
                            <h5 class="fw-bold text-primary text-uppercase">Thông tin</h5>
                            <h1 class="mb-0">Sân bóng nhân tạo hàng đầu Đà Nẵng</h1>
                        </div>
                        <p class="mb-4">
                            Chào mừng bạn đến với trung tâm quản lý sân bóng đá nhân tạo FLeague Đà Nẵng! Với hơn 10 năm kinh nghiệm, chúng tôi tự hào giới thiệu đến bạn một không gian chất lượng và hiện đại, đáp ứng đầy đủ nhu cầu cho mọi đội bóng và đấu trường thể thao.</p>
                        <div class="row g-0 mb-3">
                            <div class="col-sm-6 wow zoomIn" data-wow-delay="0.2s">
                                <h5 class="mb-3"><i class="fa fa-check text-primary me-3"></i>CHẤT LƯỢNG KIỂM CHỨNG</h5>
                                <h5 class="mb-3"><i class="fa fa-check text-primary me-3"></i>CƠ SỞ VẬT CHẤT HIỆN ĐẠI</h5>
                            </div>
                            <div class="col-sm-6 wow zoomIn" data-wow-delay="0.4s">
                                <h5 class="mb-3"><i class="fa fa-check text-primary me-3"></i> ĐA DẠNG VÀ TIỆN NGHI</h5>
                                <h5 class="mb-3"><i class="fa fa-check text-primary me-3"></i>DỊCH VỤ CHUYÊN NGHIỆP</h5>
                            </div>
                        </div>
                        <div class="d-flex align-items-center mb-4 wow fadeIn" data-wow-delay="0.6s">
                            <div class="bg-primary d-flex align-items-center justify-content-center rounded" style="width: 60px; height: 60px;">
                                <i class="fa fa-phone-alt text-white"></i>
                            </div>
                            <div class="ps-4">
                                <h5 class="mb-2">Liên Hệ Để Đuợc Tư Vấn</h5>
                                <h4 class="text-primary mb-0">+012 345 6789</h4>
                            </div>
                        </div>
                        <a href="danhsachtimsan" class="btn btn-primary py-3 px-5 mt-3 wow zoomIn" data-wow-delay="0.9s">Đặt sân</a>
                    </div>
                    <div class="col-lg-5" style="min-height: 500px;">
                        <div class="position-relative h-100">
                            <img class="position-absolute w-100 h-100 rounded wow zoomIn" data-wow-delay="0.9s" src="${pageContext.request.contextPath}/img/anh6.jpg" style="object-fit: cover;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- About End -->


        <!-- Features Start -->
        <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s" id="lienlac">
            <div class="container py-5">
                <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 1000px;">
                    <h5 class="fw-bold text-primary text-uppercase">Tại sao bạn nên chọn chúng tôi</h5>
                    <h1 class="mb-0">Cỏ nhân tạo, trận đấu thực sự <br> Chúng tôi là lựa chọn của người chiến thắng!</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-4">
                        <div class="row g-5">
                            <div class="col-12 wow zoomIn" data-wow-delay="0.2s">
                                <div class="bg-primary rounded d-flex align-items-center justify-content-center mb-3" style="width: 60px; height: 60px;">
                                    <i class="fa fa-cubes text-white"></i>
                                </div>
                                <h4>Sân cỏ tốt nhất</h4>
                                <p class="mb-0">Sử dụng cỏ chuyên dụng, chăm sóc đặc biệt để đảm bảo độ chơi tốt nhất và an toàn cho cầu thủ</p>
                            </div>
                            <div class="col-12 wow zoomIn" data-wow-delay="0.6s">
                                <div class="bg-primary rounded d-flex align-items-center justify-content-center mb-3" style="width: 60px; height: 60px;">
                                    <i class="fa fa-award text-white"></i>
                                </div>
                                <h4>Đội ngũ nhân viên nhiệt tình, chuyên nghiệp</h4>
                                <p class="mb-0">Sẵn sàng hỗ trợ và giải đáp mọi thắc mắc khách hàng cần, chăm sóc khách hàng mọi lúc mọi nơi </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4  wow zoomIn" data-wow-delay="0.9s" style="min-height: 350px;">
                        <div class="position-relative h-100">
                            <img class="position-absolute w-100 h-100 rounded wow zoomIn" data-wow-delay="0.1s" src="${pageContext.request.contextPath}/img/anh5.jpg" style="object-fit: cover;">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="row g-5">
                            <div class="col-12 wow zoomIn" data-wow-delay="0.4s">
                                <div class="bg-primary rounded d-flex align-items-center justify-content-center mb-3" style="width: 60px; height: 60px;">
                                    <i class="fa fa-users-cog text-white"></i>
                                </div>
                                <h4>Cơ sở vật chất hiện đại</h4>
                                <p class="mb-0">Đảm bảo cơ sở vật chất hiện đại hàng đầu, an toàn dành cho mọi người chơi</p>
                            </div>
                            <div class="col-12 wow zoomIn" data-wow-delay="0.8s">
                                <div class="bg-primary rounded d-flex align-items-center justify-content-center mb-3" style="width: 60px; height: 60px;">
                                    <i class="fa fa-phone-alt text-white"></i>
                                </div>
                                <h4>Hỗ trợ 24/7 </h4>
                                <p class="mb-0">Mọi vấn đề đều được giải quyết nhanh chóng và sớm nhất</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Features Start -->


        <!-- Service Start -->
        <!-- <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
                    <h5 class="fw-bold text-primary text-uppercase">Our Services</h5>
                    <h1 class="mb-0">Custom IT Solutions for Your Successful Business</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.3s">
                        <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                            <div class="service-icon">
                                <i class="fa fa-shield-alt text-white"></i>
                            </div>
                            <h4 class="mb-3">Cyber Security</h4>
                            <p class="m-0">Amet justo dolor lorem kasd amet magna sea stet eos vero lorem ipsum dolore sed</p>
                            <a class="btn btn-lg btn-primary rounded" href="">
                                <i class="bi bi-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.6s">
                        <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                            <div class="service-icon">
                                <i class="fa fa-chart-pie text-white"></i>
                            </div>
                            <h4 class="mb-3">Data Analytics</h4>
                            <p class="m-0">Amet justo dolor lorem kasd amet magna sea stet eos vero lorem ipsum dolore sed</p>
                            <a class="btn btn-lg btn-primary rounded" href="">
                                <i class="bi bi-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.9s">
                        <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                            <div class="service-icon">
                                <i class="fa fa-code text-white"></i>
                            </div>
                            <h4 class="mb-3">Web Development</h4>
                            <p class="m-0">Amet justo dolor lorem kasd amet magna sea stet eos vero lorem ipsum dolore sed</p>
                            <a class="btn btn-lg btn-primary rounded" href="">
                                <i class="bi bi-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.3s">
                        <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                            <div class="service-icon">
                                <i class="fab fa-android text-white"></i>
                            </div>
                            <h4 class="mb-3">Apps Development</h4>
                            <p class="m-0">Amet justo dolor lorem kasd amet magna sea stet eos vero lorem ipsum dolore sed</p>
                            <a class="btn btn-lg btn-primary rounded" href="">
                                <i class="bi bi-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.6s">
                        <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                            <div class="service-icon">
                                <i class="fa fa-search text-white"></i>
                            </div>
                            <h4 class="mb-3">SEO Optimization</h4>
                            <p class="m-0">Amet justo dolor lorem kasd amet magna sea stet eos vero lorem ipsum dolore sed</p>
                            <a class="btn btn-lg btn-primary rounded" href="">
                                <i class="bi bi-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow zoomIn" data-wow-delay="0.9s">
                        <div class="position-relative bg-primary rounded h-100 d-flex flex-column align-items-center justify-content-center text-center p-5">
                            <h3 class="text-white mb-3">Liên Hệ Để Đuợc Tư Vấn</h3>
                            <p class="text-white mb-3">Clita ipsum magna kasd rebum at ipsum amet dolor justo dolor est magna stet eirmod</p>
                            <h2 class="text-white mb-0">+012 345 6789</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- Service End -->

        <!-- Pricing Plan Start -->
        <!-- <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
                    <h5 class="fw-bold text-primary text-uppercase">Pricing Plans</h5>
                    <h1 class="mb-0">We are Offering Competitive Prices for Our Clients</h1>
                </div>
                <div class="row g-0">
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.6s">
                        <div class="bg-light rounded">
                            <div class="border-bottom py-4 px-5 mb-4">
                                <h4 class="text-primary mb-1">Basic Plan</h4>
                                <small class="text-uppercase">For Small Size Business</small>
                            </div>
                            <div class="p-5 pt-0">
                                <h1 class="display-5 mb-3">
                                    <small class="align-top" style="font-size: 22px; line-height: 45px;">$</small>49.00<small
                                        class="align-bottom" style="font-size: 16px; line-height: 40px;">/ Month</small>
                                </h1>
                                <div class="d-flex justify-content-between mb-3"><span>HTML5 & CSS3</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-3"><span>Bootstrap v5</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-3"><span>Responsive Layout</span><i class="fa fa-times text-danger pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-2"><span>Cross-browser Support</span><i class="fa fa-times text-danger pt-1"></i></div>
                                <a href="" class="btn btn-primary py-2 px-4 mt-4">Order Now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.3s">
                        <div class="bg-white rounded shadow position-relative" style="z-index: 1;">
                            <div class="border-bottom py-4 px-5 mb-4">
                                <h4 class="text-primary mb-1">Standard Plan</h4>
                                <small class="text-uppercase">For Medium Size Business</small>
                            </div>
                            <div class="p-5 pt-0">
                                <h1 class="display-5 mb-3">
                                    <small class="align-top" style="font-size: 22px; line-height: 45px;">$</small>99.00<small
                                        class="align-bottom" style="font-size: 16px; line-height: 40px;">/ Month</small>
                                </h1>
                                <div class="d-flex justify-content-between mb-3"><span>HTML5 & CSS3</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-3"><span>Bootstrap v5</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-3"><span>Responsive Layout</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-2"><span>Cross-browser Support</span><i class="fa fa-times text-danger pt-1"></i></div>
                                <a href="" class="btn btn-primary py-2 px-4 mt-4">Order Now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.9s">
                        <div class="bg-light rounded">
                            <div class="border-bottom py-4 px-5 mb-4">
                                <h4 class="text-primary mb-1">Advanced Plan</h4>
                                <small class="text-uppercase">For Large Size Business</small>
                            </div>
                            <div class="p-5 pt-0">
                                <h1 class="display-5 mb-3">
                                    <small class="align-top" style="font-size: 22px; line-height: 45px;">$</small>149.00<small
                                        class="align-bottom" style="font-size: 16px; line-height: 40px;">/ Month</small>
                                </h1>
                                <div class="d-flex justify-content-between mb-3"><span>HTML5 & CSS3</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-3"><span>Bootstrap v5</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-3"><span>Responsive Layout</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <div class="d-flex justify-content-between mb-2"><span>Cross-browser Support</span><i class="fa fa-check text-primary pt-1"></i></div>
                                <a href="" class="btn btn-primary py-2 px-4 mt-4">Order Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- Pricing Plan End -->


        <!-- Quote Start -->
        <!-- <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-7">
                        <div class="section-title position-relative pb-3 mb-5">
                            <h5 class="fw-bold text-primary text-uppercase">Request A Quote</h5>
                            <h1 class="mb-0">Need A Free Quote? Please Feel Free to Contact Us</h1>
                        </div>
                        <div class="row gx-3">
                            <div class="col-sm-6 wow zoomIn" data-wow-delay="0.2s">
                                <h5 class="mb-4"><i class="fa fa-reply text-primary me-3"></i>Reply within 24 hours</h5>
                            </div>
                            <div class="col-sm-6 wow zoomIn" data-wow-delay="0.4s">
                                <h5 class="mb-4"><i class="fa fa-phone-alt text-primary me-3"></i>24 hrs telephone support</h5>
                            </div>
                        </div>
                        <p class="mb-4">Eirmod sed tempor lorem ut dolores. Aliquyam sit sadipscing kasd ipsum. Dolor ea et dolore et at sea ea at dolor, justo ipsum duo rebum sea invidunt voluptua. Eos vero eos vero ea et dolore eirmod et. Dolores diam duo invidunt lorem. Elitr ut dolores magna sit. Sea dolore sanctus sed et. Takimata takimata sanctus sed.</p>
                        <div class="d-flex align-items-center mt-2 wow zoomIn" data-wow-delay="0.6s">
                            <div class="bg-primary d-flex align-items-center justify-content-center rounded" style="width: 60px; height: 60px;">
                                <i class="fa fa-phone-alt text-white"></i>
                            </div>
                            <div class="ps-4">
                                <h5 class="mb-2">Call to ask any question</h5>
                                <h4 class="text-primary mb-0">+012 345 6789</h4>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <div class="bg-primary rounded h-100 d-flex align-items-center p-5 wow zoomIn" data-wow-delay="0.9s">
                            <form>
                                <div class="row g-3">
                                    <div class="col-xl-12">
                                        <input type="text" class="form-control bg-light border-0" placeholder="Your Name" style="height: 55px;">
                                    </div>
                                    <div class="col-12">
                                        <input type="email" class="form-control bg-light border-0" placeholder="Your Email" style="height: 55px;">
                                    </div>
                                    <div class="col-12">
                                        <select class="form-select bg-light border-0" style="height: 55px;">
                                            <option selected>Select A Service</option>
                                            <option value="1">Service 1</option>
                                            <option value="2">Service 2</option>
                                            <option value="3">Service 3</option>
                                        </select>
                                    </div>
                                    <div class="col-12">
                                        <textarea class="form-control bg-light border-0" rows="3" placeholder="Message"></textarea>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-dark w-100 py-3" type="submit">Request A Quote</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- Quote End -->


        <!-- Testimonial Start -->
        <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="section-title text-center position-relative pb-3 mb-4 mx-auto" style="max-width: 600px;">
                    <h5 class="fw-bold text-primary text-uppercase">Người dùng chia sẻ</h5>
                    <h1 class="mb-0">Danh sách bài blog</h1>
                </div>
                <div class="owl-carousel testimonial-carousel wow fadeInUp" data-wow-delay="0.6s">
                    <c:forEach items="${BLOG_LIST}" var="blog">
                        <div class="testimonial-item bg-light my-4">
                            <div class="d-flex align-items-center border-bottom pt-5 pb-4 px-5">
                                <img class="w-full h-48 object-cover" src="data:image/png;base64,${Base64.getEncoder().encodeToString(blog.getImage())}" alt="${blog.getTitle()}" style="width: 60px; height: 60px;">
                                <div class="ps-4">
                                    <h4 class="text-primary mb-1">${blog.title}</h4>
                                    <small class="text-uppercase">${blog.createAt}</small>
                                </div>
                            </div>
                            <div class="pt-4 pb-5 px-5 blog-content" style="height: 80px; overflow: hidden">
                                ${blog.description}
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div> -->
        <!-- Testimonial End
    
    
        <!-- Team Start -->
        <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
                    <h5 class="fw-bold text-primary text-uppercase">Huấn luyện viên</h5>
                    <h1 class="mb-0">Đội ngũ huấn luyện viên trẻ vô cùng chuyên nghiệp</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.3s">
                        <div class="team-item bg-light rounded overflow-hidden">
                            <div class="team-img position-relative overflow-hidden">
                                <img class="img-fluid w-100" src="https://www.rmit.edu.au/content/dam/rmit/vn/en/assets-for-production/images/news/news-thumb-coach-park-hang-seo-from-the-shadow-to-mainstream-phs.jpg" alt="">
                                <div class="team-social">
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-twitter fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-facebook-f fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-instagram fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-linkedin-in fw-normal"></i></a>
                                </div>
                            </div>
                            <div class="text-center py-4">
                                <h4 class="text-primary">Park Hang-seo</h4>
                                <p class="text-uppercase m-0">Huấn luyện viên</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.6s">
                        <div class="team-item bg-light rounded overflow-hidden">
                            <div class="team-img position-relative overflow-hidden">
                                <img class="img-fluid w-300" src="https://image.baogialai.com.vn/w800/dataimages/202109/original/images3083267_CDN.jpg" alt="">
                                <div class="team-social">
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-twitter fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-facebook-f fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-instagram fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-linkedin-in fw-normal"></i></a>
                                </div>
                            </div>
                            <div class="text-center py-4">
                                <h4 class="text-primary">Chu Đình Nghiêm</h4>
                                <p class="text-uppercase m-0">Huấn luyện viên</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.9s">
                        <div class="team-item bg-light rounded overflow-hidden">
                            <div class="team-img position-relative overflow-hidden">
                                <img class="img-fluid w-100" src="https://cdn-i.vtcnews.vn/resize-v1/yKoUY7taF5I8JGlXLXLsc7CsBB0Slio4Ltogdt3D16A/upload/2023/08/28/truong-viet-hoang-111-09305059.jpeg" alt="">
                                <div class="team-social">
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-twitter fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-facebook-f fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-instagram fw-normal"></i></a>
                                    <a class="btn btn-lg btn-primary btn-lg-square rounded" href=""><i class="fab fa-linkedin-in fw-normal"></i></a>
                                </div>
                            </div>
                            <div class="text-center py-4">
                                <h4 class="text-primary">Trương Việt Hoàng</h4>
                                <p class="text-uppercase m-0">Huấn luyện viên</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Team End -->


        <!-- Blog Start -->
        <!-- <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
                    <h5 class="fw-bold text-primary text-uppercase">Latest Blog</h5>
                    <h1 class="mb-0">Read The Latest Articles from Our Blog Post</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.3s">
                        <div class="blog-item bg-light rounded overflow-hidden">
                            <div class="blog-img position-relative overflow-hidden">
                                <img class="img-fluid" src="img/blog-1.jpg" alt="">
                                <a class="position-absolute top-0 start-0 bg-primary text-white rounded-end mt-5 py-2 px-4" href="">Web Design</a>
                            </div>
                            <div class="p-4">
                                <div class="d-flex mb-3">
                                    <small class="me-3"><i class="far fa-user text-primary me-2"></i>John Doe</small>
                                    <small><i class="far fa-calendar-alt text-primary me-2"></i>01 Jan, 2045</small>
                                </div>
                                <h4 class="mb-3">How to build a website</h4>
                                <p>Dolor et eos labore stet justo sed est sed sed sed dolor stet amet</p>
                                <a class="text-uppercase" href="">Read More <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.6s">
                        <div class="blog-item bg-light rounded overflow-hidden">
                            <div class="blog-img position-relative overflow-hidden">
                                <img class="img-fluid" src="img/blog-2.jpg" alt="">
                                <a class="position-absolute top-0 start-0 bg-primary text-white rounded-end mt-5 py-2 px-4" href="">Web Design</a>
                            </div>
                            <div class="p-4">
                                <div class="d-flex mb-3">
                                    <small class="me-3"><i class="far fa-user text-primary me-2"></i>John Doe</small>
                                    <small><i class="far fa-calendar-alt text-primary me-2"></i>01 Jan, 2045</small>
                                </div>
                                <h4 class="mb-3">How to build a website</h4>
                                <p>Dolor et eos labore stet justo sed est sed sed sed dolor stet amet</p>
                                <a class="text-uppercase" href="">Read More <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 wow slideInUp" data-wow-delay="0.9s">
                        <div class="blog-item bg-light rounded overflow-hidden">
                            <div class="blog-img position-relative overflow-hidden">
                                <img class="img-fluid" src="img/blog-3.jpg" alt="">
                                <a class="position-absolute top-0 start-0 bg-primary text-white rounded-end mt-5 py-2 px-4" href="">Web Design</a>
                            </div>
                            <div class="p-4">
                                <div class="d-flex mb-3">
                                    <small class="me-3"><i class="far fa-user text-primary me-2"></i>John Doe</small>
                                    <small><i class="far fa-calendar-alt text-primary me-2"></i>01 Jan, 2045</small>
                                </div>
                                <h4 class="mb-3">How to build a website</h4>
                                <p>Dolor et eos labore stet justo sed est sed sed sed dolor stet amet</p>
                                <a class="text-uppercase" href="">Read More <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- Blog Start -->


        <!-- Vendor Start -->
        <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5 mb-5">
                <div class="bg-white">
                    <div class="owl-carousel vendor-carousel">
                        <img src="${pageContext.request.contextPath}/img/anh8.png" alt="">
                        <img src="${pageContext.request.contextPath}/img/anh9.webp" alt="">
                        <img src="${pageContext.request.contextPath}/img/anh10.png" alt="">
                        <img src="${pageContext.request.contextPath}/img/anh11.png" alt="">
                        <img src="${pageContext.request.contextPath}/img/anh12.png" alt="">
                        <img src="${pageContext.request.contextPath}/img/anh13.png" alt="">
                        <img src="${pageContext.request.contextPath}/img/vendor-7.jpg" alt="">
                        <img src="${pageContext.request.contextPath}/img/vendor-8.jpg" alt="">
                        <img src="${pageContext.request.contextPath}/img/vendor-9.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
        <!-- Vendor End -->


        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light mt-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container">
                <div class="row gx-5">
                    <div class="col-lg-4 col-md-6 footer-about">
                        <div class="d-flex flex-column align-items-center justify-content-center text-center h-100 bg-primary p-4">
                            <a href="index.html" class="navbar-brand">
                                <h1 class="m-0 text-white"><i class="fa fa-user-tie me-2"></i>FLeague</h1>
                            </a>
                            <p class="mt-3 mb-4">Địa chỉ chuyên nghiệp dành cho mọi lứa tuổi, hãy cùng nhau trải nghiệm sân chơi đẳng cấp của chúng tôi ngay nhé.</p>
                            <form action="">
                                <div class="input-group">
                                    <input type="text" class="form-control border-white p-3" placeholder="Your Email">
                                    <button class="btn btn-dark">Sign Up</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-8 col-md-6">
                        <div class="row gx-5">
                            <div class="col-lg-4 col-md-12 pt-5 mb-5">
                                <div class="section-title section-title-sm position-relative pb-3 mb-4">
                                    <h3 class="text-light mb-0">Get In Touch</h3>
                                </div>
                                <div class="d-flex mb-2">
                                    <i class="bi bi-geo-alt text-primary me-2"></i>
                                    <p class="mb-0">78 Bình Kỳ, Ngũ Hành Sơn, Đà Nẵng</p>
                                </div>
                                <div class="d-flex mb-2">
                                    <i class="bi bi-envelope-open text-primary me-2"></i>
                                    <p class="mb-0">FLeague@gmail.com</p>
                                </div>
                                <div class="d-flex mb-2">
                                    <i class="bi bi-telephone text-primary me-2"></i>
                                    <p class="mb-0">+012 345 67890</p>
                                </div>
                                <div class="d-flex mt-4">
                                    <a class="btn btn-primary btn-square me-2" href="#"><i class="fab fa-twitter fw-normal"></i></a>
                                    <a class="btn btn-primary btn-square me-2" href="#"><i class="fab fa-facebook-f fw-normal"></i></a>
                                    <a class="btn btn-primary btn-square me-2" href="#"><i class="fab fa-linkedin-in fw-normal"></i></a>
                                    <a class="btn btn-primary btn-square" href="#"><i class="fab fa-instagram fw-normal"></i></a>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-12 pt-0 pt-lg-5 mb-5">
                                <div class="section-title section-title-sm position-relative pb-3 mb-4">
                                    <h3 class="text-light mb-0">Quick Links</h3>
                                </div>
                                <div class="link-animated d-flex flex-column justify-content-start">
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Trang Chủ</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Tìm Sân</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Tìm Đối Thủ</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Giải Đấu</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Khóa Học Bóng Đá</a>
                                    <a class="text-light" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Liên Hệ</a>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-12 pt-0 pt-lg-5 mb-5">
                                <div class="section-title section-title-sm position-relative pb-3 mb-4">
                                    <h3 class="text-light mb-0">Popular Links</h3>
                                </div>
                                <div class="link-animated d-flex flex-column justify-content-start">
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Trang Chủ</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Tìm Sân</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Tìm Đối Thủ</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Giải Đấu</a>
                                    <a class="text-light mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Khóa Học Bóng Đá</a>
                                    <a class="text-light" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Liên Hệ</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid text-white" style="background: #061429;">
            <div class="container text-center">
                <div class="row justify-content-end">
                    <div class="col-lg-8 col-md-6">
                        <div class="d-flex align-items-center justify-content-center" style="height: 75px;">
                            <p class="mb-0">&copy; <a class="text-white border-bottom" href="#">Your Site Name</a>. All Rights Reserved. 

                                <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                                Designed by <a class="text-white border-bottom" href="https://htmlcodex.com">HTML Codex</a></p>
                            <br>Distributed By: <a class="border-bottom" href="https://themewagon.com" target="_blank">ThemeWagon</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded back-to-top"><i class="bi bi-arrow-up"></i></a>

        <input type="hidden" id="error" name="error" value="${ERROR}"/>
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
            if (error.value) {
                Swal.fire({
                    icon: "info",
                    title: "Oops...",
                    text: error.value,
                    footer: '<a href="profile?action=send-request">Xin cấp quyền tạo giải tại đây</a>'
                });
            }
        </script>
    </body>

</html>