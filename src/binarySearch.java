import java.io.IOException;
import java.io.RandomAccessFile;

public class binarySearch
{
    int binarySeach(String path,int target) throws IOException {
        RandomAccessFile finalFile=new RandomAccessFile(path,"rw");
        int low=0, high=(int)finalFile.length();int mid;
        while (low<=high)
        {
            int recordnumber =((high-low) /8);
            if (recordnumber%2==0)
                 mid=(high+low)/2;
            else
                 mid=((high+low)/2)-4;
            // Check if x is present at mid
            finalFile.seek(mid);
            if (finalFile.readInt()==target)
                return finalFile.readInt();

            // If target greater, ignore left half
            finalFile.seek(mid);
            if (finalFile.readInt()>target)
                high = mid ;//

                // If x is smaller, ignore right half

            else
            {
                finalFile.seek(mid);
                low =mid+8;
            }

        }
        return -1;
    }
}

