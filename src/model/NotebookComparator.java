package model;

import java.util.Comparator;

public class NotebookComparator implements Comparator<Notebook> {
    private OptionType option;

    public NotebookComparator(OptionType optionName) {
        this.option = optionName;
    }

    @Override
    public int compare(Notebook o1, Notebook o2) {
        Object value1 = o1.getOption(option);
        Object value2 = o2.getOption(option);
        if (option.isNumber()) {
            return (Integer) value1 - (Integer) value2;
        }
        if (((String) value1).equals((String) value2)) {
            return 0;
        }
        if (((String) value1).contains((String) value2)) {
            return 1;
        }
        return -1;
    }
}
