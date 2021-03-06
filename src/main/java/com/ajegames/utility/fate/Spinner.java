package com.ajegames.utility.fate;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Acts like a game spinner, which is shaped like a pie with each wedge being an option.  Spinning the spinner
 * results in it pointing to one of the wedges at random.  This is designed to be stateless; results must be
 * held by the spinning agent to preserve any sense of history.  This design allows multiple games to be played
 * simultaneously using the same spinner configuration.
 */
public class Spinner {

  private Randomizer randomSource;
  private List<SpinnerOption> options = Lists.newArrayList();

  public Spinner() {
    setRandomizer(new RandomNumberGenerator());
  }

  /**
   * Allows use of alternative randomization strategies, including strategies for controlling values during testing
   *
   * @param randomizer
   */
  protected void setRandomizer(Randomizer randomizer) {
    randomSource = randomizer;
  }

  public Spinner addOption(SpinnerOption option) {
    options.add(option);
    return this;
  }

  public boolean hasSpinnerOption(SpinnerOption option) {
    return options.contains(option);
  }

  synchronized public SpinnerOption spin() {
    int indexOfSelection = (int) Math.floor(randomSource.getRandom() * options.size());
    if (indexOfSelection == options.size()) {
      indexOfSelection--;
    }
    return options.get(indexOfSelection);
  }
}
