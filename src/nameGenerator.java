import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class nameGenerator {
    //this function generate string for paths files according number of runs

    ArrayList<String> nameGenerator(String mainPath, int runSize) throws IOException {
        RandomAccessFile mainFile =new RandomAccessFile(mainPath,"rw");
        float fileLength = (float) mainFile.length();
        float numberOfRuns=(fileLength/8)/runSize;
        if(numberOfRuns < (int)numberOfRuns) //here i will up for 1 ex : 12.4 --> 13 to handle last file
            numberOfRuns = (int)numberOfRuns++;

        int counter=0;
        ArrayList<String> ar = new ArrayList<String>();
        for(int i=0;i<numberOfRuns;i++) {
            counter++;
            String strI="Run"+counter+".bin";
            ar.add(strI);
        }
        return ar;
    }

}
