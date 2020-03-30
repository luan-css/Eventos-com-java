package com.gft.casadeshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.casadeshow.model.Casa;
import com.gft.casadeshow.repository.Casas;


@Controller
@RequestMapping("/casas")
public class CasaController {
	@Autowired
	private Casas casas;
	@RequestMapping("/nova")
	public String novo() {
		return "CadastroCasa";
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Casa casa) {
		casas.save(casa);
		ModelAndView mv = new ModelAndView("CadastroCasa");
		mv.addObject("mensagem", "Casa cadastrada com sucesso");
		return mv;
	}
	@RequestMapping
	public String pesquisar() {
		return "PesquisaCasas";
	}
}
