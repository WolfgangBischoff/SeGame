package Core;

public class Government
{
    static Government singleton = null;
    Integer balance = 0;
    PoliticalOpinion rulingParty = PoliticalOpinion.Unpolitical;
    static Integer INCOME_TAX_RATE = 30;

    public static Government getGoverment()
    {
        if(singleton == null)
            singleton = new Government();
        return singleton;
    }

    @Override
    public String toString() {
        return "Government{" +
                "balance=" + balance +
                ", rulingParty=" + rulingParty +
                '}';
    }

    public static Integer CalcIncomeTax(Integer base)
    {
        return (base * INCOME_TAX_RATE) / 100;
    }

    public void raiseIncomeTax(Integer amount)
    {
        balance += amount;
    }
}
