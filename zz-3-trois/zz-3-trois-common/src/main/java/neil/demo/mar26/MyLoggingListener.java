package neil.demo.mar26;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("rawtypes")
@Slf4j
public class MyLoggingListener implements EntryAddedListener, EntryUpdatedListener {
        
        @Override
        public void entryUpdated(EntryEvent event) {
                this.logIt(event);
        }

        @Override
        public void entryAdded(EntryEvent event) {
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