package com.ajegames.utility.fate;

import com.ajegames.utility.fate.Randomizer;

/**
 */
public interface PluggableRandomizer extends Randomizer {

  public void setValue(double value);
}
