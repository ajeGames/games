package com.ajegames.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class SpinnerTest {

  private static Logger LOG = LoggerFactory.getLogger(SpinnerTest.class);

  @Test
  public void testSpinnerWithOneChoice() {
    Spinner spinner = new Spinner().addOption(new BaseSpinnerOption("Amanda"));

    for (int i = 0; i < 1000; i++) {
      SpinnerOption result = spinner.spin();
      assert "Amanda".equals(result.getValue()) : "Expected value Amanda, for " + result.getValue();
    }
  }

  @Test
  public void testSpinnerWithMultipleChoices() {

    String[] options = new String[] { "SpongeBob", "Patrick", "Squidward", "Mr. Krabs", "Sandra" };

    Spinner spinner = new Spinner();
    Map<String, Integer> counts = new HashMap<String, Integer>();
    for (String option : options) {
      SpinnerOption opt = new BaseSpinnerOption(option);
      spinner.addOption(opt);
      counts.put(option, 0);
    }

    for (int i = 0; i < 1000; i++) {
      String selectedValue = spinner.spin().getValue();
      Integer count = counts.get(selectedValue);
      counts.put(selectedValue, count+1);
    }

    // make sure every choice was selected at least once; highly unlikely to have missed one after so many tries
    for (String choice : counts.keySet()) {
      assert (0 < counts.get(choice)) : "Choice " + choice + " should have been picked at least once.";
      LOG.debug("Choice " + choice + " was picked " + counts.get(choice) + " times.");
    }
  }

  @Test
  public void testFourChoices() {
    String[] options = new String[] { "North", "South", "East", "West" };
    PluggableRandomizer fixedRandomNumber = MockRandomNumberGenerator.createMockRandomizer();
    Spinner spinner = new ControlSpinner(fixedRandomNumber);
    for (String option : options) {
      SpinnerOption opt = new BaseSpinnerOption(option);
      spinner.addOption(opt);
    }

    fixedRandomNumber.setValue(0.15d);
    assert spinner.spin().getValue().equals(options[0]) : "Should have picked the first option";

    fixedRandomNumber.setValue(0.49d);
    assert spinner.spin().getValue().equals(options[1]) : "Should have picked the second option";

    fixedRandomNumber.setValue(0.50d);
    assert spinner.spin().getValue().equals(options[2]) : "Should have picked the third option";

    fixedRandomNumber.setValue(0.99d);
    spinner.spin();
    assert spinner.spin().getValue().equals(options[3]) : "Should have picked the fourth option";

    fixedRandomNumber.setValue(1.0d);
    spinner.spin();
    assert spinner.spin().getValue().equals(options[3]) : "Should have picked the fourth option";
  }
}
