package Lesson_2;

public class Main {
    public static void main(String[] args) {
        // генерим хороший массив
        String [][] arr1 = new ArrayGenerator("-2", 4, 4).arrGenerate();
        // генерим массив неверной размерности
        String [][] arr2 = new ArrayGenerator("2", 3, 4).arrGenerate();
        // генерим массив с испорченной ячейкой
        String [][] arr3 = new ArrayGenerator("1", 4, 4).arrGenerate();
        arr3[2][3] = "tnh";

        try {
            //передаем правильный массив в ArraySum
            System.out.println(new ArraySum().arrayElementsSum(arr1));
            //передаем массив неверной размерности в ArraySum
            System.out.println(new ArraySum().arrayElementsSum(arr2));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
          catch (MyArrayDataException e) {
            e.printStackTrace();
        }

        // для демонстрации программы немножко дублируем код
        try {
            //передаем массив с неверной ячейкой в ArraySum
            System.out.println(new ArraySum().arrayElementsSum(arr3));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
          catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }
}
