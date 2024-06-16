<%@ page import="java.net.URLEncoder" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

    <head>
        <!-- Title -->
        <title>Dashboard | Quản lí danh sách giải đấu</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Favicon -->
        <link rel="shortcut icon" href="img/favicon.ico">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">

        <!-- DEMO CHARTS -->
        <!-- Template -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/graindashboard/css/graindashboard.css">

    </head>

    <body class="has-sidebar has-fixed-sidebar-and-header">
        <!-- Header -->
        <header class="header bg-body">
            <nav class="navbar flex-nowrap p-0">
                <div class="navbar-brand-wrapper d-flex align-items-center col-auto">
                    <!-- Logo For Mobile View -->
                    <a class="navbar-brand navbar-brand-mobile" href="/">
                        <img class="img-fluid w-100" src="img/logo-mini.png" alt="Graindashboard">
                    </a>
                    <!-- End Logo For Mobile View -->
                    <!-- Logo For Desktop View -->
                    <a class="navbar-brand navbar-brand-desktop" href="/">
                        <img class="side-nav-show-on-closed" src="img/logo-mini.png" alt="Graindashboard"
                             style="width: auto; height: 33px;">
                        <img class="side-nav-hide-on-closed" src="img/logo.png" alt="Graindashboard"
                             style="width: auto; height: 33px;">
                    </a>
                    <!-- End Logo For Desktop View -->
                </div>

                <div class="header-content col px-md-3">
                    <div class="d-flex align-items-center">
                        <!-- Side Nav Toggle -->
                        <a class="js-side-nav header-invoker d-flex mr-md-2" href="#" data-close-invoker="#sidebarClose"
                           data-target="#sidebar" data-target-wrapper="body">
                            <i class="gd-align-left"></i>
                        </a>
                        <!-- End Side Nav Toggle -->
                        <!-- User Notifications -->
                        <div class="dropdown ml-auto">
                            <a id="notificationsInvoker" class="header-invoker" href="#" aria-controls="notifications"
                               aria-haspopup="true" aria-expanded="false" data-unfold-event="click"
                               data-unfold-target="#notifications" data-unfold-type="css-animation"
                               data-unfold-duration="300" data-unfold-animation-in="fadeIn"
                               data-unfold-animation-out="fadeOut">
                                <span
                                    class="indicator indicator-bordered indicator-top-right indicator-primary rounded-circle"></span>
                                <i class="gd-bell"></i>
                            </a>

                            <div id="notifications"
                                 class="dropdown-menu dropdown-menu-center py-0 mt-4 w-18_75rem w-md-22_5rem unfold-css-animation unfold-hidden"
                                 aria-labelledby="notificationsInvoker" style="animation-duration: 300ms;">
                                <div class="card">
                                    <div class="card-header d-flex align-items-center border-bottom py-3">
                                        <h5 class="mb-0">Notifications</h5>
                                        <a class="link small ml-auto" href="#">Clear All</a>
                                    </div>

                                    <div class="card-body p-0">
                                        <div class="list-group list-group-flush">
                                            <div class="list-group-item list-group-item-action">
                                                <div class="d-flex align-items-center text-nowrap mb-2">
                                                    <i class="gd-info-alt icon-text text-primary mr-2"></i>
                                                    <h6 class="font-weight-semi-bold mb-0">New Update</h6>
                                                    <span class="list-group-item-date text-muted ml-auto">just now</span>
                                                </div>
                                                <p class="mb-0">
                                                    Order <strong>#10000</strong> has been updated.
                                                </p>
                                                <a class="list-group-item-closer text-muted" href="#"><i
                                                        class="gd-close"></i></a>
                                            </div>
                                            <div class="list-group-item list-group-item-action">
                                                <div class="d-flex align-items-center text-nowrap mb-2">
                                                    <i class="gd-info-alt icon-text text-primary mr-2"></i>
                                                    <h6 class="font-weight-semi-bold mb-0">New Update</h6>
                                                    <span class="list-group-item-date text-muted ml-auto">just now</span>
                                                </div>
                                                <p class="mb-0">
                                                    Order <strong>#10001</strong> has been updated.
                                                </p>
                                                <a class="list-group-item-closer text-muted" href="#"><i
                                                        class="gd-close"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End User Notifications -->
                        <!-- User Avatar -->
                        <div class="dropdown mx-3 dropdown ml-2">
                            <a id="profileMenuInvoker" class="header-complex-invoker" href="#" aria-controls="profileMenu"
                               aria-haspopup="true" aria-expanded="false" data-unfold-event="click"
                               data-unfold-target="#profileMenu" data-unfold-type="css-animation"
                               data-unfold-duration="300" data-unfold-animation-in="fadeIn"
                               data-unfold-animation-out="fadeOut">
                                <!--img class="avatar rounded-circle mr-md-2" src="#" alt="John Doe"-->
                                <span class="mr-md-2 avatar-placeholder">J</span>
                                <span class="d-none d-md-block">John Doe</span>
                                <i class="gd-angle-down d-none d-md-block ml-2"></i>
                            </a>

                            <ul id="profileMenu"
                                class="unfold unfold-user unfold-light unfold-top unfold-centered position-absolute pt-2 pb-1 mt-4 unfold-css-animation unfold-hidden fadeOut"
                                aria-labelledby="profileMenuInvoker" style="animation-duration: 300ms;">
                                <li class="unfold-item">
                                    <a class="unfold-link d-flex align-items-center text-nowrap" href="#">
                                        <span class="unfold-item-icon mr-3">
                                            <i class="gd-user"></i>
                                        </span>
                                        My Profile
                                    </a>
                                </li>
                                <li class="unfold-item unfold-item-has-divider">
                                    <a class="unfold-link d-flex align-items-center text-nowrap" href="#">
                                        <span class="unfold-item-icon mr-3">
                                            <i class="gd-power-off"></i>
                                        </span>
                                        Sign Out
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <!-- End User Avatar -->
                    </div>
                </div>
            </nav>
        </header>
        <!-- End Header -->

        <main class="main">
            <!-- Sidebar Nav -->
            <aside id="sidebar" class="js-custom-scroll side-nav">
                <ul id="sideNav" class="side-nav-menu side-nav-menu-top-level mb-0">
                    <!-- Title -->
                    <li class="sidebar-heading h6">Dashboard</li>
                    <!-- End Title -->
                    <!-- Dashboard -->
                    <li class="side-nav-menu-item active">
                        <a class="side-nav-menu-link media align-items-center" href="/">
                            <span class="side-nav-menu-icon d-flex mr-3">
                                <i class="gd-dashboard"></i>
                            </span>
                            <span class="side-nav-fadeout-on-closed media-body">Quản lí danh sách giải đấu</span>
                        </a>
                    </li>
                    <!-- End Dashboard -->
                    <!-- Documentation -->
                    <li class="side-nav-menu-item">
                        <a class="side-nav-menu-link media align-items-center" href="documentation/" target="_blank">
                            <span class="side-nav-menu-icon d-flex mr-3">
                                <i class="gd-file"></i>
                            </span>
                            <span class="side-nav-fadeout-on-closed media-body">Documentation</span>
                        </a>
                    </li>

                </ul>
            </aside>
            <!-- End Sidebar Nav -->

            <div class="content">
                <div class="py-4 px-3 px-md-4">

                    <div class="mb-3 mb-md-4 d-flex justify-content-between">
                        <div class="h3 mb-0">Danh sách giải đấu</div>
                    </div>

                    <div class="row">

                        <div class="col-md-6 col-xl-4 mb-3 mb-xl-4">
                            <!-- Widget -->
                            <div class="card flex-row align-items-center p-3 p-md-4">
                                <div class="icon icon-lg bg-soft-primary rounded-circle mr-3">
                                    <i class="gd-bar-chart icon-text d-inline-block text-primary"></i>
                                </div>
                                <div>
                                    <h4 class="lh-1 mb-1">Chờ duyệt</h4>
                                    <h6 class="mb-0">... Giải</h6>
                                </div>
                                <i class="gd-arrow-up icon-text d-flex text-success ml-auto"></i>
                            </div>
                            <!-- End Widget -->
                        </div>

                        <div class="col-md-6 col-xl-4 mb-3 mb-xl-4">
                            <!-- Widget -->
                            <div class="card flex-row align-items-center p-3 p-md-4">
                                <div class="icon icon-lg bg-soft-secondary rounded-circle mr-3">
                                    <i class="gd-wallet icon-text d-inline-block text-secondary"></i>
                                </div>
                                <div>
                                    <h4 class="lh-1 mb-1">Đang diễn ra</h4>
                                    <h6 class="mb-0">... Giải</h6>
                                </div>
                                <i class="gd-arrow-down icon-text d-flex text-danger ml-auto"></i>
                            </div>
                            <!-- End Widget -->
                        </div>

                        <div class="col-md-6 col-xl-4 mb-3 mb-xl-4">
                            <!-- Widget -->
                            <div class="card flex-row align-items-center p-3 p-md-4">
                                <div class="icon icon-lg bg-soft-warning rounded-circle mr-3">
                                    <i class="gd-money icon-text d-inline-block text-warning"></i>
                                </div>
                                <div>
                                    <h4 class="lh-1 mb-1">Hoàn thành</h4>
                                    <h6 class="mb-0">... giải</h6>
                                </div>
                                <i class="gd-arrow-up icon-text d-flex text-success ml-auto"></i>
                            </div>
                            <!-- End Widget -->
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card mb-3 mb-md-4">
                                <div class="card-header">
                                    <h5 class="font-weight-semi-bold mb-0">Danh sách giải đấu</h5>
                                </div>

                                <div class="card-body pt-0">
                                    <div class="table-responsive-xl">
                                        <table class="table text-nowrap mb-0">
                                            <thead>
                                                <tr>
                                                    <th class="font-weight-semi-bold border-top-0 py-2">#</th>
                                                    <th class="font-weight-semi-bold border-top-0 py-2">Tên</th>
                                                    <th class="font-weight-semi-bold border-top-0 py-2">Người tạo</th>
                                                    <th class="font-weight-semi-bold border-top-0 py-2"></th>
                                                    <th class="font-weight-semi-bold border-top-0 py-2">Trạng thái</th>
                                                    <th class="font-weight-semi-bold border-top-0 py-2">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td class="py-3">
                                                        <div style="text-wrap: wrap;
                                                             max-height: 40px;
                                                             width: 60px;
                                                             text-overflow: ellipsis;
                                                             word-wrap: break-word;
                                                             overflow: hidden;
                                                             display: -webkit-box;
                                                             -webkit-line-clamp: 1;
                                                             -webkit-box-orient: vertical;">
                                                            123
                                                        </div>
                                                    </td>
                                                    <td class="py-3">
                                                        <div>Tên giải đấu</div>
                                                        <div class="text-muted">@item.Username</div>
                                                    </td>
                                                    <td class="py-3"></td>
                                                    <td class="py-3"></td>
                                                    <td class="py-3">

                                                        <span class="badge badge-pill badge-light">Chờ lấy hàng</span>
                                                        <!--                                                      
                                                                <span class="badge badge-pill badge-warning">Vận chuyển</span>
                                                            
                                                          
                                                                <span class="badge badge-pill badge-success">Thành công</span>
                                                            
                                                         
                                                                <span class="badge badge-pill badge-danger">Thất bại</span> -->

                                                    </td>
                                                    <td class="py-3">
                                                        <div class="position-relative">
                                                            <a id="dropDown16Invoker" class="link-dark d-flex" href="#"
                                                               aria-controls="dropDown16" aria-haspopup="true"
                                                               aria-expanded="false"
                                                               data-unfold-target="#dropDown16-1"
                                                               data-unfold-event="click" data-unfold-type="css-animation"
                                                               data-unfold-duration="300" data-unfold-animation-in="fadeIn"
                                                               data-unfold-animation-out="fadeOut">
                                                                <i class="gd-more-alt icon-text"></i>
                                                            </a>
                                                            <ul id="dropDown16-1"
                                                                class="unfold unfold-light unfold-top unfold-right position-absolute py-3 mt-1 unfold-css-animation unfold-hidden fadeOut"
                                                                aria-labelledby="dropDown16Invoker"
                                                                style="min-width: 150px; animation-duration: 300ms; right: 0px;">
                                                                <li class="unfold-item">
                                                                    <a class="unfold-link media align-items-center text-nowrap chi-tiet-link"
                                                                       data-bs-toggle="modal"
                                                                       data-bs-target="#orderDetail-1">
                                                                        <i class="gd-pencil unfold-item-icon mr-3"></i>
                                                                        <span class="media-body">Chi tiết</span>

                                                                    </a>
                                                                </li>
                                                                <li class="unfold-item">
                                                                    <a class="unfold-link media align-items-center text-nowrap update-order"
                                                                       href="#" data-order-id="@item.Id"
                                                                       data-order-status="@item.Status">
                                                                        <i class="gd-pencil unfold-item-icon mr-3"></i>
                                                                        <span class="media-body">Xác nhận</span>
                                                                    </a>
                                                                </li>
                                                                <li class="unfold-item">
                                                                    <a class="unfold-link media align-items-center text-nowrap reject-order"
                                                                       href="#" data-order-id="@item.Id">
                                                                        <i class="gd-close unfold-item-icon mr-3"></i>
                                                                        <span class="media-body">Từ chối</span>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

                <!-- Footer -->
                <footer class="small p-3 px-md-4 mt-auto">
                    <div class="row justify-content-between">
                        <div class="col-lg text-center text-lg-left mb-3 mb-lg-0">
                            <ul class="list-dot list-inline mb-0">
                                <li class="list-dot-item list-dot-item-not list-inline-item mr-lg-2"><a class="link-dark"
                                                                                                        href="#">FAQ</a></li>
                                <li class="list-dot-item list-inline-item mr-lg-2"><a class="link-dark" href="#">Support</a>
                                </li>
                                <li class="list-dot-item list-inline-item mr-lg-2"><a class="link-dark" href="#">Contact
                                        us</a></li>
                            </ul>
                        </div>

                        <div class="col-lg text-center mb-3 mb-lg-0">
                            <ul class="list-inline mb-0">
                                <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i
                                            class="gd-twitter-alt"></i></a></li>
                                <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i
                                            class="gd-facebook"></i></a></li>
                                <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i
                                            class="gd-github"></i></a></li>
                            </ul>
                        </div>

                    </div>
                </footer>
                <!-- End Footer -->
            </div>

        </main>
        <!-- Footer -->
        <footer class="small p-3 px-md-4 mt-auto">
            <div class="row justify-content-between">
                <div class="col-lg text-center text-lg-left mb-3 mb-lg-0">
                    <ul class="list-dot list-inline mb-0">
                        <li class="list-dot-item list-dot-item-not list-inline-item mr-lg-2"><a class="link-dark"
                                                                                                href="#">FAQ</a></li>
                        <li class="list-dot-item list-inline-item mr-lg-2"><a class="link-dark" href="#">Support</a></li>
                        <li class="list-dot-item list-inline-item mr-lg-2"><a class="link-dark" href="#">Contact us</a></li>
                    </ul>
                </div>

                <div class="col-lg text-center mb-3 mb-lg-0">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i class="gd-twitter-alt"></i></a>
                        </li>
                        <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i class="gd-facebook"></i></a>
                        </li>
                        <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i class="gd-github"></i></a></li>
                    </ul>
                </div>

            </div>
        </footer>
        <!-- End Footer -->
        <!-- Modal -->

        <div class="modal fade" id="orderDetail-1" tabindex="-1" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" style="width: 50%">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Chi tiết</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
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
                                                    <input name="start_date" value="${USER_LEAGUE.startDate}" type="date" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                                                </div>
                                                <div style="margin-left: 10px">
                                                    <label for="Phone" class="text-gray-700">Ngày kết thúc *</label>
                                                    <input name="end_date" value="${USER_LEAGUE.endDate}" type="date" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  readonly>
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
                                    <label for="surname" class="text-gray-700">Hình thức thi đấu *</label>
                                    <select name="type" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  readonly>
                                        <option value="Đá xoay vòng" >Đá xoay vòng</option>
                                        <option value="Thi đấu theo bảng">Thi đấu theo bảng</option>
                                    </select>
                                </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

    </body>
    <script src="${pageContext.request.contextPath}/graindashboard/js/graindashboard.js"></script>
    <script src="${pageContext.request.contextPath}/graindashboard/js/graindashboard.vendor.js"></script>

</html>