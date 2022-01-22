 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
public class endStage extends AbstractStages{

    private double StarvingTime ;

    private LimitQueue<item> pre;
    private LimitQueue<item> finished;

    private boolean isStarving = false;
    private boolean isBlock = false;

    private double itemTakeTime;
    private double itemStarve;


    public endStage (String stageName,LimitQueue<item> pre,LimitQueue<item> finished,Time time){
        this.time = time;
        this.pre = pre;
        this.stageName = stageName;
        this.finished = finished;
        
    }
    @Override
    public void checkComplete() {
        
        if(time.now()<=10000000 && isAvailable()){
            // if end stage have no item processing
            
                item = pre.peek();
                //get item from pre queue , if pre queue have item
                if(!pre.isEmpty() ){
                    
                    // poll First from pre queue 
                    item = pre.pollFirst();

                    //calculate the P for normalStage
                    item.setProcessTime(); 

                    item.setpTime(time.now());

                    if(isStarving == true){
                        // calculate how long the item been block
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
            //add into finished production queue

            if(item.getpTime()<= time.now()){     
    
                //add to finished queue
                finished.add(item);
                 
            }         
    }
    // method of retrun the blockCount 
    @Override
    public double getBlockCount(){
        double blockTime = 0;
        return blockTime ;       // end stage never get block
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
    public boolean isStarving(){
        return isStarving;
    }
    @Override
    public boolean isBlock(){
          return isBlock;  // end stage never get block
    }
    public void addStarvingTime(double t) {
        StarvingTime += t;
    }
    @Override
    public String getStageName(){
        return stageName;
    }
    public void setBlock(){
        isBlock = false; // end stage never get block
    }
    @Override
    public LimitQueue<item> getNextQ() {
        // end stage dont have next Queue
        return null;
    }


}
