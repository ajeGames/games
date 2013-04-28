package com.ajegames.picnic;

import com.ajegames.utility.BaseSpinnerOption;
import junit.framework.TestCase;

/**
 * Make sure PicnicSpinner works as expected.
 */
public class PicnicSpinnerTest extends TestCase {

  public void testCreateSpinnerWithDefaults() {
    Nuisance rain = Nuisance.create("rain");
    PicnicSpinner spinner = PicnicSpinner.createBlankSpinner()
            .addItem(Item.createFood("hamburgers"))
            .addItem(Item.createDrink("soda"))
            .addItem(Item.createUtensil("napkins"))
            .addItem(Prevention.createPrevention("umbrella", rain))
            .addNuisance(rain)
            .addNuisance(Nuisance.createAgainstFood("ants"));
    assertEquals(6, spinner.getNumberOfChoices());
  }
}
