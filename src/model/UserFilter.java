package model;


import java.util.LinkedHashMap;
import java.util.Scanner;


public class UserFilter {
    private boolean userHere;
    private Notebook filter;
    private DataBase base;
    private DataBase filterBase;
    private LinkedHashMap<Integer, String> optionList;


    public UserFilter(DataBase base) {
        userHere = base.size() > 0;
        this.base = base.unloadBase();
        this.filter = new Notebook();
        System.out.printf("We have %d notebooks!\n",this.base.size());
    }


    private void printOptions() {
        System.out.println("0 - Exit");
        for (OptionType option : OptionType.values()) {
            String str = String.format("%d - %s %s", option.number(), option, filter.getOption(option));
            System.out.println(str);
        }
    }


    public void askFilter() {
        printOptions();
        System.out.print("Option: ");
        Scanner sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        if (userChoice == 0) this.userHere = false;
        else if (userChoice > OptionType.values().length) System.out.println("Wrong option");
        else {
            askOption(userChoice);
            runFilter();
            printFilter();
        }
    }

    private void runFilter() {
        filterBase = base.unloadBase();
        for (OptionType option : OptionType.values()) {
            filterByOption(option);
        }
    }

    private void filterByOption(OptionType option) {
        Notebook item;
        NotebookComparator comp = new NotebookComparator(option);
        for (Object temp : base) {
            item = (Notebook) temp;

            if (comp.compare(item, filter) < 0) {
                filterBase.remove(item);
            }
        }
    }

    private void printFilter() {
        for (Object temp : filterBase) {
            System.out.println((Notebook) temp);
        }
    }

    private void askOption(int userChoice) {
        OptionType option = OptionType.getByNumber(userChoice);
        String variants = base.getOptionValueString(option);
        Object value;
        System.out.print(option + " (" + variants + ") :");
        if (option.isNumber()) {
            value = HelpUtils.askInt();
        } else {
            value = HelpUtils.askString();
        }
        filter.setOption(option, value);
    }



    public boolean isUserHere() {
        return userHere;
    }
}


//}

