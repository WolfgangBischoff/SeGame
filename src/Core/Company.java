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

    public static Company createRandomCompany()
    {
        String[] names = {"HOFER", "Capgemini", "Allianz", "Löwenherz", "SwingKitchen", "PWC", "Kiss Bar", "Segafredo", "Merkur", "Maran Vegan", "Lenovo", "Bayer"};
        return new Company(names[Util.getRandom().nextInt(names.length)], Util.getRandom().nextInt(4)+1);
    }

    @Override
    public String toString()
    {
        return "\nCompany{" +
                "name='" + name + '\'' +
                ", workpositions=" + workpositions +
                '}';
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
