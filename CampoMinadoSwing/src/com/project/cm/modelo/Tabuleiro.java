package com.project.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador{
	
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();
	private final List<Consumer<ResultadoEvento>> obs = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarOsVizinhos();
		sortearMinas();
		
	}
	
	public void registrarObs(Consumer<ResultadoEvento> ob) {
		
		obs.add(ob);
		
	}
	
	private void notificarObservadores(boolean resultado) {
		
		obs.stream().forEach(o -> o.accept(new ResultadoEvento(resultado)));
		
	}
	
	public void abrir(int linha, int coluna) {
		
		campos.parallelStream()
			  .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			  .findFirst()
			  .ifPresent(c -> c.abrir());	
		
	}
	
	public void alterarMarcacao(int linha, int coluna) {
		
		campos.parallelStream()
			  .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			  .findFirst()
			  .ifPresent(c -> c.alterarMarcacao());		  
		
	}

	private void sortearMinas() {
		
		long minaArmadas = 0;
		
		Predicate<Campo> minado = c -> c.isMinado();
		
		do {
			int aleatorio = (int)(Math.random() * campos.size());
			
			campos.get(aleatorio).minar();
			minaArmadas = campos.stream().filter(minado).count();
			
		} while (minaArmadas < minas);
		
	}
	
	public boolean objetivoAlcancado() {
		
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}

 	private void associarOsVizinhos() {
		
		for(Campo c1: campos) {
			
			for(Campo c2: campos) {
				
				c1.adicionarVizinho(c2);
			}
		}
		
	}

	private void gerarCampos() {
		
		for(int l = 0; l < linhas; l++) {
			
			for(int c = 0; c < colunas; c++) {
				
				Campo campo = new Campo(l,c);
				
				campo.registrarObservador(this);
				campos.add(campo);
				
			}
		}
		
	}

	@Override
	public void eventoOcorreu(Campo c, CampoEvento evento) {
		
		if(evento == CampoEvento.EXPLODIR) {
			
			mostrarMinas();
			notificarObservadores(false);
			
		}else if(objetivoAlcancado()) {
			
			notificarObservadores(true);
			
		}
		
	}
	
	private void mostrarMinas() {
		
		campos.stream()
			  .filter(c -> c.isMinado())
			  .forEach(c -> c.setAberto(true));
		
	}

}
