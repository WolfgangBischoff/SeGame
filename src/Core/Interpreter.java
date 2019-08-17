package Core;


import java.util.HashMap;
import java.util.Map;




/*
Existing Instructions:
Person print -fn -ln -a



 */
public class Interpreter {
    private static Interpreter instance = null;
    private Society society;
    private Economy economy;
    private Government government;
    private boolean returnRun = true;
    private String inputString = null;

    //Constructors
    private Interpreter(Society soc, Economy eco, Government gov)
    {
        society = soc;
        economy = eco;
        government = gov;
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
            case "per":
                processSecondParamAfterPerson(newParam);
                break;
            case "society":
            case "soc":
                processSecondParamAfterSociety(newParam);
                break;
            case "company":
            case "com":
                processSecondParamAfterCompany(newParam);
                break;
            case "government":
            case "gov":
                processSecondParamAfterGovernment(newParam);
                break;
            case "economy":
            case "eco":
                processSecondParamAfterEconomy(newParam);
                break;

            case "quit":
            case "end":
                returnRun = false;
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }

    }

    //SECOND INSTRUCTION
    private void processSecondParamAfterPerson(String[] param)
    {
        //just "person"
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Fill possible option parameter like person ??? -fn Wolfgang -a -b
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        String firstname = null;
        String lastname = null;
        Integer age = null;
        Boolean printAll = false;
        if (optionPara.length > 0)
        {
            for (Map.Entry<String, String> entry : options.entrySet())
            {
                switch (entry.getKey())
                {
                    case "-firstname":
                    case "-fn":
                        firstname = entry.getValue();
                        break;
                    case "-lastname":
                    case "-ln":
                        lastname = entry.getValue();
                        break;
                    case "-age":
                    case "-a":
                        age = Integer.valueOf(entry.getValue());
                        break;
                    case "-all":
                        if(entry.getValue() != null)
                            throw new IllegalArgumentException("processSecondParamAfterPerson\n\t-all must not have arguments: " + entry.getKey() + " " + entry.getValue());
                        printAll = true;
                        break;
                    default:
                        throw new IllegalArgumentException("Found illegal argument: " + entry.getKey());
                }
            }
        }

        //Switch to second param
        switch (param[0].toLowerCase())
        {
            case "add":
                personAdd(firstname, lastname, age);
                break;
            case "print":
                personPrint(firstname, lastname, age, printAll);
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    private void processSecondParamAfterSociety(String[] param)
    {
        //Ggf Option parameter processing in future
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);

        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        String[] newParam = cutFirstIndexPositions(param, 1);
        switch (param[0].toLowerCase())
        {
            case "print":
                societyPrint();
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    private void processSecondParamAfterCompany(String[] param)
    {
        //GGF OPtions Processing in future
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        System.out.println("TODO");
    }

    private void processSecondParamAfterGovernment(String[] param)
    {
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Ggf options processing in future
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);

        switch (param[0].toLowerCase())
        {
            case "print":
                System.out.println(government);
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    private void processSecondParamAfterEconomy(String[] param)
    {
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Find Method arguments for further methods
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        Boolean base = false;
        Boolean all = false;
        for (Map.Entry<String, String> entry : options.entrySet())
        {
            switch (entry.getKey())
            {
                case "-base":
                    base = true;
                    if(entry.getValue() != null)
                        throw new IllegalArgumentException("processSecondParamAfterPerson\n\t-base must not have arguments: " + entry.getKey() + " " + entry.getValue());
                    break;
                case "-all":
                    if(entry.getValue() != null)
                        throw new IllegalArgumentException("processSecondParamAfterPerson\n\t-all must not have arguments: " + entry.getKey() + " " + entry.getValue());
                    all = true;
                    break;
                default:
                    throw new IllegalArgumentException("Found illegal argument: " + entry.getKey());
            }
        }

        switch (param[0].toLowerCase())
        {
            case "print":
                enconomyPrint(base, all);
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }

    }

    //OPTIONS
    private void enconomyPrint(Boolean baseData, Boolean AllData)
    {
        if(!baseData && !AllData)
            System.out.println("No arguments given");

        if(baseData)
            System.out.println(economy.economyBaseData());
        if(AllData)
            System.out.println(economy.economyCompanyData());
    }

    private void societyPrint()
    {
        //Analyse Options
        System.out.println(society.getSocietyStatistics().printGeneral());
    }

    private void personPrint(String firstname, String lastname, Integer age, Boolean Printall) throws NumberFormatException
    {

        if (firstname == null && lastname == null && age == null && !Printall)
        {
            System.out.println("No search-parameters given, -all to print all persons");
            return;
        }

        //Iterate all persons and check query
        boolean foundEntry = false;
        for (Person p : society.getPeople())
        {
            if //if query == null => we dont need to check => true
            (
                    (firstname == null || firstname.equals(p.firstname)) &&
                            (lastname == null || lastname.equals(p.lastname)) &&
                            (age == null || age.equals(p.age))
            )
            {
                System.out.println(p);
                foundEntry = true;
            }
        }
        if (!foundEntry)
            System.out.println("No Entries found");
    }

    private void personAdd(String firstname, String lastname, Integer age)
    {
        Person newPerson = new Person(firstname, lastname, age);
        society.addPerson(newPerson);
        System.out.println("Added Person: " + newPerson);
    }

    //Helper
    private String[] cutFirstIndexPositions(String[] input, Integer NumberCutPostions)
    {
        String[] ret = new String[input.length - NumberCutPostions];
        for (int i = 0; i < ret.length; i++)
            ret[i] = input[i + NumberCutPostions];
        return ret;
    }

    private Map<String, String> readOptionParameter(String[] params)
    {
        //Iterate params and create Map
        Map<String, String> results = new HashMap<>();
        String[] residualParams = params;

        while (residualParams.length > 0)
        {
            //Next Token is parameter type
            if (residualParams[0].contains("-"))
            {
                //paramter type with parameter value (-n Wolfgang)
                if (residualParams.length >= 2 && !residualParams[1].contains("-"))
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
                throw new IllegalArgumentException("Invalid option parameter: " + residualParams[0] + " in " + inputString + "\n Correct parameter example: -n name");
            }
        }
        return results;
    }

    //Getter
    public static Interpreter getInterpreter()
    {
        if (instance != null)
            return instance;
        else
        {
            throw new RuntimeException("Interpreter not initialized");
        }
    }

    public static Interpreter getInterpreter(Society soc, Economy eco, Government gov)
    {
        if (instance != null)
            throw new RuntimeException("Interpreter already initialized");
        else
        {
            return new Interpreter(soc, eco, gov);
        }
    }
}
