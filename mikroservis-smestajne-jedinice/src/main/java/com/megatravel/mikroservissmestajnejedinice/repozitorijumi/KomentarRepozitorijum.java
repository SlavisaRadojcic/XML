package com.megatravel.mikroservissmestajnejedinice.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroservissmestajnejedinice.model.Komentar;

@Repository
public interface KomentarRepozitorijum extends JpaRepository<Komentar, Long> { }
