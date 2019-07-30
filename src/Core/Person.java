package Core;

import static Util.Config.*;


public class Person
{
    String name;
    int age;
    int baseHappiness;
    int effectiveHappiness;
    int GrossIncome;

    EconomicLayer economicLayer;
    EducationalLayer educationalLayer;
    PoliticalOpinion politicalOpinion;

    EducationalLayer persGoalEducation;
    EconomicLayer persGoalEconomic;

    void calcState()
    {
        calculateEconomicLayer();
        calcBaseHappiness();
        calcEffectiveHappiness();
        politicalOpinion = calcPoliticalOpinion();
    }

    void calculateEconomicLayer()
    {
        if(GrossIncome < THRESHOLD_VERY_POOR)
            economicLayer = EconomicLayer.veryPoor;
        else if(GrossIncome < THRESHOLD_POOR)
            economicLayer = EconomicLayer.poor;
        else if(GrossIncome < THRESHOLD_MEDIUM)
            economicLayer = EconomicLayer.medium;
        else if (GrossIncome < THRESHOLD_RICH)
            economicLayer = EconomicLayer.rich;
        else if (GrossIncome > THRESHOLD_RICH)
            economicLayer = EconomicLayer.veryRich;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", GrossIncome=" + GrossIncome +
                ", educationalLayer=" + educationalLayer +
                ", politicalOpinion=" + politicalOpinion +
                '}';
    }

    static Person createRandomPerson()
    {
        int age = getRandom().nextInt(100);
        EducationalLayer edu = EducationalLayer.getRandomEdu();
        return new Person(getRandomName(), age, edu);
    }

    static Person createRandomPerson(EducationalLayer definedEdu)
    {
        int age = getRandom().nextInt(100);
        return new Person(getRandomName(), age, definedEdu);
    }

    int setIncomeOnEducation(EducationalLayer edu)
    {
        switch (edu)
        {

            case BASE: return MIN_GROSS_INCOME_BASE_EDU + getRandom().nextInt(SPAN_GROSS_INCOME_BASE_EDU);
            case APPRENTICESHIP: return MIN_GROSS_INCOME_APPRENTICE_EDU  + getRandom().nextInt(SPAN_GROSS_INCOME_APPRENTICE_EDU);
            case HIGHER: return MIN_GROSS_INCOME_HIGHER_EDU  + getRandom().nextInt(SPAN_GROSS_INCOME_HIGHER_EDU);
            case UNIVERSITY: return MIN_GROSS_INCOME_UNIVERSITY_EDU  + getRandom().nextInt(SPAN_GROSS_INCOME_UNIVERSITY_EDU);
                default: return 0;
        }
    }

    Person(String name, int age, EducationalLayer edu)
    {
        baseHappiness = calcBaseHappiness();
        this.name = name;
        this.age = age;
        educationalLayer = edu;
        this.GrossIncome = this.setIncomeOnEducation(educationalLayer);
        calculateEconomicLayer();
    }

    static String getRandomName()
    {
        String[] firstnames = {"Wolfgang", "Markus", "Hans", "Stefan", "Elisabeth", "Sebastian", "Juraj", "Anna", "Michael", "Eva", "Stefanie", "Tobias"};
        String[] lastnames = {"Bischoff", "Delitz", "Otto", "Lempa", "Rosenkranz", "Pay", "Veres", "Markt", "Mitterer", "Storf", "Sprengnagel", "Park"};
        return firstnames[getRandom().nextInt(firstnames.length)] + " " + lastnames[getRandom().nextInt(lastnames.length)];
    }

    int calcBaseHappiness()
    {
        //Calc on base of sickness, age and external things
        return 100;

    }

    void calcEffectiveHappiness()
    {
        //calc on base of internal vars
        double avginc = SocietyStatistics.getSocietyStatistics().getAvgIncome();
        effectiveHappiness = baseHappiness;

        if(GrossIncome < avginc)
            effectiveHappiness -= 10;
        else
            effectiveHappiness += 10;
        effectiveHappiness += educationalLayer.getNr();
    }

    PoliticalOpinion calcPoliticalOpinion()
    {
        if(effectiveHappiness < baseHappiness)
            return PoliticalOpinion.SocialDemocratic;
        else
            return PoliticalOpinion.Conservativ;
    }
}
