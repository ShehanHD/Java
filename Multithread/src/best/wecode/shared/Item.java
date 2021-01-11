package best.wecode.shared;

public class Item {
    public String cod;
    public String name;
    public String openingBid;
    public String lastBid;
    public String lastBidder;

    public Item(String cod, String name, String openingBid, String lastBid, String lastBidder){
        this.cod = cod;
        this.name = name;
        this.openingBid = openingBid;
        this.lastBid = lastBid;
        this.lastBidder =lastBidder;
    }
}
