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
        economy = new Economy();
        government = Government.getGoverment();
        //Init Interpreter
        interpreter = Interpreter.getInterpreter(society, economy, government);
    }

    public Interpreter getInterpreter()
    {
        return interpreter;
    }

}
