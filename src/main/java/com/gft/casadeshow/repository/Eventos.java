package com.gft.casadeshow.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.casadeshow.model.Evento;

public interface Eventos extends JpaRepository<Evento, Long>{
	
}
