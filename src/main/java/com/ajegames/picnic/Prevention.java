package com.ajegames.picnic;

/**
 * Something to collect that counteracts or neutralizes the negative effects of a Nuisance.
 */
public class Prevention extends Item {

  private Nuisance counteracts;

  private Prevention(String key, String description) {
    super(key, description, ItemType.PREVENTION);
  }

  public static Prevention createPrevention(String key, String description, Nuisance counteracts) {
    Prevention newPrevention = new Prevention(key, description);
    newPrevention.setCounteracts(counteracts);
    return newPrevention;
  }

  public static Prevention createPrevention(String key, Nuisance counteracts) {
    return createPrevention(key, key, counteracts);
  }

  @Override
  public boolean isPrevention() {
    return true;
  }

  private void setCounteracts(Nuisance counteracts) {
    this.counteracts = counteracts;
  }

  public Nuisance getCounteracts() {
    return counteracts;
  }

}
