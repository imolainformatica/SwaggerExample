package it.imolinfo.adp.api.service.impl;

import com.adp.api.invoker.api.WorkerApi;
import com.adp.api.invoker.client.ApiException;
import com.adp.api.invoker.model.WorkerPersonalAddressChangeEvent;
import it.imolinfo.adp.api.*;
import it.imolinfo.adp.api.model.*;

import it.imolinfo.adp.api.model.Events;

import java.util.List;

import it.imolinfo.adp.api.service.ApiResponseMessage;
import it.imolinfo.adp.api.service.CoreApiService;
import it.imolinfo.adp.api.service.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class CoreApiServiceImpl extends CoreApiService {
    static final Logger LOG = LoggerFactory.getLogger(CoreApiServiceImpl.class);
    @Override
    public Response eventNotificationMessages(Events body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        LOG.info(new StringBuilder().append("eventID").append(body.getEvents().get(0).getEventID()).toString());
        String eventId = "123";
        String associateoid = "test";
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
        String originatingEventID = "test";
        String relatedEventID = "test";
        WorkerPersonalAddressChangeEvent response=null;
        try {
            WorkerApi api = new WorkerApi();
//            api.getApiClient().setBasePath("");
            response = api.workerPersonalAddressChange(eventId, associateoid, orgoid, SOR, realm, roleCode, smServersessionid, sORContext, sORUri, acceptLanguage, CONSUMEROOID, CONSUMERAOID, consumerAppOID, aDPActAsOrgOID, aDPActAsAssociateOID, aDPOnBehalfOfOrgOID, aDPOnBehalfOfAssociateOID, aDPActingSessionID, originatingEventID, relatedEventID);
        }catch (ApiException e){
            LOG.error(e.getLocalizedMessage(),e);
        }
        if(response!=null){
            LOG.debug("response size: " + response.getEvents().size());
        }
        return Response.ok().entity(response).build();
    }
}
