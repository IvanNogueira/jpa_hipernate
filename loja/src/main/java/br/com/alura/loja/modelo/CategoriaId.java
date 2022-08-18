package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

@Embeddable
public class CategoriaId {
	
	@EmbeddedId
	private CategoriaId id;
	
	private String tipo;
	private String nome;

	public CategoriaId() {
		// TODO Auto-generated constructor stub
	}

	public CategoriaId(String tipo, String nome) {
		super();
		this.tipo = tipo;
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
