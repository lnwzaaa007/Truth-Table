
import java.util.Scanner;

public class TruthTable {
   static  final int MAX = 100;
   static  int[] stack = new int[MAX];
   static  int top=-1;
   
    static void push(int value){    
        if((top+1)<MAX){
            stack[++top]=value;
        }
    }
    static int pop(){
        if(top>=0){
            return  stack[top--];
        }return 0;
    }
    static int Operator(int str1,int str2,char op){  
        if(op == '.'){
            return str1 & str2;

        }else if(op == '+'){
            return str1 | str2;

        }else if(op == '*'){
            return str1 ^ str2; 

        }
        return 0;
    }
    static int Solution(String B, int a, int b, int c, int d, int e, int f){
        int[] str = new int[MAX];
        int topstr=-1;

        for(int i=0;i<B.length(); i++){

            if(B.charAt(i)=='a' || B.charAt(i)=='w'){str[++topstr]='a';
            }
            else if(B.charAt(i)=='b' || B.charAt(i)=='x'){str[++topstr]='b';
            }
            else if(B.charAt(i)=='c' || B.charAt(i)=='y'){str[++topstr]='c';
            }
            else if(B.charAt(i)=='d' || B.charAt(i)=='z'){str[++topstr]='d';
            }
            else if(B.charAt(i)=='e'){str[++topstr]='e';
            }
            else if(B.charAt(i)=='1'){str[++topstr]='1';
            }
            else if(B.charAt(i)=='0'){str[++topstr]='0';
            }
            else if(B.charAt(i)=='f'){str[++topstr]='f';
            }
            else if(B.charAt(i)=='('){push('(');
            }
            else if(B.charAt(i)=='\''){str[++topstr]='\'';
            }
            else if(B.charAt(i)=='.'){push('.'); //AND
            }
            else if(B.charAt(i)=='+'){ push('+'); //OR
            }
            else if(B.charAt(i)=='*'){ push('*'); //XOR
            }
            else if(B.charAt(i)==')'){
                while(top!=-1 && stack[top]!='('){
                    str[++topstr]=pop();
                }pop();
            }
        }
        while(top>=0) {str[++topstr]=pop();}

        for(int i=0; i<=topstr; i++){

            char ch = (char)str[i];

            if(Character.isLetter(ch) || Character.isDigit(ch)){
                int value=0;
                if(ch == 'a') {value = a;}
                else if(ch == 'b') {value = b;}
                else if(ch == 'c') {value = c;}
                else if(ch == 'd') {value = d;}
                else if(ch == 'e') {value = e;}
                else if(ch == 'f') {value = f;}
                else if(ch == '1') {value = 1;}
                else if(ch == '0') {value = 0;}
                push(value);

            }else if(ch == '\'') {
                int not_ = pop();
                push(not_ == 0 ? 1 : 0 );

            }else if(ch == '.' || ch == '+' || ch == '*'){
                int str2 = pop();
                int str1 = pop();
                push(Operator(str1,str2,ch));

            } 
        }

        return pop();
    }
    public static void main(String[] args) {  //main function
        Scanner sn=new Scanner(System.in);

        System.out.print("How many variable(1-6) => ");
        int x = sn.nextInt();

        sn.nextLine();
        System.out.println("");
        System.out.println("Operator (and = .) (or = +) (not = ') (xor = *) (xnor = (xor)' )");
        System.out.println("Ex.1 (a.b)+c' \nEx.2 (b0d)'.e \nEx.3 (w0x).(y+z)");

        System.out.print("Input Boolean Expression => ");
        String Boolean = sn.nextLine();
        System.out.println("");

        for(int i=0;i<x;i++){
            System.out.printf("%c ",'A'+i);
        }System.out.println("  N  F");
        
        int rowe = 1<<x;

        int a=0,b=0,c=0,d=0,e=0,f=0;
        int c_min=-1,c_max=-1;
        int [] a_min = new int [rowe];
        int [] a_max = new int [rowe];

        for(int i=0; i<rowe; i++){
            
            a = (i >> (x-1)) &1 ;
            if(x>1) b = (i >> (x-2)) &1 ;
            if(x>2) c = (i >> (x-3)) &1 ;
            if(x>3) d = (i >> (x-4)) &1 ;
            if(x>4) e = (i >> (x-5)) &1 ;
            if(x>5) f = (i >> (x-6)) &1 ;
            
            int result = Solution(Boolean,a,b,c,d,e,f);

            for(int j=0; j<x; j++){
                System.out.printf("%d ",(i >> (x-j-1)) &1);
            }
            
            if(result == 1){
                System.out.printf("(%2d) %d <\n",i,result);
                a_min[++c_min] = i;

            }else if(result == 0){
                System.out.printf("(%2d) %d\n",i,result);
                a_max[++c_max] = i;
            }

            if((i+1)%4 == 0){System.out.println("");}
        }

        System.out.print("Minterm => ");
        for(int i=0; i<=c_min; i++){System.out.printf("%d ",a_min[i]);}

        System.out.println("");

        System.out.print("Maxterm => ");
        for(int i=0; i<=c_max; i++){System.out.printf("%d ",a_max[i]);}
    }
}
