package com.ajegames.utility;

/**
 */
public class MockRandomNumberGenerator implements PluggableRandomizer {

  private double stagedValue = 0.0d;

  public static PluggableRandomizer createMockRandomizer() {
    return new MockRandomNumberGenerator();
  }

  @Override
  public double getRandom() {
    return stagedValue;
  }

  @Override
  public void setValue(double value) {
    stagedValue = value;
  }
}
