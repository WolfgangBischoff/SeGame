package Core;

import java.util.ArrayList;
import static Util.Config.*;

public class Main
{

    public static void main(String[] args)
    {
        Society soc = new Society();
        soc.populateSociety();
        SocietyStatistics stat = SocietyStatistics.getSocietyStatistics();
        stat.setPersons(soc.people);

        for(Person p : soc.people)
        {
            p.calcState();
            System.out.println(p);
        }

        System.out.println(stat);
    }
}
