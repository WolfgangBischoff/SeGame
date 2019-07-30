import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        ArrayList<Person> persons = new ArrayList<>();
        for(int i=0; i<10; i++)
        {
            persons.add(Person.createRandomPerson());
        }

        for(Person p : persons)
            System.out.println(p);

    }
}
