package com.megatravel.mikroservissmestajnejedinice.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroservissmestajnejedinice.model.Usluga;

@Repository
public interface UslugaRepozitorijum extends JpaRepository<Usluga, Long> { }
