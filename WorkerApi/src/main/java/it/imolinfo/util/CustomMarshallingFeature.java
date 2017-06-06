package it.imolinfo.util;


import it.imolinfo.app.CustomJsonProvider;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * Created by morlins on 30/05/17.
 */
public class CustomMarshallingFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(CustomJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        return true;
    }
}