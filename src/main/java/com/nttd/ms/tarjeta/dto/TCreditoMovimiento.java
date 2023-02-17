package com.nttd.ms.tarjeta.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TCreditoMovimiento {

    private String id;

    private String numeroTarjeta;

    private Double lineaCredito;

    private Double lineaDisponible;

    private Double creditoUtilizado;

    private String fechaFacturacion;

    //Movimientos
    private List<Movimiento> movimientos = new ArrayList<>();

}
