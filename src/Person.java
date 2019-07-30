import java.util.Random;

public class Person
{
    String name;
    int age;
    int happiness;
    int income;

    EconomicLayer economicLayer;
    EducationalLayer educationalLayer;

    static Random rand = new Random();

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

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", income=" + income +
                ", economicLayer=" + economicLayer +
                ", educationalLayer=" + educationalLayer +
                '}';
    }

    static Person createRandomPerson()
    {
        int age = rand.nextInt(100);
        int income = rand.nextInt(2500);
        EducationalLayer edu = EducationalLayer.getRandomEdu();
        return new Person(getRandomName(), age, income, edu);
    }

    Person(String name, int age, int income, EducationalLayer edu)
    {
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
}
