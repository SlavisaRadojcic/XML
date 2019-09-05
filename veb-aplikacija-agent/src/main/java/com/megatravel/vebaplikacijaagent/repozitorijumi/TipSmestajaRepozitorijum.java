package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.TipSmestaja;

@Repository
public interface TipSmestajaRepozitorijum extends JpaRepository<TipSmestaja, Long>{

}
