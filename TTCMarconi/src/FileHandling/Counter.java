package FileHandling;

public class Counter implements ICounter {
   private ICounter counter;

   public Counter(String[] num){
       if(num[0].contains(".") ){
           this.counter = new CounterDouble(num);
       }else this.counter = new CounterInteger(num);

   }
    @Override
    public String countAction() {
        return counter.countAction();
    }
}
