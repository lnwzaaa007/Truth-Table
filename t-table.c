#include<stdio.h>
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
void main(){  //ฟังก์ชันหลัก
    int num;
    do{
        printf("Enter number: ");
        scanf("%d",&num);
        if(num>0){
            push(num);
        }
    }while(num!=0);
    do{
        printf("%d\n",pop());
    }while(top>=0);
    
}