/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.factory;

import java.util.ArrayList;
import java.util.List;

import ro.isdc.wro.model.resource.locator.UriLocator;


/**
 * Holds a list of uri locators. The uriLocator will be created based on the first
 * uriLocator from the supplied list which will accept the url.
 *
 * @author Alex Objelean
 * @created Created on Nov 4, 2008
 */
public final class SimpleUriLocatorFactory extends AbstractUriLocatorFactory {
  private final List<UriLocator> uriLocators = new ArrayList<UriLocator>();

  /**
   * @param uri to handle by the locator.
   * @return an instance of {@link UriLocator} which is capable of handling provided uri. Returns null if no locator
   *         found.
   */
  public UriLocator getInstance(final String uri) {
    for (final UriLocator uriLocator : uriLocators) {
      if (uriLocator.accept(uri)) {
        return uriLocator;
      }
    }
    return null;
  }

  /**
   * Allow adding more than one uriLocators.
   *
   * @param locators list of {@link UriLocator} arguments.
   */
  public final SimpleUriLocatorFactory addUriLocator(final UriLocator... locators) {
    for (final UriLocator locator : locators) {
      uriLocators.add(locator);
    }
    return this;
  }
}
