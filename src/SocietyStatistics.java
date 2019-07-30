import java.util.ArrayList;

 public class SocietyStatistics
{
    static SocietyStatistics singleton = null;
    ArrayList<Person> persons;
    static double avgIncome;
    double medionIncome;

    public static SocietyStatistics getSocietyStatistics()
    {
        if (singleton == null)
        {
            singleton = new SocietyStatistics();
            return singleton;
        }
        else return singleton;
    }

    public void setPersons(ArrayList<Person> pers)
    {
        this.persons = pers;
        calcAvgIncome();
    }

    private SocietyStatistics()
    {

    }

    void calcAvgIncome()
    {
        int incomeSum = 0;
        for(Person p :persons)
        {
            incomeSum += p.income;
        }
        if(persons.size() != 0)
            avgIncome = incomeSum / persons.size();

    }

    public double getAvgIncome()
    {
        return avgIncome;
    }
}
