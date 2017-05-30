package it.imolinfo.adp.api.service.impl;

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
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic^2!")).build();
    }
}
