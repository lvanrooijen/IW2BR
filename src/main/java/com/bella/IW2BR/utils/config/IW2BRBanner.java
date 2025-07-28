package com.bella.IW2BR.utils.config;

import java.io.PrintStream;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

/**
 * Creates a new Banner that gets printed in the terminal and changes the colors of text printed in
 * the terminal
 */
public class IW2BRBanner implements Banner {
  private static final String SEA_GREEN = "\u001B[38;2;0;145;110m";
  private static final String MURREY = "\u001B[38;2;213;106;160m";
  private static final String RESET_COLOR = "\u001B[0m";

  private static final String BANNER =
      MURREY
          + "        _  _  _           _             _              _  _  _              _  _  _  _              _  _  _  _       \n "
          + "      (_)(_)(_)         (_)           (_)          _ (_)(_)(_) _          (_)(_)(_)(_) _          (_)(_)(_)(_) _    \n "
          + "         (_)            (_)           (_)         (_)         (_)          (_)        (_)         (_)         (_)   \n "
          + "         (_)            (_)     _     (_)                   _ (_)          (_) _  _  _(_)         (_) _  _  _ (_)   \n "
          + "         (_)            (_)   _(_)_   (_)                _ (_)             (_)(_)(_)(_)_          (_)(_)(_)(_)      \n "
          + "         (_)            (_)  (_) (_)  (_)             _ (_)                (_)        (_)         (_)   (_) _       \n "
          + "       _ (_) _          (_)_(_)   (_)_(_)          _ (_) _  _  _           (_)_  _  _ (_)         (_)      (_) _    \n "
          + "      (_)(_)(_)           (_)       (_)           (_)(_)(_)(_)(_)         (_)(_)(_)(_)            (_)         (_)   \n "
          + SEA_GREEN
          + "\n :: Spring Boot ::                                                                                       (v3.5.4) \n"
          + RESET_COLOR;

  @Override
  public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
    out.println(BANNER);
  }
}
