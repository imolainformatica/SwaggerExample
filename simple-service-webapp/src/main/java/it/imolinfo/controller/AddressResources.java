package it.imolinfo.controller;

import com.adp.types.worker.Address;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("address")
public class AddressResources {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Address getAdress() {
        Address address = new Address();
        address.setAttentionOfName("pippo");
        address.setBlockName("pippo");
        return address;
    }
}
