package Core;

public class InvalidInterpreterOptionCombination extends IllegalArgumentException
{
    public InvalidInterpreterOptionCombination(String methodName, String[] option)
    {
        /*StringBuilder sb = new StringBuilder()
                for(String args : option)
                    sb.append(args + " ");*/
        super("In " + methodName + "\n\t" + " invalid option combination");
    }
}
