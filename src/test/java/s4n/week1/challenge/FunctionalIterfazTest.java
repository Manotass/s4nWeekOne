package s4n.week1.challenge;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalIterfazTest {

    @FunctionalInterface
    public interface sumIterface{
        int sum(int a, int b);
    }

    public int numberSum(int a, int b, sumIterface s ){
        return s.sum(a,b);
    }

    @Test
    public void InterfaceTest(){
        sumIterface s=(a,b)->a+b;
        assertEquals(8,numberSum(2,6,s));
    }

    @Test
    public void composeTest(){
        Function<Integer, Integer> doubleNumber= n->n*2;
        Function<Integer,String> toString=n->"The double of the number is: "+n;
        Function<Integer, String> multiplyThenToString= toString.compose(doubleNumber);
        assertEquals("The double of the number is: 10", multiplyThenToString.apply(5));
    }
    BiFunction<Integer,Integer,Integer> multiply=(a,b)->a*b;

    public String multiplyDescription( BiFunction<Integer,Integer,String> multiply, int a,int b){
       return multiply.apply(a,b);
    }

  @Test
    void biFunctionTest(){
      assertEquals(48,multiply.apply(6,8).intValue());
      String result=multiplyDescription((a,b)->"The result is: "+a*b,6,8);
      assertEquals("The result is: 48",result);
  }

  @Test
    void supplyTest(){
        int[] number={6,8};
      Supplier<Integer> multiply=()->{return number[0]*number[1];};
      assertEquals(48,multiply.get().intValue());
  }

  @Test
  void predicateTest(){
      List<String> names = Arrays.asList("Maria", "Andres", "Marcos", "Juan", "Jose", "Diego");
      List<String> namesThatContainsANoCaseSensitive = names.stream().filter(name -> name.toUpperCase().contains("A")).collect(Collectors.toList());
      assertEquals("[Maria, Andres, Marcos, Juan]",namesThatContainsANoCaseSensitive.toString());
    }

}
