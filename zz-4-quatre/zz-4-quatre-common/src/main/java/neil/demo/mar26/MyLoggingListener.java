package neil.demo.mar26;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryLoadedListener;
import com.hazelcast.map.listener.EntryEvictedListener;
import com.hazelcast.map.listener.EntryExpiredListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("rawtypes")
@Slf4j
public class MyLoggingListener implements EntryAddedListener, EntryUpdatedListener,
	EntryRemovedListener, EntryEvictedListener, EntryExpiredListener, EntryLoadedListener
	{
        
        @Override
        public void entryLoaded(EntryEvent event) {
                this.logIt(event);
        }
        @Override
        public void entryAdded(EntryEvent event) {
                this.logIt(event);
        }
        @Override
        public void entryEvicted(EntryEvent event) {
                this.logIt(event);
        }
        @Override
        public void entryExpired(EntryEvent event) {
                this.logIt(event);
        }
        @Override
        public void entryUpdated(EntryEvent event) {
                this.logIt(event);
        }
        @Override
        public void entryRemoved(EntryEvent event) {
                this.logIt(event);
        }

        private void logIt(EntryEvent event) {
                log.info("Map '{}' {} : {} {}",
                                event.getSource(),
                                event.getEventType(),
                                event.getKey(),
                                event.getValue()
                                );
        }

}