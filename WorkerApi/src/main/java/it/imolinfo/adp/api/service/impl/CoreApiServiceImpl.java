package it.imolinfo.adp.api.service.impl;

import com.adp.api.invoker.client.ApiException;
import it.imolinfo.adp.api.process.EventProcessorFactory;
import it.imolinfo.adp.api.model.*;

import it.imolinfo.adp.api.model.Events;

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
        LOG.info(new StringBuilder().append("eventID").append(body.getEvents().get(0).getEventID()).toString());

        Object response=null;
        ResponseMessage responseEntity = new ResponseMessage();
        if(body.getEvents().get(0).getLinks().size()>0){
            //ho qualcosa da fare
            for(EventsLinks eventsLinks : body.getEvents().get(0).getLinks()){
                LOG.debug(String.format("process EventLinks: %s", eventsLinks));
                if(eventsLinks.getMethod()!=null && eventsLinks.getMethod().equals(EventsLinks.MethodEnum.GET)){
                    String href = eventsLinks.getHref();
                    String title = eventsLinks.getTitle();
                    try {
                        response =  EventProcessorFactory.getProcessor(href).invoke();
                    } catch (ApiException e) {
                        String message = String.format("Error from backend: %s", e.getLocalizedMessage());
                        LOG.error(message);
                        responseEntity.setSuccess(false);
                        responseEntity.setMessage(message);
                        responseEntity.setCode(400);
                        responseEntity.setData(e);
                        return Response.serverError().status(Response.Status.BAD_REQUEST).entity(responseEntity).build();
                    }
                }else{
                    String message=String.format("metodo non supportato: %s", eventsLinks.getMethod());
                    LOG.error(message);
                    responseEntity.setSuccess(false);
                    responseEntity.setMessage(message);
                    responseEntity.setCode(400);
                    return Response.serverError().status(Response.Status.BAD_REQUEST).entity(responseEntity).build();
                }
            }
        }
        responseEntity.setSuccess(true);
        responseEntity.setMessage("OK");
        responseEntity.setCode(200);
        responseEntity.setData(response);

        return Response.ok().entity(responseEntity).build();
    }
}
