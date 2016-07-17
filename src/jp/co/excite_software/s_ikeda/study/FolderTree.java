package jp.co.excite_software.s_ikeda.study;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

public class FolderTree {

    public static void main(String[] args) {

        String pathname = "../JavaStudy2016";

        try {
            // 非表示属性のファイルは表示しない
            FileFilter filter = new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return !file.isHidden();
                }
            };

            // ファイルを優先にしてファイル名の昇順でソート
            Comparator<File> comparator = new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory() && !o2.isDirectory()) {
                        return 1;
                    }
                    if (!o1.isDirectory() && o2.isDirectory()) {
                        return -1;
                    }
                    return o1.compareTo(o2);
                }
            };

            // ツリー表示実行
            (new FolderTree(filter, comparator)).outputTree(pathname);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** */
    private FileFilter filter;
    /** */
    private Comparator<File> comparator;

    public FolderTree() {
    }
    public FolderTree(FileFilter filter) {
        this.filter = filter;
    }
    public FolderTree(Comparator<File> comparator) {
        this.comparator = comparator;
    }
    public FolderTree(FileFilter filter, Comparator<File> comparator) {
        this.filter = filter;
        this.comparator = comparator;
    }

    /**
     * 
     * @param pathname
     */
    public void outputTree(String pathname) throws IOException {

        System.out.println(new File(pathname).getCanonicalPath());
        outputTree(System.out, "", pathname);
    }

    /**
     * 
     * @param out
     * @param level
     * @param pathname
     * @throws IOException 
     */
    protected void outputTree(PrintStream out, String indent, String pathname) throws IOException {

        File path = new File(pathname);

        File[] files = path.listFiles(filter);
        Arrays.sort(files, comparator);

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String node;
            if (i < files.length - 1) {
                node = "├─ ";
            }
            else {
                node = "└─ ";
            }

            out.println(indent + node + file.getName());

            if (file.isDirectory()) {
                if (i < files.length - 1) {
                    outputTree(out, indent + "｜  ", file.getCanonicalPath());
                }
                else {
                    outputTree(out, indent + "   ", file.getCanonicalPath());
                }
            }
        }
    }
}
