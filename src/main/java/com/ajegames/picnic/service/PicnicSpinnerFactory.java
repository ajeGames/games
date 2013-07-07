package com.ajegames.picnic.service;

import com.ajegames.picnic.*;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Populates spinner options from configuration data.
 */
public class PicnicSpinnerFactory {

  synchronized public static void configureSpinner(SpinnerConfiguration config) {
    Item toAdd;
    List<Item> spinnerOptions = Lists.newArrayList();

    for (SpinnerItemConfig item : config.getFoods()) {
      toAdd = Item.createFood(item.getKey(), item.getDescription());
      addToCatalogAndSpinnerWithWeight(spinnerOptions, toAdd, item.getWeight());
    }

    for (SpinnerItemConfig item : config.getDrinks()) {
      toAdd = Item.createDrink(item.getKey(), item.getDescription());
      addToCatalogAndSpinnerWithWeight(spinnerOptions, toAdd, item.getWeight());
    }

    for (SpinnerItemConfig item : config.getSupplies()) {
      toAdd = Item.createSupply(item.getKey(), item.getDescription());
      addToCatalogAndSpinnerWithWeight(spinnerOptions, toAdd, item.getWeight());
    }

    for (SpinnerItemConfig item : config.getNuisances()) {
      if ("reducesFood".equals(item.getImpact())) {
        toAdd = Nuisance.createAgainstFood(item.getKey(), item.getDescription());
      } else if ("wipeout".equals(item.getImpact())) {
        toAdd = Nuisance.createWipeOut(item.getKey(), item.getDescription());
      } else {
        // TODO log some kind of config error
        continue;
      }
      addToCatalogAndSpinnerWithWeight(spinnerOptions, toAdd, item.getWeight());
    }

    for (SpinnerItemConfig item : config.getPreventions()) {
      Item counteracts = ItemCatalog.getInstance().getItem(item.getCounteracts());
      if (counteracts != null && counteracts instanceof Nuisance) {
        toAdd = Prevention.createPrevention(item.getKey(), item.getDescription(), (Nuisance) counteracts);
      } else {
        // TODO log some kind of config error
        continue;
      }
      addToCatalogAndSpinnerWithWeight(spinnerOptions, toAdd, item.getWeight());
    }

    PicnicSpinner.configureSpinner(spinnerOptions);
  }

  private static void addToCatalogAndSpinnerWithWeight(List<Item> spinnerOptions, Item toAdd, int weight) {
    ItemCatalog.getInstance().addItem(toAdd);
    for (int i = 0; i < weight; i++) {
      spinnerOptions.add(toAdd);
    }
  }
}
