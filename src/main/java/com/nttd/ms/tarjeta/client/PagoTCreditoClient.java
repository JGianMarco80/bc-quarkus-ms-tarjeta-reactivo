package com.nttd.ms.tarjeta.client;

import com.nttd.ms.tarjeta.client.model.PagoCredito;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient
@Path("/pago-credito")
public interface PagoTCreditoClient {

    @GET
    List<PagoCredito> findByNumeroCuenta(@QueryParam("numeroCuenta") String numeroCuenta,
                                         @QueryParam("tipoPago") String tipoPago);

}
