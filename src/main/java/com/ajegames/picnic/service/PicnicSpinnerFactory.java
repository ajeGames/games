package com.ajegames.picnic.service;

import com.ajegames.picnic.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Populates spinner options from configuration data.
 */
public class PicnicSpinnerFactory {

  private static Logger LOG = LoggerFactory.getLogger(PicnicSpinnerFactory.class);

  synchronized public static void configureSpinner(SpinnerConfiguration config) {
    LOG.info("Configuring spinner options...");

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
        LOG.warn("Problem with spinner configuration: impact '" + item.getImpact() + "' is not recognized.");
        continue;
      }
      addToCatalogAndSpinnerWithWeight(spinnerOptions, toAdd, item.getWeight());
    }
    for (SpinnerItemConfig item : config.getPreventions()) {
      Item counteracts = ItemCatalog.getInstance().getItem(item.getCounteracts());
      if (counteracts == null) {
        LOG.warn("Problem with spinner configuration: item '" + item.getCounteracts() + "' was not found.");
      } else if (!(counteracts instanceof Nuisance)) {
        LOG.warn("Problem with spinner configuration: '" + item.getCounteracts() + "' is not a nuisance and cannot be counteracted.");
      } else {
        toAdd = Prevention.createPrevention(item.getKey(), item.getDescription(), (Nuisance) counteracts);
        addToCatalogAndSpinnerWithWeight(spinnerOptions, toAdd, item.getWeight());
      }
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
