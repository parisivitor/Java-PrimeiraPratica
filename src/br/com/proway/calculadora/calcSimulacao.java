package br.com.proway.calculadora;

import java.util.Random;

/*
 * 
 *1 reais = gera 30views
 *
 * 1views tem 12% de clickar
 * 1clicks tem 6.67% de share
 * cada share gera +40views
 * 
 */

public class calcSimulacao {

	public static void main(String[] args) {
		int contClick = 0;
		int contShare = 0;
		int contViews = 0;

		AnuncioOriginal a1 = new AnuncioOriginal(5);
		ProbabilidadeDeGerar pg1 = new ProbabilidadeDeGerar();

		contClick = pg1.ViewGeraClick(a1.getViews());
		System.out.println("Views: " + a1.getViews());
		System.out.println("Obeteve " + contClick + " clicks");

		contShare = pg1.ClickGeraShare(contClick);
		contViews = contShare * 40;
		System.out.println("Obeteve " + contShare + " share e mais " + contViews + "views");

		int contTotalShare = 0;
		for (int i = 1; i <= contShare; i++) {
			System.out.println("--------------------");
			System.out.println(i + "o share");
			if (contShare >= 1) {
				int share = pg1.ShareDoShare();
				System.out.println("share do share gero + " + share + " share");
				if (share > 0) {
					share = pg1.ShareDoShare();
					System.out.println("share do share do Share gero + " + share + " share");
					if (share > 0) {
						share = pg1.ShareDoShare();
						System.out.println("share do share do Share do share + " + share + " share");
					}
				}
			}
			System.out.println();
			System.out.println();

		}

	}

}

class ProbabilidadeDeGerar {

	private static final int VIEWS_POR_REAL = 30; // 1 real = 30 views
	private static final int PROBABILIDADE_CLICK = 12 / 100 * 100; // 100vies = 12 sclick
	private static final int PROVAVILIDADE_SHARE = 3 / 20 * 100; // 20 clicks = 3 share
	private static final int SEQUENCIA_MAX_SHARE = 4;
	private static final int SHARE_GERA_VIEWS = 40;

	public Integer ViewGeraClick(int views) {
		Random gerador = new Random();
		int contClick = 0;
		for (int i = 0; i < views; i++) {
			if (gerador.nextInt(100) <= 12)
				contClick++;
		}
		return contClick;
	}

	public Integer ClickGeraShare(int click) {
		Random gerador = new Random();
		int contShare = 0;
		for (int i = 0; i < click; i++) {
			if (gerador.nextInt(100) <= 15)
				contShare++;
		}
		return contShare;
	}

	public Integer ShareDoShare() {
		int contClick = ViewGeraClick(SHARE_GERA_VIEWS);
		System.out.println();
		System.out.println("ContCLick  do Share do Share: " + contClick);
		System.out.println();
		int contShare = ClickGeraShare(contClick);
		return contShare;
	}

}

class AnuncioShare {
	private Integer views;
	private Integer share;
	private Integer clicks;

}

class AnuncioOriginal {
	private Double investimento;
	private Integer views;
	private Integer share;
	private Integer clicks;

	public AnuncioOriginal(double investimento) {
		this.investimento = investimento;
		Double views = investimento * 30; // 1 real = 30 views
		this.views = views.intValue();
	}

	public double getInvestimento() {
		return investimento;
	}

	public Integer getViews() {
		return views;
	}

	public Integer getShare() {
		return share;
	}

	public Integer getClicks() {
		return clicks;
	}

	@Override
	public String toString() {
		return "Investimento: " + this.getInvestimento() + ", Views Iniciais: " + this.getViews()
				+ ", Clicks Iniciais: " + this.getClicks() + ", Share Iniciais: " + this.getShare();
	}

}
