package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static Util.Util.NUMBER_COMPANIES;


public class Main
{

    public static void main(String[] args) throws IOException
    {
        Simulation simulation = new Simulation();
        simulation.runInterpreter();
    }
}
