import java.util.Scanner;

public class Game {
    Player player = new Player(1000, 0);
    Asset asset = new Asset(100);
    Market market = new Market(asset);

    //Teksto spalvos
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[0;34m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String WHITE_BACKGROUND = "\033[47m";
    public static final String BLACK = "\033[0;30m";
    public static final String CYAN_BACKGROUND = "\033[46m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";


    public void play() {
        long startTime = System.currentTimeMillis();
        long duration = 150000;

        Scanner scanner = new Scanner(System.in);

        while (System.currentTimeMillis() - startTime < duration) {
            System.out.println((BLUE + "Likes laikas: " + (duration - (System.currentTimeMillis() - startTime)) / 1000) + "s" + RESET);
            System.out.println(BLACK + WHITE_BACKGROUND + "Coin vertė yra " + BLACK_BOLD + String.format("%.1f", asset.getPrice()) + "€" + RESET);
            System.out.println(BLACK + WHITE_BACKGROUND +"Jūsų balansas " + BLACK_BOLD + String.format("%.1f",player.getBalance()) + "€" + RESET);
            System.out.println(BLACK + WHITE_BACKGROUND +"Jūsų coin kiekis " + BLACK_BOLD + player.getAssetsAmount() + RESET);

            asset.addQueue(asset.getPrice());
            if(asset.queueIsReady()) {
                if(asset.getPrice() >= asset.queueAverage()){
                    System.out.println(GREEN_BOLD + "SMA: " + String.format("%.1f",asset.queueAverage()) + " BETTER BUY" + RESET);
                }
                else{
                    System.out.println(RED_BOLD + "SMA: " + String.format("%.1f",asset.queueAverage()) + " BETTER SELL" + RESET);
                }
            }

            System.out.println(BLUE + "1 = BUY | 2 = SELL | ENTER = HOLD" + RESET);

            String action = scanner.nextLine();

            if (action.equals("1")) {
                System.out.print("Kiek norite nusipirkti?");
                int amount = scanner.nextInt();
                scanner.nextLine();
                player.buy(asset, amount);
                market.updatePrice();
            } else if (action.equals("2")) {
                System.out.print("Kiek norite parduoti?");
                int amount = scanner.nextInt();
                scanner.nextLine();
                player.sell(asset, amount);
                market.updatePrice();
            } else if (action.isEmpty()) {
                market.updatePrice();
            } else {System.out.println(RED_BOLD + "Invalid input");}
        }

        scanner.close();
        gameOver();
    }

    public void gameOver() {
        player.sell(asset, player.getAssetsAmount());

        System.out.println("Laikas baigėsi!");

        if(player.getBalance() > 1000){
            System.out.println(GREEN_BOLD + "Sveikinu užsidirbus " + String.format("%.1f",(player.getBalance()) - 1000) + "€" + RESET);
        }
        else if(player.getBalance() < 1000){
            System.out.println(RED_BOLD + "Nepasiseke :( . Praradote " + String.format("%.1f",(1000 -  player.getBalance())) + "€" + RESET);
        }
        else {
            System.out.println("Išlipote sausas");
        }
    }
}

