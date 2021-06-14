package br.com.proway.cadastro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TesteData {

	public static void main(String[] args) {
		int dia = 15;
		int mes = 5;
		int ano = 2021;
		
		Calendar dataInicio =  Calendar.getInstance();
		dataInicio.set(2021,5-1,16);
		System.out.println(dataInicio.getTime());
		
		SimpleDateFormat format_ = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = format_.format(dataInicio.getTime());
		System.out.println(dataFormatada);
		
//		System.out.println(dataInicio.get(Calendar.DAY_OF_WEEK));
		

		Calendar dataFim = Calendar.getInstance();
		dataFim.set(2021,5-1,20);
		System.out.println(dataFim.getTime());
//		System.out.println(dataFim.get(Calendar.DAY_OF_WEEK));
		
		int dias = dataFim.get(Calendar.DAY_OF_YEAR) -  dataInicio.get(Calendar.DAY_OF_YEAR);
		System.out.println(dias);
			
		Calendar cal = Calendar.getInstance();
		cal.set(ano,mes-1,dia);
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
	}

}
