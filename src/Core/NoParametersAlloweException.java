package Core;

public class NoParametersAlloweException extends IllegalArgumentException
{
    public NoParametersAlloweException(String methodName, String option)
    {
        super("In " + methodName + "\n\t" + option +" must not have arguments, did your forget \"-\"?");
    }
}
