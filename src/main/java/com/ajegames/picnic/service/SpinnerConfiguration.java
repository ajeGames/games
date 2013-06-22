package com.ajegames.picnic.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created for AJE Games by bigdaddy on 6/15/13 at 10:56 AM.
 */
public class SpinnerConfiguration {

  private List<ItemFactory> items;

  @JsonProperty
  public List<ItemFactory> getItems() {
    return items;
  }

  @JsonProperty
  public void setItems(List<ItemFactory> items) {
    this.items = items;
  }
}
