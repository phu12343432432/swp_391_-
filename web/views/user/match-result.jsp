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

        .yellowCardInputs > div {
            display: flex;
        }

        .result-container {
            display: flex;
        }
        .team-events {
            flex: 1;
            padding: 20px;
        }
        .team-a {
            text-align: left;
        }
        .team-b {
            text-align: right;
        }
        .event {
            margin-bottom: 10px;
        }
    </style>
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
                                    <input name="scoreA" id="teamAScore"    value="${MATCH.scoreHome}" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
                                </div>

                                <div style="margin: 0 10px;">-</div>

                                <div class="btn-group">
                                    <input name="scoreB"  id="teamBScore" value="${MATCH.scoreAway}" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" readonly>
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
                    <div class="result-container">

                        <div class="team-events team-a">
                            <c:forEach var="event" items="${teamAEvents}">
                                <div class="event">
                                    <c:choose>
                                        <c:when test="${event.ActionType == 'Goal'}">
                                            <div style="display: flex; justify-content: flex-start; align-items: center">
                                                <img style="width: 30px; margin-right: 15px;" src="${pageContext.request.contextPath}/img/match-result/soccer-ball-variant.png"/> 
                                                <b>${event.ActionTime}'</b> -  ${event.PlayerName}  
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>

                                                <c:when test="${event.CardType == 'red'}">
                                                    <div style="display: flex; justify-content: flex-start; align-items: center">
                                                        <img style="width: 30px; margin-right: 15px;" src="${pageContext.request.contextPath}/img/match-result/red-card.png"/> 
                                                        <b>${event.ActionTime}'</b> - ${event.PlayerName}  
                                                    </div>

                                                </c:when>
                                                <c:otherwise>
                                                    <div style="display: flex; justify-content: flex-start; align-items: center">
                                                        <img style="width: 30px; margin-right: 15px;" src="${pageContext.request.contextPath}/img/match-result/yellow-card.png"/> 
                                                        <b>${event.ActionTime}'</b> - ${event.PlayerName}  
                                                    </div>
                                                </c:otherwise>

                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="team-events team-b">
                            <c:forEach var="event" items="${teamBEvents}">
                                <div class="event">
                                    <c:choose>
                                        <c:when test="${event.ActionType == 'Goal'}">
                                            <div style="display: flex; justify-content: flex-end; align-items: center">
                                                <b>${event.ActionTime}'</b> -  ${event.PlayerName}  
                                                <img style="width: 30px; margin-left: 15px;" src="${pageContext.request.contextPath}/img/match-result/soccer-ball-variant.png"/> 
                                            </div>

                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>

                                                <c:when test="${event.CardType == 'red'}">
                                                    <div style="display: flex; justify-content: flex-end; align-items: center">
                                                        <b>${event.ActionTime}'</b> - ${event.PlayerName}  
                                                        <img style="width: 30px; margin-left: 15px;" src="${pageContext.request.contextPath}/img/match-result/red-card.png"/> 
                                                    </div>

                                                </c:when>
                                                <c:otherwise>
                                                    <div style="display: flex; justify-content: flex-end; align-items: center">
                                                        <b>${event.ActionTime}'</b> - ${event.PlayerName}  
                                                        <img style="width: 30px; margin-left: 15px;" src="${pageContext.request.contextPath}/img/match-result/yellow-card.png"/> 
                                                    </div>
                                                </c:otherwise>

                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>            

                    </div>
                </div>



                <div style="display: flex; justify-content: center; margin-top: 15px">
                    <a href="league?action=league-match&leagueId=${MATCH.leagueId}&groupId=${groupId}" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                       style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Trở về</a>
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

    generateGoalInputs();
    generateCardInputs();
    function generateCardInputs(cardType) {
    const cartType = cardType.concat('Cards');
    const cardInput = parseInt(document.getElementById(cartType).value) || 0;
    const cardInputsDiv = document.getElementById(cardType.concat('CardInputs'));
    cardInputsDiv.innerHTML = ''; // Clear existing inputs

    if (cardInput > 0) {
    for (let i = 0; i < cardInput; i++) {
    const selectElement = document.createElement('select');
    selectElement.name = cardType + 'CardPlayer';
    const defaultOption = document.createElement('option');
    const timeInput = document.createElement('input');
    timeInput.name = 'cardTime'
            defaultOption.value = '';
    defaultOption.text = 'Người nhận thẻ'
            selectElement.appendChild(defaultOption);
    // Placeholder: Replace with actual player list
    const playersA = {
    <c:forEach items="${TEAMA}" var="teamA" >
        ${teamA.id} : '${teamA.number}-${teamA.name}',
    </c:forEach>};
            const  playersGotCard = Object.assign(playersA, {   <c:forEach items="${TEAMB}" var="teamB" >
        ${teamB.id} : '${teamB.number}-${teamB.name}',
    </c:forEach>});
            Object.keys(playersGotCard).forEach(key => {
            const option = document.createElement('option');
            option.value = key;
            option.text = playersGotCard[key];
            selectElement.appendChild(option);
            });
            const containDiv = document.createElement('div');
            const textTime = document.createElement('div');
            containDiv.style.display = 'flex';
            containDiv.style.width = '70%';
            cardInputsDiv.appendChild(containDiv);
            containDiv.appendChild(selectElement)
                    containDiv.appendChild(selectElement)

                    containDiv.appendChild('<div>Thoiwf gian</div>')
                    containDiv.appendChild(timeInput);
            cardInputsDiv.appendChild(document.createElement('br'));
            }
            }
            }

            function generateGoalInputs() {
            const teamAScore = parseInt(document.getElementById('teamAScore').value) || 0;
            const teamBScore = parseInt(document.getElementById('teamBScore').value) || 0;
            const goalInputsADiv = document.getElementById('goalInputsTeamA');
            const goalInputsBDiv = document.getElementById('goalInputsTeamB');
            goalInputsADiv.innerHTML = ''; // Clear existing inputs
            goalInputsBDiv.innerHTML = ''; // Clear existing inputs

            // Generate inputs for Team A goals
            if (teamAScore > 0) {
            for (let i = 0; i < teamAScore; i++) {
            const selectElement = document.createElement('select');
            selectElement.name = `teamAGoalPlayer}`;
            const defaultOption = document.createElement('option');
            defaultOption.value = '';
            defaultOption.text = 'Select player (Team A)';
            selectElement.appendChild(defaultOption);
            const players = {
    <c:forEach items="${TEAMA}" var="teamA" >
        ${teamA.id} : '${teamA.number}-${teamA.name}',
    </c:forEach>};
            Object.keys(players).forEach(key => {
            const option = document.createElement('option');
            option.value = key;
            option.text = players[key];
            selectElement.appendChild(option);
            });
            goalInputsADiv.appendChild(selectElement);
            goalInputsADiv.appendChild(document.createElement('br'));
            }
            }

            // Generate inputs for Team B goals
            if (teamBScore > 0) {
            for (let i = 0; i < teamBScore; i++) {
            const selectElement = document.createElement('select');
            selectElement.name = `teamBGoalPlayer`;
            const defaultOption = document.createElement('option');
            defaultOption.value = '';
            defaultOption.text = 'Select player (Team B)';
            selectElement.appendChild(defaultOption);
            // Placeholder: Replace with actual player list for Team B
            const  players = {
    <c:forEach items="${TEAMB}" var="teamB" >
        ${teamB.id} : '${teamB.number}-${teamB.name}',
    </c:forEach>};
            Object.keys(players).forEach(key => {
            const option = document.createElement('option');
            option.value = key;
            option.text = players[key];
            selectElement.appendChild(option);
            });
            goalInputsBDiv.appendChild(selectElement);
            goalInputsBDiv.appendChild(document.createElement('br'));
            }
            }
            }




</script>
</html>