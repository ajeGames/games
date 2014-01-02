package com.ajegames.picnic.domain;

import com.ajegames.picnic.domain.*;
import org.testng.annotations.Test;

import java.util.List;

public class BasketTest {

  @Test
  public void testGatherItem() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("peanuts"));
    assert basket.getItemCount() == 1 : "expect one item in basket";
  }

  @Test
  public void testGatherMultipleItems() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("peanuts"));
    basket.gatherItem(Item.createFood("popcorn"));
    basket.gatherItem(Item.createFood("potato chips"));
    basket.gatherItem(Item.createFood("pretzels"));
    assert basket.getItemCount() == 4 : "expect one item in basket";
  }

  @Test
  public void testItemCounts() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("peanuts"));
    basket.gatherItem(Item.createFood("popcorn"));
    basket.gatherItem(Item.createDrink("iced tea"));
    basket.gatherItem(Item.createDrink("fruit juice"));
    basket.gatherItem(Item.createDrink("mai tais"));
    basket.gatherItem(Item.createSupply("napkins"));
    assert basket.getFoodCount() == 2 : "should have 2 food items";
    assert basket.getDrinkCount() == 3 : "should have 3 drink items";
    assert basket.getSupplyCount() == 1 : "should have 1 supply item";
  }

  @Test
  public void testItemCountsWhenBasketEmpty() {
    Basket basket = new Basket();
    assert basket.getItemCount() == 0 : "should have none";
    assert basket.getFoodCount() == 0 : "should have none";
    assert basket.getDrinkCount() == 0 : "should have none";
    assert basket.getSupplyCount() == 0 : "should have none";
  }

  @Test
  public void testHasPrevention() {
    Nuisance rain = Nuisance.createWipeOut("rain");
    Prevention umbrella = Prevention.createPrevention("umbrella", rain);

    Basket basket = new Basket();
    basket.gatherItem(umbrella);
    assert basket.hasPrevention(rain) : "umbrella should prevent rain";
  }

  @Test
  public void testHasPreventionWhenPlayerHasNothing() {
    Nuisance rain = Nuisance.createWipeOut("rain");
    Basket basket = new Basket();
    assert !basket.hasPrevention(rain) : "umbrella should prevent rain";
  }

  @Test
  public void testHasPreventionWhenPlayerDoesNot() {
    Nuisance rain = Nuisance.createWipeOut("rain");

    Basket basket = new Basket();
    basket.gatherItem(Item.createDrink("sweet tea"));
    basket.gatherItem(Item.createFood("popcorn"));
    assert !basket.hasPrevention(rain) : "umbrella should prevent rain";
  }

  @Test
  public void testGetDifferentTypesOfItems() {
    Basket basket = new Basket();

    Item peanuts = Item.createFood("peanuts");
    Item popcorn = Item.createFood("popcorn");
    Item icedTea = Item.createDrink("iced tea");
    Item fruitJuice = Item.createDrink("fruit juice");
    Item maiTais = Item.createDrink("mai tais");
    Item napkins = Item.createSupply("napkins");
    Item bugSpray = Prevention.createPrevention("bug spray", Nuisance.createWipeOut("bugs"));

    basket.gatherItem(peanuts);
    basket.gatherItem(popcorn);
    basket.gatherItem(icedTea);
    basket.gatherItem(fruitJuice);
    basket.gatherItem(maiTais);
    basket.gatherItem(napkins);
    basket.gatherItem(bugSpray);

    List<Item> items = basket.getFoods();
    assert items.contains(peanuts);
    assert items.contains(popcorn);

    items = basket.getDrinks();
    assert items.contains(icedTea);
    assert items.contains(fruitJuice);
    assert items.contains(maiTais);

    items = basket.getSupplies();
    assert items.contains(napkins);

    items = basket.getPreventions();
    assert items.contains(bugSpray);
  }

  @Test
  public void testRemoveItem() {
    Basket basket = new Basket();

    Item peanuts = Item.createFood("peanuts");
    Item popcorn = Item.createFood("popcorn");
    basket.gatherItem(peanuts);
    basket.gatherItem(popcorn);

    // different instance but ought to be equivalent
    Item peanutsTwin = Item.createFood("peanuts");
    basket.removeItem(peanutsTwin);

    assert !basket.holdsItem(peanuts);
    assert basket.holdsItem(popcorn);
  }

  @Test
  public void testRemoveItemOfType() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("hot dogs"));
    basket.gatherItem(Item.createFood("chicken"));
    basket.gatherItem(Item.createFood("tacos"));
    basket.gatherItem(Item.createFood("pizza"));
    assert basket.getFoodCount() == 4;

    basket.removeItemOfType(ItemType.FOOD);
    assert basket.getFoodCount() == 3;
    assert basket.getFoods().size() == 3;
  }

  @Test
  public void testWipeOut() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("hot dogs"));
    basket.gatherItem(Item.createFood("chicken"));
    basket.gatherItem(Item.createDrink("milk tea"));
    basket.gatherItem(Item.createSupply("forks"));
    basket.gatherItem(Prevention.createPrevention("bug spray", Nuisance.createWipeOut("mosquitos")));
    basket.empty();
    assert basket.getItemCount() == 0;
    assert basket.getSupplyCount() == 0;
    assert basket.getFoodCount() == 0;
    assert basket.getDrinkCount() == 0;
    assert basket.getDrinks().isEmpty();
    assert basket.getFoods().isEmpty();
    assert basket.getSupplies().isEmpty();
    assert basket.getPreventions().isEmpty();
  }
}
