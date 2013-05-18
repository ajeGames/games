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
@Produces(MediaType.TEXT_HTML)
public class PicnicResource {

  public PicnicResource() {
  }

  @GET
  @Timed
  public String getInfo() {
    return "<p>Hi! Want to play Picnic?</p>"
            + "<form action=\"/service/picnic/player\" method=\"POST\">"
            + "<input type=\"text\" name=\"name\"/> <label for=\"name\">Player name: </label>"
            + "<input type=\"submit\" value=\"Add Player\"/></form>";
  }

}
