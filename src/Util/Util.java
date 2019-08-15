package Util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

public class Util
{
    //Society
    public static final int NUMBER_PERSONS = 12;
    public static final Integer RATION_BASIC_EDU = 20;
    public static final Integer RATION_APP_EDU = 35;
    public static final Integer RATION_HIGHER_EDU = 30;
    public static final Integer RATION_UNIVERSITY_EDU = 15;

    //Person
    public static final int INIT_BASE_HAPPINESS = 100;
    public static final int THRESHOLD_VERY_POOR = 1000;
    public static final int THRESHOLD_POOR = 1400;
    public static final int THRESHOLD_MEDIUM = 2000;
    public static final int THRESHOLD_RICH = 3500;

    //Company
    public static final Integer NUM_WORKPLACES_DEFAULT = 10;
    public static final int RATIO_NEEDED_BASE_EDU = 15;
    public static final int RATIO_NEEDED_APPR_EDU = 40;
    public static final int RATIO_NEEDED_HIGH_EDU = 40;
    public static final int RATIO_NEEDED_UNIV_EDU = 5;

    //Economy
    public static final int NUMBER_COMPANIES = 1;

    //Government

    //Random
    static Random rand = new Random();
    public static Random getRandom()
    {
        return rand;
    }

    //Round
    public static Double roundTwoDigits(Double input)
    {
        DecimalFormatSymbols point = new DecimalFormatSymbols();
        point.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", point);
        return Double.valueOf(df.format(input));
    }

}
