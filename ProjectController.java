package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.project;
import util.connectionFactory;

/**
 *
 * @author willi
 */
public class ProjectController {

    public void save(Project project) {

        String sql = "INSERT INTO projects(name, "
                + "description =,"
                + "createdAt = ?,"
                + "updatedAt) = ?, "
                + "VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conexão com a database
            connection = ConnectionFactory.getConnection();
            //Cria um PreparedStatement, classe usada para executar a query
            statement = connection.prepareStatement(sql);

            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, new Date(task.getCreatedAt().getTime()));
            statement.setDate(4, new Date(task.getUpdatedAt().getTime()));

        }
    }

    //Executa a SQL para inserir os dados
    statement.execute ();
}
catch (SQLException ex) {
 throw new RuntimeException("Erro ao salvar o projeto", ex);
            } finally {
                ConnectionFactory.closeConnection(connection, statement);
            }
        }


public void update(Project project) {

            String sql = "UPDATE projects SET "
                   + "name = ?, "
+ "description = ?,"
+ "createdAt = ?,"
+ "updatedAt = ?, "
+ "WHERE id = ?";

Connection connection = null;
            PreparedStatement statement = null;

            try {
                // Cria conexão com a database
                ConnectionFactory.getConnection();

//Cria um prepareStatement, classe usada para preparar a Query
                statement = connection.prepareStatement(sql);

// Setando os valores do statement
                  statement.setString(1, project.getName());
                statement.setString(2, project.getDescription());
                  statement.setDate(3, new Date(task.getCreatedAt().getTime()));
                statement.setDate(4, new Date(task.getUpdatedAt().getTime()));
                statement.setInt(5, project.getId()) //Executando a query

//Executa a SQL para inserir os dados
                statement.execute();
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao atualizar o projeto " + ex.getMessage(), ex);
            } finally {
                ConnectionFactory.closeConnection(connection, statement);
            }
        }

 public List<Project> getAll() {

            String sql = "SELECT * FROM projects = ?";

List <Project> projects = new Arraylist<>();

            Connection connection = null;
            PreparedStatement statement = null;

//Classe que vai recuperar os dados da database
 ResultSet resultset = null;

try {
connection = ConnectionFactory.getConnetion();
statement = connection.prepareStatement(sql);

resultSet = statement.executeQuery();
}

//Enquanto existir dados no banco de dados, faça
while (resultSet.next()) {

Project project = new Project();
                    project.setId(resultSet.getInt("id"));
                    project.setName(resultSet.getString("name"));
                   project.setdescription(resultSet.getString("description"));
                    project.setNotes(resultSet.getString("notes"));
                    project.setCreatedAt(resultSet.getDate("createdAt"));
                    project.setUpdatedAt(resultSet.getDate("updatedAt"));
// Add o contato recuperado, a lista de contatos
                    projects.add(project);
                }

            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao buscar os projetos ", ex);
            } finally {
                ConnectionFactory.closeConnection(connection, statement, resultSet);
}
return projects;

 public void removeById(Int Id) 
      
        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null
        PreparedStatement statement = null;

        try {
            //Criação da conexão com a database
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores
            statment.setInt(1, projectId);

            // Executando a query
            statment.execute();
        } catch (SQLException ex) {
            throw new RunException("Erro ao deletar o projeto" + ex)
           
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
}
}
}




