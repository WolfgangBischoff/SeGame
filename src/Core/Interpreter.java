package Core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Util.Util.*;


/*
Existing Instructions:
Person print -fn -ln -a



 */
public class Interpreter
{
    private static Interpreter instance = null;
    private Society society;
    private Economy economy;
    private boolean returnRun = true;
    private String inputString = null;

    //Constructors
    private Interpreter(Society soc, Economy eco)
    {
        society = soc;
        economy = eco;
    }

    public boolean readInstruction(String inputParam)
    {
        inputString = inputParam;
        String[] param = inputParam.split(" ");
        try
        {
            processFirstParam(param);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("readInstruction()\n\t" + e.getMessage() + "\nInput: " + inputString);
        }
        return returnRun;
    }

    //FIRST INSTRUCTION
    private void processFirstParam(String[] param) throws IllegalArgumentException
    {
        String[] newParam = cutFirstIndexPositions(param, 1);
        switch (param[0].toLowerCase())
        {
            case "person":
                processSecondParamAfterPerson(newParam); break;
            case "society":
                processSecondParamAfterSociety(newParam);break;
            case "company":
                processSecondParamAfterCompany(newParam); break;

            case "quit":
            case "end":
                returnRun = false; break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }

    }

    //SECOND INSTRUCTION
    private void processSecondParamAfterPerson(String[] param)
    {
        if(param.length == 0)
        {
            System.out.println("Further arguments needed"); return;
        }

        String[] newParam = cutFirstIndexPositions(param, 1);
        switch (param[0].toLowerCase())
        {
            case "add":
                personAdd(newParam);
                break;
            case "print":
                personPrint(newParam);
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    private void processSecondParamAfterSociety(String[] param)
    {
        if(param.length == 0)
        {
            System.out.println("Further arguments needed"); return;
        }

        String[] newParam = cutFirstIndexPositions(param, 1);
        switch (param[0].toLowerCase())
        {
            case "print":
                societyPrint(newParam);
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    private void processSecondParamAfterCompany(String[] param)
    {
        System.out.println("TODO");
    }

    //OPTIONS
    private void societyPrint(String[] newParam)
    {
        //Analyse Options
        System.out.println(society.getSocietyStatistics().printGeneral());
    }

    /***
     * Looks for Persons who match all given cmd parameters
     * @param param given cmd parameters
     */
    private void personPrint(String[] param) throws NumberFormatException
    {
        //No options
        if (param.length == 0)
        {
            System.out.println(society.printSocPeople());
        }
        //Option Parameters exist
        else
        {
            Map<String, String> options = readOptionParameter(param);
            //Possible Queries
            String firstname = null;
            String lastname = null;
            Integer age = null;

            //Populate Query options
            for(Map.Entry<String, String> entry : options.entrySet())
            {
                switch (entry.getKey())
                {
                    case "-firstname":
                    case "-fn":
                        firstname = entry.getValue(); break;
                    case "-lastname":
                    case "-ln":
                        lastname = entry.getValue(); break;
                    case "-age":
                    case "-a":
                        age = Integer.valueOf(entry.getValue()); break;
                        default:
                            throw new IllegalArgumentException("Found illegal argument: " + entry.getKey());
                }
            }

            //Iterate all persons and check query
            for(Person p : society.getPeople())
            {
                if //if query == null => we dont need to check => true
                (
                        (firstname == null || firstname.equals(p.firstname)) &&
                                (lastname == null || lastname.equals(p.lastname)) &&
                                (age == null || age.equals(p.age))
                )
                System.out.println(p);
            }
        }
    }

    private void personAdd(String[] param)
    {
        Map<String, String> options;
        String firstname = DEFAULT_FIRSTNAME;
        String lastname = DEFAULT_LASTNAME;
        Integer age = DEFAULT_AGE;
        Person newPerson;

        if (param.length == 0)
        {
            newPerson = society.addPerson();
        }
        else
        {
            options = readOptionParameter(param);

            for(Map.Entry<String, String> entry : options.entrySet())
            {
                switch (entry.getKey())
                {
                    case "-firstname":
                    case "-fn":
                        firstname = entry.getValue(); break;
                    case "-lastname":
                    case "-ln":
                        lastname = entry.getValue(); break;
                    case "-age":
                    case "-a":
                        age = Integer.valueOf(entry.getValue()); break;
                    default:
                        throw new IllegalArgumentException("Found illegal argument: " + entry.getKey());
                }
            }
            newPerson = society.addPerson(firstname, lastname, age, DEFAULT_EDU);
        }
        System.out.println("Added Person: " + newPerson);
    }

    //Helper
    private String[] cutFirstIndexPositions(String[] input, Integer NumberCutPostions)
    {
        String[] ret = new String[input.length-NumberCutPostions];
        for(int i=0; i<ret.length; i++)
            ret[i]=input[i + NumberCutPostions];
        return ret;
    }

    private Map<String, String> readOptionParameter(String[] params)
    {
        //Iterate params and create Map
        Map<String, String> results = new HashMap<>();
        String[] residualParams = params;

        while(residualParams.length > 0)
        {
            //Next Token is parameter type
            if(residualParams[0].contains("-"))
            {
                //paramter type with parameter value (-n Wolfgang)
                if(residualParams.length >= 2 && !residualParams[1].contains("-"))
                {
                    results.put(residualParams[0], residualParams[1]);
                    residualParams = cutFirstIndexPositions(residualParams, 2);
                }
                else
                //just parameter type (-all)
                {
                    results.put(residualParams[0], null);
                    residualParams = cutFirstIndexPositions(residualParams, 1);
                }
            }
            else
            {
                throw new IllegalArgumentException("Invalid option parameter: " + residualParams[0] + " in "+ inputString + "\n Correct parameter example: -n name");
            }
        }
        return  results;
    }

    //Getter
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
}
