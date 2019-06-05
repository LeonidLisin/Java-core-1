package Lesson_1.Marafon.Obstacles;

import Lesson_1.Marafon.Competitors.Competitor;
import Lesson_1.Marafon.Competitors.Team;

public class Course {
    Obstacle[] course;

    public Course(Obstacle[] course){
        this.course = course;
    }

    public void start(Team team){
        for (Competitor t : team.competitors)
            for (Obstacle o : course)
                o.doIt(t);
    }
}
