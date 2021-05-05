import java.io.IOException;
import java.util.*;
import Enumerations.DificuldadeEnum;
import Enumerations.OpcaoMenuEnum;
import Models.*;

public class ProjetoRPG {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		OpcaoMenuEnum itemSelecionado = OpcaoMenuEnum.SEM_ESCOLHA;	
		try {
			while(itemSelecionado == OpcaoMenuEnum.SEM_ESCOLHA || itemSelecionado != OpcaoMenuEnum.SEM_ESCOLHA  ) {
				ConfiguracaoJogo.LimparTela();
				System.out.println("- MENU PRINCIPAL -");
				System.out.println("===================");
				System.out.println("sonho_de_dev.java");
				System.out.println("=======OPÇÕES=======");
				
				for(OpcaoMenuEnum opc: OpcaoMenuEnum.values()) {
					if(opc == OpcaoMenuEnum.SEM_ESCOLHA) break;
					System.out.println((opc.ordinal()+1)+": "+ opc.getEscolhaDificuldade());
				}
				
				System.out.println("===================");
				System.out.println("Digite o número da opção: ");
				
				try{
					
				   itemSelecionado = OpcaoMenuEnum.values()[new Scanner(System.in).nextInt()-1];
				   System.out.println( itemSelecionado);
				   
				}catch (Exception e) {
					ConfiguracaoJogo.LimparTela();
					itemSelecionado = OpcaoMenuEnum.SEM_ESCOLHA;
				}
				switch (itemSelecionado){
				    
					case NOVO_JOGO:
						System.out.println("Opção NOVO JOGO selecionada!");
						System.out.println("Tenha um bom jogo :)");
						
						RPG.IniciaJogo();
						break;
						
					case REGRAS:
						System.out.println("Opção REGRAS selecionada!");
						System.out.println("=======REGRAS=======");
						System.out.println("1 - Você deverá acertar a resposta das perguntas para causar dano no inimigo.");
						System.out.println("2 - Caso você erre a resposta, sofrerá dano.");
						System.out.println("3 - Você deverá fazer a barra de vida do inimigo chegar a zero para ganhar a batalha.");
						System.out.println("4 - Se sua barra de vida chegar a zero primeiro, você perderá a batalha.");
						System.out.println("5 - Faça boas escolhas, pois elas determinam seu progresso no jogo.");
						System.out.println("6 - Divirta-se! :)");
						break;
						
					case CREDITOS:
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
					
					case DIFICULDADE:
						
						int escolhaDificuldade = 0;
						do {
							
							System.out.println("Opção DIFICULDADE selecionada!");
							System.out.println("=======DIFICULDADE=======");
							
							for(DificuldadeEnum dif: DificuldadeEnum.values() ) {
								System.out.println((dif.ordinal()+1)+" -"+dif.getEscolhaDificuldade());
							}
							
							System.out.println("==========================");
							System.out.println("Selecione a dificuldade do jogo: ");
							
							escolhaDificuldade  = new Scanner(System.in).nextInt();
							
						}while(!ConfiguracaoJogo.EscolhaDificuldadeSelecionadaExiste(escolhaDificuldade));
						
						String dificuldade = DificuldadeEnum.values()[escolhaDificuldade-1].getEscolhaDificuldade();	
						ConfiguracaoJogo.ConfiguraDificuldade(dificuldade);
						break;
						
					case SAIR:
						System.out.println("Opção SAIR selecionada!");
						System.out.println("O jogo será encerrado.");
						System.exit(0);
						break;
						
					case SEM_ESCOLHA:
						System.out.println("NADA SELECIONADO");
						break;
				}
			}
		} catch (Exception e) {
			ConfiguracaoJogo.LimparTela();
			System.out.println("Um erro inesperado ocorreu.");
		}
	}
}
