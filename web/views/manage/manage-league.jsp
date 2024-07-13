<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Timeshare</title>
        <link
            href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
            rel="stylesheet"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
            />
    </head>
    <body>
        <div class="flex">
            <div class="w-2/12 bg-gray-800 text-white min-h-screen p-4" style="background: #ccc; color: #000;">
                <div class="mb-4">
                    <h1 class="text-2xl font-bold flex items-center p-4">
                        <i class="fas fa-building mr-2"></i><b style="color: #000">Footbal League</b>
                    </h1>
                </div>
                <nav class="space-y-2">
                    <a style="text-decoration: none"
                        href="${pageContext.request.contextPath}/admin?action=dashboard"
                        class="block py-2 px-4 rounded text-sm flex items-center "
                        >
                        <i class="fas fa-clipboard mr-2"></i>Dashboard
                    </a>
                    <a style="text-decoration: none"
                        href="ManageLeagueController"
                        class="block py-2 px-4 rounded text-sm  flex items-center "
                        >
                        <i class="fas fa-clipboard mr-2"></i>League Management
                    </a>
                    <a style="text-decoration: none"
                        href="GetAllUser"
                        class="block py-2 px-4 rounded text-sm flex items-center "
                        >
                        <i class="fas fa-user mr-2"></i>User Management
                    </a>

                    <a style="text-decoration: none"
                        href="${pageContext.request.contextPath}/admin?action=list-request-create-league"
                        class="block py-2 px-4 rounded text-sm flex items-center "
                        >
                        <i class="fas fa-user mr-2"></i>Create League Permission
                    </a>
                    <a style="text-decoration: none"
                        href="${pageContext.request.contextPath}/admin/UserWalletManageController"
                        class="block py-2 px-4 rounded text-sm flex items-center"
                        >
                        <i class="fas fa-user mr-2"></i>Wallet Management
                    </a>
                    <a style="text-decoration: none"
                        href="${pageContext.request.contextPath}/auth?action=logout"
                        class="block py-2 px-4 rounded text-sm flex items-center"
                        >
                        <i class="fas fa-sign-out-alt mr-2"></i>Logout
                    </a>

                </nav>
            </div>
            <div class="w-9/12 p-8">
                <div class="flex justify-between mb-4">
                    <h2 class="text-2xl font-bold">Post Management</h2>
                    <form class="relative" action="ManageLeagueController">
                        <input
                            type="text" name="search"
                            class="border border-gray-300 rounded pl-8 pr-4 py-2"
                            placeholder="Search with title..." value="${search}"
                            />
                        <i
                            class="fas fa-search absolute top-1/2 transform -translate-y-1/2 left-2 text-gray-400"
                            ></i>
                        <button type="submit" class="btn btn-success">Search</button>
                    </form>
                </div>
                <div class="grid grid-cols-4 gap-4">
                    <!-- Card 1 -->
                    <c:forEach items="${LIST_LEAGUE}" var="league">
                        <div class="bg-white rounded-lg shadow-md overflow-hidden">
                            <c:choose>
                                <c:when test="${league.image != null}">
                                    <img src="data:image/png;base64,${league.image}" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 100%; min-height: 300px; height: 300px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                </c:when>
                                <c:otherwise>
                                    <img src="https://placehold.co/100x100" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                </c:otherwise>
                            </c:choose>
                            <div class="p-4">
                                <h3 class="text-xl font-bold">${league.name}</h3>
                                <p class="text-gray-600" style="overflow: hidden;
                                   max-height: 50px; height: 50px">
                                    ${league.description}
                                </p>
                            </div>
                            <div class="flex justify-between p-4">
                                <a href="#" data-bs-toggle="modal" data-bs-target="#accepet-${league.id}" aria-hidden="true"
                                   class="text-green-500">
                                    <i class="fas fa-check-circle text-2xl"></i>
                                </a>
                                <!-- accpet form-->
                                <!-- Modal -->
                                <div class="modal fade" id="accepet-${league.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" >Bạn có chắc phê duyệt giải đấu này</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Hành động này sẽ cho phép giải đấu: ${league.name} xuất hiện ở trang homepage.
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <a class="btn btn-success"   href="ChangeStatusLeagueController?action=accept&leagueId=${league.id}">Confirm</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <a  href="#" class="text-red-500" data-bs-toggle="modal" data-bs-target="#reject-${post.postId}" aria-hidden="true">
                                    <i class="fas fa-times-circle text-2xl"></i>
                                </a>

                                <!-- REJECT -->
                                <!-- Modal -->
                                <div class="modal fade" id="reject-${post.postId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" >Từ chối giải đấu này</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Không cho phép giải đấu:  ${league.name}  xuất hiện ở trang homepage
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <a class="btn btn-danger"   href="ChangeStatusLeagueController?action=reject&leagueId=${league.id}">Confirm</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <a class="text-blue-500" data-bs-toggle="modal" data-bs-target="#leaguedetail-${league.id}" aria-hidden="true">
                                    <i class="fas fa-info-circle text-2xl"></i>
                                </a>


                                <div class="modal fade" id="leaguedetail-${league.id}" tabindex="-1" aria-labelledby="post-detail-${post.postId}" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content" style="width: 150%">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Post details</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form class="bg-white shadow-lg rounded-lg p-8 max-w-4xl w-full" action="league" method="POST" enctype="multipart/form-data">
                                                    <input type="hidden" name="action" value="update"/>
                                                    <div class="flex flex-col md:flex-row justify-between items-start">
                                                        <div class="flex flex-col items-center text-center md:text-left md:items-start">
                                                            <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                                                            </h2>
                                                            <c:choose>
                                                                <c:when test="${league.image != null}">
                                                                    <img src="data:image/png;base64,${league.image}" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" style="width: 400px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
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
                                                                <input type="hidden" name="leagueId" value="${league.id}" />
                                                                <div>
                                                                    <label for="surname" class="text-gray-700">Tên giải đấu *</label>
                                                                    <input name="name" value="${league.name}" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                                                                </div>
                                                                <div style="display: flex">
                                                                    <div>
                                                                        <label for="surname" class="text-gray-700">Ngày bắt đầu *</label>
                                                                        <input name="start_date" value="${league.startDate}" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                                                                    </div>
                                                                    <div style="margin-left: 10px">
                                                                        <label for="Phone" class="text-gray-700">Ngày kết thúc *</label>
                                                                        <input name="end_date" value="${league.endDate}" type="datetime-local" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  readonly>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <label for="surname" class="text-gray-700">Địa điểm thi đấu *</label>
                                                                    <input name="address" value="${league.address}" type="text" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
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
                                                                  pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Email phải chứa ký tự '@' và không được chứa các ký tự đặc biệt không hợp lệ." readonly>${league.description}</textarea>
                                                    </div>
                                                    <div>
                                                        <label for="surname" class="text-gray-700">Số lượng đội tham gia: *</label>
                                                        <input name="teamsize" value="${league.teamSize}" type="number" id="surname"  class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                                                    </div>

                                                    <div>
                                                        <label for="surname" class="text-gray-700">Hình thức thi đấu *</label>
                                                        <select name="type" class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"  readonly>
                                                            <option value="Đá xoay vòng" >Đá xoay vòng</option>
                                                            <option value="Thi đấu theo bảng">Thi đấu theo bảng</option>
                                                        </select>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer" style="display: flex;">
                                                <a class="btn btn-success"  href="ChangeStatusLeagueController?action=accept&leagueId=${league.id}">Appove</a>        
                                                <a class="btn btn-danger"  href="ChangeStatusLeagueController?action=reject&leagueId=${league.id}">Reject</a>

                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="mt-4 flex justify-end">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="display: flex">
                            <c:choose>
                                <c:when test ="${selectedPage - 1 < 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">«</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="ManageLeagueController?search=${search}&index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="ManageLeagueController?search=${search}&index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="ManageLeagueController?search=${search}&index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </body>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"
    ></script>
</html>
