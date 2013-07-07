package com.ajegames.picnic;

import junit.framework.TestCase;

/**
 * Make sure Nuisance behaves as expected.
 */
public class NuisanceTest extends TestCase {

  public void testCreateNuisance() {
    Nuisance newNuisance = Nuisance.createWipeOut("rain");
    assertFalse("should be generic", newNuisance.isAgainstItem());
    assertFalse("should be generic", newNuisance.isAgainstItemType());
  }

  public void testCreateNuisanceAgainstItem() {
    Nuisance newNuisance = Nuisance.createAgainstItem("heat wave", Item.createFood("ice cream"));
    assertTrue("should be specific", newNuisance.isAgainstItem());
    assertFalse(newNuisance.isAgainstItemType());
    assertEquals("should work against ice cream", "ice cream", newNuisance.getWorksAgainst().getValue());
  }

  public void testCreateNuisanceAgainstFood() {
    Nuisance newNuisance = Nuisance.createAgainstFood("ants");
    assertTrue(newNuisance.isAgainstItemType());
    assertFalse(newNuisance.isAgainstItem());
    assertEquals("should work against food", ItemType.FOOD, newNuisance.getWorksAgainstType());
  }

  public void testCreateNuisanceAgainstDrink() {
    Nuisance newNuisance = Nuisance.createAgainstDrink("ants");
    assertTrue(newNuisance.isAgainstItemType());
    assertFalse(newNuisance.isAgainstItem());
    assertEquals("should work against food", ItemType.DRINK, newNuisance.getWorksAgainstType());
  }

  public void testCreateNuisanceAgainstSupply() {
    Nuisance newNuisance = Nuisance.createAgainstSupply("wind");
    assertTrue(newNuisance.isAgainstItemType());
    assertFalse(newNuisance.isAgainstItem());
    assertEquals("should work against food", ItemType.SUPPLY, newNuisance.getWorksAgainstType());
  }
}
