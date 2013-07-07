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
        items.add(Item.createFood(item.getKey(), item.getDescription()));
      }
    }
    for (SpinnerItemConfig item : config.getDrinks()) {
      for (int i = 0; i < item.getWeight(); i++) {
        items.add(Item.createDrink(item.getKey(), item.getDescription()));
      }
    }
    for (SpinnerItemConfig item : config.getSupplies()) {
      for (int i = 0; i < item.getWeight(); i++) {
        items.add(Item.createSupply(item.getKey(), item.getDescription()));
      }
    }
    for (SpinnerItemConfig item : config.getNuisances()) {
      for (int i = 0; i < item.getWeight(); i++) {
        if ("reducesFood".equals(item.getImpact())) {
          items.add(Nuisance.createAgainstFood(item.getKey(), item.getDescription()));
        } else if ("wipeout".equals(item.getImpact())) {
          items.add(Nuisance.createWipeOut(item.getKey(), item.getDescription()));
        } else {
          // add message to log that nuisance unsupported
        }
      }
    }
    for (SpinnerItemConfig item : config.getPreventions()) {
      for (int i = 0; i < item.getWeight(); i++) {
        Item counteracts = ItemCatalog.getInstance().getItem(item.getCounteracts());
        if (counteracts instanceof Nuisance) {
          items.add(Prevention.createPrevention(item.getKey(), item.getDescription(), (Nuisance) counteracts));
        }
      }
    }
    PicnicSpinner.configureSpinner(items);
  }
}
