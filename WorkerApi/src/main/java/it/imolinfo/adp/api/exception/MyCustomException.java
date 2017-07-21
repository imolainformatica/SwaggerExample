package it.imolinfo.adp.api.exception;

import it.imolinfo.adp.api.service.ApiException;

/**
 * Created by morlins on 08/06/17.
 */
public class MyCustomException extends ApiException {
    public MyCustomException(int code, String msg) {
        super(code, msg);
    }
}
