package Core;

import java.util.HashMap;
import java.util.Map;

/*
Existing Instructions:
Person print -firstname -lastname -age -all
Person add -firstname -lastname -age
economy print -companies
society print -income
government print
company print -name -all
company pay -name -all
test cash

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

    public boolean readInstruction(String input)
    {
        inputString = input;
        //String[] param = inputParam.split(" ");
        String[] param = input.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?");//split along whitespaces, but respects quotation marks "two strings"
        try
        {
            processFirstParam(param);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("In readInstruction()\n\t" + e.getMessage() + "\nInput: " + inputString);
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
            case "comp":
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
            case "test":
                processSecondParamAfterTest(newParam);
                break;

            case "help":
            case "?":
                printGeneralHelp(); break;

            case "quit":
            case "end":
                returnRun = false;
                break;
            default:
                throw new IllegalArgumentException("In processFirstParam\nArgument: " + param[0] + " not known, from\n >>>" + inputString);
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
                    case "-name":
                    case "-n":
                        //-name "Hans von Hohenzollern" => Hans - von Hohenzollern
                        PersonName name = new PersonName(entry.getValue());
                        firstname = name.getFirstname();
                        lastname = name.getLastname();
                        break;
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
                        if (entry.getValue() != null)
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
                personAdd(firstname, lastname);
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
                            throw new IllegalArgumentException("In processSecondParamAfterSociety\n\t-all must not have arguments: " + entry.getKey() + " " + entry.getValue());
                        printIncomeStat = true;
                        break;
                    default:
                        throw new IllegalArgumentException("Found illegal argument: " + entry.getKey());
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
                throw new IllegalArgumentException("In processSecondParamAfterSociety\n\tArgument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    private void processSecondParamAfterCompany(String[] param)
    {
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
        //Company identyfiedCompany = null;
        if (optionPara.length > 0)
        {
            for (Map.Entry<String, String> entry : options.entrySet())
            {
                switch (entry.getKey())
                {
                    case "-all":
                        if (entry.getValue() != null)
                            throw new IllegalArgumentException("processSecondParamAfterCompany\n\t-all must not have arguments: " + entry.getKey() + " " + entry.getValue());
                        allCompanies = true;
                        break;

                    case "-name":
                        companyName = entry.getValue();
                        break;

                    default:
                        throw new IllegalArgumentException("Found illegal argument: " + entry.getKey());
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
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }
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
                        throw new IllegalArgumentException("processSecondParamAfterPerson\n\t-all must not have arguments: " + entry.getKey() + " " + entry.getValue());
                    printCompanies = true;
                    break;
                default:
                    throw new IllegalArgumentException("Found illegal argument: " + entry.getKey());
            }
        }

        switch (param[0].toLowerCase())
        {
            case "print":
                enconomyPrint(printCompanies);
                break;
            default:
                throw new IllegalArgumentException("Argument: " + param[0] + " not known, from\n >>>" + inputString);
        }

    }

    private void  processSecondParamAfterTest(String[] param)
    {
        //Just: test
        if (param.length == 0)
        {
            System.out.println("Further arguments needed");
            return;
        }

        //Find Method arguments for further methods
        String[] optionPara = cutFirstIndexPositions(param, 1);
        Map<String, String> options = readOptionParameter(optionPara);
        //Boolean checkAmountCash = false;
        for (Map.Entry<String, String> entry : options.entrySet())
        {
            switch (entry.getKey())
            {

                default:
                    throw new IllegalArgumentException("In processSecondParamAfterTest\n" + "Found illegal option: " + entry.getKey());
            }
        }

        switch (param[0].toLowerCase())
        {
            case "cash":
                test();
                break;
            default:
                throw new IllegalArgumentException("In processSecondParamAfterTest\nArgument: " + param[0] + " not known, from\n >>>" + inputString);
        }
    }

    //OPTIONS
    private void test()
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
                    (firstname == null || firstname.equals(p.name.getFirstname())) &&
                            (lastname == null || lastname.equals(p.name.getLastname())) &&
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

    private void personAdd(String firstname, String lastname)
    {
        Person newPerson = new Person(firstname, lastname);
        society.addPerson(newPerson);
        System.out.println("Added Person: " + newPerson);
    }

    private void companyPay(String compName, Boolean all)
    {
        //Check if company exists
        Company comp = null;
        if(compName != null)
        {
            comp = economy.getCompanyByName(compName);
            if(comp == null)
            {
                System.out.println("No Company found with name: " + compName);
                return;
            }
        }

        if(comp != null && all)
        {
            System.out.println("Ambiguous command, don`t use a special company and -all companies");
            return;
        }

        //One company pays
        if(comp != null && !all)
        {
            comp.paySalaries();
            System.out.println(comp.getName() + " payed salaries");
        }

        //All companies pay
        if(comp == null && all)
        {
            economy.companiesPaySalary();
            System.out.println("Companies payed salaries");
        }
    }

    private void companyPrint(String compName, Boolean printAllCompanies)
    {
        //Check if company exists
        Company comp = null;
        if(compName != null)
        {
            comp = economy.getCompanyByName(compName);
            if(comp == null)
            {
                System.out.println("No Company found with name: " + compName);
                return;
            }
        }

        if(!printAllCompanies && comp == null)
            System.out.println("Specify company. -all for printing all companies");

        if(printAllCompanies)
            System.out.println(economy.economyBaseCompanyData());

        if(comp != null)
            System.out.println(comp.baseData());
    }

    private void companyAdd(String companyName)
    {
        economy.addCompanyByName(companyName);
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
        //Iterate params and create Map
        Map<String, String> results = new HashMap<>();
        String[] residualParams = params;

        while (residualParams.length > 0)
        {
            //Next Token is parameter type
            if (residualParams[0].contains("-"))
            {
                //TODO Case "Multiple Words are one Parameter" and "Word"



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

    private String[] splitInstruction(String input)
    {
        //Case company add "Multiple Words are one Parameter"
        //Case company add "Word"
        String[] ret = null;





        return ret;

    }

    //Helptext
    private void printGeneralHelp()
    {
        System.out.println(
                "Existing Instructions:\n" +
                "Person print -firstname -lastname -age -all\n" +
                "Person add -firstname -lastname -age\n" +
                "economy print -companies\n" +
                "society print -income\n" +
                "government print"
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
