package com.ajegames.picnic.service;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created for AJE Games by bigdaddy on 6/22/13 at 11:13 AM.
 */
public class ItemFactory {

  private String key;
  private String description;
  private int weight = 1;

  @JsonProperty
  public String getKey() {
    return key;
  }

  @JsonProperty
  public void setKey(String key) {
    this.key = key;
  }

  @JsonProperty
  public String getDescription() {
    return description;
  }

  @JsonProperty
  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty
  public int getWeight() {
    return weight;
  }

  @JsonProperty
  public void setWeight(int weight) {
    this.weight = weight;
  }

}
