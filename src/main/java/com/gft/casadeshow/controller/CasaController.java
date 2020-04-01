package com.gft.casadeshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.casadeshow.model.Casa;
import com.gft.casadeshow.repository.Casas;


@Controller
@RequestMapping("/casas")
public class CasaController {
	
	private static final String CADASTRO_VIEW = "CadastroCasa";
	@Autowired
	private Casas casas;
	@RequestMapping("/nova")
	public ModelAndView novo() {
		List<Casa> ccasa = casas.findAll();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Casa());
		mv.addObject("casas", ccasa);
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Casa casa, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		casas.save(casa);
		attributes.addFlashAttribute("mensagem",  "Casa cadastrada com sucesso");
		return "redirect:/casas/nova";
	}
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo ) {
		Casa casa = casas.findById(codigo).get();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(casa);
		return mv;
	}
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Casa> todosCasas = casas.findAll();
		ModelAndView mv = new ModelAndView("PesquisaCasas");
		mv.addObject("casas", todosCasas);
		return mv;
	}
	
	@RequestMapping(path = "/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") Casa casa) {
        this.casas.delete(casa);
        return "redirect:/casas";
    }
}
