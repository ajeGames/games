package com.ajegames.picnic.domain;


import com.ajegames.picnic.domain.Item;
import com.ajegames.picnic.domain.ItemType;
import org.testng.annotations.Test;

public class ItemTest {

  @Test
  public void testCreateFood() {
    Item picnicItem = Item.createFood("Hamburgers");
    assert ItemType.FOOD.equals(picnicItem.getType()) : "should be food";
    assert picnicItem.isFood();
    assert !picnicItem.isDrink();
    assert !picnicItem.isSupply();
  }

  @Test
  public void testCreateDrink() {
    Item picnicItem;
    picnicItem = Item.createDrink("Soda");
    assert ItemType.DRINK.equals(picnicItem.getType()) : "should be food";
    assert picnicItem.isDrink();
    assert !picnicItem.isFood();
    assert !picnicItem.isSupply();
  }

  @Test
  public void testCreateSupply() {
    Item picnicItem;
    picnicItem = Item.createSupply("Forks");
    assert ItemType.SUPPLY.equals(picnicItem.getType()) : "should be food";
    assert picnicItem.isSupply();
    assert !picnicItem.isFood();
    assert !picnicItem.isDrink();
  }

  @Test
  public void testEquality() {
    Item item1 = Item.createDrink("Dr. Pepper");
    Item item2 = Item.createDrink("Dr. Pepper");
    Item item3 = Item.createDrink("Mr. Pip");
    Item item4 = Item.createFood("Mr. Pip");

    assert item1.equals(item1);
    assert item1.equals(item2);
    assert !item1.equals(item3) : "these are not equal";
    assert !item3.equals(item4) : "different type";
  }
}
