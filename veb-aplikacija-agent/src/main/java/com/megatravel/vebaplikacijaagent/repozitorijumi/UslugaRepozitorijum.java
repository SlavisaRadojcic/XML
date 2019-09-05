package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.Usluga;

@Repository
public interface UslugaRepozitorijum extends JpaRepository<Usluga, Long>{

}
