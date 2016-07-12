package jp.co.excite_software.s_ikeda.study;

import static java.util.Calendar.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.GregorianCalendar;

public class CalendarBuilder {

    /**
     * カレンダー出力処理を実装するためのインターフェイスです。
     */
    public interface CalendarOutput {

        /**
         * 年月の出力処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         * @param year 年
         * @param month 月
         */
        public void outputYearAndMonth(PrintStream out, int year, int month);

        /**
         * 曜日ヘッダの出力処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         * @param dayOfWeek 曜日 {@link java.util.Calendar.DAY_OF_WEEK}
         */
        public void outputWeekHeader(PrintStream out, int dayOfWeek);

        /**
         * 日の出力処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         * @param year 年
         * @param month 月
         * @param dayOfMonth 日
         * @param dayOfWeek 曜日 {@link java.util.Calendar.DAY_OF_WEEK}
         * @param monthOffset 指定した月ならば 0、前の月なら -1 次の月なら 1
         */
        public void outputDate(PrintStream out, int year, int month, int dayOfMonth, int dayOfWeek, int monthOffset);

        /**
         * 月表示の開始処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         */
        public void outputOpenMonth(PrintStream out);

        /**
         * 月表示の終了処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         */
        public void outputCloseMonth(PrintStream out);

        /**
         * 週ヘッダ表示の開始処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         */
        public void outputOpenWeekHeader(PrintStream out);

        /**
         * 週ヘッダ表示の終了処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         */
        public void outputCloseWeekHeader(PrintStream out);

        /**
         * 週表示の開始処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         */
        public void outputOpenWeek(PrintStream out);

        /**
         * 週表示の終了処理を実装してください。
         * 
         * @param out このPrintSreamに対して出力してください
         */
        public void outputCloseWeek(PrintStream out);
    }

    /** System.lineSeparator() */
    private static final String LS = System.lineSeparator();
    /**  */
    private static final int[] WEEK = {
            SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY,
    };

    /**
     * 
     * @param year
     * @param month
     * @param output
     * @return
     * @throws IOException 
     */
    public static String build(final int year, final int month, CalendarOutput output) throws IOException {

        try (PipedInputStream pis = new PipedInputStream();
             PipedOutputStream pos = new PipedOutputStream();
             PrintStream out = new PrintStream(pos);) {

            pis.connect(pos);

            output.outputYearAndMonth(out, year, month);
            output.outputOpenWeekHeader(out);

            for (int w : WEEK) {
                output.outputWeekHeader(out, w);
            }
            output.outputCloseWeekHeader(out);

            GregorianCalendar calendar = new GregorianCalendar (year, month - 1, 1);
            calendar.add(DATE, 1 - calendar.get(DAY_OF_WEEK));

            output.outputOpenMonth(out);

            while (true) {
                output.outputOpenWeek(out);

                for (int w : WEEK) {
                    int y = calendar.get(YEAR);
                    int m = calendar.get(MONTH) + 1;
                    int d = calendar.get(DAY_OF_MONTH);
                    output.outputDate(out, y, m, d, w, m - month);

                    calendar.add(DATE, 1);
                }

                output.outputCloseWeek(out);

                if (calendar.get(MONTH) + 1 != month) {
                    break;
                }
            }

            output.outputCloseMonth(out);

            out.close();

            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(pis));) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + LS);
                }
            }

            return sb.toString();
        }
    }
}
