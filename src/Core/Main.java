package Core;

public class Main
{

    public static void main(String[] args)
    {
        Society soc = Society.getSociety();
        soc.populateSociety();
        Economy economy = new Economy();
        economy.populateEconomy(soc.people);

        //System.out.println(economy);
        System.out.println(soc.printSocPeople());
        System.out.println(soc.printSocStatistics());
    }
}
