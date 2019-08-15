package Core;

import java.util.ArrayList;

import static Util.Util.NUMBER_COMPANIES;

public class Economy
{
    ArrayList<Company> companies = new ArrayList<>();

    public void populateEconomy()
    {
        for(int i=0; i<NUMBER_COMPANIES; i++)
        {
            companies.add(new Company());
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
