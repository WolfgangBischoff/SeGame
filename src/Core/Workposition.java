package Core;

public class Workposition
{
    EducationalLayer neededEducation;
    Paygrade paygrade;
    Person worker = null;
    Company company;

    public Workposition(Company company, EducationalLayer edu)
    {
        this.company = company;
        this.neededEducation = edu;
        setPaygrade();
    }

    public boolean isWorkerAppropriate(Person worker)
    {
        if (neededEducation.getInt() <= worker.educationalLayer.getInt() && worker.worksAt == null)
            return true;
        else
            return false;
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
