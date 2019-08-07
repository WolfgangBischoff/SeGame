package Core;

public class Government
{
    static Government singleton = null;
    Integer balance = 0;
    PoliticalOpinion rulingParty = null;
    Integer INCOME_TAX_RATE = 30;

    public static Government getGoverment()
    {
        if(singleton == null)
            singleton = new Government();
        return singleton;
    }

    public Integer calcTaxPercentage(Integer base, Integer perc)
    {
        return (base * perc) / 100;
    }

    public void raiseIncomeTax(Integer amount)
    {
        balance += amount;
    }
}
