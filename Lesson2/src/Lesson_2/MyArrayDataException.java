package Lesson_2;

public class MyArrayDataException extends Exception {
    public MyArrayDataException (int i, int j){
        super ("Ошибка преобразования в ячейке " + i + " " + j);
    }
}
