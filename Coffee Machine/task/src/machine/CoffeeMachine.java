package machine;

import java.util.Scanner;

import static machine.Machine.N;


enum State {
    MENU,
    BUY,
    FILL
}
enum FillStage {
    WATER,
    MILK,
    BEANS,
    CUPS
}
class Machine{
    static public boolean N = false;
    private int water;
    private int milk;
    private int beans;
    private int money;
    private int cups;
    private State state;
    private FillStage fillStage;

    public Machine(int water, int milk, int beans, int money, int cups) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
        this.cups = cups;
        this.state = State.MENU;
    }
    private void handleMenu(String input) {
        switch (input) {
            case "buy" : {
                state = State.BUY;
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                N = true;
                break;
            }
            case "fill" : {
                state = State.FILL;
                System.out.println("Write how many ml of water you want to add: ");
                fillStage = FillStage.WATER;
                N = true;
                break;
            }
            case "take" : take();break;
            case "remaining" : remaining();break;
        }
    }
    private void buy(String choice) {
        switch(choice) {
            case "1":
                if(this.water < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if(this.beans < 16) {
                    System.out.println("Sorry, not enough beans!");
                } else if(this.cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.water -= 250;
                    this.beans -= 16;
                    this.money += 4;
                    this.cups--;
                }
                break;
            case "2":
                if(this.water < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if(this.milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                } else if(this.beans < 20) {
                    System.out.println("Sorry, not enough beans!");
                } else if(this.cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.water -= 350;
                    this.milk -= 75;
                    this.beans -= 20;
                    this.money += 7;
                    this.cups--;
                }
                break;
            case "3":
                if(this.water < 200) {
                    System.out.println("Sorry, not enough water!");
                    N = false;
                } else if(this.milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                    N = false;
                } else if(this.beans < 12) {
                    System.out.println("Sorry, not enough beans!");
                    N = false;
                } else if(this.cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                    N = false;
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.water -= 200;
                    this.milk -= 100;
                    this.beans -= 12;
                    this.money += 6;
                    this.cups--;
                }
                break;
            default:
                break;
        }
        state = State.MENU;
        N = false;
    }

    public void remaining(){
        System.out.print("\n");
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water \n",water);
        System.out.printf("%d ml of milk \n",milk);
        System.out.printf("%d g of coffee beans \n",beans);
        System.out.printf("%d disposable cups \n",cups);
        System.out.printf("$%d of money\n",money);
    }
    private void fill(int input) {
        switch (fillStage) {
            case WATER : {
                this.water = this.water + input;
                fillStage = FillStage.MILK;
                break;
            }
            case MILK : {
                this.milk += input;
                fillStage = FillStage.BEANS;
                break;
            }
            case BEANS : {
                this.beans += input;
                fillStage = FillStage.CUPS;
                break;
            }
            case CUPS : {
                this.cups += input;
                fillStage = FillStage.WATER;
                state = State.MENU;
                N = false;
                break;
            }
            default : {
            }
        }

    }
    public void take(){
        System.out.printf("I gave you $%d", this.money);
        System.out.println("\n");
        this.money = 0;
    }
    public void input(String input) {
        switch (state) {
            case MENU : handleMenu(input);
            break;
            case BUY : buy(input);
            break;
            case FILL : {
                switch (fillStage){
                    case WATER : System.out.println("Write how many ml of milk you want to add: ");
                    break;
                    case MILK : System.out.println("Write how many grams of coffee beans you want to add: ");
                    break;
                    case BEANS : System.out.println("Write how many disposable cups of coffee you want to add: ");
                    break;
                }
                fill(Integer.parseInt(input));}
            default : {
            }
        }
    }
}
public class CoffeeMachine {

    public static void main(String[] args) {
        Machine machine = new Machine(400, 540, 120, 550,9);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        String action;

        while(running) {
            if(!N){
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
            }
            action = scanner.nextLine();

            if(action.equals("exit"))
            {
                running = false;
            }
            else
            {
                machine.input(action);
            }
        }
    }

}
