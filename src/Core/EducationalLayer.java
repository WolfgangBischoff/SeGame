package Core;

import java.util.Random;
import static Util.Config.*;

public enum EducationalLayer
{
    BASE(0), APPRENTICESHIP(1), HIGHER(2), UNIVERSITY(3);

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
    static EducationalLayer getRatioEdu(Integer base, Integer app, Integer high, Integer university)
    {
      return null;
    }

    static EducationalLayer fromInt(int n)
    {
        switch(n) {
            case 0:
                return BASE;
            case 1:
                return APPRENTICESHIP;
            case 2:
                    return HIGHER;
            case 3:
                    return UNIVERSITY;
            default:
                return null;
        }
    }
}
