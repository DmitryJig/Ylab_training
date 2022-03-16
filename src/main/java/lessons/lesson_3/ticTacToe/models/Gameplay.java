package lessons.lesson_3.ticTacToe.models;

import java.util.*;

public class Gameplay {

    // Список для преобразования координат из формата с одной цифрой
    private static List<String> STEP_VALUES = Arrays.asList("11", "21", "31", "12", "22", "32", "13", "23", "33");

    // карта с игроками содержит имена игроков и символы которыми они будут ходить
    private Map<String, Character> gamers = new LinkedHashMap<>();

    // Список шагов, выполненных игроками
    private List<String> steps = new ArrayList<>();

    // результат игры
    private List<String> result = new ArrayList<>();

    //Геттеры
    public Map<String, Character> getGamers() {
        return gamers;
    }

    public List<String> getSteps() {
        return steps;
    }

    public List<String> getResult() {
        return result;
    }

    /**
     * В этом методе загоняем все данные о результате игры в коллекцию строк
     * @param attribute строковое значение атрибута результата игры
     */
    public void addResult(String attribute){
        this.result.add(attribute);
    }

    /**
     * метод добавления игрока в мапу gamers
     *
     * @param name   имя игрока - строка
     * @param symbol символ которым ходит игрок
     */
    public void addGamer(String name, Character symbol) {
        gamers.put(name, symbol);
    }

    /**
     * Метод для добавления шага игры (прогоняем через валидатор чтобы работал с любыми форматами)
     */
    public void addStep(String step) {
        steps.add(validationStep(step));
    }

    /**
     * метод преобразует любой допускаемый формат шагов к формату "22"
     * @param step входящее значение хода в любом допустимом формате
     * @return строка шага в формате "22"
     */
    public String validationStep(String step) {
        if (step.length() == 1) {
            return STEP_VALUES.get(Integer.parseInt(step) - 1);

        } else if (step.length() > 2) {
            return step.replaceAll("[^0-9]", "");
        }
        return step;
    }
}
