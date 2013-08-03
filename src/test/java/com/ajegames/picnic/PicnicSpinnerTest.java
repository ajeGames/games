package com.ajegames.picnic;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PicnicSpinnerTest {

  List<Item> testItems;

  @BeforeClass
  private void init() {
    testItems = TestPicnicSpinnerFactory.configureSpinnerWithBasicTestItems();
  }

  @Test
  public void testCreateSpinner() {
    PicnicSpinner spinner = PicnicSpinner.createInstance();

    // at least check that spin returns the items added
    for (int i = 0; i < 100; i++) {
      assert spinner.hasPicnicItem(spinner.spin());
    }
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void testCannotConfigurationSpinnerTwice() {
    PicnicSpinner.configureSpinner(testItems);
  }
}
