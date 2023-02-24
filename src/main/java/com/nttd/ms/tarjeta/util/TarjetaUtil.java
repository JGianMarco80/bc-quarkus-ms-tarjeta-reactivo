package com.nttd.ms.tarjeta.util;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarjetaUtil {

    public String generarCodigo(){
        int codigo = (int) (10000000 + Math.random() * 90000000);
        return String.valueOf(codigo);
    }

}
