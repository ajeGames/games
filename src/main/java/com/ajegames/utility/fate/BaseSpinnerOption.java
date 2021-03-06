package com.ajegames.utility.fate;

/**
 * base implementation that holds a single key value
 */
public class BaseSpinnerOption implements SpinnerOption {

  protected String value;

  public BaseSpinnerOption(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BaseSpinnerOption that = (BaseSpinnerOption) o;

    return !(value != null ? !value.equals(that.value) : that.value != null);
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
