package com.ajegames.picnic;

import junit.framework.TestCase;

import java.util.List;

public class BasketTest extends TestCase {

  public void testGatherItem() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("peanuts"));
    assertEquals("expect one item in basket", 1, basket.getItemCount());
  }

  public void testGatherMultipleItems() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("peanuts"));
    basket.gatherItem(Item.createFood("popcorn"));
    basket.gatherItem(Item.createFood("potato chips"));
    basket.gatherItem(Item.createFood("pretzels"));
    assertEquals("expect one item in basket", 4, basket.getItemCount());
  }

  public void testItemCounts() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("peanuts"));
    basket.gatherItem(Item.createFood("popcorn"));
    basket.gatherItem(Item.createDrink("iced tea"));
    basket.gatherItem(Item.createDrink("fruit juice"));
    basket.gatherItem(Item.createDrink("mai tais"));
    basket.gatherItem(Item.createSupply("napkins"));
    assertEquals("should have 2 food items", 2, basket.getFoodCount());
    assertEquals("should have 3 drink items", 3, basket.getDrinkCount());
    assertEquals("should have 1 supply item", 1, basket.getSupplyCount());
  }

  public void testItemCountsWhenBasketEmpty() {
    Basket basket = new Basket();
    assertEquals("should have none", 0, basket.getItemCount());
    assertEquals("should have none", 0, basket.getFoodCount());
    assertEquals("should have none", 0, basket.getDrinkCount());
    assertEquals("should have none", 0, basket.getSupplyCount());
  }

  public void testHasPrevention() {
    Nuisance rain = Nuisance.createWipeOut("rain");
    Prevention umbrella = Prevention.createPrevention("umbrella", rain);

    Basket basket = new Basket();
    basket.gatherItem(umbrella);
    assertTrue("umbrella should prevent rain", basket.hasPrevention(rain));
  }

  public void testHasPreventionWhenPlayerHasNothing() {
    Nuisance rain = Nuisance.createWipeOut("rain");
    Basket basket = new Basket();
    assertFalse("umbrella should prevent rain", basket.hasPrevention(rain));
  }

  public void testHasPreventionWhenPlayerDoesNot() {
    Nuisance rain = Nuisance.createWipeOut("rain");

    Basket basket = new Basket();
    basket.gatherItem(Item.createDrink("sweet tea"));
    basket.gatherItem(Item.createFood("popcorn"));
    assertFalse("umbrella should prevent rain", basket.hasPrevention(rain));
  }

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
    assertTrue(items.contains(peanuts));
    assertTrue(items.contains(popcorn));

    items = basket.getDrinks();
    assertTrue(items.contains(icedTea));
    assertTrue(items.contains(fruitJuice));
    assertTrue(items.contains(maiTais));

    items = basket.getSupplies();
    assertTrue(items.contains(napkins));

    items = basket.getPreventions();
    assertTrue(items.contains(bugSpray));
  }

  public void testRemoveItem() {
    Basket basket = new Basket();

    Item peanuts = Item.createFood("peanuts");
    Item popcorn = Item.createFood("popcorn");
    basket.gatherItem(peanuts);
    basket.gatherItem(popcorn);

    // different instance but ought to be equivalent
    Item peanutsTwin = Item.createFood("peanuts");
    basket.removeItem(peanutsTwin);

    assertFalse(basket.holdsItem(peanuts));
    assertTrue(basket.holdsItem(popcorn));
  }

  public void testRemoveItemOfType() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("hot dogs"));
    basket.gatherItem(Item.createFood("chicken"));
    basket.gatherItem(Item.createFood("tacos"));
    basket.gatherItem(Item.createFood("pizza"));
    assertEquals(4, basket.getFoodCount());

    basket.removeItemOfType(ItemType.FOOD);
    assertEquals(3, basket.getFoodCount());
    assertEquals(3, basket.getFoods().size());
  }

  public void testWipeOut() {
    Basket basket = new Basket();
    basket.gatherItem(Item.createFood("hot dogs"));
    basket.gatherItem(Item.createFood("chicken"));
    basket.gatherItem(Item.createDrink("milk tea"));
    basket.gatherItem(Item.createSupply("forks"));
    basket.gatherItem(Prevention.createPrevention("bug spray", Nuisance.createWipeOut("mosquitos")));
    basket.empty();
    assertEquals(0, basket.getItemCount());
    assertEquals(0, basket.getSupplyCount());
    assertEquals(0, basket.getFoodCount());
    assertEquals(0, basket.getDrinkCount());
    assertTrue(basket.getDrinks().isEmpty());
    assertTrue(basket.getFoods().isEmpty());
    assertTrue(basket.getSupplies().isEmpty());
    assertTrue(basket.getPreventions().isEmpty());
  }
}
