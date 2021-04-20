package br.com.jamaglian.primeiroprojetojava;

public enum defines {
    DB_NAME("save.db"),
    TB_FILMES("filmes"),
    	TB_FILMES_ID("id"),
    	TB_FILMES_TITULO("titulo"),
    	TB_FILMES_SINOPSE("sinopse"),
    	TB_FILMES_GENERO("genero"),
    	TB_FILMES_PLATAFORMA("plataforma"),
    	TB_FILMES_ASSISTIU("assistiu"),
    	TB_FILMES_AVALIACAO("avaliacao"),
    	TB_FILMES_IMAGEM("imagem");

    private Object valor;

    defines(Object valor) {
        this.valor = valor;
    }

    public Object getValor() {
        return valor;
    }

}
