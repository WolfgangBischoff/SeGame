package Core;

public class Main
{

    public static void main(String[] args)
    {
        Society soc = Society.getSociety();
        soc.populateSociety();

        Economy economy = new Economy();
        economy.populateEconomy();
        economy.fillWorkspaces(soc.getPeople());

        soc.getSocietyStatistics().calcStatistics();

        System.out.println(soc.printSocPeople());
        System.out.println(soc.printSocStatistics());
        System.out.println(economy);

    }
}
