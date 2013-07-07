package com.ajegames.picnic;

import junit.framework.TestCase;

import java.util.List;

/**
 * Makes sure that Player behaves as expected.  Player holds picnic items in a basket, so make sure collection and
 * inspection works.
 */
public class PlayerTest extends TestCase {

  private Player genPlayer(String name) {
    return new Player(name);
  }

  public void testCreatePlayer() {
    Player player = genPlayer("player 1");
    assertEquals("Expected name to be set", "player 1", player.getName());
  }

  public void testGatherItem() {
    Player tester = genPlayer("tester");
    tester.gatherItem(Item.createFood("peanuts"));
    assertEquals("expect one item in basket", 1, tester.getBasket().getItemCount());
  }

  public void testGatherMultipleItems() {
    Player tester = genPlayer("tester");
    tester.gatherItem(Item.createFood("peanuts"));
    tester.gatherItem(Item.createFood("popcorn"));
    tester.gatherItem(Item.createFood("potato chips"));
    tester.gatherItem(Item.createFood("pretzels"));
    assertEquals("expect one item in basket", 4, tester.getBasket().getItemCount());
  }

  public void testItemCounts() {
    Player tester = genPlayer("tester");
    tester.gatherItem(Item.createFood("peanuts"));
    tester.gatherItem(Item.createFood("popcorn"));
    tester.gatherItem(Item.createDrink("iced tea"));
    tester.gatherItem(Item.createDrink("fruit juice"));
    tester.gatherItem(Item.createDrink("mai tais"));
    tester.gatherItem(Item.createSupply("napkins"));
    Basket basket = tester.getBasket();
    assertEquals("should have 2 food items", 2, basket.getFoodCount());
    assertEquals("should have 3 drink items", 3, basket.getDrinkCount());
    assertEquals("should have 1 supply item", 1, basket.getSupplyCount());
  }

  public void testItemCountsWhenBasketEmpty() {
    Player tester = genPlayer("goober");
    Basket basket = tester.getBasket();
    assertEquals("should have none", 0, basket.getItemCount());
    assertEquals("should have none", 0, basket.getFoodCount());
    assertEquals("should have none", 0, basket.getDrinkCount());
    assertEquals("should have none", 0, basket.getSupplyCount());
  }

  public void testHasPrevention() {
    Nuisance rain = Nuisance.createWipeOut("rain");
    Prevention umbrella = Prevention.createPrevention("umbrella", rain);

    Player tester = genPlayer("Little Miss Sunshine");
    tester.gatherItem(umbrella);
    assertTrue("umbrella should prevent rain", tester.getBasket().hasPrevention(rain));
  }

  public void testHasPreventionWhenPlayerHasNothing() {
    Nuisance rain = Nuisance.createWipeOut("rain");
    Player tester = genPlayer("Little Miss Sunshine");
    assertFalse("umbrella should prevent rain", tester.getBasket().hasPrevention(rain));
  }

  public void testHasPreventionWhenPlayerDoesNot() {
    Nuisance rain = Nuisance.createWipeOut("rain");

    Player tester = genPlayer("Little Miss Sunshine");
    tester.gatherItem(Item.createDrink("sweet tea"));
    tester.gatherItem(Item.createFood("popcorn"));
    assertFalse("umbrella should prevent rain", tester.getBasket().hasPrevention(rain));
  }

  public void testGetDifferentTypesOfItems() {
    Player tester = genPlayer("Little Miss Sunshine");

    Item peanuts = Item.createFood("peanuts");
    Item popcorn = Item.createFood("popcorn");
    Item icedTea = Item.createDrink("iced tea");
    Item fruitJuice = Item.createDrink("fruit juice");
    Item maiTais = Item.createDrink("mai tais");
    Item napkins = Item.createSupply("napkins");
    Item bugSpray = Prevention.createPrevention("bug spray", Nuisance.createWipeOut("bugs"));

    tester.gatherItem(peanuts);
    tester.gatherItem(popcorn);
    tester.gatherItem(icedTea);
    tester.gatherItem(fruitJuice);
    tester.gatherItem(maiTais);
    tester.gatherItem(napkins);
    tester.gatherItem(bugSpray);

    List<Item> items = tester.getBasket().getFoods();
    assertTrue(items.contains(peanuts));
    assertTrue(items.contains(popcorn));

    items = tester.getBasket().getDrinks();
    assertTrue(items.contains(icedTea));
    assertTrue(items.contains(fruitJuice));
    assertTrue(items.contains(maiTais));

    items = tester.getBasket().getSupplies();
    assertTrue(items.contains(napkins));

    items = tester.getBasket().getPreventions();
    assertTrue(items.contains(bugSpray));
  }

  public void testRemoveItem() {
    Player tester = genPlayer("Little Miss Sunshine");

    Item peanuts = Item.createFood("peanuts");
    Item popcorn = Item.createFood("popcorn");
    tester.gatherItem(peanuts);
    tester.gatherItem(popcorn);

    // different instance but ought to be equivalent
    Item peanutsTwin = Item.createFood("peanuts");
    tester.getBasket().removeItem(peanutsTwin);

    assertFalse(tester.getBasket().holdsItem(peanuts));
    assertTrue(tester.getBasket().holdsItem(popcorn));
  }

  public void testRemoveItemOfType() {
    Player tester = genPlayer("Mr. Happy");
    tester.gatherItem(Item.createFood("hot dogs"));
    tester.gatherItem(Item.createFood("chicken"));
    tester.gatherItem(Item.createFood("tacos"));
    tester.gatherItem(Item.createFood("pizza"));
    Basket basket = tester.getBasket();
    assertEquals(4, basket.getFoodCount());

    basket.removeItemOfType(ItemType.FOOD);
    assertEquals(3, basket.getFoodCount());
    assertEquals(3, basket.getFoods().size());
  }

  public void testWipeOut() {
    Player tester = genPlayer("Mr. Strong");
    tester.gatherItem(Item.createFood("hot dogs"));
    tester.gatherItem(Item.createFood("chicken"));
    tester.gatherItem(Item.createDrink("milk tea"));
    tester.gatherItem(Item.createSupply("forks"));
    tester.gatherItem(Prevention.createPrevention("bug spray", Nuisance.createWipeOut("mosquitos")));
    Basket basket = tester.getBasket();
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
