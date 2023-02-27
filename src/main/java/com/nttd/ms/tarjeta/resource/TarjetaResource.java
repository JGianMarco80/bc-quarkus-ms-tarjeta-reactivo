package com.nttd.ms.tarjeta.resource;

//import com.nttd.ms.tarjeta.dto.TCreditoMovimiento;
import com.nttd.ms.tarjeta.dto.Redis;
import com.nttd.ms.tarjeta.entity.Tarjeta;
import com.nttd.ms.tarjeta.service.TarjetaService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/tarjeta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarjetaResource {

    @Inject
    TarjetaService tarjetaService;

    //Proyecto2
    /*@GET
    @Path("/movimiento-tarjeta-credito")
    public TCreditoMovimiento movimientoTCredito(@QueryParam("numeroTarjeta") String numeroTarjeta) {
        return tarjetaService.movimientoTCredito(numeroTarjeta);
    }*/

    //Proyecto3
    /*@GET
    @Path("/validar-tarjeta-debito")
    public String validarTarjeta(@QueryParam("numeroTarjeta") String numeroTarjeta,
                                 @QueryParam("codigoValidacion") String codigoValidacion){
        return tarjetaService.validarTarjeta(numeroTarjeta, codigoValidacion);
    }*/

    @GET
    @Path("/validar-tarjeta-debito")
    public Uni<String> validarTarjeta(@QueryParam("numeroTarjeta") String numeroTarjeta,
                                      @QueryParam("codigoValidacion") String codigoValidacion){
        return tarjetaService.validarTarjeta(numeroTarjeta, codigoValidacion);
    }

    @GET
    public Multi<Tarjeta> listarTarjetasDebitos(){
        return tarjetaService.listarTarjetasDebitos();
    }

    @GET
    @Path("/redis")
    public Multi<Redis> listAllRedis(){
        return tarjetaService.listAllRedis();
    }

}
