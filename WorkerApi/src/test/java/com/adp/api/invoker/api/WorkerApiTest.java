/**
 * Worker Events
 * This is a example for POC
 *
 * OpenAPI spec version: 1.0.0
 * Contact: gmorlini@imolinfo.it
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.adp.api.invoker.api;

import com.adp.api.invoker.client.ApiException;
import com.adp.api.invoker.model.ConfirmMessage;
import com.adp.api.invoker.model.WorkerPersonalAddressChangeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import it.imolinfo.app.CustomObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Rule;

import java.io.IOException;
import java.util.Date;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * API tests for WorkerApi
 */
public class WorkerApiTest {

    private static final Log LOG = LogFactory.getLog(WorkerApiTest.class);
    private final WorkerApi api = new WorkerApi();
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8989); // No-args constructor defaults to port 8080
    
    /**
     * Change on personal address of worker
     *
     * Returns an event instance
     *
     * @throws ApiException
     *          if the Api call fails
     */
  //  @Test
    public void workerPersonalAddressChangeTestSuccesfull() {
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
        String urlMock= String.format(".*/events/hr/v1/worker.personal-address.change/%s.*", eventId);
        stubFor(get(urlMatching(urlMock))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("WorkerApiTest/events.hr.v1.worker.personal-address.change.123.json")
                ));
        WorkerPersonalAddressChangeEvent response=null;
        try {
            response = api.workerPersonalAddressChange(eventId, associateoid, orgoid, SOR, realm, roleCode, smServersessionid, sORContext, sORUri, acceptLanguage, CONSUMEROOID, CONSUMERAOID, consumerAppOID, aDPActAsOrgOID, aDPActAsAssociateOID, aDPOnBehalfOfOrgOID, aDPOnBehalfOfAssociateOID, aDPActingSessionID, originatingEventID, relatedEventID);
        }catch (ApiException e){
        LOG.error(e.getLocalizedMessage(),e);
        }
        if(response!=null){
            LOG.debug("response size: " + response.getEvents().size());
        }
        verify(1, getRequestedFor(urlMatching(urlMock)));
    }
    /**
     * Change on personal address of worker
     *
     * Returns an event instance
     *
     * @throws ApiException
     *          if the Api call fails
     */
   // @Test
    public void workerPersonalAddressChangeTestFail() {
        String eventId = "124";
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
        String relatedEventID = "test"; String urlMock= String.format(".*/events/hr/v1/worker.personal-address.change/%s.*", eventId);
        stubFor(get(urlMatching(urlMock))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("WorkerApiTest/confirmMessage_v1_0_rev002_400_GET.json")
                ));
        WorkerPersonalAddressChangeEvent response=null;
        try {
            response = api.workerPersonalAddressChange(eventId, associateoid, orgoid, SOR, realm, roleCode, smServersessionid, sORContext, sORUri, acceptLanguage, CONSUMEROOID, CONSUMERAOID, consumerAppOID, aDPActAsOrgOID, aDPActAsAssociateOID, aDPOnBehalfOfOrgOID, aDPOnBehalfOfAssociateOID, aDPActingSessionID, originatingEventID, relatedEventID);
        }catch (ApiException e){
            ObjectMapper mapper = CustomObjectMapper.get();
            //JSON from String to Object
            try {
                ConfirmMessage obj = mapper.readValue(e.getResponseBody(), ConfirmMessage.class);
                assertEquals("12312321",obj.getConfirmMessage().getConfirmMessageID().getIdValue());
                assertEquals(new Date(Long.parseLong("1372087801000")),obj.getConfirmMessage().getCreateDateTime());
                assertEquals(new Date(Long.parseLong("1372109400000")),obj.getConfirmMessage().getRequestReceiptDateTime());

            } catch (IOException e1) {
                LOG.error("impossibile eseguire parsing json risposta:"+e1.getLocalizedMessage(),e1);
                LOG.error(e.getResponseBody());
                fail();
            }
        }
        assertEquals(null,response);
        if(response!=null){
            LOG.debug("response size: " + response.getEvents().size());
        }
        verify(1, getRequestedFor(urlMatching(urlMock)));
    }
}
