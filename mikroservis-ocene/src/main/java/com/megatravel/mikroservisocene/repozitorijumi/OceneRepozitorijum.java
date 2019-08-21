package com.megatravel.mikroservisocene.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroservisocene.model.Ocena;

@Repository
public interface OceneRepozitorijum extends JpaRepository<Ocena, Long> {

}
