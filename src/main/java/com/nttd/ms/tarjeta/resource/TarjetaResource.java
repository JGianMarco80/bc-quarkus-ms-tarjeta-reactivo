package com.nttd.ms.tarjeta.resource;

import com.nttd.ms.tarjeta.dto.TCreditoMovimiento;
import com.nttd.ms.tarjeta.service.TarjetaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/tarjeta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarjetaResource {

    @Inject
    TarjetaService tarjetaService;

    @GET
    @Path("/movimiento-tarjeta-credito")
    public TCreditoMovimiento movimientoTCredito(@QueryParam("numeroTarjeta") String numeroTarjeta) {
        return tarjetaService.movimientoTCredito(numeroTarjeta);
    }

    @GET
    @Path("/validar-tarjeta-debito")
    public String validarTarjeta(@QueryParam("numeroTarjeta") String numeroTarjeta,
                                 @QueryParam("codigoValidacion") String codigoValidacion){
        return tarjetaService.validarTarjeta(numeroTarjeta, codigoValidacion);
    }

}
