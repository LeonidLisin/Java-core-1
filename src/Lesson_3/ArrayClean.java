package Lesson_3;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayClean {

    private static ArrayList<String> arrFilter (ArrayList<String> arrRaw){
        ArrayList<String> arrFiltered = new ArrayList<>();
        for (String raw : arrRaw)
            if (!arrFiltered.contains(raw)) arrFiltered.add(raw);
        return arrFiltered;
    }

    public static void main(String[] args) {
        int count = 0;
        String[] names = {"Леонид", "Георгий", "Эмма", "Парамон", "Эмма", "Георгий", "Георгий", "Парамон", "Маркус", "Маркус"};
        ArrayList<String> arrRaw = new ArrayList<>(names.length);
        arrRaw.addAll(Arrays.asList(names));

        System.out.println("Исходный массив: " + arrRaw);
        System.out.println("После удаления дубликатов: " + arrFilter(arrRaw));

        for (String filtered: arrFilter(arrRaw)){
            for (String raw: arrRaw){
                if(filtered.equals(raw)) count++;
            }
            System.out.println(filtered + " встречается " + count + " раз(а)");
            count = 0;
        }

        TelNote note = new TelNote();
        Integer[] phones = {7777777, 6666666, 5555555, 4444444, 3333333, 2222222, 1111111, 8888888, 999999, 123456};

        for (int i=0; i<names.length; i++)
            note.add(names[i], phones[i]);

        System.out.println();
        for (String name : arrFilter(arrRaw))
            System.out.println(name + " имеет следующие телефонные номера: " + note.get(name));
    }
}
