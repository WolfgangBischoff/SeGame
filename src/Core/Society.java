package Core;

import java.util.ArrayList;
import static Util.Config.*;

public class Society {

    ArrayList<Person> people = new ArrayList<>();


    public void populateSociety()
    {
        for(int i=0; i<NUMBER_PERSONS; i++)
        {
            Integer[] ratios = {RATION_BASIC_EDU,RATION_APP_EDU,RATION_HIGHER_EDU,RATION_UNIVERSITY_EDU};
            people.add(
                    Person.createRandomPerson(
                            EducationalLayer.fromInt(Statistics.randomWithRatio(ratios))));
        }

    }

}
