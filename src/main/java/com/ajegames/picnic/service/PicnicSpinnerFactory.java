package com.ajegames.picnic.service;

import com.ajegames.picnic.*;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Populates spinner options from configuration data.
 */
public class PicnicSpinnerFactory {

  synchronized public static void configureSpinner(SpinnerConfiguration config) {
    List<Item> items = Lists.newArrayList();
    for (SpinnerItemConfig item : config.getFoods()) {
      for (int i = 0; i < item.getWeight(); i++) {
        items.add(Item.createFood(item.getKey()));
      }
    }
    for (SpinnerItemConfig item : config.getDrinks()) {
      for (int i = 0; i < item.getWeight(); i++) {
        items.add(Item.createDrink(item.getKey()));
      }
    }
    for (SpinnerItemConfig item : config.getSupplies()) {
      for (int i = 0; i < item.getWeight(); i++) {
        items.add(Item.createSupply(item.getKey()));
      }
    }
    for (SpinnerItemConfig item : config.getNuisances()) {
      Nuisance toAdd;
      for (int i = 0; i < item.getWeight(); i++) {
        if ("reducesFood".equals(item.getImpact())) {
          toAdd = Nuisance.createAgainstFood(item.getKey());
        } else if ("wipeout".equals(item.getImpact())) {
          toAdd = Nuisance.createWipeOut(item.getKey());
        } else {
          toAdd = Nuisance.create(item.getKey());
        }
        items.add(toAdd);
      }
    }
    for (SpinnerItemConfig item : config.getPreventions()) {
      for (int i = 0; i < item.getWeight(); i++) {
        Item counteracts = ItemCatalog.instance().getItem(item.getCounteracts());
        if (counteracts instanceof Nuisance) {
          items.add(Prevention.createPrevention(item.getKey(), (Nuisance) counteracts));
        }
      }
    }
    PicnicSpinner.configureSpinner(items);
  }
}
