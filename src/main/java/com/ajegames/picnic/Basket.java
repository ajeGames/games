package com.ajegames.picnic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created for AJE Games by bigdaddy on 5/1/13 at 10:08 PM.
 */
public class Basket {

  private List<Item> basket;
  private int foodCount;
  private int drinkCount;
  private int utensilCount;

  public Basket() {
    empty();
  }

  public void empty() {
    basket = new ArrayList<Item>();
    foodCount = 0;
    drinkCount = 0;
    utensilCount = 0;
  }

  public void gatherItem(Item item) {
    basket.add(item);
    incrementCount(item);
  }

  public boolean holdsItem(Item item) {
    return basket.contains(item);
  }

  public int getItemCount() {
    return basket.size();
  }

  public int getFoodCount() {
    return foodCount;
  }

  public int getDrinkCount() {
    return drinkCount;
  }

  public int getUtensilCount() {
    return utensilCount;
  }

  public List<Item> getFoods() {
    List<Item> foodItems = new ArrayList<Item>();
    for (Item item : basket) {
      if (item.isFood()) {
        foodItems.add(item);
      }
    }
    return foodItems;
  }

  public List<Item> getDrinks() {
    List<Item> foodItems = new ArrayList<Item>();
    for (Item item : basket) {
      if (item.isDrink()) {
        foodItems.add(item);
      }
    }
    return foodItems;
  }

  public List<Item> getUtensils() {
    List<Item> foodItems = new ArrayList<Item>();
    for (Item item : basket) {
      if (item.isUtensil()) {
        foodItems.add(item);
      }
    }
    return foodItems;
  }

  public List<Item> getPreventions() {
    List<Item> foodItems = new ArrayList<Item>();
    for (Item item : basket) {
      if (item.isPrevention()) {
        foodItems.add(item);
      }
    }
    return foodItems;
  }

  public boolean hasPrevention(Nuisance rain) {
    for (Item picnicItem : basket) {
      if (picnicItem instanceof Prevention) {
        if (((Prevention) picnicItem).getCounteracts().equals(rain)) {
          return true;
        }
      }
    }
    return false;
  }

  public void removeItem(Item itemToRemove) {
    basket.remove(itemToRemove);
    decrementCount(itemToRemove);
  }

  public void removeItemOfType(ItemType type) {
    for (Item itemInBasket : basket) {
      if (type.equals(itemInBasket.getType())) {
        basket.remove(itemInBasket);
        decrementCount(itemInBasket);
        return;
      }
    }
  }

  private void incrementCount(Item itemBeingAdded) {
    if (itemBeingAdded.isFood()) {
      foodCount++;
    } else if (itemBeingAdded.isDrink()) {
      drinkCount++;
    } else if (itemBeingAdded.isUtensil()) {
      utensilCount++;
    }
  }

  private void decrementCount(Item itemBeingRemoved) {
    if (itemBeingRemoved.isFood()) {
      foodCount--;
    } else if (itemBeingRemoved.isDrink()) {
      drinkCount--;
    } else if (itemBeingRemoved.isUtensil()) {
      utensilCount--;
    }
  }

}
