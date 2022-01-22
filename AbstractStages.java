 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
public abstract class AbstractStages implements Comparable<AbstractStages> {
    protected Time time;

    protected item item;
    protected String stageName;

    public abstract void checkComplete();
    public abstract boolean isAvailable();
    public abstract double getBlockCount() ;
    public abstract double getStarvingCount() ;
    public abstract void addToStorage();
    public abstract boolean isBlock();     
    public abstract boolean isStarving();  
    public abstract void setBlock();
    public abstract LimitQueue<item> getNextQ();

    //standard Comparable<T> interface   
    public int compareTo(AbstractStages obj){
        // compare if stage hold an item whith its pTime (now + processing time)
        //===============================
        if( this.item.getpTime()< obj.item.getpTime())   {
            return -1; // true, insert before 
        } 
        else if( this.item.getpTime() == obj.item.getpTime()){
            return 0;
        }else
            return 1; // false
   
    }
    public String getStageName() {
        return stageName;
    }

    
     
    
}
