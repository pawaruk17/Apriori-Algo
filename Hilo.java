package product;
class Kmean
{
public static void main(String args[])
{
int N=9;//total no of data sets
int arr[]={2,4,10,12,3,20,30,11,25};    // initial data
int i,m1,m2,a,b,n=0;//m1 & m2 are mean for cluster1 & cluster2 resp n is for counting iterations
boolean flag=true;
float sum1=0,sum2=0;
a=arr[0];b=arr[1];//taking seed values as 2 & 4
m1=a; m2=b;
int cluster1[]=new int[9],cluster2[]=new int[9];
for(i=0;i<9;i++)
    System.out.print(arr[i]+ "\t");
System.out.println();

do
{
 sum1=0;sum2=0;
 for(i=0;i<9;i++)
 {
        cluster1[i]=0;
        cluster2[i]=0;
 }
 n++;
 int k=0,j=0;
 for(i=0;i<9;i++)
 {
    if(Math.abs(arr[i]-m1)<=Math.abs(arr[i]-m2))
    {   cluster1[k]=arr[i];
        k++;
    }
    else
    {   cluster2[j]=arr[i];
        j++;
    }
 }
    System.out.println();
    for(i=0;i<9;i++)
        sum1=sum1+cluster1[i];
    for(i=0;i<9;i++)
        sum2=sum2+cluster2[i];
    a=m1;
    b=m2;
    m1=Math.round(sum1/k);
    m2=Math.round(sum2/j);
    if(m1==a && m2==b)
        flag=false;
    else
        flag=true;

    System.out.println("After iteration "+ n +" , cluster 1 :\n");    //printing the clusters of each iteration
    for(i=0;i<9 ;i++){
        if(cluster1[i]!=0)
        System.out.print(cluster1[i]+ "\t");
    }
    System.out.println("\n");
    System.out.println("After iteration "+ n +" , cluster 2 :\n");
    for(i=0;i<9;i++){
        if(cluster2[i]!=0)
        System.out.print(cluster2[i]+ "\t");
    }

}while(flag);

    System.out.println("\nFinal cluster 1 :\n");            // final clusters
    for(i=0;i<9;i++){
        if(cluster1[i]!=0)
        System.out.print(cluster1[i]+ "\t");
    }
    System.out.println();
    System.out.println("Final cluster 2 :\n");
    for(i=0;i<9;i++){
        if(cluster2[i]!=0)
        System.out.print(cluster2[i]+ "\t");
    }
 }
}