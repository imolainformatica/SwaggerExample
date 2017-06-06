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

    private String href;

    public WorkerEventChangePersonalAddressProcessor(String href) {
        this.href = href;
    }

    public WorkerPersonalAddressChangeEvent invoke() {
        WorkerPersonalAddressChangeEvent response = null;
        try {
            WorkerApi api = new WorkerApi();
            //gestione host in maniera selettiva in base alla richiesta
            //            api.getApiClient().setBasePath("");
            //da swagger http://localhost:8989/events/hr/v1
            //es. https://test-api.adp.com/events/hr/v1/worker.personal-address.change
            if(href!=null && href.contains(PATTERN)){
                //testo se href contiene url completo da cui dedurre host
                String relativeUrl=href.replace(PATTERN,"");
                LOG.info("l'url potrebbe essere fra quelli censiti");
                LOG.info(String.format("imposto il basePath da:%s a %s/%s", api.getApiClient().getBasePath(), ConfigurationManager.getHCMHost(), PATTERN));
                api.getApiClient().setBasePath(ConfigurationManager.getHCMHost()+"/"+PATTERN);
                //"/events/hr/v1/worker.personal-address.change/%s?originatingEventID=%s&relatedEventID=%s
                try {
                    String[] paramStr=relativeUrl.split("\\?");
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
                } catch (UnsupportedEncodingException e) {
                    LOG.error("impossibile estrarre campi da url ",e);
                    //TODO gestire errori
                }
            } else {

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