package POM.Steps;

import POM.Data.Model.User;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static POM.Data.Dao.DaoUtils.Utils.getStudentById;
import static POM.Data.Dao.UserDao.*;

public class JDBCSteps {

    User student;

    public JDBCSteps(User student){
        this.student = student;
    }

    @Step("Insert new row of student")
    public JDBCSteps insertUserWithCustomPrerequisites() throws SQLException {
        insertIntoStudentsTableUsingCustomAutoCommit(this.student);

        return this;
    }

    @Step("Validate inserted student")
    public JDBCSteps validateInsertedStudent() throws SQLException {
        User insertedStudent = getStudentById(this.student.getId());

        Assert.assertEquals(this.student.getFullInfo(), insertedStudent.getFullInfo(), "Invalid inserted student!!");
        System.out.println("Success! All the values of the inserted row are valid! 👌");
        return this;
    }

    @Step("Update inserted student's name")
    public JDBCSteps updateInsertedStudentName(String nameToUpdate) throws SQLException {
        updateStudentFirstName(this.student.getId(), nameToUpdate);
        return this;
    }

    @Step("Validate updated name")
    public JDBCSteps validateUpdatedName(String expectedName) throws SQLException {
        String updatedStudentName = getStudentById(this.student.getId()).getFirstName();

        Assert.assertEquals(updatedStudentName, expectedName, "Invalid Name!!");
        System.out.println("Updated name validated successfully! 😉");

        return this;
    }
}
