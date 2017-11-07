package root.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private String phone;
    private String gender;//enum
   // private LocalDate birthDate;
    private Preferences preferences;

    public Person(String name, String surname, String career, String phone,boolean smoke,boolean food,String gender){
        this.name=name;
        this.surname=surname;
        this.phone=phone;
        this.preferences = new Preferences(career, smoke, food);
        this.gender=gender;
        //this.birthDate=birthDate;
    }


    public String getName () {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    /*public LocalDate getBirthDate() {
        return birthDate;
    }*/

    public Preferences getPreferences() {
        return preferences;
    }

    @Override
    public String toString() {
        String s="";
        s="Nombre: "+ name+"\nApellido: "+surname+"\nTelefono: "+phone+"\nSexo:"+gender+"\n"+preferences.toString();
        return s;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Person))
            return false;
        Person aux = (Person) obj;
        if (!(this.name.equals(aux.name)))
            return false;
        if (!(this.surname.equals(aux.surname)))
            return false;
        if (!(this.phone.equals(aux.phone)))
            return false;
        if (!(this.gender.equals(aux.gender)))
            return false;
        return this.preferences.equals(aux.preferences);
    }
}