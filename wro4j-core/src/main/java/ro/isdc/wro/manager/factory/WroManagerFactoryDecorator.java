package ro.isdc.wro.manager.factory;

import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.manager.WroManager.Builder;
import ro.isdc.wro.util.AbstractDecorator;
import ro.isdc.wro.util.DestroyableLazyInitializer;


/**
 * Simple decorator for {@link WroManagerFactory}.
 *
 * @author Alex Objelean
 * @created 23 Jun 2012
 * @since 1.4.7
 */
public class WroManagerFactoryDecorator
    extends AbstractDecorator<WroManagerFactory>
    implements WroManagerFactory {
  private final DestroyableLazyInitializer<WroManager> managerInitializer = new DestroyableLazyInitializer<WroManager>() {
    @Override
    protected WroManager initialize() {
      final WroManager.Builder builder = new WroManager.Builder(getDecoratedObject().create());
      onBeforeBuild(builder);
      return builder.build();
    }

    @Override
    public void destroy() {
      getDecoratedObject().destroy();
      super.destroy();
    };
  };
  public WroManagerFactoryDecorator(final WroManagerFactory managerFactory) {
    super(managerFactory);
  }

  /**
   * {@inheritDoc}
   */
  public WroManager create() {
    return managerInitializer.get();
  }

  /**
   * Allows client code to change the builder before the {@link WroManager} is created.
   */
  protected void onBeforeBuild(final Builder builder) {
  }

  /**
   * {@inheritDoc}
   */
  public void onCachePeriodChanged(final long value) {
    managerInitializer.get().onCachePeriodChanged(value);
  }

  /**
   * {@inheritDoc}
   */
  public void onModelPeriodChanged(final long value) {
    managerInitializer.get().onModelPeriodChanged(value);
  }

  /**
   * {@inheritDoc}
   */
  public void destroy() {
    managerInitializer.destroy();
  }
}
