package com.ajegames.picnic.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created for AJE Games by bigdaddy on 6/15/13 at 10:56 AM.
 */
public class SpinnerConfiguration {

  private List<SpinnerItemConfig> foods = Lists.newArrayList();
  private List<SpinnerItemConfig> drinks = Lists.newArrayList();
  private List<SpinnerItemConfig> supplies = Lists.newArrayList();
  private List<SpinnerItemConfig> nuisances = Lists.newArrayList();
  private List<SpinnerItemConfig> preventions = Lists.newArrayList();

  @JsonProperty
  public List<SpinnerItemConfig> getFoods() {
    return foods;
  }

  @JsonProperty
  public void setFoods(List<SpinnerItemConfig> foods) {
    this.foods = foods;
  }

  @JsonProperty
  public List<SpinnerItemConfig> getDrinks() {
    return drinks;
  }

  @JsonProperty
  public void setDrinks(List<SpinnerItemConfig> drinks) {
    this.drinks = drinks;
  }

  @JsonProperty
  public List<SpinnerItemConfig> getSupplies() {
    return supplies;
  }

  @JsonProperty
  public void setSupplies(List<SpinnerItemConfig> supplies) {
    this.supplies = supplies;
  }

  @JsonProperty
  public List<SpinnerItemConfig> getNuisances() {
    return nuisances;
  }

  @JsonProperty
  public void setNuisances(List<SpinnerItemConfig> nuisances) {
    this.nuisances = nuisances;
  }

  @JsonProperty
  public List<SpinnerItemConfig> getPreventions() {
    return preventions;
  }

  @JsonProperty
  public void setPreventions(List<SpinnerItemConfig> preventions) {
    this.preventions = preventions;
  }
}
