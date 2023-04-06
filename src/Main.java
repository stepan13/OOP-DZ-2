import model.DataBase;
import model.UserFilter;

public class Main {
    public static void main(String[] args) {
        DataBase data = new DataBase();
        for(int i = 1; i<=40; i++){
            data.addNew(DataBase.createRandomNotebook());
        }
        //data.printBase();

        UserFilter userFilter = new UserFilter(data);
        while (userFilter.isUserHere()) {
            userFilter.askFilter();
        }
    }
}