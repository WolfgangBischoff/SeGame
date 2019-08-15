package Core;

import static Util.Util.NUMBER_COMPANIES;


public class Main
{

    public static void main(String[] args)
    {
        //Init Society
        Society soc = Society.getSociety();
        soc.populateSociety(15, 20, 10, 5);

        Economy economy = new Economy();
        economy.populateEconomy(NUMBER_COMPANIES);
        economy.fillWorkspaces(soc.getPeople());

        //Calc after Init
        soc.getSocietyStatistics().calcStatistics();
        soc.calcSociety();

        System.out.println(soc.printSocPeople());
        System.out.println(economy);
        System.out.println(soc.printSocStatistics());

    }
}
