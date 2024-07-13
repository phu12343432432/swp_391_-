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
            const cardInput = document.createElement('div');
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

