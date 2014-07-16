package sorting;

import uitl.AlgoUtil;

/**
 * �ϲ�������������
 * Solution 1 :
 *  �½�һ������Ȼ��һ����������š��临�Ӷ�Ϊa+b
 * Solution 2 :
 *   ��B���ö��ַ�ѡȡ���������Ԫ�ؿ飬ֱ�Ӳ�������Ԫ�أ�
 *   
 *   ��ʼֵx=0;b[x]Ӧ���뵽a[cur]��λ��(���ַ��õ�λ�ã���   
 *   ��ôֱ��b�����һ��С��a[cur+1]��Ԫ��b[y](���ַ��õ�y)��y-x����
 *   ��Ӧ���β��뵽��ǰa[cur]��λ�ã�
 *   
 *   ��a[cur+1]��ʼʣ���Ԫ�أ���Ӧ����ƶ�y-x��λ�á�
 *   ��ɺ���b[y]֮ǰ��Ԫ�ض��Ѿ����롣
 *   
 *   Ȼ��x��Ϊy+1,�ظ����ϲ�����ֱ������b��Ԫ�ض�����a��
 *   
 *   ���ֲ���ʱ��λ�ü������ƣ������ֲ��������λ�ÿ�ʼ�������ֽ����˲��ָ��Ӷ�
 *   ���Ӷȴ���Ϊlog2(a)+log2(b). ���ʱ�ȽϺ��ƶ���a+b��
 * 
 * @author rayeaster
 *
 */

public class Combine2SortedArray 
{
	
	//������������ĺϲ�����,��ǰ����Ҳ���ԴӺ���ǰ
    public static int[] MergeList1(int a[],int b[])
    {
        int result[];  
        result = new int[a.length+b.length];
        
        int i=0,j=0,k=0;

        while(i<a.length && j<b.length)
        {
            if(a[i] <= b[j]) 
            {
                result[k++] = a[i++];
            }
            else
            {
                result[k++] = b[j++];
            }
        }
        
        while(i < a.length) 
        {
        	result[k++] = a[i++];
        }
        
        while(j < b.length)
        {
            result[k++] = b[j++];
        }       
        return result;
    }

    //a is the bigger array
	public static int[] MergeList2(int[] a, int[] b) {
		int sIdx = 0;
		int eIdx = a.length - 1;
		
		for(int i = 0;i < b.length && sIdx <= eIdx;i++)
		{
			int bIdx = AlgoUtil.getFirstBiggerIdx(b[i], a, sIdx, eIdx);
			
			int aIdx = AlgoUtil.getFirstBiggerIdx(a[bIdx], b, i, b.length - 1);
			
			int moveSize = aIdx - i;//start from 0
			
			//move elements in a to make room
			for(int s = 0;s < moveSize;s++)
			{
				for(int m = a.length - 1;m >= bIdx && m > 0;m--)
				{			
				    a[m] = a[m - 1];
				}
			}
			
			//move elements in b to a
			for(int j = i, tmp = bIdx;j < i + moveSize && j <= b.length - 1 && tmp <= a.length - 1;)
			{
				a[tmp++] = b[j++];
			}
			
			a[bIdx] = b[i];
			
			if(moveSize > 0)
			{
			   i += moveSize - 1;				
			}
			
			sIdx = bIdx + 1;
			
			//AlgoUtil.print(a);
		}		
			
		return a;
	}
	
	public static void main(String[] args){
    	testSolu1();
    	testSolu2();
    }
	
    public static void testSolu1()
    {
        int a[]={1,2,2,3,5,6,7,7};
        int b[]={1,2,4,5,8,8,9,10,11,12,12,13,14};
        
        int c[]= MergeList1(a,b);
        
        if(c!=null)
        {
        	AlgoUtil.print(c);
        }
    }
    
    public static void testSolu2()
    {
        int b[]={1,2,2,3,6,7};
        
        int a[]= new int[10]; 
        for(int i = 0;i < a.length;i++)
        {
        	a[i] = Integer.MAX_VALUE;
        }
        a[0]=1;a[1]=2;a[2]=4;a[3]=5;
        
        int c[]= MergeList2(a,b);
        
        if(c!=null)
        {
        	AlgoUtil.print(c);
        }
    }
	
}
