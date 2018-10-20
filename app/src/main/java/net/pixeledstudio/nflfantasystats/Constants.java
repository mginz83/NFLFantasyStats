package net.pixeledstudio.nflfantasystats;

/**
 * Created by mattginsberg on 10/19/18.
 */

public class Constants {

    final public static String NFL_SCORES_BY_WEEK_API = "http://www.nfl.com/ajax/scorestrip?season=2018&seasonType=REG&week=";

    public static String convertWeekFromSpinner(String week) {
        String[] splitWeek = week.split("\\s+");
        return splitWeek[1];
    }

}
