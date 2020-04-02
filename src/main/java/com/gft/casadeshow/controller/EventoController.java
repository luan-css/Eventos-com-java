package com.gft.casadeshow.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.casadeshow.model.Casa;
import com.gft.casadeshow.model.Evento;
import com.gft.casadeshow.model.Genero;
import com.gft.casadeshow.repository.Casas;
import com.gft.casadeshow.repository.Eventos;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	@Autowired
	private Casas casa;
	
	@Autowired
	private Eventos eventos;
	
	@RequestMapping("/novo")
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
	public ModelAndView salvar(@Validated Evento evento, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroEvento");
		if(errors.hasErrors()) {
			return mv;
		}
		eventos.save(evento);
		mv.addObject("mensagem", "Evento salvo com sucesso!");
		return mv;
	}
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Evento> todosEventos = eventos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaEvento");
		mv.addObject("eventos", todosEventos);
		return mv;
	}
	@ModelAttribute("todosGenero")
	public List<Genero> todosGenero(){
		return Arrays.asList(Genero.values());
	}
}
