package com.ajegames.picnic;

import junit.framework.TestCase;

/**
 * Make sure PicnicSpinner works as expected.
 */
public class PicnicSpinnerTest extends TestCase {

  public void testCreateSpinnerWithDefaults() {
    Nuisance rain = Nuisance.create("rain");
    PicnicSpinner spinner = PicnicSpinner.createEmptySpinner()
            .addItem(Item.createFood("hamburgers"))
            .addItem(Item.createDrink("soda"))
            .addItem(Item.createSupply("napkins"))
            .addItem(Prevention.createPrevention("umbrella", rain))
            .addNuisance(rain)
            .addNuisance(Nuisance.createAgainstFood("ants"));
    assertEquals(6, spinner.getNumberOfChoices());
  }
}
