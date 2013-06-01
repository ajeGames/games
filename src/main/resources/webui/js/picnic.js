$(document).ready(function() {

    $("h1").click(function() {
        $(this).css("color", "red");
    });

    $("#addFoodButton").click(function() {
        addToBasket("#foodList", "bacon");
    });

    $("#addDrinkButton").click(function() {
        addToBasket("#drinkList", "water");
    });

    $("#addSupplyButton").click(function() {
        addToBasket("#supplyList", "sunscreen");
    });
});

function addToBasket(type, foodItem) {
    $(type).append('<img src="img/' + foodItem + '.png" width="120"/>');
}
