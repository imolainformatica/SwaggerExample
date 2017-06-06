package it.imolinfo.adp.api.service.impl;

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
    private static final java.lang.String PATTERN = "events/hr/v1";

    @Override
    public Response eventNotificationMessages(Events body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        LOG.info(new StringBuilder().append("eventID").append(body.getEvents().get(0).getEventID()).toString());

        Object response=null;
        //TODO disaccopiamento
        if(body.getEvents().get(0).getLinks().size()>0){
            //ho qualcosa da fare
            for(EventsLinks eventsLinks : body.getEvents().get(0).getLinks()){
                LOG.debug(String.format("process EventLinks: %s", eventsLinks));
                if(eventsLinks.getMethod()!=null && eventsLinks.getMethod().equals(EventsLinks.MethodEnum.GET)){
                    //TODO fattorizzazione
                    String href = eventsLinks.getHref();
                    String title = eventsLinks.getTitle();
                    response =  EventProcessorFactory.getProcessor(href).invoke();
                }else{
                    LOG.error(String.format("metodo non supportato: %s", eventsLinks.getMethod()));
                    //TODO gestione sensata errori
                    return Response.serverError().status(Response.Status.BAD_REQUEST).entity(String.format("Method not supported: %s", eventsLinks.getMethod())).build();
                }
            }
        }


        return Response.ok().entity(response).build();
    }
}
