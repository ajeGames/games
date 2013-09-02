package com.ajegames.picnic.repository;

import com.ajegames.utility.fate.GameDie;

public class KeyGenerator {

  private static GameDie generator = new GameDie(62);
  private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  public static String generateKey(int keyLength) {
    StringBuilder key = new StringBuilder();
    for (int i = 0; i < keyLength; i++) {
      key.append(CHARACTERS.charAt(generator.roll() - 1));
    }
    return key.toString();
  }
}
