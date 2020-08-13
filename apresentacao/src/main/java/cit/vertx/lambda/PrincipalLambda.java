package cit.vertx.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import io.vertx.core.AbstractVerticle;

public class PrincipalLambda extends AbstractVerticle {

	// Como funciona lambda ???
	public static void main(String[] args) {

		List<String> listaPalavras = new ArrayList<String>();
		listaPalavras.add("aviao");
		listaPalavras.add("cao");
		listaPalavras.add("gato");
		listaPalavras.add("carro");

		List<String> listaPalavrasSort = new ArrayList<String>();
		listaPalavrasSort.add("abcdef");
		listaPalavrasSort.add("a");
		listaPalavrasSort.add("abc");
		listaPalavrasSort.add("abcde");
		listaPalavrasSort.add("ab");
		listaPalavrasSort.add("abcd");

		
		
		// Exemplo 2
		// Consumer<String> consumidor = new Consumer<String>() {};
		Consumer<String> consumidor = new Consumer<String>() {

			@Override
			public void accept(String s) {
				System.out.println("Exemplo 2 " + s);

			}
		};
		listaPalavras.forEach(consumidor);
		System.out.println(" ");

		// Exemplo 3
		// listaPalavras.forEach
		listaPalavras.forEach(new Consumer<String>() {

			@Override
			public void accept(String s) {
				System.out.println("Exemplo 3 " + s);
			}
		});
		System.out.println(" ");

		
		
		listaPalavras.forEach( /*Consumer -> accept -> (String s) */ s -> System.out.println());
		
		
		// Exemplo 4
		listaPalavras.forEach((String s) -> {
			System.out.println("Exemplo 4 " + s);
		});
		System.out.println(" ");

		// Exemplo 5
		listaPalavras.forEach(s -> System.out.println("Exemplo 5 " + s));
		System.out.println(" ");

		
		Comparator c = new Comparator<String>() {

			@Override
			public int compare(String A, String B) {

				if (A.length() > B.length())
					return 1;
			
				if (A.length() < B.length())
					return -1;

				if (A.length() == B.length())
					return 0;

				return 0;
			}
		};

		
		// Exemplo 6
		System.out.println("Lista de Palavras Sort -> Sem ordenação");
		System.out.println(listaPalavrasSort);
		System.out.println(" ");
		// listaPalavras.forEach(System.out::println);
	
		
		
		Function<String, Integer> funcao = new Function<String, Integer>() {

			@Override
			public Integer apply(String s) {
				return s.length();
			}
		};

		Comparator<String> comparador = Comparator.comparing(funcao);

		listaPalavrasSort.sort(comparador);

		listaPalavrasSort.sort(Comparator.comparing(s -> s.length()));
		
		
		
		
		listaPalavrasSort.sort(Comparator.comparing(new Function<String, Integer>() {

			@Override
			public Integer apply(String s) {
				return s.length();
			}
		}));
		
		listaPalavrasSort.sort(Comparator.comparing(s -> {
			// logica 
			System.out.println("");
			double x = Math.random();
			return s.length();
		}));
		
		
		
		
		
		listaPalavrasSort.sort(Comparator.comparing(String::length));
		
		
		System.out.println("Lista de Palavras Sort -> Menor para Maior");
		System.out.println(listaPalavrasSort);

		/*************************************************************
		 **************************** FIM ****************************
		 *************************************************************/

		// Exemplo 1
		// Consumer<String> consumer = new ImprimeUmaMensagem();
		ImprimeUmaMensagem consumer = new ImprimeUmaMensagem();
		System.out.println(" ");
		
		
		listaPalavras.forEach(consumer);
	}

	public static class ImprimeUmaMensagem implements Consumer<String> {

		@Override
		public void accept(String t) {
			System.out.println("Exemplo 1: " + t);
			
		}
	}

}
