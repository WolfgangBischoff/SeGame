package Core;

public class Interpreter
{
    private static Interpreter instance = null;
    private Society society;
    private Economy economy;

    private Interpreter(Society soc, Economy eco)
    {
        society = soc;
        economy = eco;
    }

    public static Interpreter getInterpreter()
    {
        if(instance != null)
            return instance;
        else
        {
            throw new RuntimeException("Interpreter not initialized");
        }
    }

    public static Interpreter getInterpreter(Society soc, Economy eco)
    {
        if(instance != null)
            throw new RuntimeException("Interpreter already initialized");
        else
        {
            return new Interpreter(soc, eco);
        }
    }

    public void readInstruction(String inputParam)
    {
        System.out.println("Input: " + inputParam);
        String[] param = inputParam.split(" ");
        processInstruction(param[0], param);
    }


    private void processInstruction(String instruction, String[] param) throws IllegalArgumentException
    {
        switch (instruction.toLowerCase())
        {
            case "add":
                System.out.println("Add");
                break;
            default:
                throw new IllegalArgumentException("Argument: " + instruction + " not known");
        }

    }

}
