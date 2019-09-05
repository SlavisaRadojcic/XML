package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.Adresa;

@Repository
public interface AdreseRepozitorijum extends JpaRepository<Adresa, Long> {

}
