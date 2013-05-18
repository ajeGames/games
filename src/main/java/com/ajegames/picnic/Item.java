package com.ajegames.picnic;

import com.ajegames.utility.BaseSpinnerOption;

/**
 * Something you collect in order to assemble a picnic and win.
 */
public class Item extends BaseSpinnerOption {

  private ItemType type;

  protected Item(String name, ItemType type) {
    super(name);
    this.type = type;
  }

  public static Item createPicnicItem(String name, ItemType type) {
    return new Item(name, type);
  }

  public static final Item createFood(String name) {
    return createPicnicItem(name, ItemType.FOOD);
  }

  public static Item createDrink(String name) {
    return createPicnicItem(name, ItemType.DRINK);
  }

  public static Item createSupply(String name) {
    return createPicnicItem(name, ItemType.SUPPLY);
  }

  public boolean isFood() {
    return type.equals(ItemType.FOOD);
  }

  public boolean isDrink() {
    return type.equals(ItemType.DRINK);
  }

  public boolean isSupply() {
    return type.equals(ItemType.SUPPLY);
  }

  public boolean isPrevention() {
    return false;
  }

  public ItemType getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Item)) return false;
    if (!super.equals(o)) return false;

    Item item = (Item) o;

    if (type != item.type) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }
}
