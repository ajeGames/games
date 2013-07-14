package com.ajegames.picnic.service.resource;

import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by AJE Games by bigdaddy on 5/3/13 at 9:53 PM.
 */
@Path("/picnic")
@Produces(MediaType.TEXT_HTML)
public class PicnicResource {

  private static Logger LOG = LoggerFactory.getLogger(PicnicResource.class);

  @GET
  @Timed
  public String getInfo() {
    LOG.info("Invoked getInfo");
    return "<p>Hi! Want to play Picnic?</p>\n"
            + "<form action=\"/service/picnic/player\" method=\"POST\">\n"
            + "  <input type=\"text\" name=\"playerName\"/> <label for=\"playerName\">Player name: </label>\n"
            + "  <input type=\"submit\" value=\"Add Player\"/>\n</form>\n\n";
  }

}
