package it.imolinfo.adp.api.process;

import com.adp.api.invoker.api.WorkerApi;
import it.imolinfo.adp.api.service.NotFoundException;
import it.imolinfo.app.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by morlins on 06/06/17.
 */
public class EventProcessorFactory {
    static final Logger LOG = LoggerFactory.getLogger(EventProcessorFactory.class);
    private static final String PERSONAL_ADDRESS_CHANGE= ConfigurationManager.getBasePatternEvent() +"/worker.personal-address.change";

    public static IWorkerEventProcessor getProcessor(String href) throws NotFoundException {
        WorkerApi api = getWorkerApi(href);

        if(href.contains(PERSONAL_ADDRESS_CHANGE)){
            if (ConfigurationManager.testMode()){
                return new WorkerEventChangePersonalAddressMockedProcessor(href,api);
            } else {
                String relativeUrl=href.replace(ConfigurationManager.getBasePatternEvent(),"");
                return new WorkerEventChangePersonalAddressProcessor(relativeUrl,api);
            }
        } else {
            LOG.error(String.format("url %s not recognized", href));
            throw new NotFoundException(400,String.format("url %s not recognized", href));
        }
    }

    /**
     * Setup client api
     * @param href url da evento
     * @return
     * @throws NotFoundException
     */
    private static WorkerApi getWorkerApi(String href) throws NotFoundException {
        WorkerApi api = new WorkerApi();
        //gestione host in maniera selettiva in base alla richiesta
        //            api.getApiClient().setBasePath("");
        //da swagger http://localhost:8989/events/hr/v1
        //es. https://test-api.adp.com/events/hr/v1/worker.personal-address.change
        if(href!=null && href.contains(ConfigurationManager.getBasePatternEvent())) {
            //testo se href contiene url completo da cui dedurre host
            String relativeUrl = href.replace(ConfigurationManager.getBasePatternEvent(), "");
            LOG.info("l'url potrebbe essere fra quelli censiti");
            LOG.info(String.format("imposto il basePath da:%s a %s/%s", api.getApiClient().getBasePath(), ConfigurationManager.getHCMHost(), ConfigurationManager.getBasePatternEvent()));
            api.getApiClient().setBasePath(ConfigurationManager.getHCMHost() + "/" + ConfigurationManager.getBasePatternEvent());
        }
        else{
            LOG.error(String.format("url %s not recognized", href));
            throw new NotFoundException(400,String.format("url %s not recognized", href));
        }
        return api;
    }
}
