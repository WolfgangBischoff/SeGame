package Core;

import java.util.ArrayList;

import static Util.Util.*;

public class Economy
{
    ArrayList<Company> companies = new ArrayList<>();

    public void populateEconomy(Integer numberComp)
    {
        for(int i=0; i<numberComp; i++)
        {
            companies.add(new Company(NUM_BASE_EDU_WORK, NUM_APPR_EDU_WORK, NUM_HIGH_EDU_WORK, NUM_UNIV_EDU_WORK));
            //companies.add(new Company(NUM_WORKPLACES_DEFAULT));
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
        StringBuilder tmp = new StringBuilder();
        for(Company company : companies)
        {
            tmp.append(company.toString() + "\n");
        }
        return tmp.toString();
    }
}
