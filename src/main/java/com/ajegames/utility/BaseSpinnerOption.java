package com.ajegames.utility;

/**
 * base implementation that takes care of things like name, value, weight, image
 *
 * TODO add support for value, weight, image, etc
 */
public class BaseSpinnerOption implements SpinnerOption {

  protected String name;

  public BaseSpinnerOption(String name) {
    this.name = name;
  }

  public String getValue() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BaseSpinnerOption that = (BaseSpinnerOption) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}
