package br.com.jamaglian.primeiroprojetojava.modelos;

public class Filme {
	
	private Integer id;
	
	private String imagem;
	
	private String titulo;
	
	private String sinopse;
	
	private String genero;
	
	private String plataforma;
	
	private Boolean assistido;
	
	private Double avaliacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Boolean getAssistido() {
		return assistido;
	}

	public void setAssistido(Boolean assistido) {
		this.assistido = assistido;
	}
	
	public Double getAvaliacao() {
		return avaliacao;
	}
	
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public String toString() {
		return "Filme [id=" + id + ", titulo=" + titulo + ", sinopse=" + sinopse + ", genero=" + genero
				+ ", plataforma=" + plataforma + ", assistido=" + assistido + ", avaliacao=" + avaliacao + "]";
	}
	
}