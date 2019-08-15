package Core;

import java.util.ArrayList;
import static Util.Util.*;

public class Society {

    private static Society singleton = null;
    private ArrayList<Person> people = new ArrayList<>();
    private SocietyStatistics societyStatistics;

    private Society(){}

    public ArrayList<Person> getPeople()
    {
        return people;
    }

    public void calcSociety()
    {
        for(Person person : people)
            person.calcState();
    }

    public SocietyStatistics getSocietyStatistics()
    {
        return societyStatistics;
    }

    public static Society getSociety()
    {
        if(singleton == null)
        {
            singleton = new Society();
            return singleton;
        }

        else
            return singleton;
    }

    @Override
    public String toString()
    {
        return "Society: " +
                "people=" + people +
                '}';
    }

    public String printSocPeople()
    {
        StringBuilder ret = new StringBuilder();
        for(Person person : people)
            ret.append("\nPerson: " +
                    person.printBasicData() + "\n\t" +
                    person.printHappiness()  + "\n\t" +
                    person.printLayers() + "\n\t" +
                    person.printEconomical()
            );
        return ret.toString();
    }

    public String printSocStatistics()
    {
        return societyStatistics.toString();
    }

    /***
     * Creates a population randomly based on defined educational Ratios
     * @param numberPersons Number of people
     */
    public void populateSociety(Integer numberPersons)
    {
        //Create People
        for(int i=0; i<numberPersons; i++)
        {
            //Random generation of EduLayer
            Integer[] ratios = {RATION_BASIC_EDU,RATION_APP_EDU,RATION_HIGHER_EDU,RATION_UNIVERSITY_EDU};
            people.add(
                    new Person(
                            EducationalLayer.fromInt(Statistics.randomWithRatio(ratios))));
        }
        societyStatistics =  new SocietyStatistics(this);
    }

    public void populateSociety(Integer baseEdu, Integer apprEdu, Integer higherEdu, Integer unicEdu)
    {
        for(int i=0; i < baseEdu; i++)
        {
            people.add(new Person(EducationalLayer.EDU_BASE));
        }
        for(int i=0; i < apprEdu; i++)
        {
            people.add(new Person(EducationalLayer.EDU_APPRENTICESHIP));
        }
        for(int i=0; i < higherEdu; i++)
        {
            people.add(new Person(EducationalLayer.EDU_HIGHER));
        }
        for(int i=0; i < unicEdu; i++)
        {
            people.add(new Person(EducationalLayer.EDU_UNIVERSITY));
        }
        societyStatistics = new SocietyStatistics(this);
    }

    public Person addPerson(String name, Integer age, EducationalLayer edu)
    {
        Person newPerson = Person.getNewPerson(name, age, edu);
        people.add(newPerson);
        return newPerson;
    }

    public Person addPerson(String name, EducationalLayer edu)
    {
        return addPerson(name, DEFAULT_AGE, edu);
    }

    public Person addPerson(String name)
    {
        return addPerson(name, EducationalLayer.EDU_BASE);
    }

    public Person addPerson()
    {
        return addPerson(DEFAULT_NAME);
    }



}
