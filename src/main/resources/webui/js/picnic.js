const serviceUri = 'http://localhost:8080/service/picnic/';

var myPlayerKey, myGameKey, mySpinToken

$(document).ready(function() {

    $("#registerPlayerButton").click(function() {
        $.post(serviceUri + 'player',
            {
                playerName: $("#playerName").val()
            },
            function(data, status) {
                myPlayerKey = data.key;
                $("#playerKey").text(myPlayerKey);
            });
    });

    $("#newGameButton").click(function() {
        $.post(serviceUri + 'game',
            {
                playerKey: myPlayerKey
            },
            function(data, status) {
                myGameKey = data.key;
                $("#gameKey").text(myGameKey);
                showGameStatus(data.status);
            });
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
            }
        });
    });

    $("#checkTurnButton").click(function() {
        $.get(serviceUri + 'spinner/' + myGameKey,
            {
                playerKey: myPlayerKey
            },
            function(data, status) {
                mySpinToken = data.spinToken;
                $('#spinToken').text(mySpinToken);
            });
    });

    $("#spinButton").click(function() {
        $.ajax({
            type: 'POST',
            url: serviceUri + 'spinner/' + mySpinToken,
            success: function(data, status) {
                showLatestSpin(data.spinResult);
                if (data.remove) {
                    removeFromBasket(data.itemToRemove);
                } else {
                    addToBasket("#foodList", data.spinResult);
                }
            },
            async: false,
            complete: checkGameStatus
        });
    });

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

    function addToBasket(type, foodItem) {
        $(type).append( '<img src="img/' + foodItem + '.png" width="120" name="' + foodItem + '"/>' );
    }

    function removeFromBasket(type, foodItem) {
        var items = $(type + ' img');
        items.each(function() {
            if ($(this).attr('name') == foodItem) {
                $(this).remove();
                return false;
            }
            return true;
        });
    }
});

