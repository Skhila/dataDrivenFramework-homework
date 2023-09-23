package POM.Data.Dao;

import POM.Data.Model.User;
import org.testng.annotations.DataProvider;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static POM.Data.Dao.UserDao.getUsersInfo;

public class UserDataProvider {

    @DataProvider(name = "userDataProvider")
    public static Object[][] userDataProvider() throws SQLException {
        List<User> userList;
        Object[][] userData = null;
        try {
            userList = new ArrayList<>(getUsersInfo());

            int userListSize = getUsersInfo().size();
            int userAttributesSize = getUsersInfo().get(0).getClass().getDeclaredFields().length;

            userData = new Object[userListSize][userAttributesSize];

            for(int i = 0; i < userListSize; i++){
                User currentUser = userList.get(i);
                Object[] currentUserData = new Object[]{currentUser.getFirstName(), currentUser.getLastName(), currentUser.getPhoneNumber()};
                userData[i] = currentUserData;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userData;
    }
}
