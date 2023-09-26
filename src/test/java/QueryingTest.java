import POM.Data.Model.User;
import POM.Steps.JDBCSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static POM.Data.Dao.UserDao.deleteStudentById;

public class QueryingTest {

    JDBCSteps jdbcSteps;
    User student;

    @BeforeMethod
    public void setupJdbcStepsAndStudent(){
        student = new User(1004, "Cotne", "Aburjania", "551222324");
        jdbcSteps = new JDBCSteps(student);
    }

    @Test
    public void queryingTest() throws SQLException{
        String newName = "Irakli";

        jdbcSteps.insertUserWithCustomPrerequisites().validateInsertedStudent().updateInsertedStudentName(newName).validateUpdatedName(newName);

//        deleteStudentById(student.getId());
    }

}
