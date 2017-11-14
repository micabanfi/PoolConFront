package root.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Person implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String phone;
    private Gender gender;
    private Preferences preferences;

    public Person(String name, String surname, String career, String phone,boolean smoke,boolean food,Gender gender){
        this.name=name;
        this.surname=surname;
        this.phone=phone;
        this.preferences = new Preferences(career, smoke, food);
        this.gender=gender;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(name);
        out.writeUTF(surname);
        out.writeUTF(phone);
        out.writeObject(preferences);
        out.writeObject(gender);
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        name = ois.readUTF();
        surname = ois.readUTF();
        phone = ois.readUTF();
        preferences = (Preferences) ois.readObject();
        gender = (Gender) ois.readObject();
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Preferences getPreferences() {
        return preferences;
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

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + name.hashCode();
        result = 31*result + surname.hashCode();
        result = 31*result + phone.hashCode();
        result = 31*result + gender.hashCode();
        result = 31*result + preferences.hashCode();
        return result;
    }
    

    @Override
    public String toString() {
        String s="";
        s="Nombre: "+ name+"\nApellido: "+surname+"\nTelefono: "+phone+"\nSexo:"+gender.getGender()+"\n"+preferences.toString();
        return s;
    }
}