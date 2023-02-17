package com.nttd.ms.tarjeta.client.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
public class Cargo {

    private ObjectId id;

    private String numeroCuenta;

    private Integer cuotasAPagar;

    private Integer cuotasPagadas;

    private Double montoCargado;

    private Double montoAPagar;

    private LocalDate fecha;

    private String descripcion;

}
