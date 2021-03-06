package com.adp.api.invoker.api;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import it.imolinfo.adp.api.model.Events;
import it.imolinfo.adp.api.model.EventsEvents;
import it.imolinfo.adp.api.model.EventsLinks;
import it.imolinfo.adp.api.model.ResponseMessage;
import it.imolinfo.adp.api.service.CoreApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Rule;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

/**
 * Created by morlins on 01/06/17.
 */
public class FlowTest extends JerseyTest {
    private int PORT = 8989 ;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT); // No-args constructor defaults to port 8080
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(CoreApi.class);
    }

    //@Test
    public void test() {
        String eventId = "123";
        String originatingEventID = "test";
        String relatedEventID = "test";
        String associateoid = "test";
        String SOR = "test";
        String realm = "realm";
        String roleCode = "roleCode";
        String smServersessionid = "smServersessionid";
        String href= String.format("/events/hr/v1/worker.personal-address.change/%s?originatingEventID=%s&relatedEventID=%s&associateoid=%s&SOR=%s&realm=%s&roleCode=%s&smServersessionid=%s", eventId, originatingEventID, relatedEventID, associateoid, SOR, realm, roleCode, smServersessionid);
        String urlMock= String.format(".*/events/hr/v1/worker.personal-address.change/%s.*", eventId);
        stubFor(get(urlMatching(urlMock))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("WorkerApiTest/events.hr.v1.worker.personal-address.change.123.json")
                ));
        Events e = new Events();
        EventsEvents eveve = new EventsEvents();
        EventsLinks link = new EventsLinks();
        link.setTitle("worker.personal-address.change");
        link.setHref(href);
        link.setMethod(EventsLinks.MethodEnum.GET);
        ArrayList<EventsLinks> links = new ArrayList<EventsLinks>();
        links.add(link);
        eveve.setLinks(links);
        eveve.setEventID("test");
        e.addEventsItem(eveve);
        Response response = target("core/v1/event-notification-messages").request().post(Entity.entity(e, MediaType.APPLICATION_JSON));
//        assertTrue(response.hasEntity());
        assertEquals("Should return status 200", 200, response.getStatus());
        assertNotNull("Should return notification", response.getEntity());
        ResponseMessage responseMessage = response.readEntity(ResponseMessage.class);
        assertTrue("response data non aspettata",responseMessage.getData() instanceof Map);
        Map map = (Map) responseMessage.getData();
        assertTrue(map!=null && map.get("events") instanceof List && ((List)map.get("events")).size()>0);
        assertEquals("actor.associateOID non corrisponde","G4O73G9Z62SL2NFM",((Map)((Map)((List)map.get("events")).get(0)).get("actor")).get("associateOID"));
        verify(1, getRequestedFor(urlMatching(urlMock)));
        response.close();
    }

}
