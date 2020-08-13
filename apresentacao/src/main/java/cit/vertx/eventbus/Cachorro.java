package cit.vertx.eventbus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Cachorro {

	@JsonProperty("cor")
	public String cor;

	@JsonProperty("raca")
	public String raca;

	@JsonProperty("porte")
	public String porte;

	@JsonProperty("nome")
	public String nome;

	public Cachorro() {

	}

	public Cachorro(String cor, String raca, String porte, String nome) {
		this.cor = cor;
		this.raca = raca;
		this.porte = porte;
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "[cor: " + this.cor + ", " + "raca: " + this.raca + ", " + "porte: " + this.porte + ", " + "nome: "
				+ this.nome + "]";
	}

}
