import java.io.RandomAccessFile;


public class DivideInputFileIntoRuns {

    void DivideInputFileIntoRuns (String Inputfilename, int runSize)throws Exception
    {
        String pathes[] = {"run1.bin", "run2.bin", "run3.bin", "run4.bin", "run5.bin", "run6.bin", "run7.bin", "run8.bin", "run9.bin", "run10.bin", "run11.bin","run12.bin", "run13.bin", "run14.bin", "run15.bin","runs16.bin","runs17.bin"};
        RandomAccessFile fileStore1 = new RandomAccessFile(Inputfilename, "rw");
        for(int j=0;j<(fileStore1.length()/8)/runSize;j++)//loop number of runs <how many run>(number of record / run size)
        {
            RandomAccessFile fileStore2 = new RandomAccessFile(pathes[j], "rw");//open new physical file on same logic file
            for (int i = 0; i < runSize * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
                fileStore2.writeInt(fileStore1.readInt());// will write record
        }
        // now runs have been fulled , BUT may there remain record didn't wrote in run
        //ex: there 4 record the run size 3 will remain 1 , so i tried to read until end of file , i couldn't
        /*
        RandomAccessFile fileStore2 = new RandomAccessFile("remainder.bin", "rw");
        while(!EOF)
        fileStore2.writeInt(fileStore1.readInt());
        */

        //here i get idea to solve it , i use %
        //record number % run size =will get me the last run size
        RandomAccessFile fileStore2 = new RandomAccessFile("remainder.bin", "rw");
        long remainRecord=(fileStore1.length()/8)%runSize;
        for (int i = 0; i < remainRecord * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
            fileStore2.writeInt(fileStore1.readInt());// will write record



                             /////   just for test it's just print /////
        for(int j=0;j<(fileStore1.length()/8)/runSize;j++)//loop number of runs<how many runs> (number of record / run size)
        {
            RandomAccessFile fileStore3 = new RandomAccessFile(pathes[j], "rw");
            for (int i = 0; i < runSize * 2; i++)// will loop N*2 times each run (i put *2 that record have tow integer)
            {
                System.out.print(fileStore3.readInt()+"    ");// will write record
                if(i%2!=0){System.out.println(" ");}
            }
            System.out.println("-----------Run number : "+ (j+1)+"-------------");
        }

        RandomAccessFile fileStore3 = new RandomAccessFile("remainder.bin", "rw");
        long remainRecord1=(fileStore1.length()/8)%runSize;
        for (int i = 0; i < remainRecord1 * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
        {
            System.out.print(fileStore3.readInt()+"   ");// will write record
            if(i%2!=0){System.out.println(" ");}
        }
        System.out.println("-----------Run number : "+ (((fileStore1.length()/8)/runSize)+1)+ "-------------");
                            //////  JUST FOR TEST //////


    }
}
