package Core;


public class Simulation
{
    Society society;
    Economy economy;
    Government government;
    //Interpreter interpreter;
    Console console;

    public Simulation()
    {
        //Init Players
        society = Society.getSociety();
        economy = new Economy();
        government = Government.getGoverment();
        //Init Interpreter
        //interpreter = Interpreter.getInterpreter(society, economy, government);
        console = new Console(this);
    }

    /*public Interpreter getInterpreter()
    {
        return interpreter;
    }*/

    public Console getConsole()
    {
        return console;
    }
}
