package com.ajegames.picnic.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by dave on 4/30/13 at 9:57 PM
 */
public class PicnicConfiguration extends Configuration {

  @Valid
  @NotNull
  @JsonProperty
  private SpinnerConfiguration spinner;

  public SpinnerConfiguration getSpinnerConfiguration() {
    return spinner;
  }
}
