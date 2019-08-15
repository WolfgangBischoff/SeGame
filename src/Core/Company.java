package Core;

import Util.Util;

import java.util.ArrayList;

import static Util.Util.*;

public class Company
{
    String name;
    ArrayList<Workposition> workpositions = new ArrayList();

    boolean hireWorker(Workposition workposition, Person p)
    {
        if(workposition.isWorkerAppropriate(p))
        {
            workposition.worker = p;
            p.setWorksAt(workposition);
            p.calcState();
            return true;
        }
        else
            return false;
    }


    static String getRandomCompanyName()
    {
        String[] names = {"HOFER", "Capgemini", "Allianz", "Löwenherz", "SwingKitchen", "PWC", "Kiss Bar", "Segafredo", "Merkur", "Maran Vegan", "Lenovo", "Bayer", "Young Living"};
        return names[Util.getRandom().nextInt(names.length)];
    }

    @Override
    public String toString()
    {
        return "\nCompany{" +
                "name='" + name + '\'' +
                ", workpositions=" + workpositions +
                '}';
    }

    public Company()
    {
        this(NUM_WORKPLACES_DEFAULT);
    }

    public Company(Integer numberWorkpostions)
    {
        this(getRandomCompanyName(), numberWorkpostions);
    }

    public Company(String name, int numberWorkpositions)
    {
        this.name = name;
        Integer[] ratio = {RATIO_NEEDED_BASE_EDU, RATIO_NEEDED_APPR_EDU, RATIO_NEEDED_HIGH_EDU, RATIO_NEEDED_UNIV_EDU};
        for(int i=0; i<numberWorkpositions; i++)
            workpositions.add(new Workposition(this,
                    EducationalLayer.fromInt(
                            Statistics.randomWithRatio(ratio))));
    }
}
