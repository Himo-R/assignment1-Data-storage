import java.io.RandomAccessFile;
import java.util.ArrayList;


public class DivideInputFileIntoRuns extends nameGenerator {


    public
    void DivideInputFileIntoRuns (String Inputfilename, int runSize)throws Exception
    {
        RandomAccessFile mainFile = new RandomAccessFile(Inputfilename, "rw");
        ArrayList<String>pathes=nameGenerator(Inputfilename,runSize);

        for(int j=0;j<pathes.size();j++)//loop number of runs <how many run>(number of record / run size)
        {
            RandomAccessFile fileForRun = new RandomAccessFile(pathes.get(j), "rw");//open new physical file on same logic file
            if(mainFile.getFilePointer()+(runSize*8) < mainFile.length())
            for (int i = 0; i < runSize * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
                fileForRun.writeInt(mainFile.readInt());// will write record
            else
            {
                int numberRemainRecord= (int) ((mainFile.length() - mainFile.getFilePointer()) /4);
                for (int i = 0; i < numberRemainRecord; i++)
                    fileForRun.writeInt(mainFile.readInt());
            }


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
        /*
        RandomAccessFile lastRun = new RandomAccessFile("remainder.bin", "rw");
        long remainRecord=(mainFile.length()/8)%runSize;
        for (int i = 0; i < remainRecord * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
            lastRun.writeInt(mainFile.readInt());// will write record
        */


                 /*   /////   just for test it's just print /////
        mainFile.seek(0);
        for(int j=0;j<pathes.size();j++)//loop number of runs<how many runs> (number of record / run size)
        {
            RandomAccessFile fileRuns = new RandomAccessFile(pathes.get(j),"rw");
            if(mainFile.getFilePointer()+(runSize*8) < mainFile.length())
            {
                for (int i = 0; i < runSize * 2; i++)// will loop N*2 times each run (i put *2 that record have tow integer)
                {
                    System.out.print(fileRuns.readInt() + "    ");// will write record
                    if (i % 2 != 0) {
                        System.out.println(" ");
                    }
                }
                System.out.println("-----------Run number : " + (j + 1) + "-------------");
            }
            else
            {
                int remainRecord= (int) ((mainFile.length()-mainFile.getFilePointer())/4);
                for (int i = 0; i <remainRecord; i++)
                fileRuns.writeInt(mainFile.readInt());
            }
        }




                            //////  JUST FOR TEST //////  */

    }
}
