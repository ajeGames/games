package com.ajegames.picnic;

import com.google.common.collect.Lists;
import junit.framework.TestCase;

import java.util.List;

/**
 * Make sure PicnicSpinner works as expected.
 */
public class PicnicSpinnerTest extends TestCase {

  public void testCreateSpinner() {
    List<Item> itemsToAdd = Lists.newArrayList();
    Nuisance rain = Nuisance.create("rain");
    itemsToAdd.add(Item.createFood("hamburgers"));
    itemsToAdd.add(Item.createDrink("soda"));
    itemsToAdd.add(Item.createSupply("napkins"));
    itemsToAdd.add(Prevention.createPrevention("umbrella", rain));
    itemsToAdd.add(rain);
    itemsToAdd.add(Nuisance.createAgainstFood("ants"));
    PicnicSpinner.configureSpinner(itemsToAdd);
    PicnicSpinner spinner = PicnicSpinner.createInstance();

    // at least check that spin returns the items added
    for (int i = 0; i < 100; i++) {
      assertTrue(itemsToAdd.contains(spinner.spin()));
    }
  }
}
