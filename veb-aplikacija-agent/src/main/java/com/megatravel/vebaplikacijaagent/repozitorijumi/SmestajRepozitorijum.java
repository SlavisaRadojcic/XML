package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.Smestaj;

@Repository
public interface SmestajRepozitorijum extends JpaRepository<Smestaj, Long> {

}
