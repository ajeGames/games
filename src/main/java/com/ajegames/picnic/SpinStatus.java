package com.ajegames.picnic;

public class SpinStatus {

  private Item spinResult;
  private Item whatToActOn;
  private TurnAction whatToDo = TurnAction.ADD;

  public Item getSpinResult() {
    return spinResult;
  }

  public Item getWhatToActOn() {
    return whatToActOn;
  }

  public TurnAction getWhatToDo() {
    return whatToDo;
  }

  public void setSpinResult(Item spinResult) {
    this.spinResult = spinResult;
    this.whatToActOn = spinResult;
  }

  public void setToRemoveItem(Item whatToActOn) {
    this.whatToActOn = whatToActOn;
    this.whatToDo = TurnAction.REMOVE;
  }

  public void setToLoseTurn() {
    this.whatToDo = TurnAction.LOSE_TURN;
  }

  public void setToWipeout() {
    this.whatToActOn = null;
    this.whatToDo = TurnAction.WIPEOUT;
  }
}
