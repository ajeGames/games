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
                $("#gameStatus").text(data.status)
            });
    });

    $("#startGameButton").click(function() {
        $.ajax({
            url: 'service/picnic/game/' + $("#gameKey").text() + '/play',
            type: 'PUT',
            success: function(data) {
                $('#gameStatus').text(data.status)
            },
            error: function(data) {
                alert('Something went wrong -- game was not started!!!');
                $('#gameStatus').text(data.status)
            }
        });
    });

    // 1. click to request turn
    // 2. if granted, change state so that click takes turn
    // 3. once turn is over, change state back to request turn
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
        $.post("service/picnic/spinner/" + $("#spinToken").text(), {},
            function(data, status) {
                $("#spinResult").text(data.spinResult);
                if (data.remove) {
                    removeFromBasket(data.itemToRemove);
                } else {
                    addToBasket("#foodList", data.spinResult);
                }
            });
    });

    $("#addFoodButton").click(function() {
        addToBasket("#foodList", "bacon");
    });

    $("#removeFoodButton").click(function() {
        removeFromBasket("#foodList", "bacon");
    });

    $("#removeDrinkButton").click(function() {
        removeFromBasket("#drinkList", "water");
    });

    $("#removeSupplyButton").click(function() {
        removeFromBasket("#supplyList", "sunscreen");
    });

    $("#addDrinkButton").click(function() {
        addToBasket("#drinkList", "water");
    });

    $("#addSupplyButton").click(function() {
        addToBasket("#supplyList", "sunscreen");
    });

    $("#testAddButton").click(function() {
        addToBasket("#foodList", $("#testItem").val());
    });

    $("#testRemoveButton").click(function() {
        removeFromBasket("#foodList", $("#testItem").val());
    });

    function addToBasket(type, foodItem) {
        $(type).append('<img src="img/' + foodItem + '.png" width="120" name="' + foodItem + '"/>');
    }

    function removeFromBasket(type, foodItem) {
        var blah = $("img").attr("src");
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

