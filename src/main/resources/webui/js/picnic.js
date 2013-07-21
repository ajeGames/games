var myPlayerKey, myGameKey

$(document).ready(function() {

    $("#registerPlayerButton").click(function() {
        $.post("service/picnic/player",
            {
                playerName: $("#playerName").val()
            },
            function(data, status) {
                myPlayerKey = data.key;
                $("#playerKey").text(myPlayerKey);
            });
    });

    $("#newGameButton").click(function() {
        $.post("service/picnic/game",
            {
                playerKey: myPlayerKey
            },
            function(data, status) {
                myGameKey = data.key;
                $("#gameKey").text(myGameKey);
                $("#gameStatus").text(data.status);
            });
    });

    $("#startGameButton").click(function() {
        $.ajax({
            url: 'service/picnic/game/' + myGameKey + '/play',
            type: 'PUT',
            success: function(data) {
                $('#gameStatus').text(data.status);
            },
            error: function(data) {
                alert('Something went wrong -- game was not started!!!');
                $('#gameStatus').text(data.status);
            }
        });
    });

    $("#checkTurnButton").click(function() {
        $.get('service/picnic/spinner/' + $('#gameKey').text(),
            {
                playerKey: myPlayerKey
            },
            function(data, status) {
                $('#spinToken').text(data.spinToken);
            });
    });

    $("#spinButton").click(function() {
        $.ajax({
            type: 'POST',
            url: "service/picnic/spinner/" + $("#spinToken").text(),
            success: function(data, status) {
                         $("#spinResult").text(data.spinResult);
                         if (data.remove) {
                             removeFromBasket(data.itemToRemove);
                         } else {
                             addToBasket("#foodList", data.spinResult);
                         }
                     },
            async: false,
            complete: checkGameStatus
        });
/*
        $.post("service/picnic/spinner/" + $("#spinToken").text(), {},
            function(data, status) {
                $("#spinResult").text(data.spinResult);
                if (data.remove) {
                    removeFromBasket(data.itemToRemove);
                } else {
                    addToBasket("#foodList", data.spinResult);
                }
            });
        checkGameStatus();
 */
    });

    function checkGameStatus() {
        alert('checking game status with gameKey ' + myGameKey);
        $.get("service/picnic/game/" + myGameKey, {},
            function(data, status) {
                $('#gameStatus').text(data.status);
            });
    }

    function addToBasket(type, foodItem) {
        $(type).append('<img src="img/' + foodItem + '.png" width="120" name="' + foodItem + '"/>');
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

