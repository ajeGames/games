$(document).ready(function() {

    $("#registerPlayerButton").click(function() {
        // create a player on the server -- hold onto player key
        // for now, just echo input field on screen
        $.post("service/picnic/player",
            {
                playerName: $("#playerName").val()
            },
            function(data, status) {
                $("#playerID").text(data.id);
            });
    });

    $("#spinButton").click(function() {
        $.post("service/picnic/game", {},
            function(data, status) {
                // FIXME -- not being invoked or at least result does not appear in UI
                $("#spinResult").text(data);
            }
        )
    })

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

