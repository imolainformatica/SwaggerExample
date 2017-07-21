package it.imolinfo.adp.api.service.impl;

import it.imolinfo.adp.api.model.Events;
import it.imolinfo.adp.api.model.ResponseMessage;
import it.imolinfo.adp.api.service.CoreApiService;
import it.imolinfo.adp.api.service.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class CoreApiServiceImpl extends CoreApiService {
    static final Logger LOG = LoggerFactory.getLogger(CoreApiServiceImpl.class);

    @Override
    public Response eventNotificationMessages(Events body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
       LOG.info("inizio");

        ResponseMessage responseEntity = new ResponseMessage();
       //TODO logica
        responseEntity.setSuccess(true);
        responseEntity.setMessage("OK");
        responseEntity.setCode(200);
        responseEntity.setData(null);

        return Response.ok().entity(responseEntity).build();
    }
}
