import java.util.Random;

public enum EducationalLayer
{
    NoEducation(0), BasicEducation(1), apprenticeship(2), HighEducation(3);

    private int nr;
    static Random rand = new Random();

    EducationalLayer(int nr)
    {
        this.nr = nr;
    }

    public int getNr()
    {
        return nr;
    }

    static EducationalLayer getRandomEdu()
    {
        return fromInt(rand.nextInt(4));
    }

    static EducationalLayer fromInt(int n)
    {
        switch(n) {
            case 0:
                return NoEducation;
            case 1:
                return BasicEducation;
            case 2:
                    return apprenticeship;
            case 3:
                    return HighEducation;
            default:
                return null;
        }
    }
}
