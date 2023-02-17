package com.nttd.ms.tarjeta.client;

import com.nttd.ms.tarjeta.client.model.Cargo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/tarjeta-credito-cargo")
public interface CargoTCreditoClient {

    @GET
    List<Cargo> findByNumeroCuenta(@QueryParam("numeroCuenta") String numeroCuenta);
}
