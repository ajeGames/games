package com.ajegames.picnic.domain;

import com.ajegames.picnic.domain.Item;
import com.ajegames.picnic.domain.ItemType;
import com.ajegames.picnic.domain.Nuisance;
import org.testng.annotations.Test;

public class NuisanceTest {

  @Test
  public void testCreateNuisance() {
    Nuisance newNuisance = Nuisance.createWipeOut("rain");
    assert !newNuisance.isAgainstItem() : "should be generic";
    assert !newNuisance.isAgainstItemType() : "should be generic";
  }

  @Test
  public void testCreateNuisanceAgainstItem() {
    Nuisance newNuisance = Nuisance.createAgainstItem("heat wave", Item.createFood("ice cream"));
    assert newNuisance.isAgainstItem() : "should be specific";
    assert !newNuisance.isAgainstItemType();
    assert "ice cream".equals(newNuisance.getWorksAgainst().getValue()) : "should work against ice cream";
  }

  @Test
  public void testCreateNuisanceAgainstFood() {
    Nuisance newNuisance = Nuisance.createAgainstFood("ants");
    assert newNuisance.isAgainstItemType();
    assert !newNuisance.isAgainstItem();
    assert ItemType.FOOD.equals(newNuisance.getWorksAgainstType()) : "should work against food";
  }

  @Test
  public void testCreateNuisanceAgainstDrink() {
    Nuisance newNuisance = Nuisance.createAgainstDrink("ants");
    assert newNuisance.isAgainstItemType();
    assert !newNuisance.isAgainstItem();
    assert ItemType.DRINK.equals(newNuisance.getWorksAgainstType()) : "should work against food";
  }

  @Test
  public void testCreateNuisanceAgainstSupply() {
    Nuisance newNuisance = Nuisance.createAgainstSupply("wind");
    assert newNuisance.isAgainstItemType();
    assert !newNuisance.isAgainstItem();
    assert ItemType.SUPPLY.equals(newNuisance.getWorksAgainstType()) : "should work against food";
  }
}
