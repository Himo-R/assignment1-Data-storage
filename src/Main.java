import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception  {
        System.out.println("himo");
        String indexPathName ="Index.bin";
        DivideInputFileIntoRuns ob1 =new DivideInputFileIntoRuns();
        ob1.DivideInputFileIntoRuns(indexPathName,4);

        SortEachRunOnMemoryAndWriteItBack ob2=new SortEachRunOnMemoryAndWriteItBack();
        ob2.SortEachRunAndWriteItBack(indexPathName,4);
        binarySearch ob3=new binarySearch();
        int x =ob3.binarySeach("run1.bin",3);
        System.out.println("the offset of "+21 + "is : "+ x);


    }
}
