package com.ajegames.picnic;

/**
 * Make sure Prevention behaves as expected.
 */
public class PreventionTest extends ItemTest {

  public void testCreatePrevention() {
    Item picnicItem;Nuisance sunburn = Nuisance.create("Sunburn");
    picnicItem = Prevention.createPrevention("Sunscreen", sunburn);
    assertEquals("should be prevention", ItemType.PREVENTION, picnicItem.getType());
    assertFalse(picnicItem.isFood());
    assertFalse(picnicItem.isDrink());
    assertFalse(picnicItem.isSupply());
  }
}
