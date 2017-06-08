package it.imolinfo.adp.api.process;

import com.adp.api.invoker.api.WorkerApi;
import com.adp.api.invoker.client.ApiException;
import com.adp.api.invoker.model.WorkerPersonalAddressChangeEvent;
import it.imolinfo.app.ConfigurationManager;
import it.imolinfo.queue.SenderAMQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by morlins on 06/06/17.
 */
public class WorkerEventChangePersonalAddressMockedProcessor implements IWorkerEventProcessor {
    static final Logger LOG = LoggerFactory.getLogger(WorkerEventChangePersonalAddressMockedProcessor.class);

    private static WorkerApi api;
    private String href;

    public WorkerEventChangePersonalAddressMockedProcessor(String href,WorkerApi api) {
        this.href = href;
        this.api = api;
    }

    public WorkerPersonalAddressChangeEvent invoke() throws ApiException {
        WorkerPersonalAddressChangeEvent response = null;

            response = api.workerPersonalAddressChange(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        SenderAMQ.sendMessage("puppa");
        if(response!=null){
            LOG.debug("response size: " + response.getEvents().size());
        }
        return response;
    }
}