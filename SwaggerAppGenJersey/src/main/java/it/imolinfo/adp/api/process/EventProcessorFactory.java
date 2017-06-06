package it.imolinfo.adp.api.process;

import it.imolinfo.adp.api.service.NotFoundException;
import it.imolinfo.app.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by morlins on 06/06/17.
 */
public class EventProcessorFactory {
    static final Logger LOG = LoggerFactory.getLogger(EventProcessorFactory.class);
    private static final String PERSONAL_ADDRESS_CHANGE="events/hr/v1/worker.personal-address.change";
    public static IWorkerEventProcessor getProcessor(String href) throws NotFoundException {
        if(href.contains(PERSONAL_ADDRESS_CHANGE)){
            if (ConfigurationManager.testMode()){
                return new WorkerEventChangePersonalAddressMockedProcessor(href);
            } else {
                return new WorkerEventChangePersonalAddressProcessor(href);
            }
        } else {
            LOG.error(String.format("url %s not recognized", href));
            throw new NotFoundException(500,"url %s not recognized");
        }
    }
}
