package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.connectionFactory;

public class TaskController {

    public void save(Task task) {

        String sql = "INSERT INTO tasks (idProject, "
                + "name,"
                + "description,"
                + "complete,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAT) VALUES (?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void updated(Task task) {
String sql = "UPDATE tasks SET "
+ "idProject = ?, "
+ "name = ?, "
+ "description = ?,"
+ "notes = ?,"
+ "deadline = ?,"
+ "completed = ?,"
+ "createdAt = ?,"
+ "updatedAt = ? "
+ "WHERE id = ?";

Connection connection = null;
PreparedStatement statement = null;


try  {
    // Fazendo a conex�o com a database
connection = connectionFactory.getConnection();

//Preparando a query
statement =  connection.prepareStatement(sql);

// Setando os valores do statement
statement.setInt(1, task.getIdProject());
statement.setString(2, task.getName());
statement.setString(3, task.getDescription());
statement.setString(4, task.getNotes());
statement.setBoolean(5, task.isCompleted());
statement.setDate(6, new Date(task.getDeadline().getTime()));
statement.setDate(7, new Date(task.getCreatedAt().getTime()));
statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
statement.setInt(9, task.getId());
        
        //Executando a query
statement.execute();
} catch (Exception ex) {
throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage(), ex);
}
    }

    public void removeById(int taskId)  throws SQLException{
        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Cria��o da conex�o com a database
            connection = connectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores
            statement.setInt(1, taskId);
            
            // Executando a query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage());
           
        } finally {
        connectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(int idProject) {
        
    String sql = "SELECT * FROM tasks WHERE idProject = ?";

Connection connection = null;
PreparedStatement statement = null;
ResultSet resultset = null;

// Lista de tarefas a ser devolvida quando houver a chamada de m�todo

List<Task> tasks = new ArrayList<Task>();

try {
    // cria��o da conex�o
connection = connectionFactory.getConnection();
statement = connection.prepareStatement(sql);

//Setando um valor que se refere ao filtro de busca
statement.setInt(1, idProject);

// Valor retornado pela execu��o da query
resultSet = statement.executeQuery();
        
//Enquanto houver valores a serem percorridos
        while(resultSet.next()){

Task task = new Task();
task.setId(resultSet.getInt("id"));
task.setIdProject(resultSet.getInt("idProject"));
task.setName(resultSet.getString ("name"));
task.setdescription(resultSet.getString("description"));
task.setNotes(resultSet.getString ("notes"));
task.setIsCompleted(resultSet.getBoolean ("completed"));
task.setDeadline(resultSet.getDate ("deadline"));
task.setCreatedAt(resultSet.getDate ("createdAt"));
task.setUpdatedAt(resultSet.getDate ("updatedAt"));
tasks.add(task);
}

} catch (Exception ex){
throw new RuntimeException("Erro ao inserir a tarefa " + ex.getMessage());
} finally {
connectionFactory.closeConnection(connection, statement, resultSet);
}

//Lista de tarefas que foi carregada do banco de dados
return tasks;
    }
}
