$(document).ready(function() {

    $("h1").click(function() {
        $(this).css("color", "red");
    });

    $("#addFoodButton").click(function() {
        addToBasket("#foodList", "bacon");
    });

    $("#removeFoodButton").click(function() {
        removeFromBasket("#foodList", "bacon");
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

function removeFromBasket(type, foodItem) {
    var images = $(type).children();
    for (image in images) {
      var href = image.attr("href");
      if (href.contains("/" + foodItem + ".png")) {
        image.remove();
        break;
      }
    }
    alert("pause here for a moment");
    // FIXME
    var list = $(type).find("img");
    for (item in list) {
        if (item.attr("href").contains(foodItem)) {
            item.remove();
            break;
        }
    }
}
