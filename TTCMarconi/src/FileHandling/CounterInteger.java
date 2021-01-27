package FileHandling;

public class CounterInteger implements  ICounter {
private int[] numbers;

public CounterInteger(String [] num){
 numbers = new int[num.length];
  for(int i = 0;i <num.length;i++){
        numbers[i] = Integer.parseInt(num[i]);
    }
}
  public String countAction(){
   int oddCount = 0;
   int evenCount = 0;
   for(int i = 0;i <numbers.length;i++){
    if(numbers[i]%2 != 0) {
       oddCount++;
    }else evenCount++;
    }
      String res ="Odd numbers are: "+oddCount+" "+"Even numbers are "+evenCount;
    return res;
  }
}
