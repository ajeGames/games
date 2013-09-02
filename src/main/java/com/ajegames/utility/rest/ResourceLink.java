package com.ajegames.utility.rest;

public class ResourceLink {

  public static final String REL_SELF = "self";

  private String uri; // uri to the resource
  private String rel;  // relation
  private String mediaType;  // media type of resource

  private ResourceLink() {
  }

  public static ResourceLink create(String href, String relation, String mediaType) {
    ResourceLink result = new ResourceLink();
    result.uri = href;
    result.rel = relation;
    result.mediaType = mediaType;
    return result;
  }

  public static ResourceLink createSelfLink(String href, String mediaType) {
    return create(href, ResourceLink.REL_SELF, mediaType);
  }

  public String getUri() {
    return uri;
  }

  public String getRel() {
    return rel;
  }

  public String getMediaType() {
    return mediaType;
  }
}
