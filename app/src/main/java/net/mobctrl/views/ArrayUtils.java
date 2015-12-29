package net.mobctrl.views;

/**
 * Created by supengchao on 2015/11/12.
 */
public class ArrayUtils {

    /**
     * 返回数组最大值
     *
     * @param a
     * @return
     */
    public static int max(int[] a){
        // 返回数组最大值
        int x;
        int aa[]=new int[a.length];
        System.arraycopy(a,0,aa,0,a.length);
        x=aa[0];
        for(int i=1;i<aa.length;i++){
            if(aa[i]>x){
                x=aa[i];
            }
        }
        return x;
    }

    /**
     * 返回数组最小值
     *
     * @param a
     * @return
     */
    public static int min(int[] a){
        // 返回数组最小值
        int x;
        int aa[]=new int[a.length];
        System.arraycopy(a,0,aa,0,a.length);
        x=aa[0];
        for(int i=1;i<aa.length;i++){
            if(aa[i]<x){
                x=aa[i];
            }
        }
        return x;
    }

    /**
     * 将一个数组的第N到M项由小到大排列,将排列后的结果返回
     *
     * @param aa
     * @param N
     * @param M
     * @return
     */
    public static double[] up_order(double[] aa,int N,int M){
        // 将一个数组的第N到M项由小到大排列,将排列后的结果返回
        double q[]=new double[M];
        double[] a=new double[aa.length];
        System.arraycopy(aa,0,a,0,aa.length);
        for(int kk=0;kk<(M-N);kk++){
            for(int k=N-1;k<(M-1);k++){
                if(a[k]>a[k+1]){
                    q[k]=a[k];
                    a[k]=a[k+1];
                    a[k+1]=q[k];
                }
            }
        }
        return a;
    }

    /**
     * 将一个数组的第N到M项由大到小排列,将排列后的结果返回
     *
     * @param a
     * @param N
     * @param M
     * @return
     */
    public static double[] down_order(double[] a,int N,int M){
        // 将一个数组的第N到M项由大到小排列,将排列后的结果返回
        double[] q=new double[M];
        double[] aa=new double[a.length];
        System.arraycopy(a,0,aa,0,a.length);
        for(int kk=0;kk<(M-N);kk++){
            for(int k=N-1;k<(M-1);k++){
                if(aa[k]<aa[k+1]){
                    q[k]=aa[k];
                    aa[k]=aa[k+1];
                    aa[k+1]=q[k];
                }
            }
        }
        return aa;
    }

    /**
     * 将一个数组的第N与M项交换,将交换后的结果返回
     *
     * @param aa
     * @param N
     * @param M
     * @return
     */
    public static double[] change(double[] aa,int N,int M){
        // 将一个数组的第N与M项交换,将交换后的结果返回
        double[] a=new double[aa.length];
        System.arraycopy(aa,0,a,0,aa.length);
        double mid=a[M-1];
        a[M-1]=a[N-1];
        a[N-1]=mid;
        return a;
    }

    /**
     * 将一个数组从第N项到M项倒序交换,将排列后的结果返回
     *
     * @param aa
     * @param N
     * @param M
     * @return
     */
    public static double[] changeAll(double[] aa,int N,int M){
        // 将一个数组从第N项到M项倒序交换,将排列后的结果返回
        double[] a=new double[aa.length];
        System.arraycopy(aa,0,a,0,aa.length);
        double q[]=new double[M];
        for(int k=N-1;k<((M+N)/2);k++){
            q[k]=a[k];
            a[k]=a[(M+N)-k-2];
            a[(M+N)-k-2]=q[k];
        }
        return a;
    }

    /**
     * 将M分解因式后赋值给数组
     *
     * @param M
     * @return
     */
    public static int[] factor(int M){
        // 将M分解因式后赋值给数组a
        int n=0;
        int M1=M;
        for(int k=2;k<M;k++){
            if((M%k)==0){
                n++;
                M=M/k;
                k=1;
            }
        }
        n++;
        int[] a=new int[n];
        n=0;
        for(int k=2;k<M1;k++){
            if((M1%k)==0){
                a[n]=k;
                n++;
                M1=M1/k;
                k=1;
            }
        }
        a[n]=M1;
        return a;
    }
}
