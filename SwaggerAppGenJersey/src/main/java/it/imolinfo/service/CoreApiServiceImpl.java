package it.imolinfo.service;

import it.imolinfo.adp.api.*;
import it.imolinfo.adp.api.model.*;

import it.imolinfo.adp.api.model.Events;

import java.util.List;
import it.imolinfo.adp.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-29T16:46:52.347+02:00")
public class CoreApiServiceImpl extends CoreApiService {
    @Override
    public Response eventNotificationMessages(Events body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic^2!")).build();
    }
}
