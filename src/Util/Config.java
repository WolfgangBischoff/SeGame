package Util;

import java.util.Random;

public class Config
{
    //Society
    public static final int NUMBER_PERSONS = 1000;
    public static final Integer RATION_BASIC_EDU = 20;
    public static final Integer RATION_APP_EDU = 35;
    public static final Integer RATION_HIGHER_EDU = 30;
    public static final Integer RATION_UNIVERSITY_EDU = 15;

    //Person
    public static final int MIN_GROSS_INCOME_BASE_EDU = 800;
    public static final int MIN_GROSS_INCOME_APPRENTICE_EDU = 1300;
    public static final int MIN_GROSS_INCOME_HIGHER_EDU = 1900;
    public static final int MIN_GROSS_INCOME_UNIVERSITY_EDU = 2500;

    public static final int SPAN_GROSS_INCOME_BASE_EDU = 600;
    public static final int SPAN_GROSS_INCOME_APPRENTICE_EDU = 700;
    public static final int SPAN_GROSS_INCOME_HIGHER_EDU = 900;
    public static final int SPAN_GROSS_INCOME_UNIVERSITY_EDU = 2200;

    public static final int THRESHOLD_VERY_POOR = 1000;
    public static final int THRESHOLD_POOR = 1400;
    public static final int THRESHOLD_MEDIUM = 2000;
    public static final int THRESHOLD_RICH = 3500;

    //Random
    static Random rand = new Random();
    public static Random getRandom()
    {
        return rand;
    }
}
