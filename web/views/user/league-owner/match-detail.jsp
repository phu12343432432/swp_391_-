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
        label {
            display: block;
            margin-bottom: 5px;
        }
        input,
        input[type="text"],
        input[type="number"],
        select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            height: 40px;
        }

        .goalInput {
            width: 60% !important;
            margin: 0 10px !important;
            ;
        }


        button {
            background-color: #007BFF;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .yellowCardInputs > div,
        .cards > div{
            display: flex;
            align-content: center;
        }

    </style>
    <body>

        <jsp:include page="header.jsp"/>

        <div class="min-h-screen bg-gray-100 flex items-center justify-center px-4">
            <form class="bg-white shadow-lg rounded-lg p-8 max-w-4xl w-full" action="league" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="action" value="match-detail"/>
                <input type="hidden" name="matchId" value="${MATCH.id}"/>    
                <input type="hidden" name="leagueId" value="${MATCH.leagueId}"/>
                <input type="hidden" name="teamAId" value="${MATCH.homeTeamId}"/>
                <input type="hidden" name="teamBId" value="${MATCH.awayTeamId}"/>

                <div class="flex flex-col md:flex-row justify-between items-start">
                    <div class="flex flex-col items-center text-center md:text-left md:items-start">
                        <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                            <i class="fas fa-user-circle mr-2"></i>Kết quả giải đấu
                        </h2>

                    </div>
                </div>
                <div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="row">
                            <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                <div class="image">
                                    <img src="data:image/png;base64,${MATCH.hometeamImage}" alt="Profile picture"  style="width: 100%; height: 100px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                                <div class="blog-details">
                                    <p class="card-text" style="color: #198754; font-weight: bold;"></p>      
                                    <p class="card-text">${MATCH.hometeamName}</b></p>
                                    <small class="text-muted">${MATCH.hometeamShortName}</small>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4" style="">
                                <div class="d-flex justify-content-center align-items-center" >
                                    <div class="btn-group">
                                        <h3>Tỉ số trận đấu</h3>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center align-items-center" style="margin-top: 5px">
                                    <div class="btn-group">
                                        <input name="scoreA" id="teamAScore"  oninput="generateGoalInputs()"  value="${MATCH.scoreHome}" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                                    </div>

                                    <div style="margin: 0 10px;">-</div>

                                    <div class="btn-group">
                                        <input name="scoreB"  id="teamBScore" oninput="generateGoalInputs()" value="${MATCH.scoreAway}" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                                <div class="blog-details">
                                    <p class="card-text" style="color: #198754; font-weight: bold;"></p>      
                                    <p class="card-text">${MATCH.awayteamName}</b></p>
                                    <small class="text-muted">${MATCH.awayteamShortName}</small>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                <div class="image">
                                    <img src="data:image/png;base64,${MATCH.awayteamImage}" alt="Profile picture"  style="width: 100%; height: 100px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="matchForm">
                        <h2>Bàn thắng</h2>
                        <div id="goals"></div>
                        <button type="button" class="btn btn-success" onclick="addGoalInput()">Thêm bàn thắng</button><br>

                        <h2>Thẻ phạt</h2>
                        <div id="cards"></div>
                        <button type="button" class="btn btn-success" onclick="addCardInput()">Thêm thẻ phạt</button><br>

                    </div>     
                    <input type="hidden" name="cardQuantity" id="cardQuantity"/>
                    <div style="display: flex; justify-content: center; margin-top: 15px">
                        <button type="submit" href="league?action=update-match&match=${USER_LEAGUE.id}" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                                style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Cập nhật trận đấu</button>
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
    <script>
                            var goalIndex = 0;
                            var cardIndex = 0;
                            function addGoalInput() {
                                const goalDiv = document.getElementById('goals');
                                const goalInput = document.createElement('div');
                                goalInput.style.display = 'flex';
                                goalInput.innerHTML = `
                                    <label>Team</label>
            <select class="goalInput" type="number" name="goalTeamId[` + goalIndex + `]" required>    

            <option value="${MATCH.homeTeamId}">${MATCH.hometeamShortName}</option>  
          <option value="${MATCH.awayTeamId}">${MATCH.awayteamShortName}</option>

</select>
            <label>Player</label>
            <select class="goalInput"  type="number" name="goalPlayerId[` + goalIndex + `]">
        <c:forEach items="${TEAMA}" var="teamA" >
            <option value="${teamA.id}">${teamA.number}-${teamA.name} - ${MATCH.hometeamShortName}</option>
        </c:forEach>};
        <c:forEach items="${TEAMB}" var="teamB" >
            <option value="${teamB.id}">${teamB.number}-${teamB.name} - ${MATCH.awayteamShortName}</option>
        </c:forEach>};
            <select>
            <label>Time (minute):</label>
            <input class="goalInput" type="number" name="goalTime[` + goalIndex + `]" required><br>
        `;
                                goalDiv.appendChild(goalInput);
                                goalIndex++;
                                console.log(goalIndex);
                            }

                            function addCardInput() {
                                const cardDiv = document.getElementById('cards');
                                const cardQuantityDiv = document.getElementById('cardQuantity');
                                cardQuantityDiv.value = cardIndex;
                                const cardInput = document.createElement('div');
                                cardInput.style.display = 'flex';
                                cardInput.innerHTML = `
                                 <div>Team</div>
            <select type="number" name="cardTeamId[` + cardIndex + `]" required>
            <option value="${MATCH.homeTeamId}">${MATCH.hometeamShortName}</option>  
            <option value="${MATCH.awayTeamId}">${MATCH.awayteamShortName}</option>
            </select>
            <label>Player</label>
                  <select class="goalInput" type="number" name="cardPlayerId[` + cardIndex + `]">
        <c:forEach items="${TEAMA}" var="teamA" >
            <option class="goalInput" value="${teamA.id}">${teamA.number}-${teamA.name} - ${MATCH.hometeamShortName}</option>
        </c:forEach>};
        <c:forEach items="${TEAMB}" var="teamB" >
            <option class="goalInput" value="${teamB.id}">${teamB.number}-${teamB.name} - ${MATCH.awayteamShortName}</option>
        </c:forEach>};
            </select>
           
            <div>Type (yellow/red):</div>
            <select type="text" name="cardType[` + cardIndex + `]" required>
            <option value="red">yellow</option>  
            <option value="yellow">red</option>   
            <select>
            <div>Time (minute):</div>
            <input type="number" style="width: 50px" name="cardTime[` + cardIndex + `]" required><br>
        `;
                                cardDiv.appendChild(cardInput);
                                cardIndex++;
                            }


    </script>
</html>