package Core;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulation
{
    Society society;
    Economy economy;
    Government government;
    Interpreter interpreter;

    public Simulation()
    {
        //Init Players
        society = Society.getSociety();
        //soc.populateSociety(15, 20, 10, 5);

        economy = new Economy();
        //economy.populateEconomy(NUMBER_COMPANIES);
        //economy.fillWorkspaces(soc.getPeople());

        government = Government.getGoverment();

        //Calc after Init
        //soc.getSocietyStatistics().calcAll();
        //soc.calcSociety();

        //Init Interpreter
        interpreter = Interpreter.getInterpreter(society, economy, government);
    }

    public Interpreter getInterpreter()
    {
        return interpreter;
    }

    public void runInterpreter() throws IOException
    {
        boolean run = true;
        while (run)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(">>> ");
            String s;
            s = br.readLine();

            run = interpreter.readInstruction(s);

        }
    }
}
