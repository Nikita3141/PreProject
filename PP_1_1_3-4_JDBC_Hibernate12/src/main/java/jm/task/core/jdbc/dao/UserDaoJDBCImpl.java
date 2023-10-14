package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Connection connection = null;

    public void createUsersTable() {
        connection = getConnection();
            Statement statement = null;
            String sql = "CREATE TABLE `mydbtest`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,`lastname` VARCHAR(45) NULL,`age` INT(3) NULL, PRIMARY KEY (`id`))";
            try {
                statement = connection.createStatement();
                statement.execute(sql);
            }
            catch (SQLSyntaxErrorException e){
                System.out.println("Привет");
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR в методе createUsersTable 1 ");
            }finally {
                try {
                    if (statement != null){
                        statement.close();
                    }
                    if (connection != null){
                        connection.close();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("ERROR в методе createUsersTable 2 ");
                }
            }
    }

    public void dropUsersTable() {
        connection = getConnection();
        String sql = "DROP TABLE users";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLSyntaxErrorException e){
            System.out.println("Привет");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR в методе dropUsersTable 1 ");
        }finally {
            try {
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        connection = getConnection();
        User user = new User( name,  lastName,  age);
        PreparedStatement preparedStatement = null;
        String sql = "insert into users (name,lastname,age) values(?,?,?)";
        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3,user.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERORR в методе saveUser 1");
        }finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR в методе saveUser 2 ");
            }

        }
    }

    public void removeUserById(long id) {
        connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from users where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR в методе removeUserById 1 ");
        }finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR в методе removeUserById 2 ");
            }
        }
    }

    public List<User> getAllUsers() {
        connection = getConnection();
        List<User> list = new ArrayList<User>();
        String sql = " SELECT ID, NAME, LASTNAME, AGE FROM USERs";
        Statement statement = null ;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User ();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERORR в методе getAllUsers 1");
        }finally {
            try {
                if(statement != null){
                    statement.close();
                    System.out.println("Cоединение с БД закрыто");
                }
                if (connection != null){
                    connection.close();
                    System.out.println("Cоединение с БД закрыто");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERORR в методе removeUseryId 2");
            }
        }
        return  list;
    }

    public void cleanUsersTable() {
        connection = getConnection();
        Statement statement = null;
        String sql = " DELETE FROM users";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLSyntaxErrorException e){

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Кумяйт куним");
            }
        }

    }
}
