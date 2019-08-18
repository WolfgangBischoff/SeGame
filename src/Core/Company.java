package Core;

import Util.Util;

import java.util.ArrayList;

import static Util.Util.*;

public class Company {
    private String name;
    private Integer deposit = 0;
    private ArrayList<Workposition> workpositions = new ArrayList();

    //Constructors
    public Company()
    {
        name = Company.getRandomCompanyName();
        deposit = COMP_DEFAULT_DEPOSIT;
    }

    public Company(Integer base, Integer app, Integer high, Integer univ)
    {
        this();
        for (int i = 0; i < base; i++)
            workpositions.add(new Workposition(this, EducationalLayer.EDU_BASE));
        for (int i = 0; i < app; i++)
            workpositions.add(new Workposition(this, EducationalLayer.EDU_APPRENTICESHIP));
        for (int i = 0; i < high; i++)
            workpositions.add(new Workposition(this, EducationalLayer.EDU_HIGHER));
        for (int i = 0; i < univ; i++)
            workpositions.add(new Workposition(this, EducationalLayer.EDU_UNIVERSITY));
    }

    //Calculations

    public Integer calcNumberFreeWorkpositions()
    {
        Integer returnSum = 0;
        for (Workposition workposition : workpositions)
            if (workposition.worker == null)
                returnSum++;
        return returnSum;
    }

    boolean hireWorker(Workposition workposition, Person p)
    {
        if (workposition.isWorkerAppropriate(p))
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

    //Prints
    @Override
    public String toString()
    {
        return "\nCompany{" +
                "name='" + name + '\'' +
                ", workpositions=" + workpositions +
                '}';
    }

    //Getter and Setter
    public String getName() {
        return name;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public ArrayList<Workposition> getWorkpositions() {
        return workpositions;
    }
}
