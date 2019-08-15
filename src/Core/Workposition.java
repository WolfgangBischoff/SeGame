package Core;

public class Workposition
{
    EducationalLayer neededEducation;
    Paygrade paygrade;
    Integer grossIncomeWork = 0;
    Integer netIncomeWork = 0;
    Person worker = null;
    Company company;

    public Workposition(Company company, EducationalLayer edu)
    {
        this.company = company;
        this.neededEducation = edu;
        setPaygrade();
        setIncome();
        //setGrossIncomeWorkOnPaygrade();
    }

    void setIncome()
    {
        setGrossIncomeWorkOnPaygrade();
        setNetIncome();
    }
    void setNetIncome()
    {
        Integer tax = Government.CalcIncomeTax(grossIncomeWork);
        netIncomeWork = grossIncomeWork - tax;
        Government.getGoverment().raiseIncomeTax(tax);
    }

    void setGrossIncomeWorkOnPaygrade()
    {
        switch (paygrade)
        {
            case A: grossIncomeWork = 1000; break;
            case B: grossIncomeWork = 1300; break;
            case C: grossIncomeWork = 1600;break;
            case D: grossIncomeWork = 2000;break;
            case E: grossIncomeWork = 3000;break;
            case F: grossIncomeWork = 6000;break;
            default: grossIncomeWork = 0;break;
        }
    }

    public boolean isWorkerAppropriate(Person worker)
    {
        return neededEducation.getInt() <= worker.educationalLayer.getInt() && worker.worksAt == null;
    }

    @Override
    public String toString()
    {
        return "\nWorkposition{" +
                "neededEducation=" + neededEducation +
                ", worker=" + worker +
                '}';
    }

    public void setPaygrade()
    {
        this.paygrade = Paygrade.fromInt(neededEducation.getInt());
    }

    public void setWorker(Person worker)
    {
        this.worker = worker;
    }
}
