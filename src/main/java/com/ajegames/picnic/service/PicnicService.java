package com.ajegames.picnic.service;

import com.ajegames.picnic.service.health.SpinnerHealthCheck;
import com.ajegames.picnic.service.resource.GameResource;
import com.ajegames.picnic.service.resource.PicnicResource;
import com.ajegames.picnic.service.resource.PlayerResource;
import com.ajegames.picnic.service.resource.SpinnerResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

/**
 * Created by dave on 4/30/13 at 9:57 PM
 */
public class PicnicService extends Service<PicnicConfiguration> {
  public static void main(String[] args) throws Exception {
    new PicnicService().run(args);
  }

  @Override
  public void initialize(Bootstrap<PicnicConfiguration> bootstrap) {
    bootstrap.setName("picnic");
    bootstrap.addBundle(new AssetsBundle("/webui/", "/"));
  }

  @Override
  public void run(PicnicConfiguration configuration, Environment environment) {
    PicnicSpinnerFactory.configureSpinner(configuration.getSpinnerConfiguration());

    environment.addResource(new PicnicResource());
    environment.addResource(new PlayerResource());
    environment.addResource(new GameResource());
    environment.addResource(new SpinnerResource());

    environment.addHealthCheck(new SpinnerHealthCheck());
  }
}
