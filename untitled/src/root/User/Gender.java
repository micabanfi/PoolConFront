package root.User;

public enum Gender {
    MALE("Masculino"), FEMALE("Femenino"), OTHER("Otro");
	private String genderSpanish;
	
	private Gender(String gender) {
		this.genderSpanish = gender;
	}
	public String getGenderSpanish() {
		return genderSpanish;
	}
}


