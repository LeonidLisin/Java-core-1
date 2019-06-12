package Lesson_3;

import java.util.HashMap;
import java.util.HashSet;

class TelNote {
    private HashMap<String, HashSet<Integer>> phoneBook;

    TelNote(){
        phoneBook = new HashMap<>();
    }

    void add(String name, Integer phone){
        if (phoneBook.containsKey(name))
            phoneBook.get(name).add(phone);
        else {
            HashSet<Integer> phones = new HashSet<>();
            phones.add(phone);
            phoneBook.put(name, phones);
        }
    }

    HashSet<Integer> get(String name){
        return phoneBook.get(name);
    }
}
