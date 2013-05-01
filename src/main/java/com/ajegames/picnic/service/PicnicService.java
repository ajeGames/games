package com.ajegames.picnic.service;

import com.yammer.dropwizard.Service;
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
  }

  @Override
  public void run(PicnicConfiguration configuration, Environment environment) {

    // TODO something good

  }
}
