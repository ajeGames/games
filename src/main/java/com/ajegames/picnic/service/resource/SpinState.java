package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.SpinStatus;
import com.ajegames.picnic.TurnAction;

public class SpinState {

  // TODO this is incomplete -- need to account for all possible results

  private String spinToken;
  private String spinResult;
  private String itemToRemove;
  private boolean remove;

  private SpinState() {
  }

  public static SpinState createForToken(String token) {
    SpinState out = new SpinState();
    out.spinToken = token;
    return out;
  }

  public static SpinState createForSpin(SpinStatus result) {
    SpinState out = new SpinState();
    out.spinResult = result.getSpinResult().getValue();
    out.remove = TurnAction.REMOVE.equals(result.getWhatToDo());
    out.itemToRemove = result.getWhatToActOn().getValue();
    return out;
  }

  public String getSpinToken() {
    return spinToken;
  }

  public String getSpinResult() {
    return spinResult;
  }

  public String getItemToRemove() {
    return itemToRemove;
  }

  public boolean isRemove() {
    return remove;
  }
}
