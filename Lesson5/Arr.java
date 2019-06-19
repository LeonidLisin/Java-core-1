public class Arr {
    private static final int size = 10000000;

    public static void main(String[] args) throws InterruptedException {
        arr1();
        arr2(4);
        arr2(5);
        arr2(10);
    }

    private static float[] arr1(){
        float[] arr = new float[size];
        for (int i = 0; i < size ; i++) arr[i] = 1;

        System.out.println("Идет расчет в один поток...");
        long a = System.currentTimeMillis();
        for (int i = 0; i < size ; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        System.out.println("Расчет в один поток занял " + (System.currentTimeMillis() - a) + "\n");
        return arr;
    }

    private static float[] arr2(int threadsNumber) throws InterruptedException {
        float[] arr = new float[size];
        float[][] arrSplit = new float[threadsNumber][size/threadsNumber];
        for (int i = 0; i < size ; i++) arr[i] = 1;
        Thread[] thread = new Thread[threadsNumber];

        System.out.println("Идет расчет в " + threadsNumber + " потока(ов)...");
        long a = System.currentTimeMillis();

        for (int i = 0; i < threadsNumber; i++){
            thread[i] = new Thread(new Runnable() {
                private int i;
                @Override
                public void run() {
                    for (int j = 0; j < size/threadsNumber; j++) {
                        int index = i*size/threadsNumber+j;
                        arrSplit[i][j] = (float) (arr[index] * Math.sin(0.2f + index / 5)
                                * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
                    }
                }
                private Runnable init(int i){
                    this.i = i;
                    return this;
                }
            }.init(i));
            thread[i].start();
            thread[i].join();
        }

        for (int i = 0; i < threadsNumber; i++)
                System.arraycopy(arrSplit[i], 0, arr, i*size/threadsNumber, size/threadsNumber);

        System.out.println("Расчет в " + threadsNumber + " потока(ов) занял " +
                (System.currentTimeMillis() - a) +  " мс\n");
        return arr;
    }
}
