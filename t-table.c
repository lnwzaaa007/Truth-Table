#include<stdio.h>
#include<math.h>
#include<ctype.h>
#define MAX 100

int stack[MAX]; //กำหนดขนาดstack
int top =-1;

void push(int value){ //ฟังก์ชันรับข้อมูลเข้ามาในstack
    if((top+1)<MAX){
        top++;
        stack[top]=value;
    }
}
int pop(){  //ฟังก์ชันึดึงข้อมูลออกจากstack
    if(top>=0){
        int value=stack[top];
        top--;
        return value;
    }
    
}
int Operation(int str1,int str2,char op){
    if(op=='.'){
        return str1&&str2;
    }else if(op=='+'){
        return str1||str2;
    }
}
int Solution(char *eq,int a,int b,int c,int d, int e){
    int str[50];
    int topstr=-1;
    for(int i=0;eq[i]!='\0';i++){
        if(eq[i]=='a'||eq[i]=='w') str[++topstr]='a';
        else if(eq[i]=='b'||eq[i]=='x') str[++topstr]='b';
        else if(eq[i]=='c'||eq[i]=='y') str[++topstr]='c';
        else if(eq[i]=='d'||eq[i]=='z') str[++topstr]='d';
        else if(eq[i]=='e') str[++topstr]='e';
        else if(eq[i]=='(') push('(');
        else if(eq[i]=='\'') str[++topstr]='\'';
        else if(eq[i]=='.') push('.');
        else if(eq[i]=='+') push('+');
        else if(eq[i]==')') {
            while(top>=0 && stack[top]!='('){
                str[++topstr]=pop();
            }pop();
        }
    }
    while(top!=-1){
        str[++topstr]=pop();

    }

    for(int i=0;i<=topstr;i++){
        char ch=str[i];
        if(isalpha(ch)){
            int value=0;
            if(ch=='a') value=a;
            else if(ch=='b') value=b;
            else if(ch=='c') value=c;
            else if(ch=='d') value=d;
            else if(ch=='e') value=e;
            push(value);
        }else if(ch=='\''){
            int not=pop();
            push(!not);
        }else if(ch=='.'||ch=='+'){
            int str2=pop();
            int str1=pop();
            push(Operation(str1,str2,ch));
        }
    }return pop();
}

void main(){  //ฟังก์ชันหลัก
    int x;
    printf("How many variable => ");
    scanf("%d",&x);

    char equation[50];
    printf("Input Boolean (Ex. (a.b)+c or (w+y).x ) => ");
    scanf("%s",equation);

    int rowe=(int)pow(2,x);
    int a[32]={0},b[32]={0},c[32]={0},d[32]={0},e[32]={0};
    // ใส่ค่าาในตาราง
    for(int i=0;i<rowe;i++){
        a[i] = (i>>(x-1)) &1;
        if(x>1) b[i]=(i>>(x-2)) &1;
        if(x>2) c[i]=(i>>(x-3)) &1;
        if(x>3) d[i]=(i>>(x-4)) &1;
        if(x>4) e[i]=(i>>(x-5)) &1;
    }

    for(int i=0;i<x;i++){
        printf("%c ",'A'+i);

    }printf("%5c\n",'F');
    
    int count_min=0, count_max=0;
    int a_min[32], a_max[32];

    for(int i=0; i<rowe; i++){
        int result=0;
        result=Solution(equation,a[i],b[i],c[i],d[i],e[i]);

        for(int j=0;j<x;j++){
            printf("%d ",(i>>(x-j-1)) &1);
        }

        if(result == 1){
            printf(" %2d %d <",i,result);
            a_min[count_min++] = i;

        }
        else if(result == 0){
            printf(" %2d %d ",i,result);
            a_max[count_max++] = i;
        }
        if((i+1)%4==0){
            printf("\n——————————");
        }
        printf("\n");
    }
    printf("Mintrem => ");
    for(int i = 0;i<count_min; i++){
        printf("%d ",a_min[i]);

    }printf("\n");

    printf("Maxterm => ");
    for(int i = 0; i<count_max;  i++){
        printf("%d ",a_max[i]);
    }

}