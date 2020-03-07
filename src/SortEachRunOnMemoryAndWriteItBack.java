import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class SortEachRunOnMemoryAndWriteItBack extends nameGenerator {
    void SortEachRunAndWriteItBack( String mainFilePath,int runSize) throws IOException {
        ArrayList<String> pathes=nameGenerator( mainFilePath,runSize);
        class record { int Key ;int offset;}
        Vector <record> v =new Vector<record>();//vector v will contain records

        for (String i:pathes)
        {
            RandomAccessFile runFile=new RandomAccessFile(i,"rw");
            //runFile.seek(0);
            for(int j=0;j<runFile.length()/8;j++)//loop on record
            {
                record tempo = new record();
                tempo.Key = runFile.readInt();
                tempo.offset = runFile.readInt();
                v.add(tempo);
            }
        }
        System.out.println("befor sorting vector : ");
        for (record h:v)
            System.out.println(h.Key+"     "+h.offset);
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

        System.out.println("after sorting vector : ");
        for (record h:v)
            System.out.println(h.Key+"     "+h.offset);
        // here will be Write it back to files runs
        /*int y=0;
        for (int i=0;i<pathes.size();i++)
        {
            RandomAccessFile runFile=new RandomAccessFile(pathes.get(i),"rw");
            if (y+4 <= v.size())
            {
                for (int j=0 ;j<runSize;j++)
                {
                    runFile.writeInt(v.get(y).Key);
                    runFile.writeInt(v.get(y).offset);
                    y++;
                }
            }
            else
                {
                    int numberRemainRecord=  (v.size()-y);
                    for (int j=0 ;j<numberRemainRecord;j++)
                    {
                        runFile.writeInt(v.get(y).Key);
                        runFile.writeInt(v.get(y).offset);
                    }

                }

        }

         */
        int counter=0;
        int k=0;
            RandomAccessFile runFile=new RandomAccessFile(pathes.get(k),"rw");

            for (int i=0;i<v.size();i++)
            {
                if (counter == runSize)
                {
                    counter = 0;
                    k++;
                    runFile=new RandomAccessFile(pathes.get(k),"rw");
                }
                runFile.writeInt(v.get(i).Key);
                runFile.writeInt(v.get(i).offset);
                counter++;
            }

        System.out.println("after write in file : ");

        for (String i:pathes) {
            RandomAccessFile runFile2 = new RandomAccessFile(i, "rw");
            for (int j = 0; j < runFile2.length() / 4; j++)
            {
            System.out.print(runFile2.readInt() + "    ");// will write record
            if (j % 2 != 0)
                System.out.println(" ");
            }
        }
    }
}
