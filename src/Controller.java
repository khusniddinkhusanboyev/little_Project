import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final String URL = "jdbc:mysql://localhost:3306/markets";
    private final String USER = "root";
    private final String PASSWORD = "admin";


    public String save(Product product) {
        for (Product productName : findAll()) {
            if (product.getName().equalsIgnoreCase(productName.getName())) {
                return product.getName() + " is already exists in database";
            } else {
                try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    Statement statement = con.createStatement();
                    statement.execute("insert into products(name,amount,price) values('" + product.getName() + "','" + product.getAmount() + "','" + product.getPrice() + "')");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
                return "Process successful";
    }

    public void delete(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            String query = "delete from products where id=" + id;
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String delete(String name) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            String query = "delete from products where name='" + name + "'";
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name + " successfully deleted";
    }

    public int update(String name, int amount) {

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                Statement statement = connection.createStatement();
                statement.execute("update products set amount='" + (find(name).getAmount() - amount) + "' where name='" + find(name).getName()+ "'");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        /*}*/
        return find(name).getPrice() * amount;
    }

    public String alter(String name, int amount, int price) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("update products set name='" + name + "', amount='" + amount + "',price='" + price + "' where name='" + name + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return " The process ended successfully";
    }

    public Product find(String name) {
        Product product=null;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery("select * from products where name = '" + name + "'");
            while (set.next()) {
                product=new Product(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public Product find(int id) {
        Product product = null;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery("select * from products where id = " + id);
            while (set.next()) {
                product = new Product(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
