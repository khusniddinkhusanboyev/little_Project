public class Consumer {
    Controller controller;
    public void seeAllProduct(){
        controller.findAll();
    }
    public void findProduct(int needed){
        controller.find(needed);
    }
    public void buyProduct(int id){
        controller.find(id);
    }
}
