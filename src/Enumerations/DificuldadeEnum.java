package Enumerations;

public enum DificuldadeEnum{
		FACIL("facil"),
		MEDIO("medio"),
		DIFICIL("dificil");
		
		private String escolhaDificuldade;
		public String getEscolhaDificuldade() {
			return escolhaDificuldade;
		}
		private DificuldadeEnum(String escolhaDificuldade) {
			this.escolhaDificuldade = escolhaDificuldade;
		}
}
