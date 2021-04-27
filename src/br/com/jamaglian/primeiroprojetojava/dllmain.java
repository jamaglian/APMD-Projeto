package br.com.jamaglian.primeiroprojetojava;

import br.com.jamaglian.primeiroprojetojava.janelas.JanelaPrincipal;
import br.com.jamaglian.primeiroprojetojava.sqliteManager.dbBase;

public class dllmain {
	
	public static void main(String[] args) {
		new dbBase();
		new JanelaPrincipal();
	}
	
}
