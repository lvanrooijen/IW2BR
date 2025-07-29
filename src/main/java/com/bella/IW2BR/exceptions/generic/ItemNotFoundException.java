package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.base.BaseNotFoundException;

public class ItemNotFoundException extends BaseNotFoundException {
  public ItemNotFoundException(String message) {
    super(message);
  }
}
