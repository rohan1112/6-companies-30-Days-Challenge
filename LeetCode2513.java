// ifPossible(Identify numbers which are divisible by both       
//                  Identify numbers which are divisible by only d1 i.e subtract both
//                  numbers which are divisible by only d2  i.e subtract both
//                  Identify numbers which are NOT divisible both i.e(maxValue-both-step2-step3)
//                  Now we have to put d1 numbers in arr2 and d2 numbers in arr1
//                  if all conditions are satisfied then we return true
// )
// minimizeSet(Apply binary search to identify the set from 1 to MAX_VALUE i.e 10^9 and 
//                  return the left value as minimize maximum number(X))

class Solution {
    public long lcm(long d1,long d2){
        return (d1*d2)/gcd(d1,d2);
    }
    public long gcd(long a,long b){
        if(b==0)
            return a;
        return gcd(b,a%b);
    }
    public boolean isPossible(long maxValue,long divisor1, long divisor2, long uniqueCnt1, long uniqueCnt2){

        long divisible_by_both=maxValue/lcm(divisor1,divisor2);
        long arr1_divisible_by_d1=(maxValue/divisor1)-divisible_by_both;
        long arr2_divisible_by_d2=(maxValue/divisor2)-divisible_by_both;

        long not_divisible_by_both=(maxValue- arr1_divisible_by_d1- arr2_divisible_by_d2- divisible_by_both);

        if(arr2_divisible_by_d2<uniqueCnt1){
            not_divisible_by_both -= (uniqueCnt1-arr2_divisible_by_d2);
        }
         if(arr1_divisible_by_d1<uniqueCnt2){
            not_divisible_by_both -= (uniqueCnt2-arr1_divisible_by_d1);
        }
        return (not_divisible_by_both>=0);
    }


    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {

        int left=1;
        int right=Integer.MAX_VALUE;

        while(left<right){
            int middle=left+(right-left)/2;

            if(isPossible(middle,divisor1,divisor2,uniqueCnt1,uniqueCnt2)){
                right=middle;
            }else{
                left=middle+1;
            }
        }
        return left;
        
    }
}
