import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        Supplier supplier = new Supplier();
        Consumer consumer = new Consumer();
        Controller controller = new Controller();

        //Are you Consumer or Supplier?
        System.out.print(MyColor.YELLOW+"who are you  \n 1 : admin \n 2: consumer \n choose balove  ");
        if (sr.nextInt()==2) {
            int n = -1;

            while (n != 0) {
                int count=0;
                System.out.print(MyColor.GREEN+"Welcome consumer :\n 1: see all \n 2: buy \n 3: find \n choose : ");

                n = sr.nextInt();
                switch (n) {
                    case 1:
                        System.out.println(MyColor.RED+"-----------------------------------------");
                        System.out.println("    Name "+" "+" Amount "+"  "+" Price ");
                        controller.findAll().stream().forEach(a->{
                            System.out.println(MyColor.YELLOW+" | "+a.getName()+" --- "+a.getAmount()+" --- "+a.getPrice()+" | ");
                        });
                        System.out.println(MyColor.RED+"-----------------------------------------");

                        break;
                    case 2:
                        Scanner scr=new Scanner(System.in);
                        System.out.println(MyColor.RED+"--------");
                        Map<Integer,String> items=new HashMap<>();
                        Map<String,Integer> miqdor=new HashMap<>();
                        Map<Integer,Integer> summa=new HashMap<>();
                        int item;
                        do {
                            System.out.println(MyColor.RED+"0 : exit and get check > ");
                            for (int i = 0; i < controller.findAll().size(); i++) {
                                items.put(i,controller.findAll().get(i).getName());
                                System.out.println(MyColor.BlUE+(count+=1)+" : "+controller.findAll().get(i).getName()+"     price: "+controller.findAll().get(i).getPrice());
                            }
                            System.out.print(MyColor.YELLOW+"choose products : ");
                             item=scr.nextInt();
                             if (item==0){
                                 break;
                             }
                            System.out.print(MyColor.YELLOW + "amount :  ");
                            int amount = scr.nextInt();

                            miqdor.put(items.get(item - 1),amount);
                            summa.put(controller.find(items.get(item - 1)).getPrice(),controller.find(items.get(item - 1)).getPrice()*amount);
                        count=0;
                        }while (item!=0);
                        count=1;
                        System.out.println("Check: ");

                        for (String key : miqdor.keySet()){

                            System.out.println((count++)+". " +key+" : "+miqdor.get(key)+"   ");
                        }
                        for (int sumkey:summa.keySet()){

                            System.out.println(sumkey+" : "+summa.get(sumkey));
                        }

                        break;
                    case 3:
                        Scanner scanner=new Scanner(System.in);
                        System.out.println(MyColor.RED+"--------");
                        System.out.print(MyColor.YELLOW+"name :  " );
                        String names=scanner.next();
                        System.out.println(MyColor.BlUE+"total price: "+controller.find(names)+"\n ---------------");
                        break;
                }
            }
        } else if (sr.nextInt()==1){
            int n = -1;
            while (n != 0) {
                System.out.print(MyColor.BlUE+"Welcome supplier :\n 1: add product \n 2: change \n 3: delete \n choose:  ");
                n = sr.nextInt();
                switch (n) {
                    case 1:
                        Scanner scnr=new Scanner(System.in);
                        System.out.print(MyColor.CYAN+"input name: ");
                        String name=scnr.nextLine();
                        System.out.print(MyColor.CYAN+"input amount: ");
                        int amount = scnr.nextInt();
                        System.out.print(MyColor.CYAN+"input price: ");
                        int price=scnr.nextInt();
                        System.out.print(MyColor.BlUE+controller.save(new Product(name,amount,price)));
                        System.out.println(" ");
                        break;
                    case 2:
                        Scanner alt=new Scanner(System.in);
                        System.out.print(MyColor.CYAN+"input name: ");
                        String names=alt.nextLine();
                        System.out.print(MyColor.CYAN+"input amount: ");
                        int amounts = alt.nextInt();
                        System.out.print(MyColor.CYAN+"input price: ");
                        int prices=alt.nextInt();
                        System.out.print(controller.alter(names,amounts,prices));
                        System.out.println();
                        break;
                    case 3:
                        Scanner scanner=new Scanner(System.in);
                        System.out.print(MyColor.CYAN+"Delete: ");
                        String delname=scanner.nextLine();
                        System.out.println(MyColor.BlUE+controller.delete(delname));
                        break;
                }
            }
        }else {
            System.err.println("Unsupported");
        }
    }

}

