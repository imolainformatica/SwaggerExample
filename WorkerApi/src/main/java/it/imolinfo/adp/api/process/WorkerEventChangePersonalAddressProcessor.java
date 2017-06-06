package it.imolinfo.adp.api.process;

import com.adp.api.invoker.api.WorkerApi;
import com.adp.api.invoker.client.ApiException;
import com.adp.api.invoker.model.WorkerPersonalAddressChangeEvent;
import it.imolinfo.app.ConfigurationManager;
import it.imolinfo.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by morlins on 06/06/17.
 */
public class WorkerEventChangePersonalAddressProcessor implements IWorkerEventProcessor {
    static final Logger LOG = LoggerFactory.getLogger(WorkerEventChangePersonalAddressProcessor.class);
    private static final java.lang.String PATTERN = "events/hr/v1";
    private static WorkerApi api;
    private String href;

    /**
     *
     * @param href url without common prefix ex. /worker.personal-address.change/%s?originatingEventID=%s&relatedEventID=%s
     * @param api Client Swagger
     */
    public WorkerEventChangePersonalAddressProcessor(String href,WorkerApi api) {
        this.href = href;
        this.api = api;
    }

    public WorkerPersonalAddressChangeEvent invoke() throws ApiException {
        WorkerPersonalAddressChangeEvent response = null;
        //"/worker.personal-address.change/<eventId>?originatingEventID=%s&relatedEventID=%s
        try {
            String[] paramStr=href.split("\\?");
            String[] temp=paramStr[0].split("/");
            //da parametry in query genero mappa per poter scorrere i parametri
            Map<String,String> param = UrlUtils.splitSimpleQuery(paramStr[1]);
            //eventId è l'ultimo pathparam
            String eventId = temp[temp.length-1];
            String associateoid = param.get("associateoid");
            String orgoid =  param.get("originatingEventID");;
            String SOR =  param.get("SOR");
            String realm =  param.get("realm");
            String roleCode =  param.get("roleCode");
            String smServersessionid =  param.get("smServersessionid");
            String sORContext =  param.get("sORContext");
            String sORUri =  param.get("sORUri");
            String acceptLanguage =  param.get("acceptLanguage");
            String CONSUMEROOID =  param.get("CONSUMEROOID");
            String CONSUMERAOID =  param.get("CONSUMERAOID");
            String consumerAppOID =  param.get("consumerAppOID");
            String aDPActAsOrgOID =  param.get("originatingEventID");
            String aDPActAsAssociateOID =  param.get("aDPActAsAssociateOID");
            String aDPOnBehalfOfOrgOID =  param.get("aDPOnBehalfOfOrgOID");
            String aDPOnBehalfOfAssociateOID =  param.get("aDPOnBehalfOfAssociateOID");
            String aDPActingSessionID =  param.get("aDPActingSessionID");
            String originatingEventID = param.get("originatingEventID");
            String relatedEventID = param.get("relatedEventID");
            response = api.workerPersonalAddressChange(eventId, associateoid, orgoid, SOR, realm, roleCode, smServersessionid, sORContext, sORUri, acceptLanguage, CONSUMEROOID, CONSUMERAOID, consumerAppOID, aDPActAsOrgOID, aDPActAsAssociateOID, aDPOnBehalfOfOrgOID, aDPOnBehalfOfAssociateOID, aDPActingSessionID, originatingEventID, relatedEventID);
            //TODO mapping in bean BH
            //TODO chiamata BH
        } catch (UnsupportedEncodingException e) {
            LOG.error("impossibile estrarre campi da url ",e);
            throw new ApiException(String.format("Error in parsing query param: %s for %s", href, e.getLocalizedMessage()));
        }

        if(response!=null){
            LOG.debug("response size: " + response.getEvents().size());
        }
        return response;
    }
}