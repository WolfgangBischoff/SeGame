import java.util.Random;

public enum EducationalLayer
{
    NoEducation, BasicEducation, apprenticeship, HighEducation;

    static Random rand = new Random();
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
