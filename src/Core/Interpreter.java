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
    private String inputString = null;
    private Map<String, String> normalizedParams;

    //Constructors
    private Interpreter(Society soc, Economy eco, Government gov)
    {
        society = soc;
        economy = eco;
        government = gov;
    }

    public boolean readInstruction(String input)
    {
        inputString = input;
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

   /* private String[] normalizeParams(String[] rawParams)
    {
        String[] normalizedParams = new String[rawParams.length];
        int idx = 0;

        //Normalize Params (will terminate after first option like -all
        do
        {
            normalizedParams[idx] = normalizeParameter(rawParams[idx]);
        }
        while(!rawParams[idx].startsWith("-"));

        //Normalize Options
        for (; idx<rawParams.length; idx++)
        {

        }

        return normalizedParams;
    }*/

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
                return "society";
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
            default:
                throw new UndefindedOptionException(methodName, inputString);
        }
    }

    //FIRST INSTRUCTION
    private void processFirstParam(String[] inputParameter) throws IllegalArgumentException
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
                throw new IllegalArgumentException("In " + methodName + "\nArgument: \"" + inputParameter[0] + "\" undefined");
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
                throw new IllegalArgumentException("In " + methodName + "\nArgument: " + inputParameter[0] + " not known");
        }
    }

    private void processSecondParamAfterSociety(String[] param)
    {
        String methodName = "processSecondParamAfterSociety";
        //Just: society
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Fill possible option parameter like person ??? -fn Wolfgang -a -b
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        Boolean printIncomeStat = false;
        if (optionPara.length > 0)
        {
            for (Map.Entry<String, String> entry : options.entrySet())
            {
                switch (entry.getKey())
                {
                    case "-income":
                    case "-inc":
                        if (entry.getValue() != null)
                            throw new NoParametersAlloweException(methodName, entry.getValue());
                        printIncomeStat = true;
                        break;
                    default:
                        throw new UndefindedOptionException(methodName, entry.getKey());
                }
            }
        }

        switch (param[0].toLowerCase())
        {
            case "print":
                societyPrint(printIncomeStat);
                break;
            case "calc":
                societyCalc();
                break;
            default:
                throw new IllegalArgumentException("In " + methodName + "\nArgument: " + param[0] + " not known");
        }
    }

    private void processSecondParamAfterCompany(String[] param)
    {
        String methodName = "processSecondParamAfterCompany";
        //Just: company
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Fill possible option parameter like person ??? -fn Wolfgang -a -b
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        Boolean allCompanies = false;
        String companyName = null;
        if (optionPara.length > 0)
        {
            for (Map.Entry<String, String> entry : options.entrySet())
            {
                switch (entry.getKey())
                {
                    case "-all":
                        if (entry.getValue() != null)
                            throw new NoParametersAlloweException(methodName, entry.getValue());
                        allCompanies = true;
                        break;

                    case "-name":
                        companyName = entry.getValue();
                        break;

                    default:
                        throw new UndefindedOptionException(methodName, entry.getKey());
                }
            }
        }

        //Goto second parameter like "print"
        switch (param[0].toLowerCase())
        {
            case "print":
                companyPrint(companyName, allCompanies);
                break;
            case "pay":
                companyPay(companyName, allCompanies);
                break;
            case "add":
                companyAdd(companyName);
                break;

            default:
                throw new IllegalArgumentException("In " + methodName + "\nArgument: " + param[0] + " not known");
        }
    }

    private void processSecondParamAfterGovernment(String[] param)
    {
        String methodName = "processSecondParamAfterGovernment";
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
                throw new IllegalArgumentException("In " + methodName + "\nArgument: " + param[0] + " not known");
        }
    }

    private void processSecondParamAfterEconomy(String[] param)
    {
        String methodName = "processSecondParamAfterEconomy";
        //Just: Economy
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Find Method arguments for further methods
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        Boolean printCompanies = false;
        for (Map.Entry<String, String> entry : options.entrySet())
        {
            switch (entry.getKey())
            {
                case "-companies":
                case "-comp":
                case "-c":
                    if (entry.getValue() != null)
                        throw new NoParametersAlloweException(methodName, entry.getValue());
                    printCompanies = true;
                    break;
                default:
                    throw new UndefindedOptionException(methodName, entry.getKey());
            }
        }

        switch (param[0].toLowerCase())
        {
            case "print":
                enconomyPrint(printCompanies);
                break;
            default:
                throw new IllegalArgumentException("In " + methodName + "\nArgument: " + param[0] + " not known");
        }

    }

    private void processSecondParamAfterTest(String[] param)
    {
        String methodName = "processSecondParamAfterTest";
        //Just: testCash
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Find Method arguments for further methods
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        for (Map.Entry<String, String> entry : options.entrySet())
        {
            switch (entry.getKey())
            {

                default:
                    throw new UndefindedOptionException(methodName, entry.getKey());
            }
        }

        switch (param[0].toLowerCase())
        {
            case "cash":
                testCash();
                break;
            default:
                throw new IllegalArgumentException("In " + methodName + "\nArgument: " + param[0] + " not known");
        }
    }

    //OPTIONS
    private void testCash()
    {

        Integer depPeople = society.getSocietyStatistics().depositSumPeople;
        Integer depComp = economy.getEconomyStatistics().calcSumCompanyDeposits();
        Integer depGov = government.getDeposit();
        System.out.println("Society: " + depPeople + "\nCompanies: " + depComp + "\nGovernment: " + depGov + "\nSum: " + (depPeople + depGov + depComp));


    }

    private void enconomyPrint(Boolean AllData)
    {
        System.out.println(economy.economyBaseData());
        if (AllData)
            System.out.println(economy.economyBaseCompanyData());
    }

    private void societyPrint(Boolean printIncomeStats)
    {
        //Analyse Options
        System.out.println(society.getSocietyStatistics().printBase());
        if (printIncomeStats)
            System.out.println(society.getSocietyStatistics().printIncomeStat());
    }

    private void societyCalc()
    {
        society.calcSociety();
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
        if(options.containsKey("-name"))
        {
            for (Person person : society.getPeople())
            {
                if(person.name.equals(new PersonName(options.get("-name"))))
                    System.out.println(person);
            }
        }

        System.out.println(methodname + " option parameter invalid");
    }

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
        if(options.containsKey("-name"))
        {
            Person newPerson = new Person(new PersonName(options.get("-name")));
            society.addPerson(newPerson);
            System.out.println("Added Person: " + newPerson);
            return;
        }

        //case first and lastname
        if(options.containsKey("-firstname") && options.containsKey("-lastname"))
        {
            PersonName name = new PersonName(options.get("-firstname"), options.get("-lastname"));
            Person newPerson = new Person(name);
            society.addPerson(newPerson);
            System.out.println("Added Person: " + newPerson);
            return;
        }

        System.out.println(methodname + " option parameter invalid");
    }

    private void companyPay(String compName, Boolean all)
    {
        //Check if company exists
        Company comp = null;
        if (compName != null)
        {
            comp = economy.getCompanyByName(compName);
            if (comp == null)
            {
                System.out.println("No Company found with name: " + compName);
                return;
            }
        }

        if (comp != null && all)
        {
            System.out.println("Ambiguous command, don`t use a special company and -all companies");
            return;
        }

        //One company pays
        if (comp != null && !all)
        {
            comp.paySalaries();
            System.out.println(comp.getName() + " payed salaries");
        }

        //All companies pay
        if (comp == null && all)
        {
            economy.companiesPaySalary();
            System.out.println("Companies payed salaries");
        }
    }

    private void companyPrint(String compName, Boolean printAllCompanies)
    {
        //Check if company exists
        Company comp = null;
        if (compName != null)
        {
            comp = economy.getCompanyByName(compName);
            if (comp == null)
            {
                System.out.println("No Company found with name: " + compName);
                return;
            }
        }

        if (!printAllCompanies && comp == null)
            System.out.println("Specify company. -all for printing all companies");

        if (printAllCompanies)
            System.out.println(economy.economyBaseCompanyData());

        if (comp != null)
            System.out.println(comp.baseData());
    }

    private void companyAdd(String companyName)
    {
        System.out.println(economy.addCompanyByName(companyName));
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
                throw new UndefindedOptionException(methodname, residualParams[0]);
            }
        }
        return results;
    }


    //Helptext
    private void printGeneralHelp()
    {

        System.out.println(
                "Existing Instructions:\n" +
                        "Person print -name -firstname -lastname -age -all\n" +
                        "Person add -name -firstname -lastname -age\n" +
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
