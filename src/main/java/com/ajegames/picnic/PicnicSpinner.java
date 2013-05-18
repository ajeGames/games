package com.ajegames.picnic;

import com.ajegames.utility.Spinner;

/**
 * Spinner populated with kinds of SpinnerOptions that are particular to Picnic: Item, Nuisance.
 */
public class PicnicSpinner extends Spinner {

  public static PicnicSpinner createBlankSpinner() {
    return new PicnicSpinner();
  }

  public static PicnicSpinner createPicnicSpinnerWithDefaultOptions() {
    PicnicSpinner spinner = new PicnicSpinner();
    spinner.initialize();
    return spinner;
  }

  private void initialize() {
    Nuisance ants = Nuisance.createAgainstFood("Ants");
    Nuisance wind = Nuisance.createAgainstSupply("Wind");
    Nuisance blackFlies = Nuisance.create("Black Flies");  // lose a turn
    Nuisance sunburn = Nuisance.create("Sunburn");  // lose points at the end
    Nuisance rain = Nuisance.createWipeOut("Rain");  // picnic is cancelled

    addItem(Item.createFood("Hamburgers"))
            .addItem(Item.createFood("Sandwiches"))
            .addItem(Item.createFood("Fried Chicken"))
            .addItem(Item.createFood("Sushi"))
            .addItem(Item.createFood("Potato Salad"))
            .addItem(Item.createFood("Macaroni Salad"))
            .addItem(Item.createFood("Potato Chips"))
            .addItem(Item.createFood("Carrot Sticks"))
            .addItem(Item.createFood("Fruit Salad"))
            .addItem(Item.createFood("Watermelon"))
            .addItem(Item.createFood("Brownies"))
            .addItem(Item.createDrink("Water"))
            .addItem(Item.createDrink("Soda"))
            .addItem(Item.createDrink("Juice Boxes"))
            .addItem(Item.createSupply("Plastic Forks and Spoons"))
            .addItem(Item.createSupply("Chopsticks"))
            .addItem(Item.createSupply("Plates and Napkins"))
            .addItem(Prevention.createPrevention("Sunscreen", sunburn))
            .addItem(Prevention.createPrevention("Bug Spray", blackFlies))
            .addItem(Prevention.createPrevention("Umbrella", rain))
            .addNuisance(ants)
            .addNuisance(ants)
            .addNuisance(ants)
            .addNuisance(ants)
            .addNuisance(wind)
            .addNuisance(blackFlies)
            .addNuisance(sunburn)
            .addNuisance(rain)
            .addNuisance(rain)
            .addNuisance(rain);
  }

  public PicnicSpinner addItem(Item value) {
    super.addOption(value);
    return this;
  }

  public PicnicSpinner addNuisance(Nuisance value) {
    super.addOption(value);
    return this;
  }

}
