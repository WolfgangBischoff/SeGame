package Core;

public class UndefindedOptionException extends IllegalArgumentException
{
    public UndefindedOptionException(String methodName, String option)
    {
        super("In " + methodName + "\n\t" + "\t\"" + option + "\" option undefined");
    }
}
