# Apresentação: Lambda e VertX

![lambda](https://miro.medium.com/max/695/1*-jv7nGQR-mDhrAU-K_lOWg.png)    ![VertX](https://upload.wikimedia.org/wikipedia/commons/3/3d/Vertx_logo.png)



---


### Lambda
#### O que é Lambda ?
  - É uma função anônima, uma expressão que representa uma definição de método “em-linha”. Ela não tem valor ou tipo em si, mas pode ser convertida em um delegate compatível ou em uma expressão do tipo árvore. Por razões históricas existem dois tipos sintáticos das funções anônimas: expressões lambda e expressões anônimas de métodos. O operador “->” tem a mesma precedência como a atribuição (=) e associativo à direita.
    
#### Mas e na prática, como funciona ?
##### Exemplo 1
  - Quando temos um tipo de classe que implementa uma interface com apenas um ou dois métodos e se não vamos reaproveita-los em outras parte do nosso programa, é muito provavel que não utilizaremos o recurso a seguir.

```java
// Uma classe que implementa uma interface
	public static class ImprimeUmaMensagem implements Consumer<String> {
		
		@Override
		public void accept(String s) {
			System.out.println("Exemplo 1 " + s);
		}
		
	}
  
//Um trecho de código usando o método da interface na classe

		List<String> listaPalavras = new ArrayList<String>();
		listaPalavras.add("aviao");
		listaPalavras.add("cao");
		listaPalavras.add("gato");
		listaPalavras.add("carro");

		//Consumer<String> consumer = new ImprimeUmaMensagem();
    ImprimeUmaMensagem consumer = new ImprimeUmaMensagem();
    
		System.out.println(" ");
		listaPalavras.forEach(consumer);
    
```
##### Exemplo 2
  - Então em vez de instanciar a classe ImprimeUmaMensagem ( ``` ImprimeUmaMensagem mensagem = new ImprimeUmaMensagem() ``` ), instanciamos diretamente a interface. Mas podemos dar um ```new``` em uma interface ? Teoricamente não podemos pois irá faltar informaçes. Você só pode dar new se caso houver a implementaço de um ou dois métodos no máximo. Com isso, já podemos eliminar a classe ImprimeUmaMensagem. Veja o exemplo a seguir.
  
 ```java
		
		// Consumer<String> consumidor = new Consumer<String>() {};
		Consumer<String> consumidor = new Consumer<String>() {

			@Override
			public void accept(String s) {
				System.out.println("Exemplo 2 " + s);

			}
		};
		
		listaPalavras.forEach(consumidor); 
 ```
##### Exemplo 3 
  - Para deixar mais simples o código, chamamos então diretamente no parametro o Consumer. Mas por que fazer isso tudo ? Como não vamos mais reutiliza-lo e a função dele é para algo bem simples então podemos fazer conforme o exemplo.
  
```java
    
    // listaPalavras.forEach
		listaPalavras.forEach(new Consumer<String>() {

			@Override
			public void accept(String s) {
				System.out.println("Exemplo 3 " + s);
			}
		});

```


##### Exemplo 4 e 5
  - Tendo essas dificuldade e verbosidade da sintaxe das classes anônimas em vista, o Java 8 traz uma nova forma de implementar essas interfaces ainda mais sucinta. É a sintaxe do lambda. Em vez de escrever a classe anônima, deixamos de escrever alguns itens que podem ser inferidos.

  - Como essa interface só tem um método, não precisamos escrever o nome do método. Também não daremos new. Apenas declararemos os argumentos e o bloco a ser executado, separados por ->:
```java
palavras.forEach((String s) -> {
    System.out.println(s);
});
```
  - É uma forma bem mais sucinta de escrever! Essa sintaxe funciona para qualquer interface que tenha apenas um método abstrato, e é por esse motivo que nem precisamos falar que estamos implementando o método accept, já que não há outra possibilidade. Podemos ir além e remover a declaração do tipo do parâmtro, que o compilador também infere:
```java
palavras.forEach((s) -> {
    System.out.println(s);
});
```
  - Quando há apenas um parâmetro, nem mesmo os parenteses são necessários:
```java
palavras.forEach(s -> {
    System.out.println(s);
});
```

 - Dá pra melhorar? Sim. podemos remover as chaves de declaração do bloco, assim como o ponto e vírgula, pois só existe uma única instrução:
```java
palavras.forEach(s -> System.out.println(s));
```
 - Pronto. Em vez de usarmos classes anônimas, utilizamos o lambda para escrever códigos simples e sucintos nesses casos. Uma interface que possui apenas um método abstrato é agora conhecida como interface funcional e pode ser utilizada dessa forma.

##### Exemplo 6

Há vários métodos auxiliares no Java 8. Até em interfaces como o Comparator. E você pode ter um método default que é estático. Esse é o caso do Comparator.comparing, que é uma fábrica, uma factory, de Comparator. Passamos o lambda para dizer qual será o critério de comparação desse Comparator, repare:

```java
palavras.sort(Comparator.comparing(s -> s.length()));
```

Veja a expressividade da linha, está escrito algo como "palavras ordene comparando s.length". Podemos quebrar em duas linhas para ver o que esse novo método faz exatamente:

```java
Comparator<String> comparador = Comparator.comparing(s -> s.length());
palavras.sort(comparador);
```

Dizemos que Comparator.comparing recebe um lambda, mas essa é uma expressão do dia a dia. Na verdade, ela recebe uma instância de uma interface funcional. No caso é a interface Function que tem apenas um método, o apply. Para utilizarmos o Comparator.comparing, nem precisamos ficar decorando os tipos e assinatura do método dessas interfaces funcionais. 

```java
palavras.sort(Comparator.comparing(String::length));
```

---

### VertX

 #### O que é VertX
  *Desenvolvimento de aplicações assíncronas,orientadas a eventos e poliglotas*
  
  ● Framework para a JVM
  
  ● Desenvolvimento de aplicações reativas
  
  ● Orientado a Eventos
  
  ● Assíncrono
  
  ● Não blocante
  
  ● Simples, porém poderoso

  ● Poliglota
  
  
 - Desenvolvimento em JAVA

```java
// E aqui começa tudo ---> "extends AbstractVerticle"
public class PrincipalVerticle extends AbstractVerticle {

	// Fazemos o deploy para iniciar o Verticle
	public static void main(String[] args) {
		System.out.println("Començando ...\n");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new PrincipalVerticle());
	}
	
	
	// Metodo START que é chamado quando o verticle é "deeployado"
	@Override
	public void start() throws Exception {
		System.out.println("Método start() invocado ...\n");
		vertx.createHttpServer().requestHandler(req -> {
			req.response().putHeader("content-type", "application/json").end("Fala rapaziada !");
		}).listen(8080);

		System.out.println("Hey Ho Lets Go !");
		
	}

}

```

Veja a apresentação em: [VertX - Desenvolvimento de aplicações assíncronas](https://pt.slideshare.net/rpeleias/desenvolvimento-de-aplicaes-assncronas-orientadas-a-eventos-e-poliglotas-com-vertx)







Fontes:

  [O que é lambda ?](https://www.teclogica.com.br/java-8-o-que-e-lambda/)
