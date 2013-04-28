package com.ajegames.picnic;

/**
 * Something to collect that counteracts or neutralizes the negative effects of a Nuisance.
 */
public class Prevention extends Item {

  private Nuisance counteracts;

  private Prevention(String name) {
    super(name, ItemType.PREVENTION);
  }

  public static Prevention createPrevention(String name, Nuisance counteracts) {
    Prevention newPrevention = new Prevention(name);
    newPrevention.setCounteracts(counteracts);
    return newPrevention;
  }

  private void setCounteracts(Nuisance counteracts) {
    this.counteracts = counteracts;
  }

  @Override
  public boolean isPrevention() {
    return true;
  }

  public Nuisance getCounteracts() {
    return counteracts;
  }

}
