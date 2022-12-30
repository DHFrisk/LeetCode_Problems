package RomanNumbers;

import java.util.Scanner;
public class RomanNumbers {

    // LeetCode problem url/link: https://leetcode.com/problems/roman-to-integer/

    /* roman number symbols
    I=1, V=5, X=10, L=50, C=100, D=500, M=1000
    */

    /* roman numbers rules
    - When numbers are placed to the right of others, it means a sum.

    - When numbers are placed to the left of others, it means a
    substraction.

    - Symbols can only be written consecutively up to 3 times.

    - Symbols D, L and V can only be placed at the right and repeated only
    one time. These symbols cannot be used to substract value (cannot be
    placed at the left side of another symbol).

    - To write number highter/greater than 3999 put a horizontal line above
    the expression and this will be multiplied by 1000, 2 lines above means
    multiplied by 1000000.

    - Substraction summary:
        - I can be placed before V (5) and X (10) to make 4 and 9.
        - X can be placed before L (50) and C (100) to make 40 and 90.
        - C can be placed before D (500) and M (1000) to make 400 and 900.
    */

    private String inputNumber=null;
    private int i=1,v=5,x=10,l=50,c=100,d=500,m=1000;
    private int repI=0,repV=0,repX=0,repL=0,repC=0,repD=0,repM=0;

    protected void doIt(){
        requestInput();
        evaluateRepetition();
        isValid();
    }
    private void requestInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a valid roman number between 1 and 3999: \t");
        this.inputNumber=scanner.nextLine();
    }

    private void isValid(){
//        if (this.repI<=3 && this.repV<=3 && this.repX<=3 && this.repL<=3 && this.repC<=3 && this.repD<=3 && this.repM<=3){
        if (this.repV<=3 && this.repL<=3 && this.repD<=3 && evaluateConsecutiveRepetition()){
            int length=this.inputNumber.length();
            int number=0;
            for (int i=0; i<length; i++){
                char charVal=this.inputNumber.charAt(i);
                if (i<length-1){
                    char charValNext=this.inputNumber.charAt(i+1);
                    if (getSingleValue(charValNext) > getSingleValue(charVal)){
                        if (charVal == 'I') {
                            number=number+(iRule(charValNext)-getSingleValue(charVal));
                        }
                        else if (charVal == 'X'){
                            number=number+(xRule(charValNext)-getSingleValue(charVal));
                        }
                        else if (charVal == 'C'){
                            number=number+(cRule(charValNext)-getSingleValue(charVal));
                        }
                        i=i+1;
                    }else{
                        number=number+getSingleValue(charVal);
                    }
                }else{
                    number=number+getSingleValue(charVal);
                }
                System.out.println(number);
            }
        }else{
            System.out.println("One or more symbols are written more than 3 times, please input a valid number.");
        }

    }

    private int iRule(char s){
       // I symbol can only be followed by V or X
       switch(s) {
           case 'V':
               return this.v;
           case 'X':
               return this.x;
           default :
               return 0;
       }
    }

    private int xRule(char s){
        // V symbol can only be followed by V or X
        switch(s) {
            case 'L':
                return this.l;
            case 'C':
                return this.c;
            default :
                return 0;
        }
    }

    private int cRule(char s){
        // D symbol can only be followed by V or X
        switch(s) {
            case 'D':
                return this.d;
            case 'M':
                return this.m;
            default :
                return 0;
        }
    }

    private int getSingleValue(char s){
        switch(s){
            case 'I':
                return this.i;
            case 'V':
                return this.v;
            case 'X':
                return this.x;
            case 'L':
                return this.l;
            case 'C' :
                return this.c;
            case 'D':
                return this.d;
            case 'M':
                return this.m;
            default:
                return 0;
        }
    }
    private void evaluateRepetition(){
        //int i=0,v=0,x=0,l=0,c=0,d=0,m=0;
        int length= this.inputNumber.length();
        for (int counter=0; counter<length; counter++){
            switch (this.inputNumber.charAt(counter)) {
//                case 'I':
//                    this.repI++;
//                    break;
                case 'V':
                    this.repV++;
                    break;
//                case 'X':
//                    this.repX++;
//                    break;
                case 'L':
                    this.repL++;
                    break;
//                case 'C' :
//                    this.repC++;
//                    break;
                case 'D':
                    this.repD++;
                    break;
//                case 'M':
//                    this.repM++;
//                    break;
//                default:
//                    break;
            }
        }
    }

    private boolean evaluateConsecutiveRepetition(){
        //int i=0,v=0,x=0,l=0,c=0,d=0,m=0;
        boolean flag=true;
        int length= this.inputNumber.length();
        char s, s1, s2, s3;
        for (int counter=0; counter<length; counter++){
            if (counter <= length-4){ // if the expression is large enough
                s=this.inputNumber.charAt(counter);
                s1=this.inputNumber.charAt(counter+1);
                s2=this.inputNumber.charAt(counter+2);
                s3=this.inputNumber.charAt(counter+3);
                if (s == s1 && s == s2 && s == s3) flag=false;
            }
        }
        return flag;
    }
}