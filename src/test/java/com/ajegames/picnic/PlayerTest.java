package com.ajegames.picnic;

import junit.framework.TestCase;

import java.util.List;

/**
 * Makes sure that Player behaves as expected.  Player holds picnic items in a basket, so make sure collection and
 * inspection works.
 */
public class PlayerTest extends TestCase {

  private Player genPlayer(String name) {
    return Player.createPlayer(name);
  }

  public void testCreatePlayer() {
    Player player = genPlayer("player 1");
    assertEquals("Expected name to be set", "player 1", player.getName());
  }

  public void testGatherItem() {
    Player tester = genPlayer("tester");
    tester.gatherItem(Item.createFood("peanuts"));
    assertEquals("expect one item in basket", 1, tester.getItemCount());
  }

  public void testGatherMultipleItems() {
    Player tester = genPlayer("tester");
    tester.gatherItem(Item.createFood("peanuts"));
    tester.gatherItem(Item.createFood("popcorn"));
    tester.gatherItem(Item.createFood("potato chips"));
    tester.gatherItem(Item.createFood("pretzels"));
    assertEquals("expect one item in basket", 4, tester.getItemCount());
  }

  public void testItemCounts() {
    Player tester = genPlayer("tester");
    tester.gatherItem(Item.createFood("peanuts"));
    tester.gatherItem(Item.createFood("popcorn"));
    tester.gatherItem(Item.createDrink("iced tea"));
    tester.gatherItem(Item.createDrink("fruit juice"));
    tester.gatherItem(Item.createDrink("mai tais"));
    tester.gatherItem(Item.createUtensil("napkins"));
    assertEquals("should have 2 food items", 2, tester.getFoodCount());
    assertEquals("should have 3 drink items", 3, tester.getDrinkCount());
    assertEquals("should have 1 utensil item", 1, tester.getUtensilCount());
  }

  public void testItemCountsWhenBasketEmpty() {
    Player tester = genPlayer("goober");
    assertEquals("should have none", 0, tester.getItemCount());
    assertEquals("should have none", 0, tester.getFoodCount());
    assertEquals("should have none", 0, tester.getDrinkCount());
    assertEquals("should have none", 0, tester.getUtensilCount());
  }

  public void testHasPrevention() {
    Nuisance rain = Nuisance.create("rain");
    Prevention umbrella = Prevention.createPrevention("umbrella", rain);

    Player tester = genPlayer("Little Miss Sunshine");
    tester.gatherItem(umbrella);
    assertTrue("umbrella should prevent rain", tester.hasPrevention(rain));
  }

  public void testHasPreventionWhenPlayerHasNothing() {
    Nuisance rain = Nuisance.create("rain");
    Prevention umbrella = Prevention.createPrevention("umbrella", rain);

    Player tester = genPlayer("Little Miss Sunshine");
    assertFalse("umbrella should prevent rain", tester.hasPrevention(rain));
  }

  public void testHasPreventionWhenPlayerDoesNot() {
    Nuisance rain = Nuisance.create("rain");

    Player tester = genPlayer("Little Miss Sunshine");
    tester.gatherItem(Item.createDrink("sweet tea"));
    tester.gatherItem(Item.createFood("popcorn"));
    assertFalse("umbrella should prevent rain", tester.hasPrevention(rain));
  }

  public void testGetDifferentTypesOfItems() {
    Player tester = genPlayer("Little Miss Sunshine");

    Item peanuts = Item.createFood("peanuts");
    Item popcorn = Item.createFood("popcorn");
    Item icedTea = Item.createDrink("iced tea");
    Item fruitJuice = Item.createDrink("fruit juice");
    Item maiTais = Item.createDrink("mai tais");
    Item napkins = Item.createUtensil("napkins");
    Item bugSpray = Prevention.createPrevention("bug spray", Nuisance.create("bugs"));

    tester.gatherItem(peanuts);
    tester.gatherItem(popcorn);
    tester.gatherItem(icedTea);
    tester.gatherItem(fruitJuice);
    tester.gatherItem(maiTais);
    tester.gatherItem(napkins);
    tester.gatherItem(bugSpray);

    List<Item> items = tester.getFoods();
    assertTrue(items.contains(peanuts));
    assertTrue(items.contains(popcorn));

    items = tester.getDrinks();
    assertTrue(items.contains(icedTea));
    assertTrue(items.contains(fruitJuice));
    assertTrue(items.contains(maiTais));

    items = tester.getUtensils();
    assertTrue(items.contains(napkins));

    items = tester.getPreventions();
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
    tester.removeItem(peanutsTwin);

    assertFalse(tester.holdsItem(peanuts));
    assertTrue(tester.holdsItem(popcorn));
  }

  public void testRemoveItemOfType() {
    Player tester = genPlayer("Mr. Happy");
    tester.gatherItem(Item.createFood("hot dogs"));
    tester.gatherItem(Item.createFood("chicken"));
    tester.gatherItem(Item.createFood("tacos"));
    tester.gatherItem(Item.createFood("pizza"));
    assertEquals(4, tester.getFoodCount());

    tester.removeItemOfType(ItemType.FOOD);
    assertEquals(3, tester.getFoodCount());
    assertEquals(3, tester.getFoods().size());
  }

  public void testWipeOut() {
    Player tester = genPlayer("Mr. Strong");
    tester.gatherItem(Item.createFood("hot dogs"));
    tester.gatherItem(Item.createFood("chicken"));
    tester.gatherItem(Item.createDrink("milk tea"));
    tester.gatherItem(Item.createUtensil("forks"));
    tester.gatherItem(Prevention.createPrevention("bug spray", Nuisance.create("mosquitos")));
    tester.removeAllItems();
    assertEquals(0, tester.getItemCount());
    assertEquals(0, tester.getUtensilCount());
    assertEquals(0, tester.getFoodCount());
    assertEquals(0, tester.getDrinkCount());
    assertTrue(tester.getDrinks().isEmpty());
    assertTrue(tester.getFoods().isEmpty());
    assertTrue(tester.getUtensils().isEmpty());
    assertTrue(tester.getPreventions().isEmpty());
  }
}
