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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class CoreApiServiceImpl extends CoreApiService {
    @Override
    public Response eventNotificationMessages(Events body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic^2!")).build();
    }
}
