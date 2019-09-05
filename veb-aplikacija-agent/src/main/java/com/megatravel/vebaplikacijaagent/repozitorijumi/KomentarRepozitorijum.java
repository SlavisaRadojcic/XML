package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.Komentar;

@Repository
public interface KomentarRepozitorijum extends JpaRepository<Komentar, Long>{

}
