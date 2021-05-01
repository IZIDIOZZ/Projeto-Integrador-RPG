package Enumerations;

public enum OpcaoMenuEnum {
		NOVO_JOGO("NOVO JOGO"),
		REGRAS("REGRAS"),
		CREDITOS("CRÉDITOS"),
		DIFICULDADE("DIFICULDADE"),
		SAIR("SAIR"),
		SEM_ESCOLHA("SEM ESCOLHA");
		
		private String opcaoMenu;
		public String getEscolhaDificuldade() {
			return opcaoMenu;
		}
		private OpcaoMenuEnum(String opcaoMenu) {
			this.opcaoMenu = opcaoMenu;
		}
}
