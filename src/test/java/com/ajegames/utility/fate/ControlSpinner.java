package com.ajegames.utility.fate;

import com.ajegames.utility.fate.Randomizer;
import com.ajegames.utility.fate.Spinner;

/**
 * Created for AJE Games by bigdaddy on 6/23/13 at 9:52 AM.
 */
public class ControlSpinner extends Spinner {

  /**
   * For controlling randomization in tests.
   *
   * @param randomizer
   */
  public ControlSpinner(Randomizer randomizer){
    setRandomizer(randomizer);
  }

}
