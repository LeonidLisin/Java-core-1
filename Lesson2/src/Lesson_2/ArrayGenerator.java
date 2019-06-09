package Lesson_2;

public class ArrayGenerator {
    private String s;
    private String [][] arrElement;

    public ArrayGenerator(String s, int dim1, int dim2){
        this.s = s;
        arrElement = new String[dim1][dim2];
    }
    public String[][] arrGenerate(){

        for(int i=0; i < arrElement.length; i++)
            for (int j = 0; j<arrElement[i].length; j++)
                arrElement[i][j] = s;
        return arrElement;
    }
}
