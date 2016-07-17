package jp.co.excite_software.s_ikeda.study;

import static java.util.Calendar.*;

import java.io.PrintStream;
import java.util.GregorianCalendar;

public class CalendarBuilder {

    public static void main(String[] args) {

        CalendarBuilder builder = new CalendarBuilder(System.out);

        builder.outputCalendar(2016, 7);
        builder.outputCalendar(2016, 8);
        builder.outputCalendar(2016, 9);
    }

    /**  */
    private static final int[] WEEK = {
            SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY,
    };
    private static final String [] WEEK_NAME = {
            "日", "月", "火", "水", "木", "金", "土",
    };

    /**  */
    private PrintStream out;

    /**
     * 
     * @param out
     */
    public CalendarBuilder(PrintStream out) {
        this.out = out;
    }

    /**
     * 
     * @param year
     * @param month
     */
    public void outputCalendar(final int year, final int month) {

        GregorianCalendar calendar = new GregorianCalendar (year, month - 1, 1);
        calendar.add(DATE, 1 - calendar.get(DAY_OF_WEEK));

        out.printf("%d年%d月", year, month).println();

        for (int w : WEEK) {
            out.printf(WEEK_NAME[w - 1]);
            out.printf(w == SATURDAY ? "" : " ");
        }
        out.println();

        while (true) {
            for (int w : WEEK) {
                int m = calendar.get(MONTH) + 1;
                int d = calendar.get(DAY_OF_MONTH);

                if (m == month) {
                    out.printf(w == SUNDAY ? "" : " ");
                    out.printf("%2d", d);
                }
                else {
                    out.printf(w == SUNDAY ? "  " : "   ");
                }

                calendar.add(DATE, 1);
            }

            out.println();

            if (calendar.get(MONTH) + 1 != month) {
                break;
            }
        }
        out.println();
    }
}
