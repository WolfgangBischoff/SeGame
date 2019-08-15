package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Util.Util.NUMBER_COMPANIES;


public class Main
{

    public static void main(String[] args) throws IOException
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

        //Init Interpreter
        Interpreter interpreter = Interpreter.getInterpreter(soc, economy);
        while (true)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(">>> ");
            String s = null;
            s = br.readLine();

            try
            {
                interpreter.readInstruction(s);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }


    }
}
