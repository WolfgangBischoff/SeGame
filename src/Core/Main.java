package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Util.Util.NUMBER_COMPANIES;


public class Main
{

    public static void main(String[] args) throws IOException
    {
        //Init Players
        Society soc = Society.getSociety();
        soc.populateSociety(15, 20, 10, 5);

        Economy economy = new Economy();
        economy.populateEconomy(NUMBER_COMPANIES);
        economy.fillWorkspaces(soc.getPeople());

        Government government = Government.getGoverment();

        //Calc after Init
        soc.getSocietyStatistics().calcStatistics();
        soc.calcSociety();

        //System.out.println(soc.printSocPeople());
        System.out.println(economy);
        System.out.println(soc.printSocStatistics());
        System.out.println(government);

        //Init Interpreter
        Interpreter interpreter = Interpreter.getInterpreter(soc, economy);
        boolean run = true;
        while (run)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(">>> ");
            String s = null;
            s = br.readLine();

            run = interpreter.readInstruction(s);

        }


    }
}
