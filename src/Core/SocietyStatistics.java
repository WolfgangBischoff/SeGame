package Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Core.EducationalLayer.*;

public class SocietyStatistics extends Statistics
{
    static SocietyStatistics singleton = null;
    ArrayList<Person> persons;
    Map<EducationalLayer, Integer> eduStat;
    Double avgGrossIncome;
    Double medianIncome;
    Double unemploymentRate;


    @Override
    public String toString()
    {
        return "SocietyStatistics{" +
                "eduStat=" + eduStat +
                ", avgGrossIncome=" + avgGrossIncome +
                ", medianIncome=" + medianIncome +
                ", UNEmployed: " + unemploymentRate +
                '}';
    }

    public SocietyStatistics(Society soc)
    {
        persons = soc.getPeople();
        calcStatistics();
    }

    void calcEmploymentRate()
    {
        Double employed = 0.0;
        for(Person person : persons)
            if(person.worksAt != null)
                employed++;

            Double employmentRate = employed / persons.size();
            unemploymentRate = 1-employmentRate;
    }

    public void calcStatistics()
    {
        ArrayList<Integer> grossIncomes = new ArrayList<>();
        eduStat = new HashMap<>();
        Integer numberBasicEdu = 0;
        Integer numberApprenEdu = 0;
        Integer numberHigherEdu = 0;
        Integer numberUnivEdu = 0;
        for(Person p :persons)
        {
            grossIncomes.add(p.getGrossIncome());
            switch (p.educationalLayer)
            {
                case EDU_BASE: numberBasicEdu++; break;
                case EDU_APPRENTICESHIP: numberApprenEdu++; break;
                case EDU_HIGHER: numberHigherEdu++; break;
                case EDU_UNIVERSITY: numberUnivEdu++; break;
                default: System.out.println("NO EDU");
            }
        }
        avgGrossIncome = Statistics.calcAvg(grossIncomes);
        medianIncome = Statistics.calcMedian(grossIncomes);
        eduStat.put(EDU_BASE,numberBasicEdu);
        eduStat.put(EDU_APPRENTICESHIP, numberApprenEdu);
        eduStat.put(EDU_HIGHER, numberHigherEdu);
        eduStat.put(EDU_UNIVERSITY, numberUnivEdu);

        calcEmploymentRate();
    }

    private void addEduLayer(Person p)
    {

    }

    public double getAvgIncome()
    {
        return avgGrossIncome;
    }


}
