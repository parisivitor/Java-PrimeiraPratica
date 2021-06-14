package br.com.proway.cadastro;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Anuncio implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nomeAnuncio;
	private Cliente cliente;
	private Calendar dataInicio;
	private Calendar dataTermino;
	private Double investimentoPorDia;
	private Integer numeroDias;
	private double views;
	private double clicks;
	private double shares;
	

	public Anuncio(String nomeAnuncio, Cliente cliente, Calendar dataInicio, Calendar dataTermino, Double investimentoPorDia) {
		this.nomeAnuncio = nomeAnuncio;
		this.cliente = cliente;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.investimentoPorDia = investimentoPorDia;
		this.numeroDias = dataTermino.get(Calendar.DAY_OF_YEAR) -  dataInicio.get(Calendar.DAY_OF_YEAR) + 1 ;
		
		double visualizacao =investimentoPorDia*30 , clicks, shares;
		double totalViews=visualizacao;
		double totalClicks=0;
		double totalShare=0;
		for(int i=0; i<4;i++) {
			clicks = visualizacao * 0.12;
			totalClicks += clicks;
			shares = clicks*0.15;
			totalShare += shares;
			visualizacao = shares * 40;
			totalViews += visualizacao;
		}
		this.views = totalViews * this.numeroDias;
		this.clicks = totalClicks* this.numeroDias;
		this.shares = totalShare* this.numeroDias;
	}

	public String getNomeAnuncio() {
		return nomeAnuncio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public Double getInvestimentoPorDia() {
		return investimentoPorDia;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat format_ = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicioFormatada = format_.format(this.dataInicio.getTime());
		String dataFimFormatada = format_.format(this.dataTermino.getTime());
		String formatado = String.format("| Nome do anuncio: %20s | Nome do Clinete : %10s | Data de Inicio %s | Data de Termino: %s |Valor investido por dia: R$ %8.2f | Total de dias: %2d |"
								+ " Total maximo de views: %10.1f | Total maximo de clicks: %10.1f | Total maximo de shares: %5.1f |"
										,this.nomeAnuncio, this.cliente.getNome(), dataInicioFormatada,dataFimFormatada,this.investimentoPorDia,this.numeroDias,this.views,this.clicks,this.shares );
		return formatado;
	}

	@Override
	public boolean equals(Object obj) {
		Cliente cliente = (Cliente) obj;
		return this.cliente.equals(cliente.getNome());
	}
	
	
}
