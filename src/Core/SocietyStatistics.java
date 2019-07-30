package Core;

import java.util.ArrayList;

 public class SocietyStatistics extends Statistics
{
    static SocietyStatistics singleton = null;
    ArrayList<Person> persons;
    Double avgGrossIncome;
    Double medianIncome;
    Integer numberBasicEdu = 0;
    Integer numberApprenEdu = 0;
    Integer numberHigherEdu = 0;
    Integer numberUnivEdu = 0;

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
        createStatistics();
    }

    @Override
    public String toString() {
        return "SocietyStatistics{" +
                "avgGrossIncome=" + avgGrossIncome +
                ", medianIncome=" + medianIncome +
                ", numberBasicEdu=" + numberBasicEdu +
                ", numberApprenEdu=" + numberApprenEdu +
                ", numberHigherEdu=" + numberHigherEdu +
                ", numberUnivEdu=" + numberUnivEdu +
                '}';
    }

    private SocietyStatistics()
    {

    }

    private void createStatistics()
    {
        ArrayList<Integer> grossIncomes = new ArrayList<>();
        for(Person p :persons)
        {
            grossIncomes.add(p.GrossIncome);
            addEduLayer(p);
        }

        avgGrossIncome = Statistics.calcAvg(grossIncomes);
        medianIncome = Statistics.calcMedian(grossIncomes);
    }

    private void addEduLayer(Person p)
    {
        switch (p.educationalLayer)
        {
            case BASE: numberBasicEdu++; break;
            case APPRENTICESHIP: numberApprenEdu++; break;
            case HIGHER: numberHigherEdu++; break;
            case UNIVERSITY: numberUnivEdu++; break;
            default: System.out.println("NO EDU");
        }
    }

    public double getAvgIncome()
    {
        return avgGrossIncome;
    }


}
