import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Vector;

public class SortEachRunOnMemoryAndWriteItBack extends nameGenerator {
    void SortEachRunAndWriteItBack( String mainFilePath,int runSize) throws IOException {
        ArrayList<String> pathes=nameGenerator( mainFilePath,runSize );
        class record { int Key ;int offset;}
        Vector <record> v =new Vector<record>();//vector v will contain records

        for (String i:pathes)
        {
            RandomAccessFile runFile=new RandomAccessFile(i,"rw");
            runFile.seek(0);
            for(int j=0;j<runFile.length()/8;j++)//loop on record
            {
                record tempo = new record();
                tempo.Key = runFile.readInt();
                tempo.offset = runFile.readInt();
                v.add(tempo);
            }
        }
    ///sorting bubble sort
        for (int i = 0; i < v.size()-1; i++)
            for (int j = 0; j < v.size()-i-1; j++)
                if (v.get(j).Key > v.get(j+1).Key)
                {
                    record temp;
                    temp =v.get(j);
                    v.set(j,v.get(j+1));
                    v.set(j+1,temp);
                }
        for (record h:v)
            System.out.println(h.Key+"     "+h.offset);
        // here will be Write it back to files runs
        /*
        *
        *
        *
        *
        *
        *
        *
        *
        * */
    }
}
