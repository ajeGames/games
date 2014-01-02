package com.ajegames.picnic.domain;

import com.ajegames.picnic.domain.Item;
import com.ajegames.picnic.domain.ItemType;
import com.ajegames.picnic.domain.Nuisance;
import com.ajegames.picnic.domain.Prevention;
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
