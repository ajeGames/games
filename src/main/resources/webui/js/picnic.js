const serviceUri = '/service/picnic/';

var myPlayerKey, myGameKey, mySpinToken

$(document).ready(function() {

    resetView();
    changeMessage("Who are you?");

    $("#registerPlayerButton").click(function() {
        $.post(serviceUri + 'player',
            {
                playerName: $('#playerName').val()
            },
            function(data, status) {
                myPlayerKey = data.key;
                changeMessage('Hello, ' + data.name + '!');
                $("#gameInfo").show();
                $("#playerInfo").hide();
            });
    });

    $("#newGameButton").click(function() {
        $.post(serviceUri + 'game',
            {
                playerKey: myPlayerKey
            },
            function(data, status) {
                myGameKey = data.key;
                showGameStatus(data.status);
                $("#gameStatusInfo").show();
                $("#gameInfo").hide();
            });
    });

    $("#findOpenGamesButton").click(function() {
        $.get(serviceUri + 'game/list-open', {},
            function(data, status) {
                showOpenGames(data);
            });
    });

    $("#joinGameButton").click(function() {
        alert('not implemented');
    });

    $("#startGameButton").click(function() {
        $.ajax({
            url: serviceUri + 'game/' + myGameKey + '/play',
            type: 'PUT',
            error: function(data) {
                alert('Something went wrong -- game was not started!!!');
            },
            success: function(data) {
                showGameStatus(data.status);
                $("#playArea").show();
                $("#startGameButton").hide();
            }
        });
    });

    $("#spinButton").click(function() {
        // first check if it's player's turn; ask for spin token
        $.get(serviceUri + 'spinner/' + myGameKey,
            {
                playerKey: myPlayerKey
            },
            function(data, status) {
                mySpinToken = data.spinToken;
                $('#spinToken').text(mySpinToken);
                spin();
            });
    });

    function changeMessage(msg) {
        $("#message").text(msg);
    }

    function showOpenGames(data) {
        alert('Not implemented -- should populate open games list.');
    }

    function checkGameStatus() {
        $.get(serviceUri + 'game/' + myGameKey, {},
            function(data, status) {
                showGameStatus(data.status);
            });
    }

    function showGameStatus(status) {
        $('#gameStatus').text(status);
    }

    function showLatestSpin(spin) {
        $('#latestSpin').attr('src', 'img/' + spin + '.png');
    }

    function spin() {
        $.ajax({
            type: 'POST',
            url: serviceUri + 'spinner/' + mySpinToken,
            success: function(data, status) {
                showLatestSpin(data.spinResult);
                if (data.remove) {
                    removeFromBasket(data.itemToRemove);
                } else {
                    addToBasket(data.spinResult);
                }
            },
            async: false,
            complete: checkGameStatus
        });
    }

    function addToBasket(item) {
        $("#picnicItems").append( '<img class="itemOnBlanket" src="img/' + item + '.png" width="90" name="' + item + '"/>' );
    }

    function removeFromBasket(item) {
        var items = $("#picnicItems img");
        items.each(function() {
            if ($(this).attr('name') == item) {
                $(this).remove();
                return false;
            }
            return true;
        });
    }

    function resetView() {
        $("#gameInfo").hide();
        $("#gameStatusInfo").hide();
        $("#startGameButton").show();
        $("#playArea").hide();
        $("#picnicItems").empty();
    }
});

