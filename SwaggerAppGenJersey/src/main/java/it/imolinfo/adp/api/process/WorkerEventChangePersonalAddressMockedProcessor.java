package it.imolinfo.adp.api.process;

import com.adp.api.invoker.api.WorkerApi;
import com.adp.api.invoker.client.ApiException;
import com.adp.api.invoker.model.WorkerPersonalAddressChangeEvent;
import it.imolinfo.app.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by morlins on 06/06/17.
 */
public class WorkerEventChangePersonalAddressMockedProcessor implements IWorkerEventProcessor {
    static final Logger LOG = LoggerFactory.getLogger(WorkerEventChangePersonalAddressMockedProcessor.class);
    private static final String PATTERN = "events/hr/v1";

    private String href;

    public WorkerEventChangePersonalAddressMockedProcessor(String href) {
        this.href = href;
    }

    public WorkerPersonalAddressChangeEvent invoke() {
        WorkerPersonalAddressChangeEvent response = null;
        try {
            WorkerApi api = new WorkerApi();
            //gestione host in maniera selettiva in base alla richiesta
            //            api.getApiClient().setBasePath("");
            //http://localhost:8989/events/hr/v1
            //https://test-api.adp.com/events/hr/v1/worker.personal-address.change
            if(href!=null && href.contains(PATTERN)){
                LOG.info("l'url matching");
                LOG.info(String.format("set basePath from:%s to %s%s", api.getApiClient().getBasePath(), ConfigurationManager.getHCMHost(), PATTERN));
                api.getApiClient().setBasePath(ConfigurationManager.getHCMHost()+"/"+PATTERN);
                response = api.workerPersonalAddressChange(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//                            } catch (UnsupportedEncodingException e) {
//                                LOG.error("impossibile estrarre campi da url ",e);
                //TODO gestire errori
//                            }
            } else {
                LOG.error(String.format("l'url %s non presenta il pattern desiderato: %s ", href));
            }
        }catch (ApiException e){
            LOG.error(e.getLocalizedMessage(),e);
        }
        if(response!=null){
            LOG.debug("response size: " + response.getEvents().size());
        }
        return response;
    }
}