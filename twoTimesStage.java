 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
public class twoTimesStage extends AbstractStages{

    private double blockTime;     // count number of item beeing block
    private double StarvingTime ;

    private LimitQueue<item> pre;
    private LimitQueue<item> next;
    private boolean isBlock = false;    // return true if block (next queue is full)
    private boolean isStarving = false;    // return true if block (next queue is full)

    private double itemTakeTime;
    private double itemStarve;
    private double itemBlockTime;
    public twoTimesStage (String stageName,LimitQueue<item> pre , LimitQueue<item> next,Time time){
       
        this.time = time;
        this.pre = pre;
        this.next = next;
        this.stageName = stageName;
        
    }
    @Override
    public void checkComplete() {
        
        if(time.now()<=10000000){
            // check if current stage have item
                item = pre.peek();  
                if(!pre.isEmpty() ){ // if pre queue is not emplty                 
                    // poll First from pre queue 
                    item = pre.pollFirst();

                    //calculate the 2*M and 2*N for normalStage
                    item.setdoubleProcessTime(); 
                    //update current time 
            
                    item.setpTime(time.now());
                    if(isStarving == true){
                        itemTakeTime = time.now();
                        addStarvingTime(itemTakeTime - itemStarve);
                        isStarving = false;
                    }
                    
                }
                else {
                    if(isStarving != true){
                        itemStarve = time.now();
                    }
                    isStarving = true;
                    
                }
            
        }
    }
    @Override    
    public void addToStorage() {

        if(!isBlock()&&item.getpTime()<= time.now()){
                        
            //add to next queue
            next.add(item);                                                     

            itemTakeTime = time.now();
            if(isBlock == true){
                // calculate how long the item been block
                addBlockTime(itemTakeTime - itemBlockTime);
                isBlock = false;
            }
                               
        }
        
        
    }
    @Override
    public boolean isAvailable() {
        return item == null;
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
    @Override
    public boolean isStarving(){
        return isStarving;
    }
    @Override
    public boolean isBlock(){
        return next.isfull();
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
