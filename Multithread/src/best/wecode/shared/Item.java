package best.wecode.shared;

public class Item {
    public String cod;
    public String name;
    public String openingBid;
    public String lastBid;
    public String lastBidder;
    public String startsAt;
    public String endsAt;

    public Item(String cod, String name, String openingBid, String lastBid, String lastBidder, String startsAt, String endsAt){
        this.cod = cod;
        this.name = name;
        this.openingBid = openingBid;
        this.lastBid = lastBid;
        this.lastBidder =lastBidder;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }
}
