import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception  {
        System.out.println("himo");
        String indexPathName ="Index.bin";
        DivideInputFileIntoRuns ob1 =new DivideInputFileIntoRuns();
        ob1.DivideInputFileIntoRuns(indexPathName,5);

        SortEachRunOnMemoryAndWriteItBack ob2=new SortEachRunOnMemoryAndWriteItBack();
        ob2.SortEachRunAndWriteItBack(indexPathName,5);



    }
}
