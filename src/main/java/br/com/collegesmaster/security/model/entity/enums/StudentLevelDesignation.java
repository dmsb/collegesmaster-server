package br.com.collegesmaster.security.model.entity.enums;

public enum StudentLevelDesignation {

	APREDIZ_DE_MAGIA("Coruja Destemida"), 
	NOBRE_ALQUIMISTA("Alquimista Explorador"),
	BRUXO_EXPERIENTE("Espectro Etéreo"),
	DOMINADOR_DE_FEITICOS("Rei Bruxo");
	
	private final String designation;
	
	private StudentLevelDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getDesignation() {
		return designation;
	}
}
