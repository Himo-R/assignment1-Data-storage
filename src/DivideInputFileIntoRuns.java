import java.io.RandomAccessFile;
import java.util.ArrayList;


public class DivideInputFileIntoRuns {

//this function generate string for paths files according number of runs
   ArrayList<String> nameGenerator(int fileLength, int runSize){
        int numberOfRuns=((fileLength/8)/runSize);
        //System.out.println(numberOfRuns);
        int counter=0;
       ArrayList<String> ar = new ArrayList<String>();
       for(int i=0;i<numberOfRuns;i++) {
            counter++;
            String strI="Run"+counter+".bin";
            ar.add(strI);
        }
       return ar;
    }

    public
    void DivideInputFileIntoRuns (String Inputfilename, int runSize)throws Exception
    {
        RandomAccessFile mainFile = new RandomAccessFile(Inputfilename, "rw");
        ArrayList<String>pathes=nameGenerator((int)mainFile.length(),runSize);

        for(int j=0;j<(mainFile.length()/8)/runSize;j++)//loop number of runs <how many run>(number of record / run size)
        {
            RandomAccessFile fileForRun = new RandomAccessFile(pathes.get(j), "rw");//open new physical file on same logic file
            for (int i = 0; i < runSize * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
                fileForRun.writeInt(mainFile.readInt());// will write record
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
        RandomAccessFile lastRun = new RandomAccessFile("remainder.bin", "rw");
        long remainRecord=(mainFile.length()/8)%runSize;
        for (int i = 0; i < remainRecord * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
            lastRun.writeInt(mainFile.readInt());// will write record



                             /////   just for test it's just print /////
        for(int j=0;j<(mainFile.length()/8)/runSize;j++)//loop number of runs<how many runs> (number of record / run size)
        {
            RandomAccessFile fileStore3 = new RandomAccessFile(pathes.get(j), "rw");
            for (int i = 0; i < runSize * 2; i++)// will loop N*2 times each run (i put *2 that record have tow integer)
            {
                System.out.print(fileStore3.readInt()+"    ");// will write record
                if(i%2!=0){System.out.println(" ");}
            }
            System.out.println("-----------Run number : "+ (j+1)+"-------------");
        }

        RandomAccessFile fileStore3 = new RandomAccessFile("remainder.bin", "rw");
        long remainRecord1=(mainFile.length()/8)%runSize;
        for (int i = 0; i < remainRecord1 * 2; i++)//will loop N*2 times each run (i put *2 that record have tow integer)
        {
            System.out.print(fileStore3.readInt()+"   ");// will write record
            if(i%2!=0){System.out.println(" ");}
        }
        System.out.println("-----------Run number : "+ (((mainFile.length()/8)/runSize)+1)+ "-------------");
                            //////  JUST FOR TEST //////


    }
}
