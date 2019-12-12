import java.sql.*;
import java.util.Scanner;

public class BongoDB 
{

    private static Connection c = null;
    private static Statement stat = null;
    private static boolean connected = false;
    private static boolean closing = false;
    private static int MenuCode = 1;
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) 
    {
        c = getConnection();
        Scanner input = new Scanner(System.in);
        displayMenu(1);
        runQuery(input.nextLine());
        closeConnection();
    }

    private static void displayMenu(int MenuPage) 
    {
        System.out.println("\nCommand List: ");
        if (MenuPage == 1) 
        {
            System.out.println("\t ----- SELECT Login -----");
            System.out.println("\t customer: Login as a customer");
            System.out.println("\t admin: Login as an admin");
        } 

        else if (MenuPage == 2)
        {
            try
            {
                System.out.println("----- CUSTOMER LOGIN -----");
                System.out.println("Please enter username: ");
                String custUserIn = input.nextLine();
                //System.out.println("Please enter password: ");
                //String custPassIn = input.next();
                String Q_custUser = "SELECT * FROM customer WHERE c_username = " + custUserIn + ";";
                //String Q_custPass = "SELECT COUNT(*) FROM customer WHERE c_password = " + custPassIn + ";";
                ResultSet Query4P1 = stat.executeQuery(Q_custUser);
                //ResultSet Query4P2 = stat.executeQuery(Q_custPass);
                while(Query4P1.next())
			{
				
				System.out.println("\nIT EXISTS\n");

			}
            
            }
			catch(SQLException e) 
        	{
				System.out.println("\nCould not find username\n");
                e.printStackTrace();
			}
        }

        else if (MenuPage == 3) 
        {
            System.out.println("----- CUSTOMER MODE -----");
            System.out.println("\t SearchInCategory: List all product from selected category");

        } 
        
        else if (MenuPage == 1000)
        {
            System.out.println("----- ADMIN MODE -----");
            System.out.println("\t createAll: To create all tables");
            System.out.println("\t create table: To create a table");
        }

        System.out.print("$ ");
    }

    private static void runQuery(String s) {
        Scanner input = new Scanner(System.in);
        String tableName;

        switch (s) {
            case "connect":
                c = getConnection();
                break;
            
            case "customer":
                //displayMenu(2);
                if (MenuCode == 1) 
                {
                    MenuCode = 2;
                    displayMenu(2);
                } else if (MenuCode == 2){
                    System.out.println("You're already a user");
                    System.out.print("$ ");
                    runQuery(input.nextLine());
                }
            
            case "mng":
                if (MenuCode == 1) {
                    MenuCode = 1000;
                    displayMenu(1000);
                } else if (MenuCode == 1000) {
                    System.out.println("Log out first using command logout");
                    System.out.print("$ ");
                }

                    break;
            case "logout":
                MenuCode = 1;
                displayMenu(1);
                break;
            default:
                System.out.println("Wrong Query Switch");
                break;
        }
        if (MenuCode != 0)
        {
            runQuery(input.nextLine());
        }
    }


    private static PreparedStatement prepStatement(String sql) {

        if (sql == null) return null;
        try 
        {
            PreparedStatement pstmt = c.prepareStatement(sql);
            return pstmt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Statement getStatement() 
    {
        Statement s = null;
        try {
            System.out.println("Connected");
            connected = true;
            s = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static Connection getConnection() 
    {
        String url = "jdbc:sqlite:/Users/nikkosolon/UCM/Semester7/CSE111/Project/3/git/BongoDB-/BongoDB.db";
        try 
        {
            System.out.println("Establishing Connection");
            return DriverManager.getConnection(url);

        } catch (SQLException e) 
        {
            System.out.println("Failed to get Connection");
            e.printStackTrace();
            return null;
        }
    }

    private static void closeConnection() 
    {
        if (c != null) 
        {
            try 
            {
                c.close();
            } catch (SQLException e) 
            {
                System.out.println("Failed to Close Connection");
                e.printStackTrace();
            }
        }
    }
}
