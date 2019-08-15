package Core;

public enum EducationalLayer
{
    EDU_BASE(0), EDU_APPRENTICESHIP(1), EDU_HIGHER(2), EDU_UNIVERSITY(3);

    private int IntNr;

    EducationalLayer(int nr)
    {
        this.IntNr = nr;
    }

    public int getInt()
    {
        return IntNr;
    }

    static EducationalLayer fromInt(int n)
    {
        switch(n) {
            case 0:
                return EDU_BASE;
            case 1:
                return EDU_APPRENTICESHIP;
            case 2:
                    return EDU_HIGHER;
            case 3:
                    return EDU_UNIVERSITY;
            default:
                return null;
        }
    }
}
