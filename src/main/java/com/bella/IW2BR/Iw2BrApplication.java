package com.bella.IW2BR;

import com.bella.IW2BR.utils.config.IW2BRBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Iw2BrApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(Iw2BrApplication.class);
    application.setBanner(new IW2BRBanner());
    application.run(args);
  }
}
