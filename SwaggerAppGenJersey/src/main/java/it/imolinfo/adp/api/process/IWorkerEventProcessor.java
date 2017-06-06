package it.imolinfo.adp.api.process;

import com.adp.api.invoker.model.WorkerPersonalAddressChangeEvent;

/**
 * Created by morlins on 06/06/17.
 */
public interface IWorkerEventProcessor<T> {
    T invoke();
}
