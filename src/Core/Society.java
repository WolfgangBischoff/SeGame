package Core;

import java.util.ArrayList;
import static Util.Util.*;

public class Society {

    private static Society singleton = null;
    ArrayList<Person> people = new ArrayList<>();
    private SocietyStatistics societyStatistics;

    private Society()
    {

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

    public void populateSociety()
    {
        //Create People
        for(int i=0; i<NUMBER_PERSONS; i++)
        {
            Integer[] ratios = {RATION_BASIC_EDU,RATION_APP_EDU,RATION_HIGHER_EDU,RATION_UNIVERSITY_EDU};
            people.add(
                    Person.createRandomPerson(
                            EducationalLayer.fromInt(Statistics.randomWithRatio(ratios))));
        }

//TODO Refactor/Implement Calculation sewuence for time
        for(Person p : people)
            p.initState();
        societyStatistics =  new SocietyStatistics(this);
        for(Person p : people)
            p.calcState();
    }

}
