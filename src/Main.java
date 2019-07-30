import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        ArrayList<Person> persons = new ArrayList<>();
        for(int i=0; i<5; i++)
        {
            persons.add(Person.createRandomPerson());
        }

        SocietyStatistics stat = SocietyStatistics.getSocietyStatistics();
        stat.setPersons(persons);
        System.out.println(stat.getAvgIncome());

        for(Person p : persons)
        {
            p.calcState();
            System.out.println(p);
        }

    }
}
