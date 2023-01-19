import java.sql.SQLException;

public class Test{
    public static void main(String[] args){
        DatabaseUtility databaseUtility = new DatabaseUtility();
        try{
            databaseUtility.createTableFornitori();
            databaseUtility.createTableProdotti();
            databaseUtility.createTableCatalogo();
            databaseUtility.insertInTableFornitori();
            databaseUtility.selectProductInDatabase();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
