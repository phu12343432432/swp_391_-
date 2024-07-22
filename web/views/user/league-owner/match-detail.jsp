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
            width: 70%;
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
                <input type="hidden" name="groupId" value="${groupId}"/>


                <div class="flex flex-col md:flex-row justify-between items-start">
                    <div class="flex flex-col items-center text-center md:text-left md:items-start">
                        <h2 class="text-2xl text-gray-800 font-semibold mb-4">
                            <i class="fas fa-user-circle mr-2"></i>Kết quả giải đấu
                        </h2>

                    </div>
                </div>
                <div>


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

                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                            <div>Scorce</div>
                            <input name="scoreA" id="teamAScore"   min="0" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required/>
                        </div>

                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                            <div>Yellow Card</div>
                            <input name="scoreA" id="yellowCardA"    min="0" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required/>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                            <div>Red Card</div>
                            <input name="scoreA" id="redCardA"  min="0" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required/>
                        </div>


                    </div>
                    <div id="matchForm">
                        <h4>Bàn thắng</h4>
                        <div id="goalsA"></div>
                        <button type="button" class="btn btn-success" onclick="addGoalInputA()">Thêm bàn thắng</button><br>

                        <h4>Thẻ phạt</h4>
                        <div id="cardsA"></div>
                        <button type="button" class="btn btn-success" onclick="addCardInputA()">Thêm thẻ phạt</button><br>
                    </div>   

                    <div class="row">
                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                            <div class="image">
                                <img src="data:image/png;base64,${MATCH.awayteamImage}" alt="Profile picture"  style="width: 100%; height: 100px; cursor: pointer; margin: 10px auto;border: 2px solid #1b730d">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                            <div class="blog-details">
                                <p class="card-text" style="color: #198754; font-weight: bold;"></p>      
                                <p class="card-text">${MATCH.awayteamName}</b></p>
                                <small class="text-muted">${MATCH.awayteamShortName}</small>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                            <div>Scorce</div>
                            <input name="scoreB" id="teamBScore"  oninput="generateGoalInputs()"  min="0" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                        </div>

                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                            <div>Yellow Card</div>
                            <input name="scoreA" id="yellowCardB"  oninput="generateGoalInputs()" min="0"  type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-2">
                            <div>Red Card</div>
                            <input name="scoreA" id="redCardB"  oninput="generateGoalInputs()"  min="0" type="number"   class="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent" required>
                        </div>
                    </div>
                    <div id="matchForm">
                        <h4>Bàn thắng</h4>
                        <div id="goalsB"></div>
                        <button type="button" class="btn btn-warning" onclick="addGoalInputB()">Thêm bàn thắng</button><br>

                        <h4>Thẻ phạt</h4>
                        <div id="cardsB"></div>
                        <button type="button" class="btn btn-warning" onclick="addCardInputB()">Thêm thẻ phạt</button><br>
                    </div>  
                    <input type="hidden" name="cardQuantity" id="cardQuantity"/>
                    <div style="display: flex; justify-content: center; margin-top: 15px">
                        <button type="submit" href="league?action=update-match&match=${USER_LEAGUE.id}&groupId=${groupId}" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                                style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Cập nhật trận đấu</button>
                    </div>
                </div>
                <div style="display: flex; justify-content: center; margin-top: 15px">
                    <a href="league?action=league-match&leagueId=${MATCH.leagueId}&groupId=${groupId}" class="bg-green-500 text-white px-4 py-2 rounded shadow peer-checked:bg-green-500 transition-colors" 
                       style="background-image: linear-gradient(to right top,#45af2a,#3ba023,#30901c,#268215,#1b730d,#1b730d,#1b730d,#1b730d,#268215,#30901c,#3ba023,#45af2a);">Trở về</a>
                </div>

            </form>
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
                            var goalIndex = 0;
                            var cardIndex = 0;
                            var scoreA = 0;
                            var scoreB = 0;
                            const cardQuantityDiv = document.getElementById('cardQuantity');
                            cardQuantityDiv.value = cardIndex;
                            function addGoalInputA() {
                                var inputScoreA = document.getElementById('teamAScore').value;
                                scoreA++;
                                console.log('scorea', scoreA);
                                console.log('inputvalue', inputScoreA);
                                if (scoreA <= inputScoreA) {
                                    const goalDiv = document.getElementById('goalsA');
                                    const goalInput = document.createElement('div');
                                    goalInput.innerHTML = `
        <div class="row">
            <input type="hidden" name="goalTeamId[` + goalIndex + `]" value="${MATCH.homeTeamId}"/>
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <label style="width: 150px">Player</label>
                    <select class="goalInput"  type="number" name="goalPlayerId[` + goalIndex + `]">
    <c:forEach items="${TEAMA}" var="teamA" >
                    <option value="${teamA.id}">${teamA.number}-${teamA.name} - ${MATCH.hometeamShortName}</option>
    </c:forEach>
                    </select>
            </div>
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">  
                        <label>Time (minute):</label>
                        <input class="goalInput" type="number" min="0" max="130" name="goalTime[` + goalIndex + `]" required>
            </div>
        </div>`;
                                    goalDiv.appendChild(goalInput);
                                    goalIndex++;
                                }
                            }

                            function addGoalInputB() {
                                scoreB++;
                                var inputScoreB = document.getElementById('teamBScore').value;
                                if (scoreB <= inputScoreB) {
                                    const goalDiv = document.getElementById('goalsB');
                                    const goalInput = document.createElement('div');
                                    goalInput.innerHTML = `
                                <div class="row">
            <input type="hidden" name="goalTeamId[` + goalIndex + `]" value="${MATCH.awayTeamId}"/>
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <label style="width: 150px">Player</label>
                    <select class="goalInput"  type="number" name="goalPlayerId[` + goalIndex + `]">
    <c:forEach items="${TEAMB}" var="teamB" >
                    <option value="${teamB.id}">${teamB.number}-${teamB.name} - ${MATCH.awayteamShortName}</option>
    </c:forEach>
                    </select>
            </div>
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">  
                             <label>Time (minute):</label>
                <input class="goalInput" type="number"  min="0" max="130" name="goalTime[` + goalIndex + `]" required><br>
            </div>
        </div>`;
                                    goalDiv.appendChild(goalInput);
                                    goalIndex++;
                                }

                            }

                            var CardA = 0;
                            var yellowCardIndexA = 1;
                            var redCardIndexA = 1;
                            function addCardInputA() {
                                CardA++;
                                var yellowCardA = parseInt(document.getElementById('yellowCardA').value);
                                var redCardA = parseInt(document.getElementById('redCardA').value);
                                var totalCard = yellowCardA + redCardA;

                                if (CardA <= totalCard) {
                                    const cardDiv = document.getElementById('cardsA');
                                    const cardInput = document.createElement('div');
                                    if (yellowCardIndexA <= yellowCardA) {
                                        cardInput.innerHTML = `
                                <div class="row">
                                        <input type="hidden" name="cardTeamId[` + cardIndex + `]" value="${MATCH.homeTeamId}"/>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                             <label style="width: 150px">Player</label>
              <select class="goalInput" type="number" name="cardPlayerId[` + cardIndex + `]">
    <c:forEach items="${TEAMA}" var="teamA" >
        <option class="goalInput" value="${teamA.id}">${teamA.number}-${teamA.name} - ${MATCH.hometeamShortName}</option>
    </c:forEach>
        </select>
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                            <div>Type (yellow/red):</div>
        <input type="text" name="cardType[` + cardIndex + `]" value="yellow" required>
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                            <div>Time (minute):</div>
                                            <input type="number" style="width: 100px" name="cardTime[` + cardIndex + `]" required><br>
                                      </div>
                                </div>
    `;
                                        yellowCardIndexA++;
                                    } else if (redCardIndexA <= redCardA) {
                                        cardInput.innerHTML = `
                                <div class="row">
                                        <input type="hidden" name="cardTeamId[` + cardIndex + `]" value="${MATCH.homeTeamId}"/>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                             <label style="width: 150px">Player</label>
              <select class="goalInput" type="number" name="cardPlayerId[` + cardIndex + `]">
    <c:forEach items="${TEAMA}" var="teamA" >
        <option class="goalInput" value="${teamA.id}">${teamA.number}-${teamA.name} - ${MATCH.hometeamShortName}</option>
    </c:forEach>
        </select>
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                            <div>Type (yellow/red):</div>
        <input type="text" name="cardType[` + cardIndex + `]" value="red" required>
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                            <div>Time (minute):</div>
                                            <input type="number" style="width: 100px" name="cardTime[` + cardIndex + `]" required><br>
                                      </div>
                                </div>
    `;
                                        redCardIndexA++;
                                    }




                                    cardDiv.appendChild(cardInput);
                                    cardIndex++;
                                    cardQuantityDiv.value = cardIndex;
                                }
                            }


                            var CardB = 0;
                            var yellowCardIndexB = 1;
                            var redCardIndexB = 1;
                            function addCardInputB() {
                                CardB++;
                                var yellowCardB = parseInt(document.getElementById('yellowCardB').value);
                                var redCardB = parseInt(document.getElementById('redCardB').value);
                                var totalCard = yellowCardB + redCardB;
                                if (CardB <= totalCard) {
                                    const cardDiv = document.getElementById('cardsB');
                                    const cardInput = document.createElement('div');
                                    cardQuantityDiv.value = cardIndex;
                                    if (yellowCardIndexB <= yellowCardB) {
                                        cardInput.innerHTML = `
                               <div class="row">
                                               <input type="hidden" name="cardTeamId[` + cardIndex + `]" value="${MATCH.awayTeamId}"/>

                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                             <label style="width: 150px">Player</label>
              <select class="goalInput" type="number" name="cardPlayerId[` + cardIndex + `]">
    <c:forEach items="${TEAMB}" var="teamB" >
        <option class="goalInput" value="${teamB.id}">${teamB.number}-${teamB.name} - ${MATCH.awayteamShortName}</option>
    </c:forEach>
        </select>
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                            <div>Type (yellow/red):</div>
                                            <input type="text" name="cardType[` + cardIndex + `]" value="yellow" required/>
                                            
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                          <div>Time (minute):</div>
                                            <input type="number" style="width: 100px" name="cardTime[` + cardIndex + `]" required><br>
                                      </div>
                                </div>
    `;
                                        yellowCardIndexB++;
                                    } else if (redCardIndexB <= redCardB) {
                                        cardInput.innerHTML = `
                               <div class="row">
                                               <input type="hidden" name="cardTeamId[` + cardIndex + `]" value="${MATCH.awayTeamId}"/>

                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                             <label style="width: 150px">Player</label>
              <select class="goalInput" type="number" name="cardPlayerId[` + cardIndex + `]">
    <c:forEach items="${TEAMB}" var="teamB" >
        <option class="goalInput" value="${teamB.id}">${teamB.number}-${teamB.name} - ${MATCH.awayteamShortName}</option>
    </c:forEach>
        </select>
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                            <div>Type (yellow/red):</div>
                                             <input type="text" name="cardType[` + cardIndex + `]" value="red" required/>
                                      </div>
                                      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                          <div>Time (minute):</div>
                                            <input type="number" style="width: 100px" name="cardTime[` + cardIndex + `]" required><br>
                                      </div>
                                </div>
    `;
                                        redCardIndexB++;
                                    }


                                    cardDiv.appendChild(cardInput);
                                    cardIndex++;
                                    cardQuantityDiv.value = cardIndex;
                                }
                            }


</script>
</html>