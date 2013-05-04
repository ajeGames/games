package com.ajegames.picnic.service.resource;

import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by AJE Games by bigdaddy on 5/3/13 at 9:53 PM.
 */
@Path("/picnic")
@Produces(MediaType.APPLICATION_JSON)
public class PicnicResource {

  public PicnicResource() {
  }

  @GET
  @Timed
  public String getInfo() {
    return "Hi! Want to play Picnic?";
  }

}
