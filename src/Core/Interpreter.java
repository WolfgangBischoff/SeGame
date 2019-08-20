package Core;

import java.util.HashMap;
import java.util.Map;

public class Interpreter
{
    private static Interpreter instance = null;
    private Society society;
    private Economy economy;
    private Government government;

    private boolean run = true;

    //Constructors
    private Interpreter(Society soc, Economy eco, Government gov)
    {
        society = soc;
        economy = eco;
        government = gov;
    }

    public boolean readInstruction(String input)
    {
        //inputString = input;
        String[] param = input.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?");//split along whitespaces, but respects quotation marks "two strings"

        try
        {
            processFirstParam(param);
        } catch (IllegalArgumentException e)
        {
            System.out.println("In readInstruction()\n\t" + e.getMessage());
        }
        return run;
    }

    private String normalizeParameter(String rawInput)
    {
        String methodName = "normalizeParameter";
        switch (rawInput.toLowerCase())
        {
            case "person":
            case "per":
                return "person";
            case "society":
            case "soc":
                return "society";
            case "company":
            case "comp":
            case "com":
                return "company";
            case "government":
            case "gov":
                return "government";
            case "economy":
            case "eco":
                return "economy";
            case "test":
                return "test";
            case "help":
            case "?":
                return "help";
            case "quit":
            case "end":
                return "quit";
            default:
                throw new IllegalArgumentException(methodName + ": Command not known \"" + rawInput + "\"");
        }
    }

    private String normalizeOption(String inputString)
    {
        String methodName = "normalizeOption";

        switch (inputString.toLowerCase())
        {
            case "-name":
            case "-n":
                return "-name";
            case "-firstname":
            case "-fn":
                return "-firstname";
            case "-lastname":
            case "-ln":
                return "-lastname";
            case "-age":
                return "-age";
            case "-all":
            case "-a":
                return "-all";
            case "-income":
            case "-inc":
                return "-income";
            case "-companies":
            case "-comp":
            case "-c":
                return "-companies";
            default:
                throw new InterpreterUndefindedOptionException(methodName, inputString);
        }
    }

    //FIRST INSTRUCTION
    private void processFirstParam(String[] inputParameter)
    {
        String methodName = "processFirstParam";
        String parameter = normalizeParameter(inputParameter[0]);
        String[] newParam = cutFirstIndexPositions(inputParameter, 1);
        switch (parameter)
        {
            case "person":
                processSecondParamAfterPerson(newParam);
                break;
            case "society":
                processSecondParamAfterSociety(newParam);
                break;
            case "company":
                processSecondParamAfterCompany(newParam);
                break;
            case "government":
                processSecondParamAfterGovernment(newParam);
                break;
            case "economy":
                processSecondParamAfterEconomy(newParam);
                break;
            case "test":
                processSecondParamAfterTest(newParam);
                break;
            case "help":
                printGeneralHelp();
                break;
            case "quit":
                run = false;
                break;
            default:
                throw new InterpreterUndefindedOptionException(methodName, parameter);
        }

    }

    //SECOND INSTRUCTION
    private void processSecondParamAfterPerson(String[] inputParameter)
    {
        String methodName = "processSecondParamAfterPerson";
        String[] newParam = cutFirstIndexPositions(inputParameter, 1);

        //just "person"
        if (inputParameter.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //TODO Just use second param here; send to successor method with residual param
        //Switch to second param
        switch (inputParameter[0].toLowerCase())
        {
            case "add":
                personAdd(newParam);
                break;
            case "print":
                personPrint(newParam);
                break;
            default:
                throw new InterpreterUndefindedOptionException(methodName, inputParameter[0].toLowerCase());
        }
    }

    private void processSecondParamAfterSociety(String[] inputParameter)
    {
        String methodName = "processSecondParamAfterSociety";
        String[] newParam = cutFirstIndexPositions(inputParameter, 1);

        //Just: society
        if (inputParameter.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        switch (inputParameter[0].toLowerCase())
        {
            case "print":
                societyPrint(newParam);
                break;
            case "calc":
                societyCalc();
                break;
            default:
                throw new InterpreterUndefindedOptionException(methodName, inputParameter[0]);
        }
    }

    private void processSecondParamAfterCompany(String[] inputParameters)
    {
        String methodName = "processSecondParamAfterCompany";
        String[] optionPara = cutFirstIndexPositions(inputParameters, 1);
        //Just: company
        if (inputParameters.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Goto second parameter like "print"
        switch (inputParameters[0].toLowerCase())
        {
            case "print":
                companyPrint(optionPara);
                break;
            case "pay":
                companyPay(optionPara);
                break;
            case "add":
                companyAdd(optionPara);
                break;

            default:
                throw new InterpreterUndefindedOptionException(methodName, inputParameters[0].toLowerCase());
        }
    }

    private void processSecondParamAfterGovernment(String[] inputParameters)
    {
        String methodName = "processSecondParamAfterGovernment";
        String[] optionPara = cutFirstIndexPositions(inputParameters, 1);
        if (inputParameters.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        switch (inputParameters[0].toLowerCase())
        {
            case "print":
                System.out.println(government);
                break;
            default:
                throw new InterpreterUndefindedOptionException(methodName, inputParameters[0]);
        }
    }

    private void processSecondParamAfterEconomy(String[] inputParameters)
    {
        String methodName = "processSecondParamAfterEconomy";
        String[] optionPara = cutFirstIndexPositions(inputParameters, 1);
        //Just: Economy
        if (inputParameters.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        switch (inputParameters[0].toLowerCase())
        {
            case "print":
                enconomyPrint(optionPara);
                break;
            default:
                throw new InterpreterUndefindedOptionException(methodName, inputParameters[0]);
        }

    }

    private void processSecondParamAfterTest(String[] inputParameters)
    {
        String methodName = "processSecondParamAfterTest";
        String[] optionPara = cutFirstIndexPositions(inputParameters, 1);

        //Just: test
        if (inputParameters.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        switch (inputParameters[0].toLowerCase())
        {
            case "cash":
                testCash();
                break;
            default:
                throw new InterpreterUndefindedOptionException(methodName, inputParameters[0].toLowerCase());
        }
    }

    //OPTIONS
    private void personAdd(String[] inputOptions)
    {
        String methodname = "personAdd()";
        Map<String, String> options = readOptionParameter(inputOptions);
        //Case no options
        if (options.size() == 0)
        {
            System.out.println("No Person specified. Did you forget -name or -all?");
            return;
        }

        //case name
        if (options.containsKey("-name"))
        {
            Person newPerson = new Person(new PersonName(options.get("-name")));
            society.addPerson(newPerson);
            System.out.println("Added Person: " + newPerson);
            return;
        }

        //case first and lastname
        if (options.containsKey("-firstname") && options.containsKey("-lastname"))
        {
            PersonName name = new PersonName(options.get("-firstname"), options.get("-lastname"));
            Person newPerson = new Person(name);
            society.addPerson(newPerson);
            System.out.println("Added Person: " + newPerson);
            return;
        }

        throw new InterpreterInvalidOptionCombination(methodname, inputOptions);
    }

    private void personPrint(String[] inputOptions)
    {
        String methodname = "personPrint()";
        Map<String, String> options = readOptionParameter(inputOptions);
        //Case no options
        if (options.size() == 0)
        {
            System.out.println("No Person specified. Did you forget -name or -all?");
            return;
        }
        //case all persons
        if (options.containsKey("-all"))
        {
            System.out.println(society.printSocPeople());
            return;
        }
        //case some persons
        //TODO implement search function in Society and then use all options
        if (options.containsKey("-name"))
        {
            for (Person person : society.getPeople())
            {
                if (person.name.equals(new PersonName(options.get("-name"))))
                    System.out.println(person);
            }
        }

        throw new InterpreterInvalidOptionCombination(methodname, inputOptions);
    }

    private void societyPrint(String[] inputOptions)
    {
        String methodname = "personPrint()";
        Map<String, String> options = readOptionParameter(inputOptions);
        //Case no options
        if (options.size() == 0)
            System.out.println(society.getSocietyStatistics().printBase());

        if (options.containsKey("-income"))
            System.out.println(society.getSocietyStatistics().printIncomeStat());
    }

    private void societyCalc()
    {
        society.calcSociety();
    }

    private void enconomyPrint(String[] inputOptions)
    {
        String methodname = "economyPrint()";
        Map<String, String> options = readOptionParameter(inputOptions);

        //Case no options
        if (options.size() == 0)
        {
            System.out.println(economy.economyBaseData());
        }
        if (options.containsKey("-companies"))
            System.out.println(economy.getCompanies());

    }

    private void testCash()
    {
        String methodname = "testCash()";
        //Map<String, String> options = readOptionParameter(inputOptions);

        Integer depPeople = society.getSocietyStatistics().depositSumPeople;
        Integer depComp = economy.getEconomyStatistics().calcSumCompanyDeposits();
        Integer depGov = government.getDeposit();
        System.out.println("Society: " + depPeople + "\nCompanies: " + depComp + "\nGovernment: " + depGov + "\nSum: " + (depPeople + depGov + depComp));
    }

    private void companyPay(String[] inputOptions)
    {
        String methodname = "companyAdd()";
        Map<String, String> options = readOptionParameter(inputOptions);

        //Case no options
        if (options.size() == 0)
        {
            System.out.println("No Company specified. Did you forget -name?");
            return;
        }

        //Case all companies
        if (options.containsKey("-all"))
        {
            for (Company company : economy.getCompanies())
                System.out.println(company);
            return;
        }

        //Case name given
        if (options.containsKey("-name"))
        {
            economy.getCompanyByName(options.get("-name"));
            return;
        }

        throw new InterpreterInvalidOptionCombination(methodname, inputOptions);
    }

    private void companyPrint(String[] inputOptions)
    {
        String methodname = "companyAdd()";
        Map<String, String> options = readOptionParameter(inputOptions);

        //Case no options
        if (options.size() == 0)
        {
            System.out.println("No Company specified. Did you forget -name?");
            return;
        }

        //Case print all companies
        if (options.containsKey("-all"))
        {
            System.out.println(economy.economyBaseCompanyData());
            return;
        }

        //Case print company with name
        if (options.containsKey("-name"))
        {
            System.out.println(economy.getCompanyByName(options.get("-name")));
            return;
        }

        throw new InterpreterInvalidOptionCombination(methodname, inputOptions);
    }

    private void companyAdd(String[] inputOptions)
    {
        String methodname = "companyAdd()";
        Map<String, String> options = readOptionParameter(inputOptions);

        //Case no options
        if (options.size() == 0)
        {
            System.out.println("No Company specified. Did you forget -name?");
            return;
        }

        //Case name given
        if (options.containsKey("-name"))
        {
            System.out.println(economy.addCompanyByName(options.get("-name")));
            return;
        }

        throw new InterpreterInvalidOptionCombination(methodname, inputOptions);

    }

    //Util
    private String[] cutFirstIndexPositions(String[] input, Integer NumberCutPostions)
    {
        String[] ret = new String[input.length - NumberCutPostions];
        for (int i = 0; i < ret.length; i++)
            ret[i] = input[i + NumberCutPostions];
        return ret;
    }

    private Map<String, String> readOptionParameter(String[] params)
    {
        String methodname = "readOptionParameter()";

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
                    results.put(normalizeOption(residualParams[0]), residualParams[1]);
                    residualParams = cutFirstIndexPositions(residualParams, 2);
                }
                else
                //just parameter type (-all)
                {
                    results.put(normalizeOption(residualParams[0]), null);
                    residualParams = cutFirstIndexPositions(residualParams, 1);
                }
            }
            else
            {
                throw new InterpreterUndefindedOptionException(methodname, residualParams[0]);
            }
        }
        return results;
    }

    //Helptext
    private void printGeneralHelp()
    {

        System.out.println(
                "Existing Instructions:\n" +
                        "Person print -name -all\n" +
                        "Person add -name -firstname -lastname\n" +
                        "economy print -companies\n" +
                        "society print -income\n" +
                        "government print\n" +
                        "company print -name -all\n" +
                        "company pay -name -all\n" +
                        "testCash cash\n"
        );
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
