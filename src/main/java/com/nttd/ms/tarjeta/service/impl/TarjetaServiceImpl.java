package com.nttd.ms.tarjeta.service.impl;

import com.nttd.ms.tarjeta.client.CargoTCreditoClient;
import com.nttd.ms.tarjeta.client.PagoTCreditoClient;
import com.nttd.ms.tarjeta.client.model.Cargo;
import com.nttd.ms.tarjeta.client.model.PagoCredito;
import com.nttd.ms.tarjeta.dto.Movimiento;
import com.nttd.ms.tarjeta.dto.TCreditoMovimiento;
import com.nttd.ms.tarjeta.entity.Tarjeta;
import com.nttd.ms.tarjeta.repository.TarjetaRepository;
import com.nttd.ms.tarjeta.service.TarjetaService;
import com.nttd.ms.tarjeta.util.TarjetaUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TarjetaServiceImpl implements TarjetaService {

    @Inject
    TarjetaRepository repository;

    @Inject
    TarjetaUtil util;

    @RestClient
    CargoTCreditoClient cargoClient;

    @RestClient
    PagoTCreditoClient pagoClient;

    @Override
    public TCreditoMovimiento movimientoTCredito(String numeroTarjeta) {
        //Trater todas las tarjteas
        List<Tarjeta> tarjetas = repository.listAll();

        //Filtrar las tarjetas activas
        List<Tarjeta> tarjetasActivas = new ArrayList<>();

        //Crear un objeto de TCreditoMovimiento que sera la respuesta
        TCreditoMovimiento tObtenida = new TCreditoMovimiento();

        //Recorremos la lista tarjetas y filtamos por estado 1
        for (Tarjeta t: tarjetas) {
            if (t.getEstado().equals("1")) {
                tarjetasActivas.add(t);
            }
        }

        //Recorremos la lista tarjetasActivas y obtenemos el objeto de TCreditoMovimiento filtrado por el numeroTarjeta
        for (Tarjeta t: tarjetasActivas) {
            if (t.getNumeroTarjeta().equals(numeroTarjeta)) {
                if (t.getTipotarjeta().equals("2")) {
                    List<Cargo> cObtenidos = cargoClient.findByNumeroCuenta(t.getNumeroCuenta());
                    List<PagoCredito> pgObtenidos = pagoClient.findByNumeroCuenta(t.getNumeroCuenta(), "2");
                    tObtenida.setId(t.getId().toString());
                    tObtenida.setNumeroTarjeta(t.getNumeroTarjeta());
                    tObtenida.setLineaCredito(t.getLineaCredito());
                    tObtenida.setCreditoUtilizado(t.getCreditoUtilizado());
                    tObtenida.setLineaDisponible(t.getLineaCredito() - t.getCreditoUtilizado());
                    tObtenida.setFechaFacturacion(t.getFechaFacturacion());
                    if ( !(cObtenidos == null && cObtenidos.size() == 0) ) {
                        for (Cargo c: cObtenidos) {
                            Movimiento m = new Movimiento();
                            m.setDescripcion(c.getDescripcion());
                            m.setMonto(c.getMontoCargado() - (c.getMontoCargado() * 2));
                            m.setFecha(c.getFecha());
                            tObtenida.getMovimientos().add(m);
                        }
                    }
                    if ( !(pgObtenidos == null && pgObtenidos.size() == 0) ) {
                        for (PagoCredito pg: pgObtenidos) {
                            Movimiento m = new Movimiento();
                            m.setDescripcion(pg.getDescripcion());
                            m.setMonto(pg.getMonto());
                            m.setFecha(pg.getFecha());
                            tObtenida.getMovimientos().add(m);
                        }
                    }
                } else{
                    System.out.println("El numero proporcionado no pertenece a una tarjeta de credito");
                }
            }
        }

        return tObtenida;
    }

    @Override
    public String validarTarjeta(String numeroTarjeta, String codigoValidacion) {
        List<Tarjeta> tarjetas = this.listarTarjetasDebitos();
        String codigoAprobacion = "numero de tarjeta o código de valición incorrecto";

        if (tarjetas.size() != 0) {
            for (Tarjeta t: tarjetas) {
                if (t.getNumeroTarjeta().equals(numeroTarjeta) &&
                        t.getCodigoValidacion().equals(codigoValidacion)) {
                    codigoAprobacion = util.generarCodigo();
                }
            }
        }

        return codigoAprobacion;
    }

    private List<Tarjeta> listarTarjetasActivas(){
        List<Tarjeta> tarjetas = repository.listAll();
        List<Tarjeta> tarjetasActivas = new ArrayList<>();
        for (Tarjeta t: tarjetas) {
            if (t.getEstado().equals("1")) {
                tarjetasActivas.add(t);
            }
        }
        return tarjetasActivas;
    }

    private List<Tarjeta> listarTarjetasDebitos() {
        List<Tarjeta> tarjetas = this.listarTarjetasActivas();
        List<Tarjeta> tarjetasDebitos = new ArrayList<>();
        for (Tarjeta t: tarjetas) {
            if (t.getTipotarjeta().equals("1")) {
                tarjetasDebitos.add(t);
            }
        }
        return tarjetasDebitos;
    }
}
