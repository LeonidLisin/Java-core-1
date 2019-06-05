package Lesson_1.Marafon.Competitors;

public class Team {
    String name;
    public Competitor[] competitors;

    public Team(String name, Competitor[] competitors){
        this.name = name;
        this.competitors = competitors;
    }

    public void showInfo(){
        System.out.println("Командаsss " + name);
        for (Competitor c : competitors) {
            c.info();
        }
    }
    public void showResults(){
        System.out.println("\nРезультаты команды " + name);
        for (Competitor c : competitors)
            if (c.isOnDistance()) c.info();
    }
}
