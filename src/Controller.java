import java.io.PrintStream;
import java.sql.*;
import java.text.NumberFormat;
import java.util.*;

public class Controller {
    private final String URL = "jdbc:mysql://localhost:3306/markets";
    private final String USER = "root";
    private final String PASSWORD = "admin";


    public String save(String sname , int samount,int sprice) {
        for (Product product : findAll()) {
            if (product.getName().equalsIgnoreCase(sname) && product.getAmount() == samount && product.getPrice() == sprice) {
                return product.getName() + " is already exists in database";
            } else
        if (product.getName().equalsIgnoreCase(sname)) {
                try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    Statement statement = con.createStatement();
                    statement.execute("UPDATE products SET name='" + sname + "',amount='" + (find(sname).getAmount() + samount) + "', price='" + sprice + "' where name like '%"+find(sname).getName()+"%' ");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return "Process UPDATED successfully";

            } else {
                try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    Statement statement = con.createStatement();
                    statement.execute("insert into products(name,amount,price,date_time) values('" + sname + "','" + samount + "','" + sprice + "',now())");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return "Process successful";
            }

        }
        return "xxxxxxxxxxxxxxxxxx";
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

    public String alter(int id , String name, int amount, int price) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("update products set name='" + name + "', amount='" +(find(id).getAmount()+amount) + "',price='" + price + "' where id=" + id + " ");
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

    public Map<String,Integer> security(){
        Map<String,Integer> users=new HashMap<>();
        try(Connection user=DriverManager.getConnection(URL,USER,PASSWORD)) {
                Statement stmt=user.createStatement();
                ResultSet rs=stmt.executeQuery("select * from markets.securety");
                while (rs.next()){
                    users.put(rs.getString(2),rs.getInt(3));
                        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public List<Integer> password(){
        List<Integer> pr=new ArrayList<>();
        try(Connection user=DriverManager.getConnection(URL,USER,PASSWORD)) {
            Statement stmt=user.createStatement();
            ResultSet rs=stmt.executeQuery("select * from markets.securety");
            while (rs.next()){
                pr.add(rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pr;
    }

    public void purchasedProduct(Map<String , Integer> name_amount){
        try(Connection con=DriverManager.getConnection(URL,USER,PASSWORD)){
            Statement stmt= con.createStatement();
            for (String name : name_amount.keySet()) {
                stmt.execute("INSERT INTO purchased(name , amount , price , totalprice, created_time ) VALUES('" + name + "' , '"+name_amount.get(name)+"' ,'"+find(name).getPrice()+"', '"+find(name).getPrice()*name_amount.get(name)+"',now())");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String formatter(Integer unformatted){
        return NumberFormat.getInstance(Locale.of("sk","SK")).format(unformatted);

    }
}
