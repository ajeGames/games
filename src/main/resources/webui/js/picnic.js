$(document).ready(function() {

    $("#joinButton").click(function() {
        // create a player on the server -- hold onto player key
        // for now, just echo input field on screen
        $("#playerID").text($("#playerName").val())
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

