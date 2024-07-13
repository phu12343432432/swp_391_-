<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <style>
            .card {
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .card-title {
                font-size: 1.5rem;
                font-weight: bold;
            }

            .card-text {
                font-size: 3rem;
                font-weight: bold;
            }

            .card-icon {
                font-size: 3rem;
                color: #fff;
                background-color: #007bff;
                border-radius: 50%;
                padding: 1rem;
                margin-right: 1rem;
            }
        </style>
    </head>
    <body>
        <jsp:include page="headeradmin.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="navadmin.jsp"/>
                <div  class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-4 mb-4">
                            <div class="card bg-primary text-white">
                                <div class="card-body d-flex align-items-center">
                                    <i class="fas fa-users card-icon"></i>
                                    <div>
                                        <h5 class="card-title">Total Users</h5>
                                        <p class="card-text">${TOTAL_USERS}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-4 mb-4">
                            <div class="card bg-success text-white">
                                <div class="card-body d-flex align-items-center">
                                    <i class="fas fa-trophy card-icon"></i>
                                    <div>
                                        <h5 class="card-title">Active Leagues</h5>
                                        <p class="card-text">${TOTAL_ACTIVE_LEAGUES}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-4 mb-4">
                            <div class="card bg-warning text-white">
                                <div class="card-body d-flex align-items-center">
                                    <i class="fas fa-wallet card-icon"></i>
                                    <div>
                                        <h5 class="card-title">Total Wallet Balance</h5>
                                        <p class="card-text"><fmt:formatNumber value="${TOTAL_WALLET_BALANCE}" type="number" pattern="#,###"/>VND</p>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="col-12 col-md-6 col-lg-4 mb-4">
                            <div class="card card-bg-danger ">
                                <div class="card-body d-flex align-items-center">
                                    <i class="fas fa-wallet card-icon"></i>
                                    <div>
                                        <h5 class="card-title">Total Wallet Balance</h5>
                                        <p class="card-text"><fmt:formatNumber value="${TOTAL_WALLET_BALANCE}" type="number" pattern="#,###"/>VND</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-4 mb-4">
                            <div class="card card-bg-primary ">
                                <div class="card-body d-flex align-items-center">
                                    <i class="fas fa-chart-line card-icon"></i>
                                    <div>
                                        <h5 class="card-title">Monthly Wallet Revenue</h5>
                                        <p class="card-text"><fmt:formatNumber value="${MONTHLY_WALLET_REVENUE}" type="number" pattern="#,###"/>VND</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <canvas id="wallet-revenue" style="width:100%;max-width:700px"></canvas>
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="card-title">Monthly Wallet Orders</h5>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>User</th>
                                                <th>Amount</th>
                                                <th>Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${WALLET_ORDERS}" var="order">
                                                <tr>
                                                    <td>${order.id}</td>
                                                    <td>${order.userWalletId}</td>
                                                    <td>${order.ammount} VND</td>
                                                    <td>${order.createAt}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <script
                    src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
                </script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
                <script type="text/javascript">
                        var chart = document.getElementById('wallet-revenue').getContext('2d');
                        var myChart = new Chart(chart, {
                            type: 'line',
                            data: {
                                labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                                datasets: [{
                                        label: 'Revenue',
                                        data: [${REVENUE_MOUNTH_1}, ${REVENUE_MOUNTH_2}, ${REVENUE_MOUNTH_3}, ${REVENUE_MOUNTH_4}, ${REVENUE_MOUNTH_5}, ${REVENUE_MOUNTH_6}, ${REVENUE_MOUNTH_7}, ${REVENUE_MOUNTH_8}, ${REVENUE_MOUNTH_9}, ${REVENUE_MOUNTH_10}, ${REVENUE_MOUNTH_11}, ${REVENUE_MOUNTH_12}, ],
                                        backgroundColor: 'rgba(0, 156, 255, .5)'
                                    }]
                            },
                            options: {
                                responsive: true
                            }
                        });
                </script>
                </body>
                </html>
