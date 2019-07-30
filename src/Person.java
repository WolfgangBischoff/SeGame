import java.util.Random;

public class Person
{
    String name;
    int age;
    int baseHappiness;
    int effectiveHappiness;
    int income;

    EconomicLayer economicLayer;
    EducationalLayer educationalLayer;
    PoliticalOpinion politicalOpinion;

    static Random rand = new Random();



    void calcState()
    {
        calculateEconomicLayer();
        calcBaseHappiness();
        calcEffectiveHappiness();
        politicalOpinion = calcPoliticalOpinion();
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", baseHappiness=" + baseHappiness +
                ", effectiveHappiness=" + effectiveHappiness +
                ", economicLayer=" + economicLayer +
                ", educationalLayer=" + educationalLayer +
                ", politicalOpinion=" + politicalOpinion +
                '}';
    }

    void calculateEconomicLayer()
    {
        if(income < 500)
            economicLayer = EconomicLayer.veryPoor;
        else if(income < 1000)
            economicLayer = EconomicLayer.poor;
        else if(income < 1500)
            economicLayer = EconomicLayer.medium;
        else if (income < 3000)
            economicLayer = EconomicLayer.rich;
        else if (income > 6000)
            economicLayer = EconomicLayer.veryRich;
    }

    static Person createRandomPerson()
    {
        int age = rand.nextInt(100);
        int income = rand.nextInt(3000);
        EducationalLayer edu = EducationalLayer.getRandomEdu();
        return new Person(getRandomName(), age, income, edu);
    }

    Person(String name, int age, int income, EducationalLayer edu)
    {
        baseHappiness = calcBaseHappiness();
        this.name = name;
        this.age = age;
        this.income = income;
        educationalLayer = edu;
        calculateEconomicLayer();
    }

    static String getRandomName()
    {
        String[] firstnames = {"Wolfgang", "Markus", "Hans", "Stefan", "Elisabeth", "Sebastian", "Juraj", "Anna", "Michael"};
        String[] lastnames = {"Bischoff", "Delitz", "Otto", "Lempa", "Rosenkranz", "Pay", "Veres", "Markt", "Mitterer", "Storf"};
        return firstnames[rand.nextInt(firstnames.length)] + " " + lastnames[rand.nextInt(lastnames.length)];
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

        if(income < avginc)
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
