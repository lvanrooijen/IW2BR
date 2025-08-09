package com.bella.IW2BR.domain.environment.util;

import com.bella.IW2BR.domain.environment.Environment;

/**
 * Implement this interface to register an environment as a member, in order to access the {@link
 * EnvironmentHelperMethods#throwIfNotInEnvironment(EnvironmentMember, Long)} method
 */
public interface EnvironmentMember {
  public Environment getEnvironment();
}
