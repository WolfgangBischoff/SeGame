package Core;

import java.util.HashMap;
import java.util.Map;

import static Util.Util.*;


/*
Existing Instructions:

add Person -name Firstname lastname -age 42
quit/end
print





 */
public class Interpreter
{
    private static Interpreter instance = null;
    private Society society;
    private Economy economy;
    private boolean returnRun = true;
    private String inputString = null;

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

    public boolean readInstruction(String inputParam)
    {
        inputString = inputParam;
        String[] param = inputParam.split(" ");
        processInstruction(param);
        return returnRun;
    }

    private String[] cutFirstIndexPositions(String[] input, Integer NumberCutPostions)
    {
        String[] ret = new String[input.length-NumberCutPostions];
        for(int i=0; i<ret.length; i++)
            ret[i]=input[i + NumberCutPostions];
        return ret;
    }

    //TODO Change first instruction to Object, because Object may get unique second instruction like Economy revolution but not revolution Person
    private void processInstruction(String[] param) throws IllegalArgumentException
    {
        String[] newParam = cutFirstIndexPositions(param, 1);
        switch (param[0].toLowerCase())
        {
            case "add":
                add(newParam);
                break;
            case "print":
                print();
                break;
            case "quit":
            case "end":
                returnRun = false; break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }

    }

    private void print()
    {
        System.out.println(society.printSocPeople());
    }

    private void add(String[] param)
    {
        String[] newParam = cutFirstIndexPositions(param, 1);
        switch (param[0].toLowerCase())
        {
            case "person":
                addPerson(newParam); break;
                default:
                    throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    private Map<String, String> readPersonOption(String[] params)
    {
        String[] residualParams = params;
        Map<String, String> results = new HashMap<>();

        while(residualParams.length > 0)
        {
            switch (residualParams[0].toLowerCase())
            {
                case "-n":
                case "-name":
                    if(residualParams.length >= 3)
                    {
                        results.put("name", residualParams[1] + " " + residualParams[2]);
                    }
                    else
                        throw new IllegalArgumentException("Need two arguments, for example \"-n Hans Maulwurf\", but found: " + inputString);
                    residualParams = cutFirstIndexPositions(residualParams, 3);
                    break;

                case "-a":
                case "-age":
                    if(residualParams.length >= 2)
                    {
                        if(tryParseInt(residualParams[1]))
                            results.put("age", residualParams[1]);
                        else
                            throw new IllegalArgumentException("Cannot parse Integer, found: " + residualParams[1] + " in \n>>> " + inputString);
                    }
                    else
                        throw new IllegalArgumentException("Need one argument, for example \"-a 42\", but found: " + inputString);
                    residualParams = cutFirstIndexPositions(residualParams, 2);
                    break;

                default:
                    throw new IllegalArgumentException("INVALID ARGUMENTS: >>>" + inputString);
            }
        }


        return  results;
    }

    private void addPerson(String[] param)
    {
        Map<String, String> options;
        String name = DEFAULT_NAME;
        Integer age = DEFAULT_AGE;
        EducationalLayer edu = DEFAULT_EDU;
        Person newPerson = null;

        if (param.length == 0)
        {
            newPerson = society.addPerson();
            System.out.println("Added person: " + newPerson);
        }
        else
        {
            options = readPersonOption(param);
            if(options.containsKey("name"))
                name = options.get("name");
            if(options.containsKey("age"))
                age = Integer.valueOf(options.get("age"));
            //if(options.containsKey("education"))
             //   edu = EducationalLayer.fromInt(0);
            newPerson = society.addPerson(name, age, edu);
            System.out.println("Added person: " + newPerson);
        }

    }

}
