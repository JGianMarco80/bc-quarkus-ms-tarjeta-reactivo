package com.nttd.ms.tarjeta.service;

import com.nttd.ms.tarjeta.dto.TCreditoMovimiento;

public interface TarjetaService {

    TCreditoMovimiento movimientoTCredito(String numeroTarjeta);

    String validarTarjeta(String numeroTarjeta, String codigoValidacion);

}
