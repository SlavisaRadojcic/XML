package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.Ocena;

@Repository
public interface OcenaRepozitorijum extends JpaRepository<Ocena, Long> {

}
