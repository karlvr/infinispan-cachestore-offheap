package org.infinispan.loaders.offheap.configuration;

import org.infinispan.commons.configuration.Builder;
import org.infinispan.configuration.cache.AbstractStoreConfigurationBuilder;
import org.infinispan.configuration.cache.PersistenceConfigurationBuilder;

/**
 * 
 * @author <a href="mailto:rtsang@redhat.com">Ray Tsang</a>
 * 
 */
public class OffheapCacheStoreConfigurationBuilder extends AbstractStoreConfigurationBuilder<OffheapCacheStoreConfiguration, OffheapCacheStoreConfigurationBuilder> {

   protected boolean compression = false;
   protected int expiryQueueSize = 10000;

   public OffheapCacheStoreConfigurationBuilder(PersistenceConfigurationBuilder builder) {
      super(builder);
   }
   
   public OffheapCacheStoreConfigurationBuilder expiryQueueSize(int expiryQueueSize) {
      this.expiryQueueSize = expiryQueueSize;
      return self();
   }
   
   public OffheapCacheStoreConfigurationBuilder compression(boolean compression) {
      this.compression = compression;
      return self();
   }

   @Override
   public void validate() {
      // how do you validate required attributes?
      super.validate();
   }

   @Override
   public OffheapCacheStoreConfiguration create() {
      return new OffheapCacheStoreConfiguration(purgeOnStartup, fetchPersistentState, ignoreModifications, async.create(),
            singletonStore.create(), preload, shared, properties,
            compression, expiryQueueSize);
            
   }

   @Override
   public Builder<?> read(OffheapCacheStoreConfiguration template) {
      compression = template.compression();
      expiryQueueSize = template.expiryQueueSize();
      
      // AbstractStore-specific configuration
      fetchPersistentState = template.fetchPersistentState();
      ignoreModifications = template.ignoreModifications();
      properties = template.properties();
      purgeOnStartup = template.purgeOnStartup();
      this.async.read(template.async());
      this.singletonStore.read(template.singletonStore());

      return self();
   }

   @Override
   public OffheapCacheStoreConfigurationBuilder self() {
      return this;
   }

}
