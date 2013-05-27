package com.ajegames.utility.rest;

/**
 * Created for AJE Games by bigdaddy on 5/25/13 at 10:30 AM.
 */
public class ResourceLink {

  public static final String REL_SELF = "self";

  private String href; // uri to the resource
  private String rel;  // relation

  private ResourceLink() {
  }

  public static ResourceLink create(String href, String relation) {
    ResourceLink result = new ResourceLink();
    result.href = href;
    result.rel = relation;
    return result;
  }

  public static ResourceLink createSelfLink(String href) {
    return create(href, ResourceLink.REL_SELF);
  }

  public String getHref() {
    return href;
  }

  public String getRel() {
    return rel;
  }
}
