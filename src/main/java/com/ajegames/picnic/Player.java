package com.ajegames.picnic;

import java.util.*;

/**
 * Someone playing Picnic.  Player holds a basket that must be filled sufficiently to win.
 */
public class Player {

  private String name = "Mr. Nobody";
  private List<Item> basket;
  private int foodCount;
  private int drinkCount;
  private int utensilCount;

  private Player() {
    emptyBasket();
  }

  private void emptyBasket() {
    basket = new ArrayList<Item>();
    foodCount = 0;
    drinkCount = 0;
    utensilCount = 0;
  }

  public static Player createPlayer(String playerName) {
    Player newPlayer = new Player();
    newPlayer.setName(playerName);
    return newPlayer;
  }

  private void setName(String playerName) {
    name = playerName;
  }

  public String getName() {
    return name;
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

  public void removeAllItems() {
    emptyBasket();
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

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("Player ")
            .append(name)
            .append(" { food: ");
    for (Item item : getFoods()) {
      out.append(item.getValue()).append(", ");
    }
    out.append(" drinks: ");
    for (Item item : getDrinks()) {
      out.append(item.getValue()).append(", ");
    }
    out.append(" utensils: ");
    for (Item item : getUtensils()) {
      out.append(item.getValue()).append(", ");
    }
    out.append(" preventions: ");
    for (Item item : getPreventions()) {
      out.append(item.getValue()).append(", ");
    }
    out.append(" }");
    return out.toString();
  }
}
