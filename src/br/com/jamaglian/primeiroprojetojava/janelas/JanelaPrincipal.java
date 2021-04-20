package br.com.jamaglian.primeiroprojetojava.janelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import br.com.jamaglian.primeiroprojetojava.res.BarraDeNavegacao;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int LARGURA = 800;
	public static final int ALTURA = 450;
	
	public JanelaPrincipal() {
		super("Projeto Semestral - João Antonio - RM:82287");
		init();
		config();
	}
	
	private void init() {

		List<String> tabs = new ArrayList<>();
		tabs.add("Cadastro");
		tabs.add("Lista");
		
		List<JComponent> components = new ArrayList<>();
		components.add(new JanelaCadastro());
		components.add(new JanelaLista());
		BarraDeNavegacao nav = new BarraDeNavegacao(tabs, components);
	    nav.addChangeListener(change -> {
	      nav.setComponentAt(1, new JanelaLista());
	     });
	     add(nav);
	}
	
	private void config() {
		setSize(LARGURA, ALTURA);
		setLocationRelativeTo(null);
		setVisible(true);
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
