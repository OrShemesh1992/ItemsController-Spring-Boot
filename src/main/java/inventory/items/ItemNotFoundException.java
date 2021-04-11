package inventory.items;

class ItemNotFoundException extends RuntimeException {

	ItemNotFoundException(Long no) {
	    super("Could not find employee " + no);
	  }
	}