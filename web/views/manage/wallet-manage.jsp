<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Wallet Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="headeradmin.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navadmin.jsp"/>

                <div  class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <h3>Wallet Management</h3>

                    <h4>Wallet Orders</h4>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Image</th>
                                <th>User</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${WALLET_ORDERS}" var="order">
                                <tr>
                                    <td>${order.id}</td>      
                                    <td>  <c:choose>
                                            <c:when test="${order.image != null}">
                                                <img src="data:image/png;base64,${order.image}" style="width: 100px"  alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" >
                                            </c:when>
                                            <c:otherwise>
                                                <img src="https://placehold.co/100x100" style="width: 100px" alt="Profile picture" id="profile-picture" class=" border-3 border-green-500 p-1 mb-3" >
                                            </c:otherwise>
                                        </c:choose></td>
                                    <td>${order.email}</td>
                                    <td><fmt:formatNumber value="${order.ammount}" type="number" pattern="#,###"/>VND</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.status == 0}">Pending</c:when>
                                            <c:when test="${order.status == 1}">Approved</c:when>
                                            <c:when test="${order.status == 2}">Rejected</c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${order.status == 0}">
                                            <form action="UserWalletManageController" method="post">
                                                <input type="hidden" name="action" value="approve">
                                                <input type="hidden" name="orderId" value="${order.id}">
                                                <button type="submit" class="btn btn-success">Approve</button>
                                            </form>
                                            <form action="UserWalletManageController" method="post">
                                                <input type="hidden" name="action" value="reject">
                                                <input type="hidden" name="orderId" value="${order.id}">
                                                <button type="submit" class="btn btn-danger">Reject</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="display: flex">

                            <c:choose>
                                <c:when test ="${selectedPage - 1 < 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">«</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="UserWalletManageController?index=${selectedPage-1}">«</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${endP}">
                                <li class="page-item ${i == selectedPage ? "active" : "" }"> <a class="page-link" href="UserWalletManageController?index=${i}">${i}</a> <li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${selectedPage >= endP}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">»</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="UserWalletManageController?index=${selectedPage+1}">»</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>

                    <h4>User Wallets</h4>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>User</th>
                                <th>Amount</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${USER_WALLETS}" var="wallet">
                                <tr>
                                    <td>${sessionScope.USER.firstName} ${sessionScope.USER.lastName}</td>
                                    <td>${wallet.ammount}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${wallet.status == 0}">Disabled</c:when>
                                            <c:when test="${wallet.status == 1}">Active</c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
