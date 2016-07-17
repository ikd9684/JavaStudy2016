package jp.co.excite_software.s_ikeda.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SjisToUTF8 {

    public static void main(String[] args) {

        Path srcFile = null;
        Path newFile = null;
        try {
            String srcFilePathname;
            String newFilePathname;
            String newcharsetName;
            if (args.length == 3) {
                srcFilePathname = args[0];
                newFilePathname = args[1];
                newcharsetName = args[2];
            }
            else {
                srcFilePathname = "./problems.sjis.txt";
                newFilePathname = "./problems.utf-8.txt";
                newcharsetName = "UTF-8";
            }

            srcFile = Paths.get(srcFilePathname);
            newFile = Paths.get(newFilePathname);
            Charset newCharset = Charset.forName(newcharsetName);

            System.out.printf("入力：%s", srcFile).println();
            System.out.printf("出力：%s", newFile).println();
            System.out.printf("新しい文字セット：%s", newCharset.toString()).println();

            (new SjisToUTF8()).convert(srcFile, newFile, newCharset);

            System.out.println("変換を完了しました。");
        }
        catch (NoSuchFileException e) {
            System.out.println("エラー：指定された入力ファイルは存在しません。");
        }
        catch (FileAlreadyExistsException e) {
            System.out.println("エラー：指定された出力ファイルは既に存在します。");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println();
        }
    }

    /** */
    private static Charset JISAutoDetect = Charset.forName("JISAutoDetect");

    /**
     * 
     * @param srcFile
     * @param newFile
     * @param newCharset
     * @throws IOException
     */
    public void convert(Path srcFile, Path newFile, Charset newCharset) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(srcFile, JISAutoDetect);
             BufferedWriter bw = Files.newBufferedWriter(newFile, newCharset, StandardOpenOption.CREATE_NEW)) {

            String line;
            while ((line = br.readLine()) != null) {

                bw.write(line);
                bw.newLine();
            }
        }
    }
}
