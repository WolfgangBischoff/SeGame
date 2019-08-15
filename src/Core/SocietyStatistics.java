package Core;

import Util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SocietyStatistics extends Statistics
{
    static SocietyStatistics singleton = null;
    ArrayList<Person> persons;
    Map<EducationalLayer, Double> eduStat = new HashMap<>();
    Map<EducationalLayer, Integer> eduStatAbsolut;
    Map<PoliticalOpinion, Double> polStat = new HashMap<>();
    Map<PoliticalOpinion, Integer> polStatAbsolut;
    Double avgGrossIncome;
    Double medianGrossIncome;
    Double avgNetIncome;
    Double medianNetIncome;
    Integer unemployedNumber;
    Integer employedNumber;
    Double unemploymentRate;


    @Override
    public String toString()
    {
        return "\nSocietyStatistics" +
                "\n" + printIncomeStat() +
                "\n" + printPolStat() +
                "\n" + printEduStat()
                ;
    }

    String printIncomeStat()
    {
        return "Incomes: " + "AvgGross: " + avgGrossIncome + " AvgNet: " + avgNetIncome + " MedianGross " + medianGrossIncome + " Unemployed: " + unemploymentRate;
    }

    String printPolStat()
    {
        return "Political: " + polStatAbsolut + " " + polStat;
    }

    String printEduStat()
    {
        return "Educational: " + eduStatAbsolut + " " + eduStat;
    }

    public SocietyStatistics(Society soc)
    {
        persons = soc.getPeople();
        calcStatistics();
    }

    void calcEmploymentRate()
    {
        Integer employed = 0;
        for(Person person : persons)
            if(person.worksAt != null)
                employed++;

            Double employmentRate = ((double) employed) / persons.size();

            unemploymentRate = Util.roundTwoDigits((1-employmentRate));
            employedNumber = employed;
            unemployedNumber = persons.size() - employed;
    }

    void calcEnumStatsViews()
    {
        Map<PoliticalOpinion, Integer> polInput = new HashMap<>();
        Map<EducationalLayer, Integer> eduInput = new HashMap<>();

        for(Person person : persons)
        {
            //Collect political Data
            if(polInput.containsKey(person.politicalOpinion))
                polInput.put(person.politicalOpinion, polInput.get(person.politicalOpinion) + 1);
            else
                polInput.put(person.politicalOpinion, 1);

            //Collect Educational Data
            if(eduInput.containsKey(person.educationalLayer))
                eduInput.put(person.educationalLayer, eduInput.get(person.educationalLayer) + 1);
            else
                eduInput.put(person.educationalLayer, 1);
        }

        polStatAbsolut = polInput;
        polStat = Statistics.calcPercFromEnumCount(polInput);
        eduStatAbsolut = eduInput;
        eduStat = Statistics.calcPercFromEnumCount(eduInput);
    }

    void calcIncomes()
    {
        ArrayList<Integer> grossIncomes = new ArrayList<>();
        ArrayList<Integer> netIncomes = new ArrayList<>();
        for(Person p :persons)
        {
            grossIncomes.add(p.getGrossIncome());
            netIncomes.add(p.getNettIncome());
        }

        avgGrossIncome = Statistics.calcAvg(grossIncomes);
        medianGrossIncome = Statistics.calcMedian(grossIncomes);
        avgNetIncome = Statistics.calcAvg(netIncomes);
        medianNetIncome = Statistics.calcMedian(netIncomes);
    }

    public void calcStatistics()
    {
        calcIncomes();
        calcEmploymentRate();
        calcEnumStatsViews();
    }

    public double getAvgIncome()
    {
        return avgGrossIncome;
    }


}
