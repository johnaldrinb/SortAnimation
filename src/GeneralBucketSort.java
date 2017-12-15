import java.util.ArrayList;
import java.util.Random;
public class GeneralBucketSort{

    public static int[] sort(int[]arr, int bsize){

        int min=arr[0], max=arr[0];
        for(int i=1; i<arr.length; i++){
            if(min>arr[i]){
                min = arr[i];
            }
            if(max<arr[i]){
                max = arr[i];
            }
        }

        int bcount = (max-min)/bsize;
        if((max-min)%bsize>0){
            ++bcount;
        }

        //int[][]buckets = new int[bcount][];
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<bcount; i++){
            buckets.add(new ArrayList<Integer>());
        }

        for(int i=0; i<arr.length; i++){
            buckets.get((arr[i]-min)/bsize).add(arr[i]);
        }

        for(int i=0; i<buckets.size(); i++){
            // Bubble Sort each bucket
            for(int j = 0;j<buckets.get(i).size()-1;++j){
                for(int k = 0;k<buckets.get(i).size()-1-j;++k){
                    if(buckets.get(i).get(k)>buckets.get(i).get(k+1)){
                        int temp = buckets.get(i).get(k);
                        buckets.get(i).set(k, buckets.get(i).get(k+1));
                        buckets.get(i).set(k+1,temp);
                    }
                }
            }
        }

        int index = 0;
         for(int i=0; i<buckets.size(); i++){
            for(int j = 0;j<buckets.get(i).size();++j){
                arr[index++]=buckets.get(i).get(j);
            }
        }

        return arr;
    }

    public static void main(String[] args){

        Random r = new Random();
        int[] input = new int[10];
        System.out.print("Input: ");

        for(int i=0; i<input.length; i++){
            input[i] = r.nextInt(1000);  // 0 - 999
            System.out.print(input[i]+" ");
        }

        System.out.println();
        System.out.print("Sorted: ");
        input = sort(input, 100);
        
        for(int i=0; i<input.length; i++){
            System.out.print(input[i]+" ");
        }
    }
}
