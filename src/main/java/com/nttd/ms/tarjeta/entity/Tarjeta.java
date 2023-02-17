package com.nttd.ms.tarjeta.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@MongoEntity(collection = "tarjeta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta {

    private ObjectId id;

    //1: tarjeta de debito
    //2: tarjeta de credito
    private String tipotarjeta;

    private String numeroTarjeta;

    private String pin;

    private String codigoValidacion;

    private LocalDate fechaVencimiento;

    private String estado = "1";


    /* PARA TARJETA DE CRÉDITO */
    private LocalDate fechaCorte;

    private String fechaFacturacion;

    private Double lineaCredito;

    private Double lineaDisponible;

    private Double creditoUtilizado;

    private String numeroCuenta;
}
