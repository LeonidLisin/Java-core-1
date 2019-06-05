package Lesson_1.Marafon;

import Lesson_1.Marafon.Competitors.*;
import Lesson_1.Marafon.Obstacles.*;

public class Main {
    public static void main(String[] args) {

        Team team = new Team("Team1",
                new Competitor[]{new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")});
        Course course = new Course(new Obstacle[]{new Cross(8), new Wall(22), new Water(20)});
        course.start(team);
        team.showResults();
    }
}