package com.megatravel.mikroservissmestajnejedinice.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroservissmestajnejedinice.model.TipSmestaja;

@Repository
public interface TipSmestajaRepozitorijum extends JpaRepository<TipSmestaja, Long> { }
