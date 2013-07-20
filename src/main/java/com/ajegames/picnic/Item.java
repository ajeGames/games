package com.ajegames.picnic;

import com.ajegames.utility.BaseSpinnerOption;

/**
 * Something you collect in order to assemble a picnic and win.
 */
public class Item extends BaseSpinnerOption {

  public static final Item NULL_ITEM = new Item("null", "null", ItemType.NULL);

  private String description;
  private ItemType type;

  protected Item(String key, String description, ItemType type) {
    super(key);
    this.description = description;
    this.type = type;
  }

  public static Item createPicnicItem(String key, String description, ItemType type) {
    return new Item(key, description, type);
  }

  public static Item createFood(String key, String description) {
    return createPicnicItem(key, description, ItemType.FOOD);
  }

  public static Item createFood(String key) {
    return createPicnicItem(key, key, ItemType.FOOD);
  }

  public static Item createDrink(String key, String description) {
    return createPicnicItem(key, description, ItemType.DRINK);
  }

  public static Item createDrink(String key) {
    return createPicnicItem(key, key, ItemType.DRINK);
  }

  public static Item createSupply(String key, String description) {
    return createPicnicItem(key, description, ItemType.SUPPLY);
  }

  public static Item createSupply(String key) {
    return createPicnicItem(key, key, ItemType.SUPPLY);
  }

  public String getDescription() {
    return description;
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

  public boolean isNuisance() {
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
