
import java.util.Scanner;

public class TruthTable {
   static  final int MAX = 100;
   static  int[] stack = new int[MAX];
   static  int top=-1;
   static  int[] str = new int[MAX];
   static  int topstr=-1;


    static void push(int value){
        if((top+1)<MAX){
            stack[++top]=value;
        }
    }
    static int pop(){
        int result=0;
        if(top>=0){
            result = stack[top--];
            
        }return result;
    }
    static int Operater(int str1,int str2,char op){
        if(op == '.'){
            return str1 & str2;
        }else if(op == '+'){
            return str1 | str2;
        }

        return 0;
    }
    static int Solution(String B, int a, int b, int c, int d, int e){
        // System.out.printf("%d %d %d %d\n",a,b,c,d);
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
            else if(B.charAt(i)=='('){push('(');
            }
            else if(B.charAt(i)=='.'){push('.');
            }
            else if(B.charAt(i)=='+'){ push('+');
            }
            else if(B.charAt(i)==')'){
                while(top!=-1 && stack[top]!='('){
                    str[++topstr]=pop();
                }pop();
            }
        }
        while(top>=0) {str[++topstr]=pop();}

        for(int i=0; i<topstr; i++){
            char ch = (char)str[i];
            if(Character.isLetter(ch)){
                int value=0;
                if(ch == 'a') {value = a;}
                else if(ch == 'b') {value = b;}
                else if(ch == 'c') {value = c;}
                else if(ch == 'd') {value = d;}
                else if(ch == 'e') {value = e;}
                push(value);
            }else if(ch == '\'') {
                int not_ = pop();
                push(not_ == 0 ? 1 : 0 );
            }else if(ch == '.' || ch == '+'){
                int str2 = pop();
                int str1 = pop();
                push(Operater(str1,str2,ch));
            }
        }

        return pop();
    }
    public static void main(String[] args) {
        Scanner sn=new Scanner(System.in);
        System.out.print("How many variable => ");
        int x = sn.nextInt();
        sn.nextLine();
        System.out.print("Input Boolean => ");
        String Boolean = sn.nextLine();
        
        // int []a=new int[32];
        // int []b=new int[32];
        // int []c=new int[32];
        // int []d=new int[32];
        // int []e=new int[32];
        int a=0,b=0,c=0,d=0,e=0;

        int rouw = 1<<x;
        for(int i=0; i<rouw; i++){
            // a[i] = (i >> (x-1)) &1 ;
            // if(x>1) b[i] = (i >> (x-2)) &1 ;
            // if(x>2) c[i] = (i >> (x-3)) &1 ;
            // if(x>3) d[i] = (i >> (x-4)) &1 ;
            // if(x>4) e[i] = (i >> (x-5)) &1 ;
            
            a = (i >> (x-1)) &1 ;
            if(x>1) b = (i >> (x-2)) &1 ;
            if(x>2) c = (i >> (x-3)) &1 ;
            if(x>3) d = (i >> (x-4)) &1 ;
            if(x>4) e = (i >> (x-5)) &1 ;
            
            int result = Solution(Boolean,a,b,c,d,e);
            // Solution(Boolean,a[i],b[i],c[i],d[i],e[i]);
            System.out.printf(")>%d\n",result);
        }
    }
}



