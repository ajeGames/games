package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.domain.Basket;
import com.ajegames.picnic.domain.Item;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by dave on 7/14/13 at 3:49 PM.
 */
public class BasketState {

  private Basket basket;

  public BasketState(Basket basket) {
    this.basket = basket;
  }

  public List<String> getFoods() {
    List<String> items = Lists.newArrayList();
    for (Item item : basket.getFoods()) {
      items.add(item.getValue());
    }
    return items;
  }

  public List<String> getDrinks() {
    List<String> items = Lists.newArrayList();
    for (Item item : basket.getDrinks()) {
      items.add(item.getValue());
    }
    return items;
  }

  public List<String> getSupplies() {
    List<String> items = Lists.newArrayList();
    for (Item item : basket.getSupplies()) {
      items.add(item.getValue());
    }
    return items;
  }

  public List<String> getPreventions() {
    List<String> items = Lists.newArrayList();
    for (Item item : basket.getPreventions()) {
      items.add(item.getValue());
    }
    return items;
  }
}
