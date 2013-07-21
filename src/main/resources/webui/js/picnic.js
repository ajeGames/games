$(document).ready(function() {

    $("#registerPlayerButton").click(function() {
        $.post("service/picnic/player",
            {
                playerName: $("#playerName").val()
            },
            function(data, status) {
                $("#playerKey").text(data.key);
            });
    });

    $("#newGameButton").click(function() {
        $.post("service/picnic/game",
            {
                playerKey: $("#playerKey").text()
            },
            function(data, status) {
                $("#gameKey").text(data.key);
                $("#gameStatus").text(data.status);
            });
    });

    $("#startGameButton").click(function() {
        $.ajax({
            url: 'service/picnic/game/' + $("#gameKey").text() + '/play',
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
                playerKey: $('#playerKey').text()
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
        var token = $("#gameToken").text();
        alert('checking game status with gameToken ' + token);
        $.get("service/picnic/game/" + token, {},
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

