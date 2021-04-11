package inventory.items;


import java.util.Comparator;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;


@Entity
class Item {

  private @Id @GeneratedValue Long no;
  
  
  private String name;

  private int amount;
  
  private @GeneratedValue(strategy = GenerationType.AUTO) String code;

  Item() {}

  Item(String name,int amount) {

    this.name = name;
    this.amount = amount;
  }


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public Long getNo() {
	return no;
}

public String getCode() {
	return code;
}

@PrePersist
private void ensureId(){
	this.code=UUID.randomUUID().toString();
}

@Override
public String toString() {
	return "Item [no=" + no + ", name=" + name + ", amount=" + amount + ", code=" + code + ", toString()="
			+ super.toString() + "]";
}

}
class Sortbyno implements Comparator<Item> {
    // Used for sorting in ascending order of
    // no
    public int compare(Item a, Item b)
    {
        return a.getNo().compareTo(b.getNo());
    }
}