<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="main.css" />
    </head>
    <body>
        <!--header area -->
        <jsp:include page="headeradmin.jsp"/>
        <!--header end -->
        <input type="hidden" id="success" name="MESSAGE" value="${MESSAGE}"/>
        <div class="container-fluid">
            <div class="row">
                <%@include file="navadmin.jsp" %>
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="chartjs-size-monitor">
                        <div class="chartjs-size-monitor-expand"><div class=""></div></div>
                        <div class="chartjs-size-monitor-shrink"><div class=""></div></div>
                    </div>
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <div>
                            <h1 class="h2">Blog Management</h1>
                        </div>
                        <div class="col-sm-6 d-flex justify-content-end">
                            <a id="submit" href="${pageContext.request.contextPath}/views/manage/create-blog.jsp" class="btn btn-success mr-2"><i class="material-icons">&#xE147;</i> <span>Add new Blog</span></a>
                            <!--<a id="submit" href="" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>-->
                        </div>
                    </div>

                    <div class="table-responsive">


                        <form action="GetAllUser" class="d-flex" style="margin-bottom: 15px;">
                            <!--<input type="hidden" name="action" />-->
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${search}">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>


                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th scope="col">No</th>      
                                    <th scope="col">Title</th>
                                    <th scope="col">CreateAt</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">
                                        Action
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${BLOG_LIST}" var="blog" varStatus="count">
                                    <tr>
                                        <td>${count.count}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.image != null}">
                                                    <img src="data:image/png;base64,${user.image}" style="width: 100px"  alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" >
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="https://placehold.co/100x100" style="width: 100px" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" >
                                                </c:otherwise>
                                            </c:choose></td>
                                        <td>${blog.description}</td>
                                        <td>${blog.createAt}</td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="display: flex">

                            <c:choose>
                                <c:when test ="${selectedPage - 1 < 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">«</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="GetAllUser?search=${search}&index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="GetAllUser?search=${search}&index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="GetAllUser?search=${search}&index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>



                </main>
            </div>
        </div>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        var error = document.getElementById('error');
        var message = document.getElementById('success');

        if (errorTeam.value) {
            Swal.fire({
                title: errorTeam.value,
                icon: "info",
                showCancelButton: true,
                confirmButtonText: "T?o team",
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
                confirmButtonText: "Xác nh?n",
            })
        }
        if (message.value) {
            Swal.fire({
                title: message.value,
                icon: "success",
                showCancelButton: true,
                confirmButtonText: "Xác nh?n",
            })
        }
    </script>
</html>



