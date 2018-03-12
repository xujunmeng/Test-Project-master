package 第二个实例反编组;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by james on 2017/5/26.
 */
@XmlRootElement( name = "Persons" )
public class Persons {

    private Person persons;

    public Person getPersons() {
        return persons;
    }

    public void setPersons(Person persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "persons=" + persons +
                '}';
    }
}
