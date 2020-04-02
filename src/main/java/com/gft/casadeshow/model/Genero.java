package com.gft.casadeshow.model;

public enum Genero {
	ROCK("Rock"),
	FUNK("Funk"),
	AXÉ("Axé");
	
	private String genero;
	
	Genero(String genero){
		this.genero = genero;
	}
	
	public String getGenero() {
		return genero;
	}
}
