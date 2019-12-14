import java.sql.*;
import java.util.Scanner;

public class BongoDB 
{

    private static Connection c = null;
    private static Statement s = null;
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
                System.out.println("----- CUSTOMER LOGIN -----");
                System.out.println("Please enter username (Use \" \") : ");
        }

        else if (MenuPage == 3) 
        {
            System.out.println("----- CUSTOMER MODE -----");
            System.out.println("\t listAll: List all product from selected category");
            System.out.println("\t priceOver: List all product greater than or equal to");
            System.out.println("\t priceUnder: List all product greater than or equal to");
            System.out.println("\t GreattoLeast: List all product greatest to least");
            System.out.println("\t search: List all product in category");
        } 
        else if (MenuPage == 4)
        {
            System.out.println("----- ADMIN LOGIN -----");
            System.out.println("\tPlease enter Admin username (Use \" \") : ");
        }
        else if (MenuPage == 1000)
        {
            System.out.println("----- ADMIN MODE -----");
            System.out.println("\t CustInfo: Returns all customer information");
            System.out.println("\t deleteCart: Delete Cart");
            System.out.println("\t addcust: Add new customer");
            System.out.println("\t addAdmin: update Customer username");
            System.out.println("\t listStore: list all store");
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
            case "search":
                Search();
                break;
            case "listStore":
                listStore();
                break;
            case "addAdmin":
                addAdmin();
                break;
            case "addcust":
                addcust();
                break;
            case "listAll":
                listAllProd();
                break;
            case "GreattoLeast":
                GreattoLease();
                break;
            case "priceOver":
                listPriceOver();
                break;
            case "CustInfo":
                CustInfo();
                break;
            case "priceUnder":
                listPriceUnder();
                break;
            case "deleteCart":
                DeleteCart();
                break;
            case "customer":
                //displayMenu(2);
                if (MenuCode == 1) 
                {
                    MenuCode = 2;
                    displayMenu(2);
                    customerlogin();
                }
                break;
            case "admin":
                if (MenuCode == 1) 
                {
                    MenuCode = 4;
                    displayMenu(4);
                    adminlogin();
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

    private static void listStore() 
	{
        s = getStatement();
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            String qListString = "SELECT s_storename FROM store;";
            ResultSet qList = s.executeQuery(qListString);
            System.out.println("----- STORE LIST -----\n");
            while(qList.next())
            {
                System.out.println(qList.getString("p_productname"));

            }
            qList.close();
            displayMenu(4);
        }
        catch(SQLException e) 
        {
            System.out.println("\nWrong information\n");
            e.printStackTrace();
        }
	}

    private static void addAdmin() 
	{
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            System.out.println("Please input new Admin username: \n");
            String usernameInput = input.next();
            System.out.println("Please input new Admin ID: \n");
            String IDInput = input.next();
            System.out.println("Please input new Admin name: \n");
            String NameInput = input.next();
            System.out.println("Please input new Admin pID: \n");
            String pInput = input.next();
            System.out.println("Please input new Admin address: \n");
            String addyInput = input.next();
            System.out.println("Please input new Admin phone number: \n");
            String NumInput = input.next();
            System.out.println("Please input new Admin email: \n");
            String EmailInput = input.next();
            System.out.println("Please input new customer password: \n");
            String PassInput = input.next();
            String QS_in = "INSERT INTO customer(a_username, a_custID, a_name, a_productID, a_address, a_phone, a_email, a_password) VALUES (" + usernameInput + "," + IDInput + "," + NameInput + "," + pInput + ","+ addyInput + "," + NumInput + "," + EmailInput + "," + PassInput + ");";
            ResultSet QA = s.executeQuery(QS_in);
            System.out.println("----- New Customer-----\n");
            while(QA.next())
            {
                System.out.print(QA.getString("a_username"));
                System.out.print("\t$"+QA.getString("a_custID"));
                System.out.print("\t$"+QA.getString("a_name"));
                System.out.print("\t$"+QA.getString("a_address"));
                System.out.print("\t$"+QA.getString("a_phone"));
                System.out.print("\t$"+QA.getString("a_email"));
                System.out.print("\t$"+QA.getString("a_password"));
            }
            QA.close();
            displayMenu(4);
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
	}


    private static void addcust() 
	{
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            System.out.println("Please input new customer ID: \n");
            String IDInput = input.next();
            System.out.println("Please input new customer name: \n");
            String NameInput = input.next();
            System.out.println("Please input new customer address: \n");
            String addyInput = input.next();
            System.out.println("Please input new customer phone number: \n");
            String NumInput = input.next();
            System.out.println("Please input new customer username: \n");
            String usernameInput = input.next();
            System.out.println("Please input new customer email: \n");
            String EmailInput = input.next();
            System.out.println("Please input new customer password: \n");
            String PassInput = input.next();
            String QS_in = "INSERT INTO customer(c_custID, c_name, c_address, c_phone, c_username, c_email, c_password) VALUES (" + IDInput + "," + NameInput + "," + addyInput + "," + NumInput + ","+ usernameInput + "," + EmailInput + "," + PassInput + ");";
            ResultSet Qp = s.executeQuery(QS_in);
            System.out.println("----- New Customer-----\n");
            while(Qp.next())
            {
                System.out.print(Qp.getString("c_custID"));
                System.out.print("\t$"+Qp.getString("c_name"));
                System.out.print("\t$"+Qp.getString("c_address"));
                System.out.print("\t$"+Qp.getString("c_phone"));
                System.out.print("\t$"+Qp.getString("c_username"));
                System.out.print("\t$"+Qp.getString("c_email"));
            }
            Qp.close();
            displayMenu(3);
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
	}

    private static void Search() 
	{
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            System.out.println("Please input name of category: \n");
            String categoryName = input.next();
            String s_prod = "SELECT p_productname, p_price, p_color FROM product,category WHERE ctg_categoryID = p_categoryID AND ctg_categoryname = " + categoryName + ";";

            ResultSet searchp = s.executeQuery(s_prod);
            System.out.println("----- PRODUCTS FROM BAGS-----\n");
            while(searchp.next())
            {
                System.out.print(searchp.getString("p_productname"));
                System.out.print("\t$"+searchp.getString("p_price"));
                System.out.println("\t"+searchp.getString("p_color"));

            }
            searchp.close();
            displayMenu(3);
        }
        catch(SQLException e) 
        {
            System.out.println("\nWrong information\n");
            e.printStackTrace();
        }
	}

     private static void DeleteCart() 
	{

        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            System.out.println("Please input cart ID to Delete: \n");
            String CartInput = input.next();
            String CartString = "DELETE FROM cart WHERE ct_cartID =" + CartInput +";";
            ResultSet cartS = s.executeQuery(CartString);
            while(cartS.next())
            {
                System.out.println("Deleting Cart...");
                System.out.println("Deleted");
            }
            cartS.close();
            displayMenu(3);
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
	}

    private static void GreattoLease() 
	{
        s = getStatement();
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            String GtoL = "SELECT p_productname, p_price FROM product ORDER BY p_price DESC;";
            ResultSet Qgtol = s.executeQuery(GtoL);
            System.out.println("----- PRODUCT LIST GREATEST TO LEAST-----\n");
            while(Qgtol.next())
            {
                System.out.print(Qgtol.getString("p_productname"));
                System.out.println("\t$"+Qgtol.getString("p_price"));

            }
            Qgtol.close();
            displayMenu(3);
        }
        catch(SQLException e) 
        {
            System.out.println("\nWrong information\n");
            e.printStackTrace();
        }
	}

    private static void CustInfo(){
        s = getStatement();
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            String q5String = "SELECT * FROM customer WHERE c_name = 'Aaron';";
            ResultSet q5 = s.executeQuery(q5String);
            System.out.println("----- Customer Information -----\n");
            while(q5.next())
            {
                System.out.println(q5.getString("c_name") + " " + q5.getString("c_address") + " " + q5.getString("c_phone") + " " + q5.getString("c_username") + " " + q5.getString("c_email") + " " + q5.getString("c_password"));

            }
            q5.close();
            displayMenu(1000);
        }
        catch(SQLException e) 
        {
            System.out.println("\nWrong information\n");
            e.printStackTrace();
        }
    }

    private static void customerlogin()
    {
          try
            {
                Statement s = c.createStatement();
                String custUserIn = input.nextLine();
                System.out.println("Please enter password: ");
                String custPassIn = input.next();
                String Q_custUser = "SELECT * FROM customer WHERE c_username = " + custUserIn + ";";
                String Q_custPass = "SELECT * FROM customer WHERE c_password = " + custPassIn + "AND c_username = " + custUserIn + ";";
                ResultSet Query4P1 = s.executeQuery(Q_custUser);
                ResultSet Query4P2 = s.executeQuery(Q_custPass);
                while(Query4P1.next())
			{
                MenuCode = 2;
				System.out.println("\nUsername exist\n");
			}
              while(Query4P2.next())
			{
				System.out.println("\nWelcome\n");
			}

            Query4P1.close();
            Query4P2.close();
            displayMenu(3);
            }
			catch(SQLException e) 
        	{
				System.out.println("\nWrong information\n");
                e.printStackTrace();
			}
    }

    private static void adminlogin()
    {
          try
            {
                Statement s = c.createStatement();
                String adminUserIn = input.nextLine();
                System.out.println("Please enter password: ");
                String adminPassIn = input.next();
                String Q_AdUser = "SELECT * FROM admin WHERE a_username = " + adminUserIn + ";";
                String Q_AdPass = "SELECT * FROM admin WHERE a_password = " + adminPassIn + "AND a_username = " + adminUserIn + ";";
                ResultSet Q1 = s.executeQuery(Q_AdUser);
                ResultSet Q2 = s.executeQuery(Q_AdPass);
                while(Q1.next())
			{
                 MenuCode = 4;
				System.out.println("\nUsername exist\n");
			}
              while(Q2.next())
			{
				System.out.println("\nWelcome\n");
			}

            Q1.close();
            Q2.close();
            displayMenu(1000);
            }
			catch(SQLException e) 
        	{
				System.out.println("\nWrong information\n");
                e.printStackTrace();
			}
    }

    private static void listAllProd() 
	{
        s = getStatement();
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            String q5String = "SELECT * FROM product;";
            ResultSet q5 = s.executeQuery(q5String);
            System.out.println("----- PRODUCT LIST -----\n");
            while(q5.next())
            {
                System.out.println(q5.getString("p_productname"));

            }
            q5.close();
            displayMenu(3);
        }
        catch(SQLException e) 
        {
            System.out.println("\nWrong information\n");
            e.printStackTrace();
        }
	}

    private static void listPriceOver() 
	{
        s = getStatement();
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            System.out.println("Please input price: \n");
            String PriceInput = input.next();
            String q5String = "SELECT DISTINCT(p_productname),p_price FROM product WHERE p_price >= " + PriceInput + " ORDER BY p_price ASC;";
            ResultSet p = s.executeQuery(q5String);
            System.out.println("----- PRODUCT LIST OVER " + PriceInput + "-----\n");
            while(p.next())
            {
                System.out.print(p.getString("p_productname") + "\t");
                System.out.println("$"+p.getString("p_price"));
            }
            p.close();
            displayMenu(3);
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
	}

    private static void listPriceUnder() 
	{
        s = getStatement();
        System.out.println("\n");
        try
        {
            Statement s = c.createStatement();
            System.out.println("Please input price: \n");
            String PriceUnderInput = input.next();
            String Q_in = "SELECT DISTINCT(p_productname),p_price FROM product WHERE p_price <= " + PriceUnderInput + " ORDER BY p_price DESC;";
            ResultSet p = s.executeQuery(Q_in);
            System.out.println("----- PRODUCT LIST UNDER " + PriceUnderInput + "-----\n");
            while(p.next())
            {
                System.out.print(p.getString("p_productname") + "\t");
                System.out.println("$"+p.getString("p_price"));
            }
            p.close();
            displayMenu(3);
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
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

    private static Connection getConnection() 
    {
        String url = "jdbc:sqlite:/Users/aaronvanoung/Desktop/Fall2019/Database/Project2/BongoDB.db";
        try 
        {
            System.out.println("\nEstablishing Connection...");
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
