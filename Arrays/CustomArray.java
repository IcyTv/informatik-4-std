
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * HAUSAUFGABE
 *
 * @author IcyTv
 * @version 05.10.2018
 */
public class CustomArray
{
    private int[] myArray;
    private ArrayList<Integer> myList;
    
    public CustomArray(int length)
    {
        myArray = new int[length];
        myList = new ArrayList<Integer>();
    }
    
    public void fillRandom(){
        myList.clear();
        for(int i = 0; i < myArray.length; i++){
            int tmp = (int)(Math.random() * 100) + 1;
            myArray[i] = tmp;
            myList.add(tmp);
        }
    }
    
    public void fillRandomNoRepeat(){
        myList.clear();
        if(myArray.length > 100){
            fillRandom();
            return;
        }
        Integer[] tmp = new Integer[100];
        Arrays.setAll(tmp, i -> i + 1);
        ArrayList<Integer> tmp2 = new ArrayList<Integer>(Arrays.asList(tmp));
    
        for(int i = 0; i < myArray.length; i++){
            int num = tmp2.remove((int)(Math.random() * tmp2.size()));
            myArray[i] = num;
            myList.add(num);
        }
    
    }
    
    public int max(boolean array){
        int max = 0;
        if(array){
            for(int i = 0; i < myArray.length; i++){
                if(max < myArray[i]){
                    max = myArray[i];
                }
            }
        } else {
            max = Collections.max(myList);
        }
        return max;
    }
    
    public int min(boolean array){
        int min = Integer.MAX_VALUE;
        if(array){
            for(int i = 0; i < myArray.length; i++){
                if(min > myArray[i]){
                    min = myArray[i];
                }
            }
        } else {
            min = Collections.min(myList);
        }
        return min;
    }
    
    public int sum(boolean array){
        int sum = 0;
        if(array){
            for(int i: myArray){
                sum += i;
            }
        } else {
            for(int i:myList){
                sum += i;
            }
        }
        return sum;
    }
    
    public int sumOdd(boolean array){
        int sum = 0;
        if(array){
            for(int i: myArray){
                if(i % 2 == 1){
                    sum += i;
                }
            }
        } else {
            for(int i:myList){
                if(i % 2 == 1){
                    sum += i;
                }
            }
        }
        return sum;
    }
    
    public int sumEven(boolean array){
        int sum = 0;
        if(array){
            for(int i: myArray){
                if(i % 2 == 0){
                    sum += i;
                }
            }
        } else {
            for(int i:myList){
                if(i % 2 == 0){
                    sum += i;
                }
            }
        }
        return sum;
    }
    
    public void threshold(boolean array, int threshold){
        if(array){
            for(int i = 0; i < myArray.length; i++){
                myArray[i] = myArray[i] > threshold ? 1: 0;
            }
        } else {
            for(int i = 0; i < myList.size(); i++){
                myList.set(i, myList.get(i) > threshold ? 1: 0);
            }
        }
    }
    
    public void readFile(String filename) throws Exception{
        myList.clear();
        BufferedReader b = new BufferedReader(new FileReader(new File(filename)));
        String line = "";
        while((line = b.readLine()) != null){
            System.out.println(line);
            for(String val: line.split(",")){
                myList.add(Integer.parseInt(val));
            }
        }
        myArray = myList.stream().mapToInt(i->i).toArray();
        
    }
}
