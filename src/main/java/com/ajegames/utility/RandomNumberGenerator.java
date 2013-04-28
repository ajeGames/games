package com.ajegames.utility;

/**
 * True implementation for generating random numbers.
 */
public class RandomNumberGenerator implements Randomizer {

  /**
   * Returns a random double between 0 and 1.
   * @return
   */
  @Override
  public double getRandom() {
    return Math.random();
  }
}
