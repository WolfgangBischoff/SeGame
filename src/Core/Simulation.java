package Core;


public class Simulation
{
    private static Simulation singleton;
    Society society;
    Economy economy;
    Government government;
    Console console;

    private Simulation()
    {
        society = Society.getSociety();
        economy = new Economy();
        government = Government.getGoverment();
        console = new Console(this);
    }

    public static Simulation getSingleton()
    {
        if(singleton == null)
        {
            singleton = new Simulation();
        }
        return singleton;
    }

    public Console getConsole()
    {
        return console;
    }
}
