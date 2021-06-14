package br.com.proway.cadastro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Execucao {
	public static void main(String[] args) throws Exception {

		Set<Anuncio> anuncio = new HashSet<>();
		anuncio = leitura();

//		anuncio.forEach(a -> System.out.println(a));

		menu(anuncio);
		grava(anuncio);

	}

	private static void menu(Set<Anuncio> anuncio) {
		int op = 1;
		while (op != 9) {
			System.out.println("+------------------------------------------------------+");
			System.out.println("|------------------Cadastro de Anuncios----------------|");
			System.out.println("|------------------------------------------------------|");
			System.out.println("|------------ 1 -> Cadastrar novo anuncio -------------|");
			System.out.println("|------------ 2 -> Buscar anuncio ---------------------|");
			System.out.println("|------------ 9 -> Salvar e Sair ----------------------|");
			System.out.println("|------------------------------------------------------|");
			System.out.println("+------------------Digite 1, 2 ou 9! ------------------+");
			Scanner s = new Scanner(System.in);
			op = s.nextInt();
			if (op == 1) {
				anuncio.add(cadastro());
//				anuncio.forEach(a -> System.out.println(a));
			}
				

			if (op == 2)
				busca(anuncio);
		}

	}

	private static Anuncio cadastro() {
		String nomeAnuncio;
		Cliente cliente;
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataTermino = Calendar.getInstance();
		Double investimentoPorDia;
		Scanner s = new Scanner(System.in);
		Scanner d = new Scanner(System.in);
		Scanner m = new Scanner(System.in);
		Scanner a = new Scanner(System.in);
		int dia,mes,ano;

		System.out.println("+------------------------------------------------------+");
		System.out.println("|------------------Cadastro de Anuncios----------------|");
		System.out.println("|------------------------------------------------------|");
		System.out.println("|Digite o nome do anuncio: ");
		s = new Scanner(System.in);
		nomeAnuncio = s.next();

		System.out.println("|Digite o nome do cliente: ");
		s = new Scanner(System.in);
		cliente = new Cliente(s.next());

		System.out.println("|Digite a data de inicio:");
		System.out.println("|Dia: ");
		d = new Scanner(System.in);
		dia=d.nextInt();
		System.out.println("|Mes: ");
		m = new Scanner(System.in);
		mes=m.nextInt();
		System.out.println("|Ano: ");
		a = new Scanner(System.in);
		ano=a.nextInt();
		dataInicio.set(ano, mes - 1, dia);

		System.out.println("|Digite a data de termino: ");
		System.out.println("|Dia: ");
		d = new Scanner(System.in);
		dia=d.nextInt();
		System.out.println("|Mes: ");
		m = new Scanner(System.in);
		mes=m.nextInt();
		System.out.println("|Ano: ");
		a = new Scanner(System.in);
		ano=a.nextInt();
		dataTermino.set(ano, mes - 1, dia);

		System.out.println("|Digite o valor do investimento diario: ");
		s = new Scanner(System.in);
		investimentoPorDia = s.nextDouble();

		Anuncio anuncio = new Anuncio(nomeAnuncio, cliente, dataInicio, dataTermino, investimentoPorDia);
		return anuncio;
	}

	private static Set<Anuncio> leitura() throws Exception {
		Scanner scanner = new Scanner(new File("dados.csv"), "UTF-8");
		Set<Anuncio> anuncio = new HashSet<>();

		while (scanner.hasNext()) {
			String nomeAnuncio;
			String cliente;
			Double valorInvest;

			String datainicio;
			Calendar dataInicio = Calendar.getInstance();

			String datafim;
			Calendar dataFim = Calendar.getInstance();

			Scanner SdataIni;
			Scanner SdataFim;

			int diaIni;
			int mesIni;
			int anoIni;
			int diaFim;
			int mesFim;
			int anoFim;

			String linha = scanner.nextLine();
			Scanner linhaScanner = new Scanner(linha);
			linhaScanner.useLocale(Locale.US);
			linhaScanner.useDelimiter(";");

			nomeAnuncio = linhaScanner.next();
			cliente = linhaScanner.next();
			datainicio = linhaScanner.next();
			datafim = linhaScanner.next();
			valorInvest = linhaScanner.nextDouble();

			SdataIni = new Scanner(datainicio);
			SdataIni.useDelimiter("/");
			while (SdataIni.hasNext()) {
				diaIni = SdataIni.nextInt();
				mesIni = SdataIni.nextInt();
				anoIni = SdataIni.nextInt();
//				System.out.println(diaIni +"/"+mesIni+"/"+anoIni);
				dataInicio.set(anoIni, mesIni - 1, diaIni);
			}

			SdataIni.close();

			SdataFim = new Scanner(datafim);
			SdataFim.useDelimiter("/");
			while (SdataFim.hasNext()) {
				diaFim = SdataFim.nextInt();
				mesFim = SdataFim.nextInt();
				anoFim = SdataFim.nextInt();
//				System.out.println(diaFim +"/"+mesFim+"/"+anoFim);
				dataFim.set(anoFim, mesFim - 1, diaFim);
			}
			SdataFim.close();

//			System.out.format("%s ; %s ; %s ; %s ; %f", nomeAnuncio, cliente, dataini, datafim, valorInvest);
//			System.out.println();

			Cliente c1 = new Cliente(cliente);
			Anuncio a1 = new Anuncio(nomeAnuncio, c1, dataInicio, dataFim, valorInvest);
			anuncio.add(a1);

			linhaScanner.close();

		}
		scanner.close();
		return anuncio;
	}

	private static void busca(Set<Anuncio> anuncio) {
		System.out.println("+------------------------------------------------------+");
		System.out.println("|------------------Busca de Anuncios-------------------|");
		System.out.println("|------------------------------------------------------|");
		System.out.println("| Digite o nome do clinte: ");
		Scanner s = new Scanner(System.in);
		Cliente cliente = new Cliente(s.next());
//		int flag = 0;
		anuncio.forEach(a -> {
			if (a.getCliente().equals(cliente)) {
				System.out.println(a);
			}
		});

	}

	private static void grava(Set<Anuncio> anuncio) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dados.csv")));
		SimpleDateFormat format_ = new SimpleDateFormat("dd/MM/yyyy");
		anuncio.forEach(a -> {
			try {
				String dataInicioFormatada = format_.format(a.getDataInicio().getTime());
				String dataFimFormatada = format_.format(a.getDataTermino().getTime());
				bw.write(a.getNomeAnuncio() + ";" 
						+ a.getCliente().getNome() + ";" 
						+ dataInicioFormatada + ";"
						+ dataFimFormatada + ";" 
						+ a.getInvestimentoPorDia());
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		bw.close();
	}

}
