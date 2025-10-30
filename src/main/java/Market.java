import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Market {
    private Asset asset;
    private Random random = new Random();

    public Market(Asset asset) {
        this.asset = asset;
    }

    public void updatePrice() {
        double change = 1 + (random.nextDouble() - 0.5) * 0.8;
        asset.setPrice(asset.getPrice() * change);
    }
}
