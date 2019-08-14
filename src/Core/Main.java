package Core;

public class Main
{

    public static void main(String[] args)
    {
        //Init Society
        Society soc = Society.getSociety();
        soc.populateSociety();

        Economy economy = new Economy();
        economy.populateEconomy();
        economy.fillWorkspaces(soc.getPeople());

        //Calc after Init
        soc.getSocietyStatistics().calcStatistics();
        soc.calcSociety();

        System.out.println(soc.printSocPeople());
        System.out.println(economy);
        System.out.println(soc.printSocStatistics());

    }
}
