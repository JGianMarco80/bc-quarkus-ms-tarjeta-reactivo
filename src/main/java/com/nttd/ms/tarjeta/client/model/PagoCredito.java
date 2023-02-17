package com.nttd.ms.tarjeta.client.model;

import lombok.Data;
import org.bson.types.ObjectId;
import java.time.LocalDate;

@Data
public class PagoCredito {

    private ObjectId id;

    private String tipoPago;

    private String numeroCuenta;

    private Integer cantidadCuotas;

    private Double monto;

    private LocalDate fecha;

    private String descripcion;

}
