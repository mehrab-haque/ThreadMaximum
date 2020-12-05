import java.util.Random;

class ParallelMax implements Runnable {

    int [] array;
    int maximum;
    Thread thread;

    public ParallelMax(int[] arr,int n){
        array=new int[200];
        for(int i=0;i<200;i++)
            array[i]=arr[n*200+i];
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        maximum=array[0];
        for(int i=1;i<200;i++)
            if(array[i]>maximum)
                maximum=array[i];
    }

    public int getMaximum() {
        return maximum;
    }

    public Thread getThread() {
        return thread;
    }

    // your code here
}


public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int [] numbers = new int[1000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }
        ParallelMax [] parallelMax = new ParallelMax[5];
        int maximum=0;
        for(int i=0;i<5;i++){
            parallelMax[i]=new ParallelMax(numbers,i);
            try {
                parallelMax[i].getThread().join();
                if(i==0)maximum=parallelMax[i].getMaximum();
                else if(parallelMax[i].getMaximum()>maximum)maximum=parallelMax[i].getMaximum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(maximum);

    }
}