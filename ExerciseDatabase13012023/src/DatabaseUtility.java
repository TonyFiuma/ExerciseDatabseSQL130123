import java.sql.*;

public class DatabaseUtility{
    private final String url = "jdbc:mysql://localhost:3306/antonio_prova";
    private final String user = "root";
    private final String password = "password";

    public void createTableFornitori() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement = connection.createStatement();
        String query = ""
                       +"CREATE TABLE Fornitori ( "
                       +"CodiceFornitore INT AUTO_INCREMENT PRIMARY KEY, "
                       +"Nome VARCHAR(255) NOT NULL, "
                       +"Indirizzo VARCHAR(255) NOT NULL, "
                       +"Citta VARCHAR(255) NOT NULL "
                       +");";
        statement.executeUpdate(query);
        System.out.println("The table has been created!");
        connection.close();
    }

    public void createTableProdotti() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement = connection.createStatement();
        String query = ""
                       +"CREATE TABLE Prodotti ( "
                       +"CodiceProdotto INT AUTO_INCREMENT PRIMARY KEY, "
                       +"Tipo VARCHAR(255) NOT NULL, "
                       +"Marca VARCHAR(255) NOT NULL, "
                       +"Modello VARCHAR(255) NOT NULL "
                       +");";
        statement.executeUpdate(query);
        System.out.println("The table has been created!");
    }

    public void createTableCatalogo() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement = connection.createStatement();
        String query = ""
                       +"CREATE TABLE Catalogo ( "
                       +"CodiceFornitore INT, "
                       +"CodiceProdotto INT, "
                       +"Costo DECIMAL(10, 2) NOT NULL, "
                       +"PRIMARY KEY (CodiceFornitore, CodiceProdotto), "
                       +"FOREIGN KEY (CodiceFornitore) REFERENCES Fornitori(CodiceFornitore), "
                       +"FOREIGN KEY (CodiceProdotto) REFERENCES Prodotti(CodiceProdotto) "
                       +");";
        statement.executeUpdate(query);
        System.out.println("The table has been created!");
    }


    public void insertInTableFornitori() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement = connection.createStatement();
        String query1 = "INSERT INTO `fornitori` (`Nome`, `Indirizzo`, `Citta`) "
                        +"VALUES ('Ladroni', 'Via Ostense', 'Roma');";
        String query2 = "INSERT INTO `fornitori` (`Nome`, `Indirizzo`, `Citta`) "
                        +"VALUES ('Risparmietti', 'Viale Marconi', 'Roma');";
        String query3 = "INSERT INTO `fornitori` (`Nome`, `Indirizzo`, `Citta`) "
                        +"VALUES ('Teleporto', 'Via Roma', 'Milano');";
        statement.executeUpdate(query1);
        statement.executeUpdate(query2);
        statement.executeUpdate(query3);
        System.out.println("The rows are been inserted!");
        connection.close();
    }

    public void selectProductInDatabase() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement = connection.createStatement();
        String query = "" + "SELECT fornitori.Nome, prodotti.Marca, prodotti.Modello, prodotti.Tipo,catalogo.Costo "
                          + "FROM fornitori,prodotti,catalogo "
                          + "WHERE fornitori.CodiceFornitore = catalogo.CodiceFornitore "
                          + "AND prodotti.CodiceProdotto = catalogo.CodiceProdotto;";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            System.out.println("The Company is: "+resultSet.getString("nome")
                              +", brand: "+resultSet.getString("marca")
                              +", model: "+resultSet.getString("modello")
                              +", type: "+resultSet.getString("tipo")
                              +", price: "+resultSet.getString("costo"));
        }
        connection.close();
    }
}




