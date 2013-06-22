package com.ajegames.picnic.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created for AJE Games by bigdaddy on 6/22/13 at 11:13 AM.
 */
public class SpinnerItemConfig {

  private String key;
  private String description;
  private int weight = 1;
  private String impact;
  private String counteracts;

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

  @JsonProperty
  public String getImpact() {
    return impact;
  }

  @JsonProperty
  public void setImpact(String impact) {
    this.impact = impact;
  }

/*
  @JsonProperty
  public List<String> getCounteracts() {
    return counteracts;
  }

  @JsonProperty
  public void setCounteracts(List<String> counteracts) {
    this.counteracts = counteracts;
  }
*/

  public void setCounteracts(String counteracts) {
    this.counteracts = counteracts;
  }
}
