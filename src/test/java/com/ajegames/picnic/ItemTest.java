package com.ajegames.picnic;

import junit.framework.TestCase;

/**
 * Make sure Item behaves as expected.
 */
public class ItemTest extends TestCase {

  public void testCreateFood() {
    Item picnicItem = Item.createFood("Hamburgers");
    assertEquals("should be food", ItemType.FOOD, picnicItem.getType());
    assertTrue(picnicItem.isFood());
    assertFalse(picnicItem.isDrink());
    assertFalse(picnicItem.isUtensil());
  }

  public void testCreateDrink() {
    Item picnicItem;
    picnicItem = Item.createDrink("Soda");
    assertEquals("should be food", ItemType.DRINK, picnicItem.getType());
    assertTrue(picnicItem.isDrink());
    assertFalse(picnicItem.isFood());
    assertFalse(picnicItem.isUtensil());
  }

  public void testCreateUtensil() {
    Item picnicItem;
    picnicItem = Item.createUtensil("Forks");
    assertEquals("should be food", ItemType.UTENSIL, picnicItem.getType());
    assertTrue(picnicItem.isUtensil());
    assertFalse(picnicItem.isFood());
    assertFalse(picnicItem.isDrink());
  }

  public void testEquality() {
    Item item1 = Item.createDrink("Dr. Pepper");
    Item item2 = Item.createDrink("Dr. Pepper");
    Item item3 = Item.createDrink("Mr. Pip");
    Item item4 = Item.createFood("Mr. Pip");

    assertEquals(item1, item1);
    assertEquals(item1, item2);
    assertFalse("these are not equal", item1.equals(item3));
    assertFalse("different type", item3.equals(item4));
  }
}
