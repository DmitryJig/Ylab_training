package lessons.lesson_2.homeworkJavaRush.task1;

/**
 * Задачи на видимость переменных
 */
public class Cat {

    private String name;
    private static int catsCount = 0;
    private String fullName;

    /**
     *Реализовать метод setName, чтобы с его помощью можно было устанавливать
     * значение переменной private String name равное переданному параметру String name.
     */
    public void setName(String name) {
        //написать тут ваш код
        this.name = name;
    }

    /**
     * Реализовать метод addNewCat, чтобы при его вызове (т.е. добавлении нового кота),
     * количество котов увеличивалось на 1. За количество котов отвечает переменная catsCount.
     */
    public static void addNewCat()
    {
        //написать тут ваш код
        catsCount++;
    }

    /**
     * Реализовать метод setCatsCount так, чтобы с его помощью можно было устанавливать
     * значение переменной catsCount равное переданному параметру.
     */
    public static void setCatsCount(int count)
    {
        //написать тут ваш код
        catsCount = count;
    }

    /**
     * Реализовать метод setName, чтобы с его помощью можно было устанавливать значение
     * переменной private String fullName равное значению локальной переменной String fullName.
     */
    public void setName(String firstName, String lastName)
    {
        String fullName = firstName + lastName;
        //написать тут ваш код
        this.fullName = fullName;
    }

    /**
     * Написать код, который бы подсчитывал количество созданных котов (count)
     * и на экран выдавалось правильно их количество.
     */
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        //написать тут ваш код
        addNewCat();
        Cat cat2 = new Cat();
        //написать тут ваш код
        addNewCat();
        System.out.println("Cats count is " + catsCount);
    }
}
