package jp.co.excite_software.s_ikeda.study;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TextSearch {

    public static void main(String[] args) {

        String pathname;
        String keyword;
        if (args.length == 2) {
            pathname = args[0];
            keyword = args[1];
        }
        else {
            pathname = "./src/jp/co/excite_software/s_ikeda/study/TextSearch.java";
            keyword = "String";
        }

        try {
            new TextSearch().outputMatches(pathname, keyword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param pathname
     * @param keyword
     * @throws IOException
     */
    public void outputMatches(String pathname, String keyword) throws IOException {

        File path = new File(pathname);
        System.out.printf("%s", path.getCanonicalPath()).println();
        System.out.printf("[ %s ]", keyword).println();
        System.out.printf("---").println();

        boolean hasMatch = false;
        try (Scanner scanner = new Scanner(path);) {

            for (int i = 0; scanner.hasNextLine(); i++) {
                String line = scanner.nextLine();

                if (line.contains(keyword)) {
                    hasMatch = true;
                    System.out.printf("%3d| %s", i, line).println();
                }
            }
        }

        if (!hasMatch) {
            System.out.printf("%s を含む行が見つかりませんでした。", keyword).println();
        }
        System.out.println();
    }
}
