package it.imolinfo.adp.api.service.impl;

import com.adp.api.invoker.api.WorkerApi;
import com.adp.api.invoker.client.ApiException;
import com.adp.api.invoker.model.WorkerPersonalAddressChangeEvent;
import it.imolinfo.adp.api.*;
import it.imolinfo.adp.api.model.*;

import it.imolinfo.adp.api.model.Events;

import java.io.UnsupportedEncodingException;
import java.util.List;

import it.imolinfo.adp.api.service.ApiResponseMessage;
import it.imolinfo.adp.api.service.CoreApiService;
import it.imolinfo.adp.api.service.NotFoundException;

import java.io.InputStream;
import java.util.Map;

import it.imolinfo.util.UrlUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.joda.time.base.AbstractDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class CoreApiServiceImpl extends CoreApiService {
    static final Logger LOG = LoggerFactory.getLogger(CoreApiServiceImpl.class);
    private static final java.lang.String PATTERN = "events/hr/v1";

    @Override
    public Response eventNotificationMessages(Events body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        LOG.info(new StringBuilder().append("eventID").append(body.getEvents().get(0).getEventID()).toString());

        WorkerPersonalAddressChangeEvent response=null;
        //TODO disaccopiamento
        if(body.getEvents().get(0).getLinks().size()>0){
            //ho qualcosa da fare
            for(EventsLinks eventsLinks : body.getEvents().get(0).getLinks()){
                LOG.debug(String.format("process EventLinks: %s", eventsLinks));
                if(eventsLinks.getMethod()!=null && eventsLinks.getMethod().equals(EventsLinks.MethodEnum.GET)){
                    //TODO logica
                    String href = eventsLinks.getHref();
                    String title = eventsLinks.getTitle();
                    try {
                        WorkerApi api = new WorkerApi();
                        //TODO gestire host in maniera selettiva in base alla richiesta
                        //            api.getApiClient().setBasePath("");
                        //http://localhost:8989/events/hr/v1
                        //https://test-api.adp.com/events/hr/v1/worker.personal-address.change
                        String[] urls=href.split(PATTERN);
                        if(urls!=null && urls.length>1){
                            LOG.info("l'url potrebbe essere fra quelli censiti");
                            LOG.info(String.format("imposto il basePath da:%s a %s%s", api.getApiClient().getBasePath(), urls[0], PATTERN));
                            api.getApiClient().setBasePath(urls[0]+PATTERN);
                            //"/events/hr/v1/worker.personal-address.change/%s?originatingEventID=%s&relatedEventID=%s
                            try {
                                String[] paramStr=urls[1].split("\\?");
                                String[] temp=paramStr[0].split("/");
                                Map<String,String> param = UrlUtils.splitSimpleQuery(paramStr[1]);
                                String eventId = temp[temp.length-1];
                                String associateoid = param.get("associateoid");
                                String orgoid = "test";
                                String SOR = "test";
                                String realm = "test";
                                String roleCode = "test";
                                String smServersessionid = "test";
                                String sORContext = "test";
                                String sORUri = "test";
                                String acceptLanguage = "test";
                                String CONSUMEROOID = "test";
                                String CONSUMERAOID = "test";
                                String consumerAppOID = "test";
                                String aDPActAsOrgOID = "test";
                                String aDPActAsAssociateOID = "test";
                                String aDPOnBehalfOfOrgOID = "test";
                                String aDPOnBehalfOfAssociateOID = "test";
                                String aDPActingSessionID = "test";
                                String originatingEventID = param.get("originatingEventID");
                                String relatedEventID = param.get("relatedEventID");
                                LOG.info(param.toString());
                                response = api.workerPersonalAddressChange(eventId, associateoid, orgoid, SOR, realm, roleCode, smServersessionid, sORContext, sORUri, acceptLanguage, CONSUMEROOID, CONSUMERAOID, consumerAppOID, aDPActAsOrgOID, aDPActAsAssociateOID, aDPOnBehalfOfOrgOID, aDPOnBehalfOfAssociateOID, aDPActingSessionID, originatingEventID, relatedEventID);
                            } catch (UnsupportedEncodingException e) {
                                LOG.error("impossibile estrarre campi da url ",e);
                                //TODO gestire
                            }
                        } else {
                            LOG.error(String.format("l'url %s non presenta il pattern desiderato: %s ", href));
                        }
                    }catch (ApiException e){
                        LOG.error(e.getLocalizedMessage(),e);
                    }
                    if(response!=null){
                        LOG.debug("response size: " + response.getEvents().size());
                    }
                }else{
                    LOG.error(String.format("metodo non supportato: %s", eventsLinks.getMethod()));
                    //TODO gestione sensata errori
                    return Response.serverError().status(Response.Status.BAD_REQUEST).entity(String.format("metodo non supportato: %s", eventsLinks.getMethod())).build();
                }
            }
        }


        return Response.ok().entity(response).build();
    }
}
