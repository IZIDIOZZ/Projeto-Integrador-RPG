import java.util.Scanner;

public class ProjetoRPG {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		System.out.println("- MENU PRINCIPAL -");
		System.out.println("===================");
		System.out.println("sonho_de_dev.java");
		System.out.println("=======OPÇÕES=======");
		System.out.println("1: NOVO JOGO");
		System.out.println("2: REGRAS");
		System.out.println("3: CRÉDITOS");
		System.out.println("4: SAIR");
		System.out.println("===================");
		System.out.println("Digite o número da opção: ");
		int opcao;
		opcao = ler.nextInt();
		
		switch (opcao){
			case 1:
				System.out.println("Opção NOVO JOGO selecionada!");
				System.out.println("Tenha um bom jogo :)");
				break;
				
			case 2:
				System.out.println("Opção REGRAS selecionada!");
				System.out.println("=======REGRAS=======");
				System.out.println("1 - Você deverá acertar a resposta das perguntas para causar dano no inimigo.");
				System.out.println("2 - Caso você erre a resposta, sofrerá dano.");
				System.out.println("3 - Você deverá fazer a barra de vida do inimigo chegar a zero para ganhar a batalha.");
				System.out.println("4 - Se sua barra de vida chegar a zero primeiro, você perderá a batalha.");
				System.out.println("5 - Faça boas escolhas, pois elas determinam seu progresso no jogo.");
				System.out.println("6 - Divirta-se! :)");
				break;
				
			case 3:
				System.out.println("Opção CRÉDITOS selecionada!");
				System.out.println("=======CRÉDITOS=======");
				System.out.println("Este jogo foi desenvolvido pelo grupo 4 do Projeto Integrador");
				System.out.println("=====INTEGRANTES=====");
				System.out.println("Benjamin Nicolini");
				System.out.println("Daniel Izidio");
				System.out.println("Danilo Lima");
				System.out.println("Davi Yuri");
				System.out.println("Gustavo Souza");
				System.out.println("Gustavo Vieira");
				System.out.println("=====================");
				break;
			
			case 4:
				System.out.println("Opção SAIR selecionada!");
				System.out.println("O jogo será encerrado.");
				System.exit(0);
				break;
		}
		ler.close();
	}
}
