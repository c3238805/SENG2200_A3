 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
public class S0 extends AbstractStages {
    private double M;
    private double N;

    private double itemBlockTime;     // count number of item beeing block
    private double StarvingTime;
    private double itemTakeTime;
    private double blockTime; 
    private int totalItemCreated;
    private int itemFinished;
    private boolean isBlock = false;    // return true if block (next queue is full)

    private LimitQueue<item> next;
        
    public S0 (double M,double N,String stageName ,LimitQueue<item> next,Time time){
     
        this.M = M;
        this.N = N;
        this.next =  next;   
        this.time = time;
        this.stageName = stageName;
        StarvingTime = 0;
        totalItemCreated = 0;
        
    }   
    // method to check whether the S0 has completed the processing an item.
    @Override
    public void checkComplete(){
        // input item to S0 
        if(time.now()<=10000000){
            // if no item been created

            //create an item
            item = new item();   
            item.setM(M);
            item.setN(N);
            item.setProcessTime();  //set P for this item
            item.setpTime(time.now());  // time.now() = T2
            
            totalItemCreated++;
             
        }
    }
    @Override
    public void addToStorage(){
            // add to next queue
            if(!next.isfull()&& item.getpTime()<= time.now()){
                                                
                //add to next queue
                next.add(item);

                itemFinished++;
                // create item after passed pre item into inner storage                       
                
                if(isBlock == true){
                    // calculate how long the item been block
                    itemTakeTime = time.now();
                    addBlockTime(itemTakeTime - itemBlockTime);
                    isBlock = false;
                }
                    
                                   
            }
            
        
    }
    
    // method of retrun the blockCount 
    @Override
    public double getBlockCount(){
        return blockTime;
    }
    @Override
    public double getStarvingCount(){
        return StarvingTime;
    }


    // return true if item is null
    @Override
    public boolean isAvailable(){
        return item == null;
    }
    @Override
    public boolean isBlock(){
        return next.isfull();
    }
    @Override
    public boolean isStarving(){
        // stage 0 generate the item, so isStarving is false by defult
        return false;       
    }
    
    public int getTotalItem(){
        return totalItemCreated;
    }
    // get number of item been generate by S0
    public int getitemFinished(){
        return itemFinished;
    }

    public void addStarvingTime(double t) {
        StarvingTime += t;
    }
    public void addBlockTime(double t){
        blockTime +=t;
    }
    @Override
    public String getStageName(){
        return stageName;
    }
    public void setBlock(){
        if(isBlock != true){
            itemBlockTime = time.now();
        }      // if next queue is full, block the stage
        isBlock = true; 
    }
    @Override
    public LimitQueue<item> getNextQ() {
        // return next queue
        return next;
    }
    
}
