package com.ajegames.picnic.domain;

import com.ajegames.utility.fate.Spinner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Spinner populated with picnic items that keeps track of history.
 */
public class PicnicSpinner {

  private static boolean ready;
  private static Spinner spinner;  // single instance to share
  private List<Item> history = Lists.newLinkedList();

  /**
   * Sets up spinner options with supplied picnic items.
   *
   * @param items picnic items to place on the spinner
   */
  synchronized public static void configureSpinner(List<Item> items) {
    if (ready) {
      throw new IllegalStateException("Spinner is already configured");
    }
    spinner = new Spinner();
    for (Item item : items) {
      spinner.addOption(item);
    }
    ready = true;
  }

  synchronized public static boolean isConfigured() {
    return ready;
  }

  /**
   * Spawn an instance of a spinner with its own history.
   *
   * @return PicnicSpinner
   */
  public static PicnicSpinner createInstance() {
    if (!ready) {
      throw new IllegalStateException("Spinner is not configured.");
    }
    return new PicnicSpinner();
  }

  private PicnicSpinner() {}

/*
  public static List<Item> getSpinnerOptions() {
    List<> options = spinner.getOptions();
  }
*/

  public Item spin() {
    Item selected = (Item) spinner.spin();
    history.add(0, selected);
    return selected;
  }

  public boolean hasPicnicItem(Item picnicItem) {
    return spinner.hasSpinnerOption(picnicItem);
  }
}
