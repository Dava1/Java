package FileHandling;

public class CounterDouble implements ICounter {
    private double[] numbers;

    public CounterDouble(String [] num){
        numbers = new double[num.length];
        for(int i = 0;i <num.length;i++){
            numbers[i] = Double.parseDouble(num[i]);
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
