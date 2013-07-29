package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.SpinStatus;
import com.ajegames.picnic.TurnAction;

public class SpinState {

  // TODO this is incomplete -- need to account for all possible results

  private String spinToken;
  private String spinResult;
  private String itemToActOn;
  private boolean remove;
  private boolean wipeout;

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
    out.itemToActOn = result.getWhatToActOn().getValue();
    out.wipeout = TurnAction.WIPEOUT.equals(result.getWhatToDo());
    return out;
  }

  public String getSpinToken() {
    return spinToken;
  }

  public String getSpinResult() {
    return spinResult;
  }

  public String getItemToActOn() {
    return itemToActOn;
  }

  public boolean isRemove() {
    return remove;
  }

  public boolean isWipeout() {
    return wipeout;
  }
}
