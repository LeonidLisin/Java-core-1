package Lesson_2;

public class ArraySum {
    static private final int GOOD_ARRAY_DIMENSION = 4; // задаем правильную размерность
    static private final String DIGITS = "0123456789";
    private int sum;


    public ArraySum(){
        sum = 0;
    }

    public int arrayElementsSum(String[][] s) throws MyArraySizeException, MyArrayDataException{

        for (int i = 0; i < s.length;i++){
            for (int j = 0; j < s[i].length; j++){
                if (s.length != GOOD_ARRAY_DIMENSION || s[i].length != GOOD_ARRAY_DIMENSION)
                    throw new MyArraySizeException();
                if (!stringParser(s[i][j])) throw new MyArrayDataException(i, j);
                sum += Integer.parseInt(s[i][j]);
            }
        }
        return sum;
    }

    // метод проверяет строку на наличие символов, отличных от цифр
    private boolean stringParser(String s){
        int strSum = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.substring(i, i+1).equals("-") && i == 0)
                strSum++;
            for (int j = 0; j < DIGITS.length(); j++){
                if (s.substring(i, i+1).equals(DIGITS.substring(j, j+1)))
                    strSum++;
            }
        }
        return (strSum == s.length());

    }
}
