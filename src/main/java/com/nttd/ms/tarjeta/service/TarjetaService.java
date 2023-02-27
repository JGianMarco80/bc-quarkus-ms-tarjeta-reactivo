package com.nttd.ms.tarjeta.service;

//Proyecto2
//import com.nttd.ms.tarjeta.dto.TCreditoMovimiento;

import com.nttd.ms.tarjeta.dto.Redis;
import com.nttd.ms.tarjeta.entity.Tarjeta;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface TarjetaService {

    //Proyecto2
    //TCreditoMovimiento movimientoTCredito(String numeroTarjeta);

    //Proyecto3
    //String validarTarjeta(String numeroTarjeta, String codigoValidacion);
    Uni<String> validarTarjeta(String numeroTarjeta, String codigoValidacion);

    Multi<Tarjeta> listarTarjetasDebitos();

    Multi<Redis> listAllRedis();

}
