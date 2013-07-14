package com.ajegames.picnic.repository;

import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created for AJE Games by bigdaddy on 5/26/13 at 8:47 AM.
 */
public class BaseRepository {

  private static Logger LOG = LoggerFactory.getLogger(BaseRepository.class);

  public static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

  private Map<String, PersistedGameEntity> entities = Maps.newHashMap();

  public static String generateUniqueKey(String[] parts) {
    String random = String.valueOf(Math.random());
    String basis = random.substring(2, (random.length() >= 16 ? 16 : random.length()));  // had trouble going out of bounds on substring
    for (String part : parts) {
      basis += part;
    }
    basis += Long.toString(DateTime.now().getMillis());
    LOG.info("Generated key: " + basis);
    return toHex(basis);
  }

  private static String toHex(String arg) {
    return String.format("%x", new BigInteger(1, arg.getBytes()));
  }
  public void addEntity(PersistedGameEntity entity) {
    entities.put(entity.getKey(), entity);
  }

  protected Map<String, PersistedGameEntity> getEntities() {
    return entities;
  }
}
