package com.project.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.project.cm.modelo.Campo;
import com.project.cm.modelo.CampoEvento;
import com.project.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener{
	
	private Campo campo;
	private final Color BG_PADRAO = new Color(184,184,184);
	private final Color BG_MARCAR = new Color(8,179,247);
	private final Color BG_EXPLODIR = new Color(189,66,68);
	private final Color TEXTO_VERDE = new Color(0,100,0);
	
	public BotaoCampo(Campo campo) {
		
		this.campo = campo;
		
		campo.registrarObservador(this);
		addMouseListener(this);
		
		setOpaque(true);
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
	}

	@Override
	public void eventoOcorreu(Campo c, CampoEvento evento) {
		
		switch(evento) {
			
			case ABRIR -> aplicarAbrir();
			case MARCAR -> aplicarMarcar();
			case EXPLODIR -> aplicarExplodir();
			default -> aplicarPadrao();
			
		}
		
		SwingUtilities.invokeLater(() -> {
			
			repaint();
			validate();
			
		});
		
	}

	private void aplicarPadrao() {
		
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}

	private void aplicarExplodir() {
		
		setBackground(BG_EXPLODIR);
		setForeground(Color.WHITE);
		setText("X");
	}

	private void aplicarMarcar() {
		
		setBackground(BG_MARCAR);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void aplicarAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			
			setBackground(BG_EXPLODIR);
			return;
			
		}
		
		setBackground(BG_PADRAO);
		
		switch(campo.minasNaVizinhanca()) {
		
			case 1 -> setForeground(TEXTO_VERDE);
			case 2 -> setForeground(Color.BLUE);
			case 3 -> setForeground(Color.YELLOW);
			case 4 -> setForeground(Color.RED);
			case 5 -> setForeground(Color.RED);
			case 6 -> setForeground(Color.RED);
			default -> setForeground(Color.PINK);
		}
		
		String valor = !campo.vizinhacaSegura() ? campo.minasNaVizinhanca() + "" : "";
		
		setText(valor);
		
	}
	
	//EVENTOS DO MOUSE

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton() == 1)
			campo.abrir();
		else 
			campo.alterarMarcacao();
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

}
