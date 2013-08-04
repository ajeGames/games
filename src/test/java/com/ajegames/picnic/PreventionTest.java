package com.ajegames.picnic;

import org.testng.annotations.Test;

public class PreventionTest {

  @Test
  public void testCreatePrevention() {
    Item picnicItem;
    Nuisance sunburn = Nuisance.createWipeOut("sunburn");
    picnicItem = Prevention.createPrevention("sunscreen", sunburn);
    assert ItemType.PREVENTION.equals(picnicItem.getType()) : "should be prevention";
    assert !picnicItem.isFood();
    assert !picnicItem.isDrink();
    assert !picnicItem.isSupply();
  }
}
