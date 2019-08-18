package Core;

import java.util.ArrayList;

import static Util.Util.*;

public class Economy
{
    ArrayList<Company> companies = new ArrayList<>();
    EconomyStatistics economyStatistics;

    public Economy()
    {
        economyStatistics = new EconomyStatistics(this);
    }

    //Calculations
    public Integer calcNumberFreeWorkpositions()
    {
        Integer sum = 0;
        for(Company company : companies)
            sum += company.calcNumberFreeWorkpositions();
        return sum;
    }

    public void populateEconomy(Integer numberComp)
    {
        for(int i=0; i<numberComp; i++)
        {
            companies.add(new Company(NUM_BASE_EDU_WORK, NUM_APPR_EDU_WORK, NUM_HIGH_EDU_WORK, NUM_UNIV_EDU_WORK));
        }
    }

    public void fillWorkspaces(ArrayList<Person> worker)
    {
        for(Company company : companies)
        {
            for(Workposition workposition : company.workpositions)
            {
                for(Person person : worker)
                {
                    if(person.worksAt == null && company.hireWorker(workposition, person))
                    {break;}
                }
            }
        }
    }


    @Override
    public String toString()
    {
        return economyBaseData();
    }

    public String economyBaseData()
    {
        return "#Companies: " + companies.size() + " #FreeWorkplaces: " + calcNumberFreeWorkpositions();
    }

    public String economyCompanyData()
    {
        StringBuilder tmp = new StringBuilder();
        for(Company company : companies)
        {
            tmp.append(company.toString() + "\n");
        }
        return tmp.toString();
    }
}
