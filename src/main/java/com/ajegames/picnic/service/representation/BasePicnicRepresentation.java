package com.ajegames.picnic.service.representation;

import com.ajegames.utility.rest.ResourceLink;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class BasePicnicRepresentation {
  public static final String RELATIONS_URI = "http://picnic.ajegames.com/relations/";
  public static final String PICNIC_MEDIA_TYPE = "application/vnd.picnic+json";

  protected List<ResourceLink> links;

  protected void addLink(String uri, String relation) {
    if (links == null) {
      links = Lists.newArrayList();
    }
    links.add(ResourceLink.create(uri, relation, PICNIC_MEDIA_TYPE));
  }

  public List<ResourceLink> getLinks() {
    return links;
  }
}
