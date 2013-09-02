package com.ajegames.picnic.service.health;

import com.ajegames.picnic.domain.PicnicSpinner;
import com.yammer.metrics.core.HealthCheck;

/**
 * Created by dave on 7/7/13 at 9:49 PM.
 */
public class SpinnerHealthCheck extends HealthCheck {

  public SpinnerHealthCheck() {
    super("spinner");
  }

  @Override
  protected Result check() throws Exception {
    PicnicSpinner.createInstance();  // proves that spinner is configured
    return Result.healthy();
  }
}
