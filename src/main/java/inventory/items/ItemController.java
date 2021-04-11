package inventory.items;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api")
class ItemController {

  private final ItemRepository repository;

  ItemController(ItemRepository repository) {
    this.repository = repository;
  }

  /** 
   * @return all items
   */
  @GetMapping("/items")
  List<Item> getAll() {
    return repository.findAll(Sort.by(Sort.Direction.ASC, "no"));
  }

  /**
   * Gets a new item and saves it 
   * @param newItem
   * @return
   */
  @PostMapping("/items/{name}")
  Item creatNewItem(@PathVariable String name) {
	  Item t= new Item(name,0);
    return repository.save(t);
  }


  /**
   * @param no
   * @return item if exists
   */
  @GetMapping("/items/{no}")
  Item getItemByID(@PathVariable Long no) {
    
    return repository.findById(no)
      .orElseThrow(() -> new ItemNotFoundException(no));
  }
  
  /**
   * delete item if exists
   * @param no
   */
  @DeleteMapping("/items/{no}")
  void deleteItem(@PathVariable Long no) {
    repository.deleteById(no);
  }
  /**
   * @param no
   * @param amount
   * @return the updated Item
   */
  @PutMapping("/items/deposit/{no}/{amount}")
  Item Deposit(@PathVariable Long no,@PathVariable int amount) {
	  Item item=repository.findById(no).get();
	  
	  if(repository.findById(no)!=null) {
		  item.setAmount(amount+item.getAmount());
		  return repository.save(item);
	  }else {
		    return repository.findById(no)
		    	      .orElseThrow(() -> new ItemNotFoundException(no));
	  }
  }

  /**
   * Checking if item can be withdrawn
   * @param no
   * @param amount
   * @return the updated Item
   */
  @PutMapping("/items/Withdrawal/{no}/{amount}")
  Item Withdrawal(@PathVariable Long no,@PathVariable int amount) {
	  Item item=repository.findById(no).get();
	  
	  if(repository.findById(no)!=null) {
		  int t=item.getAmount()-amount;
		  if (t>=0) {
			  item.setAmount(t);
		  }
		  return repository.save(item);
	  }else {
		    return repository.findById(no)
		    	      .orElseThrow(() -> new ItemNotFoundException(no));
	  }
  }
}