 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
import java.util.UUID;

// Java program implementing Singleton class
// with getInstance() method
class Singleton 
{
	// static variable single_instance of type Singleton
	private static Singleton single_instance = null;

	// variable of type item
	public item insertItem;

	// private constructor restricted to this class itself
	public Singleton()
	{
		
	}
	public String createId(){
		String uniqueID = UUID.randomUUID().toString().replace("-","") ; 
		return  uniqueID;
	}
	// static method to create instance of Singleton class
	public static Singleton getInstance()
	{
		if (single_instance == null)
			single_instance = new Singleton();

		return single_instance;
	}
    
    
    
}


