package br.com.proway.calculadora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author vitim
 * para executar e testa o resultado obtido, basta modificar o paramentro que é passado no construtor do anuncio que esta na linha 19
 * ex: Anuncio a1 = new Anuncio(12); 12 equivale a doze reais de investimento inicial 
 * 
 * para a resolucao do exercicio, foi considera apenas valores inteiros, portanto 6.66 clicks não gera 1 share, pois nao existe metade de um share, clicks ou views
 */ 

public class calculadora {

	public static void main(String[] args) {
		int totalViews=0;
		
		Anuncio a1 = new Anuncio(12);		// instancia o objeto
		System.out.println(a1);				// printa oq foi construido
		
		a1.QuantidadeDeShare(a1.getShare()); //faz as contas em cima do que foi construido
		
//		a cada 4 compartilhamento em sequencia em cima do investimento inicial, gera uma cadeia de compartilhamento sendo assim
//		so existindo no maximo blocos de 4 share para acumulo de views e gerar outra cadeia de views, clicks e compartilhamento.
		
//		somatoria de views em cima do resultado de views obtidos no bloco maximo que tiveram (bloco maximo contem 4) multiplicado pela quantidade de blocos maximos 
		totalViews += (a1.getBlocoShareCheio().getViews() * a1.getBlocoShareCheio().getQtde()  ); 
		
//		somatoria de views em cima da quantaidade do ultimo bloco de share que existiu(se existiu) (bloco resto contem entre 0 a 3 shares)
		totalViews += (a1.getBlocoShareResto().getViews() );
		
//		somatoria dos resultados dos views dos blocos + os views iniciais
		totalViews += a1.getViews();
		System.out.println(totalViews);

	}
}

class Anuncio {
	private Double investimento;
	private Double views;
	private Double clicks;
	private Double share;
	private Double totalViews;
	
	private CadeiaShare blocoShareCheio = new CadeiaShare();
	private CadeiaShare blocoShareResto = new CadeiaShare();

	private static final int VIEWS_POR_REAL = 30; // 1 real = 30 views
	private static final int VIEWS_POR_SHARE = 40; // cada share gera 40 views
	private static final double CLICKS_POR_VIEW = (double) 12 / 100; // se 100views = 12clicks, 1views = probabilidade de  0.12 clicks
	private static final double CLICKS_POR_SHARE = 3/20.0; // se 20clicks = 3share, 1 clicks = probabilidade de 0.15 share 
	private static final int SHARE_EM_SEQUENCIA = 4;

	public Anuncio(double investimento) {
		this.investimento = investimento;
		this.views = investimento * VIEWS_POR_REAL;
		this.clicks = this.views.intValue() * CLICKS_POR_VIEW;
//		this.clicks = this.views * CLICKS_POR_VIEW;
		this.share = clicks.intValue() * CLICKS_POR_SHARE;
//		this.share = clicks * CLICKS_POR_SHARE;

	}

	public double getInvestimento() {
		return investimento;
	}

	public int getViews() {
		return views.intValue();
	}

	public int getClicks() {
		return clicks.intValue();
	}

	public int getShare() {
		return share.intValue();
	}
	
	public Double getTotalViews() {
		return totalViews;
	}

	public void setTotalViews(Double totalViews) {
		this.totalViews = totalViews;
	}

	public CadeiaShare getBlocoShareCheio() {
		return blocoShareCheio;
	}

	public CadeiaShare getBlocoShareResto() {
		return blocoShareResto;
	}

	@Override
	public String toString() {
		return "Investimento: " + this.getInvestimento() + ", Views Iniciais: " + this.getViews()
				+ ", Clicks Iniciais: " + this.getClicks() + ", Share Iniciais: " + this.getShare();
	}

	public void QuantidadeDeShare(int qtdShare) {
		double shareCheio = qtdShare / SHARE_EM_SEQUENCIA;
		double shareResto = qtdShare % SHARE_EM_SEQUENCIA;
		System.out.println(shareCheio);
		System.out.println(shareResto);

		if (shareCheio >= 1) {
			this.blocoShareCheio = calculaCadeiaShare(SHARE_EM_SEQUENCIA,shareCheio);
		}

		if (shareResto > 0 && shareResto < 4) {
			this.blocoShareResto = calculaCadeiaShare(shareResto,shareResto);
		}
		
		//System.out.println("Views blocoCheio: " + this.getBlocoShareCheio().getViews());

	}

	public CadeiaShare calculaCadeiaShare(double qtdeSequencia, double qtdBloco) {
		// 4 share em sequencia -> 160v e 19.2c -> 2share -> 80v e 9.6c -> 1share -> 40v
		// e 4.8c -> 0share

		Double viewsTotalSequencia = 0.0;
		Double shareTotalSequencia = 0.0;
		Double clickTotalSequencia = 0.0;

		if (qtdeSequencia == SHARE_EM_SEQUENCIA) {
			for (double i = qtdeSequencia; i >= 1; i = i / 2.0) {
				viewsTotalSequencia += i * VIEWS_POR_SHARE;
				shareTotalSequencia += i;
				Double clicks = i * VIEWS_POR_SHARE * CLICKS_POR_VIEW;
				clickTotalSequencia += clicks.intValue();
//				clickTotalSequencia += clicks;
			}
		}

		else {
			for (double i = qtdeSequencia; i >= 1; i--) {
				viewsTotalSequencia += i * VIEWS_POR_SHARE;
				shareTotalSequencia += i;
				Double clicks = i * VIEWS_POR_SHARE * CLICKS_POR_VIEW;
				clickTotalSequencia += clicks.intValue();
//				clickTotalSequencia += clicks;
			}

		}

		CadeiaShare ss1 = new CadeiaShare(viewsTotalSequencia, shareTotalSequencia, clickTotalSequencia, qtdBloco);
		return ss1;
	}



}

class CadeiaShare {
	Double views = 0.0;
	Double share = 0.0;
	Double clicks = 0.0;
	Double qtde = 0.0;

	public CadeiaShare() {
		super();
	}

	public CadeiaShare(Double views, Double share, Double clicks, Double qtde) {
		this.views = views;
		this.share = share;
		this.clicks = clicks;
		this.qtde = qtde;
	}

	public Double getViews() {
		return views;
	}

	public Double getShare() {
		return share;
	}

	public Double getClicks() {
		return clicks;
	}

	public Double getQtde() {
		return qtde;
	}
	

}