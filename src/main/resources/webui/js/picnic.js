const serviceUri = '/service/picnic/';

var myPlayerKey, myGameKey, mySpinToken

$(document).ready(function() {

    showPlayerSetupState();

    // there are basically 4 states: player setup, game setup, play, game over

    function showPlayerSetupState() {
        // make sure fields to register player name are visible
        changeMessage("To get started, make up a fun player name.  It does not have to be your real name.");
        $("#playerInfo").show();
        $("#playerName").focus();
        $("#gameInfo").hide();
        $("#playArea").hide();
        clearBlanket();
    }

    function showGameSetupState() {
        // make sure fields to organize or join a game are visible
        // player name should be read-only with option to change player
        changeMessage("Go ahead and start the game.")
        $("#gameInfo").show();
        $("#playerInfo").hide();
        $("#playArea").hide();
        clearBlanket();
    }

    function showPlayState() {
        // make sure spin button, spin results and blanket are visible
        // should be allowed to spin and to quit game
        changeMessage("Try to put together a picnic faster than your opponent.")
        $("#playArea").show();
        $("#spinButton").show();
        $("#latestSpin").hide();
        $("#playAgainButton").hide();
        $("#gameInfo").hide();
    }

    function showGameOverState() {
        // should be allow to organize or join a new game
        changeMessage("We have a winner!  The game is over!!!");
        $("#spinButton").hide();
        $("#playAgainButton").show();
    }

    // end of game states

    $("#registerPlayerButton").click(function() {
        $.post(serviceUri + 'player',
            {
                playerName: $('#playerName').val()
            },
            function(data, status) {
                myPlayerKey = data.key;
                changeMessage('Hello, ' + data.name + '.  Now you can start a new game.');
                showGameSetupState();
            });
    });

    $("#newGameButton").click(function() {
        $.post(serviceUri + 'game',
            {
                playerKey: myPlayerKey
            },
            function(data, status) {
                myGameKey = data.key;
                startGame();
                showPlayState();
            });
    });

    /*
    $("#findOpenGamesButton").click(function() {
        $.get(serviceUri + 'game/list-open', {},
            function(data, status) {
                showOpenGames(data);
            });
    });

     function showOpenGames(data) {
     alert('Not implemented -- should populate open games list.');
     }

     $("#joinGameButton").click(function() {
        alert('not implemented');
    });
     */

    $("#startGameButton").click(function() {
        startGame();
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

    $("#playAgainButton").click(function() {
        showGameSetupState();
    });

    function changeMessage(msg) {
        $("#message").text(msg);
    }

    function startGame() {
        $.ajax({
            url: serviceUri + 'game/' + myGameKey + '/play',
            type: 'PUT',
            error: function(data) {
                changeMessage('Something went wrong -- game was not started!!!');
            },
            success: function(data) {
                $("#playArea").show();
                $("#startGameButton").hide();
            }
        });
    }

    function checkGameStatus() {
        $.get(serviceUri + 'game/' + myGameKey, {},
            function(data, status) {
                if (data.status == "Game over") {
                    showGameOverState();
                }
            });
    }

    function showLatestSpin(spin) {
        $('#latestSpin').attr('src', 'img/' + spin + '.png');
        $("#latestSpin").show();
    }

    function spin() {
        $.ajax({
            type: 'POST',
            url: serviceUri + 'spinner/' + mySpinToken,
            success: function(data, status) {
                showLatestSpin(data.spinResult);
                if (data.remove) {
                    removeFromBlanket(data.itemToActOn);
                    if (data.itemToActOn != null) {
                        changeMessage('Sorry, you lost ' + data.itemToActOn + ' because of ' + data.spinResult + '.');
                    } else {
                        changeMessage('Hmmm, you got ' + data.spinResult + ".  Oh well.");
                    }
                } else if (data.wipeout) {
                    clearBlanket();
                    changeMessage('Oh no.  Your picnic was wiped out.');
                } else {
                    if (!data.spinAgain) {
                        placeOnBlanket(data.spinResult);
                    }
                    changeMessage('You got ' + data.spinResult + '.');
                }
            },
            async: false,
            complete: checkGameStatus
        });
    }

    function placeOnBlanket(item) {
        $("#picnicItems").append( '<img class="itemOnBlanket" src="img/' + item + '.png" width="90" name="' + item + '"/>' );
    }

    function removeFromBlanket(item) {
        var items = $("#picnicItems img");
        items.each(function() {
            if ($(this).attr('name') == item) {
                $(this).remove();
                return false;
            }
            return true;
        });
    }

    function clearBlanket() {
        $("#picnicItems").empty();
    }
});

