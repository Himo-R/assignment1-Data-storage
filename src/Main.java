import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception  {
        System.out.println("himo");
        DivideInputFileIntoRuns ob =new DivideInputFileIntoRuns();
        ob.DivideInputFileIntoRuns("Index.bin",4);

    }
}
