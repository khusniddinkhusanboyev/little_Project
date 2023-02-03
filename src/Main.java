import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        Controller controller = new Controller();
        //Are you Consumer or Supplier?
        System.out.print(MyColor.YELLOW + "who are you  \n 1 : admin \n 2: consumer \n choose balove  ");
        int ke = sr.nextInt();
        if (ke == 2) {
            Scanner s = new Scanner(System.in);
            System.out.print("UserName: ");
            String user = s.nextLine();
            System.out.print("Password: ");
            int pass = s.nextInt();
            s.close();
            if (controller.user().contains(user) && controller.password().contains(pass)) {
                int n = -1;
                while (n != 0) {
                    int count = 0;
                    System.out.print(MyColor.GREEN + "Welcome consumer :\n 1: see all \n 2: buy \n 3: find \n choose : ");
                    n = sr.nextInt();
                    switch (n) {
                        case 1 -> {
                            System.out.println(MyColor.RED + "-----------------------------------------");
                            System.out.println("    Name " + " " + " Amount " + "  " + " Price ");
                            controller.findAll().forEach(a -> System.out.println(MyColor.YELLOW + " | " + a.getName() + " --- " + a.getAmount() + " --- " + a.getPrice() + " | "));
                            System.out.println(MyColor.RED + "-----------------------------------------");
                        }
                        case 2 -> {
                            Scanner scr = new Scanner(System.in);
                            System.out.println(MyColor.RED + "--------");
                            Map<Integer, String> items = new HashMap<>();
                            Map<String, Integer> miqdor = new HashMap<>();
                            Map<Integer, Integer> summa = new HashMap<>();
                            int item;
                            do {
                                System.out.println(MyColor.RED + "0 : exit and get check > ");
                                for (int i = 0; i < controller.findAll().size(); i++) {
                                    items.put(i, controller.findAll().get(i).getName());
                                    System.out.println(MyColor.BlUE + (count += 1) + " : " + controller.findAll().get(i).getName() + "     price: " + controller.findAll().get(i).getPrice());
                                }
                                System.out.print(MyColor.YELLOW + "choose products : ");
                                item = scr.nextInt();
                                if (item == 0) {
                                    break;
                                }
                                System.out.print(MyColor.YELLOW + "amount :  ");
                                int amount = scr.nextInt();
                                if (controller.find(items.get(item - 1)).getAmount() < amount) {
                                    System.err.println(" shu mahsulotdan yetarli emas ");
                                } else {
                                    controller.update(items.get(item - 1), amount);
                                }
                                miqdor.put(items.get(item - 1), amount);
                                summa.put(controller.find(items.get(item - 1)).getPrice(), controller.find(items.get(item - 1)).getPrice() * amount);
                                count = 0;
                            } while (item != 0);
                            count = 1;
                            if (!miqdor.isEmpty() && !summa.isEmpty()) {

                                System.out.println(MyColor.RED + "--------");
                                System.out.println("Check: ");
                                int sum = 0;
                                for (String key : miqdor.keySet()) {
                                    System.out.println((count++) + ". " + key + " : " + miqdor.get(key) + "   " + controller.find(key).getPrice() + " : " + controller.find(key).getPrice() * miqdor.get(key));
                                    sum += controller.find(key).getPrice() * miqdor.get(key);
                                }
                                System.out.println(MyColor.YELLOW + "Total Price: " + sum);
                                System.out.println(MyColor.YELLOW + "QQS :12% is tax of total price" + sum * 0.12);


                                System.out.println(MyColor.RED + "--------");
                            } else {
                                System.err.println(" you did not buy anything \n");
                                System.out.println(" ");
                            }
                        }
                        case 3 -> {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println(MyColor.RED + "--------");
                            System.out.print(MyColor.YELLOW + "name :  ");
                            String names = scanner.next();
                            System.out.println(MyColor.RED + "--------");
                            System.out.println(MyColor.BlUE + controller.find(names).getId() + " : " + controller.find(names).getName() + " : " + controller.find(names).getAmount() + " : " + controller.find(names).getPrice() + "\n ---------------");
                        }
                    }
                }
            } else {
                System.out.println("Incorrexr password or username");
            }

        } else if (ke == 1) {
            Scanner s = new Scanner(System.in);
            System.out.print("UserName: ");
            String user = s.nextLine();
            System.out.print("Password: ");
            int pass = s.nextInt();
            if (controller.password().contains(pass) && controller.user().contains(user)) {
                int n = -1;
                int count = 0;
                while (n != 0) {
                    System.out.print(MyColor.BlUE + "Welcome supplier :\n 1: add product \n 2: change \n 3: delete \n choose:  ");
                    n = sr.nextInt();
                    switch (n) {
                        case 1 -> {
                            System.out.println(MyColor.RED + "All Products                           0 : cencel \n" + "-----------------------------------------");
                            System.out.println("    Name " + " " + " Amount " + "  " + " Price ");
                            controller.findAll().forEach(a -> System.out.println(MyColor.YELLOW + " | " + a.getName() + " --- " + a.getAmount() + " --- " + a.getPrice() + " | "));
                            System.err.println(" ");
                            System.out.println(MyColor.RED + "-----------------------------------------" + "\n Add New Product ");
                            Scanner scnr = new Scanner(System.in);
                            System.out.print(MyColor.CYAN + "input name: ");
                            String name = scnr.nextLine();
                            if (name.equalsIgnoreCase("0")) {
                                System.out.println("the process is ended");
                                break;
                            }
                            System.out.print(MyColor.CYAN + "input amount: ");
                            int amount = scnr.nextInt();
                            if (amount == 0) {
                                System.out.println("the process is ended");
                                break;
                            }
                            System.out.print(MyColor.CYAN + "input price: ");
                            int price = scnr.nextInt();
                            if (price == 0) {
                                System.out.println("the process is ended");
                                break;
                            }
                            System.out.print(MyColor.BlUE + controller.save(name, amount, price));
                            System.out.println(" ");
                        }
                        case 2 -> {
                            Scanner alt = new Scanner(System.in);

                            Map<Integer, String> itm = new HashMap<>();
                            int item;
                            do {
                                System.out.println(MyColor.RED + "0 : exit and get check > ");
                                for (int i = 0; i < controller.findAll().size(); i++) {
                                    itm.put(i, controller.findAll().get(i).getName());
                                    System.out.println(MyColor.BlUE + (count += 1) + " : " + controller.findAll().get(i).getName() + "  " + controller.findAll().get(i).getAmount() + "     price: " + controller.findAll().get(i).getPrice());
                                }
                                System.out.print(MyColor.YELLOW + "choose products : ");
                                item = alt.nextInt();
                                if (item == 0) {
                                    break;
                                }
                                System.out.print(MyColor.YELLOW + "amount :  ");
                                int addTo = alt.nextInt();
                                System.out.print(controller.alter(itm.get(item - 1), addTo, controller.find(itm.get(item - 1)).getPrice()));
                            /*miqdor.put(itm.get(item - 1), amount);
                            summa.put(controller.find(items.get(item - 1)).getPrice(), controller.find(items.get(item - 1)).getPrice() * amount);
                            */
                                count = 0;
                            } while (item != 0);
                            System.out.println("the process is ended");
                            System.out.println("------------");
                        }
                        case 3 -> {
                            Scanner scanner = new Scanner(System.in);
                            Map<Integer, String> del = new HashMap<>();
                            int flag1;
                            int co = 0;
                            do {
                                System.out.println(MyColor.RED + "0 : exit and get check > ");
                                for (int i = 0; i < controller.findAll().size(); i++) {
                                    del.put(i, controller.findAll().get(i).getName());
                                    System.out.println(MyColor.BlUE + (co += 1) + " : " + controller.findAll().get(i).getName() + "     price: " + controller.findAll().get(i).getPrice());
                                }
                                System.out.print(MyColor.YELLOW + "choose products : ");
                                flag1 = scanner.nextInt();
                                if (flag1 == 0) {
                                    break;
                                }
                                System.out.println(MyColor.BlUE + controller.delete(controller.find(del.get(flag1 - 1)).getName()));
                            /*miqdor.put(itm.get(item - 1), amount);
                            summa.put(controller.find(items.get(item - 1)).getPrice(), controller.find(items.get(item - 1)).getPrice() * amount);
                            */
                                co = 0;
                            } while (flag1 != 0);
                        }
                    }
                }
            } else {
                System.err.println("Unsupported");
            }
        } else {
            System.out.println("Incorrexr password or username");
        }


    }
}



