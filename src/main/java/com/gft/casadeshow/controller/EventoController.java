package com.gft.casadeshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.casadeshow.model.Casa;
import com.gft.casadeshow.model.Evento;
import com.gft.casadeshow.repository.Casas;
import com.gft.casadeshow.repository.Eventos;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	@Autowired
	private Casas casa;
	
	@Autowired
	private Eventos eventos;
	
	@RequestMapping
	public ModelAndView evento() {
		List <Evento> todosEventos = eventos.findAll();
		ModelAndView mv = new ModelAndView("CadastroEvento");
		mv.addObject(new Evento());
		mv.addObject("eventos", todosEventos);
		List <Casa> todasCasas = casa.findAll();
		mv.addObject("casas", todasCasas);
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Evento evento) {
		eventos.save(evento);
		return "CadastroEvento";
	}
}
