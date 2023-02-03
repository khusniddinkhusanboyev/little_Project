public class Supplier {
    Controller controller;

   /* public void addProduct(Product product){
        controller.save(product);
    }
*/
    public void alertProductDetails(String name,int amount){
        controller.update(name, amount);
    }

    public void del(int id){
        controller.delete(id);
    }
}
