package com.nttd.ms.tarjeta.repository;

import com.nttd.ms.tarjeta.entity.Tarjeta;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
//import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarjetaRepository implements ReactivePanacheMongoRepository<Tarjeta> {
}
