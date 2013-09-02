package com.ajegames.picnic.domain;

import com.ajegames.picnic.domain.Item;
import com.ajegames.picnic.domain.Nuisance;
import com.ajegames.picnic.domain.PicnicSpinner;
import com.ajegames.picnic.domain.Prevention;
import com.google.common.collect.Lists;

import java.util.List;

public class TestPicnicSpinnerFactory {

  public static List<Item> testItems;

  public static List<Item> configureSpinnerWithBasicTestItems() {
    if (!PicnicSpinner.isConfigured()) {
      testItems = createBasicSpinnerTestItems();
      PicnicSpinner.configureSpinner(testItems);
    }
    return testItems;
  }

  private static List<Item> createBasicSpinnerTestItems() {
    testItems = Lists.newArrayList();
    testItems.add(Item.createFood("hamburgers"));
    testItems.add(Item.createDrink("icedtea"));
    testItems.add(Item.createSupply("napkins"));
    testItems.add(Nuisance.createAgainstFood("ants"));
    Nuisance rain = Nuisance.createWipeOut("rain");
    testItems.add(rain);
    testItems.add(Prevention.createPrevention("umbrella", rain));
    return testItems;
  }
}
