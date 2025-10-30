import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player {
    private double balance;
    private int assetsAmount;

    public static final String RED_BOLD = "\033[1;31m";

    public void buy(Asset asset, int amount) {
        double fee = asset.getPrice()*amount*0.01;

        if(balance >= asset.getPrice()*amount+fee) {
            balance -= asset.getPrice()*amount+fee;
            assetsAmount += amount;
        }else{
            System.out.println(RED_BOLD + "Neturi tiek pinigų");
        }
    }
    public void sell(Asset asset, int amount) {
        double fee = asset.getPrice()*amount*0.01;

        if(assetsAmount >= amount) {
            assetsAmount -= amount;
            balance += asset.getPrice()*amount - fee;
        }
        else{
            System.out.println(RED_BOLD + "Neturi tiek coins");
        }
    }
}


