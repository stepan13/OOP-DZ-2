package model;

import java.util.*;

public class DataBase implements Iterable {
    static int index;

    private Notebook iteratorBase[];
    private HashSet<Notebook> notebooks;

    public DataBase() {
        notebooks = new LinkedHashSet<>();
        index = 0;
    }

    private DataBase(DataBase data) {
        notebooks = new LinkedHashSet<>(data.notebooks);
        index = notebooks.size() - 1;
    }

    public static Notebook createRandomNotebook() {
        Random rand = new Random();
        int ramSize = (int) Math.pow(2, (1 + rand.nextInt(6)));
        int diskSize = 1 + rand.nextInt(4);
        String os = (rand.nextInt(2) == 0 ? "windows" : "linux");
        String color = HelpUtils.getRandomColor();
        Notebook note = new Notebook("model_" + (index + 1), ramSize, diskSize, os, color);
        return note;
    }

    public void remove(Notebook item) {
        if (notebooks.contains(item)) {
            notebooks.remove(item);
            index = notebooks.size() - 1;
        }
    }

    public DataBase unloadBase() {
        return new DataBase(this);
    }

    public int size() {
        return index + 1;
    }

    public String getOptionValueString(OptionType key) {
        Set resultSet = new TreeSet();
        for (Notebook item : notebooks) {
            resultSet.add(item.getOption(key));
        }


        String result = "";
        for (Object o : resultSet) {
            result += (result.isEmpty() ? "" : ", ") + o.toString();
        }
        return result;
    }


    public void printBase() {
        for (Notebook item : notebooks) {
            System.out.println(item);
        }
    }

    public void addNew(Notebook newNote) {
        notebooks.add(newNote);
        index = notebooks.size() - 1;
    }

    public void empty() {
        notebooks.clear();
        index = 0;
    }

    @Override
    public Iterator iterator() {
        return notebooks.iterator();
    }

}
