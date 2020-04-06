package com.gft.casadeshow.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.casadeshow.model.Casa;
import com.gft.casadeshow.model.Evento;
import com.gft.casadeshow.model.Genero;
import com.gft.casadeshow.repository.Casas;
import com.gft.casadeshow.repository.Eventos;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	
	private static final String CADASTRO_VIEW = "CadastroEvento";
	
	@Autowired
	private Casas casa;
	
	@Autowired
	private Eventos eventos;
	
	@RequestMapping("/novo")
	public ModelAndView evento() {
		List <Evento> todosEventos = eventos.findAll();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Evento());
		mv.addObject("eventos", todosEventos);
		List <Casa> todasCasas = casa.findAll();
		mv.addObject("casas", todasCasas);
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Evento evento, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors()) {
			List <Casa> todasCasas = casa.findAll();
			mv.addObject("casas", todasCasas);
			return mv;
			
		}
		eventos.save(evento);
		ModelAndView mv2 = new ModelAndView("redirect:/eventos/novo");
		attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso!");
		return mv2;
	}
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo) {
		Evento evento = eventos.findById(codigo).get();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(evento);
		List <Casa> todasCasas = casa.findAll();
		mv.addObject("casas", todasCasas);
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
	@RequestMapping(path = "/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") Evento evento) {
        this.eventos.delete(evento);
        return "redirect:/eventos";
    }
}
