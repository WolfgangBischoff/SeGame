package Core;

import static Util.Util.*;
import static Util.Util.SPAN_GROSS_INCOME_UNIVERSITY_EDU;
import static Util.Util.getRandom;

public class Workposition
{
    EducationalLayer neededEducation;
    Paygrade paygrade;
    Integer grossIncomeWork;
    Person worker = null;
    Company company;

    public Workposition(Company company, EducationalLayer edu)
    {
        this.company = company;
        this.neededEducation = edu;
        setPaygrade();
    }

    void setGrossIncomeWorkOnPaygrade(Paygrade paygrade)
    {
        switch (paygrade)
        {
            case A: grossIncomeWork = 1000;
            case B: grossIncomeWork = 1300;
            case C: grossIncomeWork = 1600;
            case D: grossIncomeWork = 2000;
            case E: grossIncomeWork = 3000;
            case F: grossIncomeWork = 6000;
            default: grossIncomeWork = 0;
        }
    }

    public boolean isWorkerAppropriate(Person worker)
    {
        if (neededEducation.getInt() <= worker.educationalLayer.getInt() && worker.worksAt == null)
            return true;
        else
            return false;
    }

    @Override
    public String toString()
    {
        return "\nWorkposition{" +
                "neededEducation=" + neededEducation +
                ", worker=" + worker +
                '}';
    }

    public void setPaygrade()
    {
        this.paygrade = Paygrade.fromInt(neededEducation.getInt());
    }

    public void setWorker(Person worker)
    {
        this.worker = worker;
    }
}
