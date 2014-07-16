package sorting;

import java.util.Stack;

import uitl.AlgoUtil;

/**
 * ���Ͷ��ֲ���
 * 
 * Solution 1 :
 *  �ݹ�
 * Solution 2 :
 *   �ǵݹ飬��ջ��¼ÿ�ζ��ֲ��ҵ������յ㡣
 *   
 * @author rayeaster
 *
 */
public class BiSearch 
{
    public static int RecursiveBiSearch(int tArray[], int val, int sIdx, int eIdx)
    {
        int ret = -1;
        
        if(sIdx > eIdx)
    	{
    	   return ret;    	   
    	}
    	
    	if(sIdx < 0)
    	{
    	   sIdx = 0;
    	}
    	if(eIdx > tArray.length - 1)
    	{
    	   eIdx = tArray.length - 1;
    	}
        
    	if(eIdx - sIdx <= 1)
    	{
           if(tArray[sIdx] == val)
           {
        	 return sIdx;
           }
           else if(tArray[eIdx] == val)
           {
        	 return eIdx;
           }
           else
           {
        	 return -1;
           }
    	}
    	
    	int midIdx = (sIdx + eIdx) / 2;
    	
    	if(val <= tArray[midIdx])
    	{
    	   ret = RecursiveBiSearch(tArray, val, sIdx, midIdx);
    	}
    	else
    	{
    	   ret = RecursiveBiSearch(tArray, val, midIdx, eIdx);
    	}
    	
        return ret;
    }

    public static int NonRecursiveBiSearch(int tArray[], int val)
    {
        int ret = -1;
        
        int sIdx = 0;
        int eIdx = tArray.length - 1;
        
        Stack<Integer> stackSIdx = new Stack<Integer>();
        Stack<Integer> stackEIdx = new Stack<Integer>();
        
        stackSIdx.push(sIdx);
        stackEIdx.push(eIdx);
        
        while(stackSIdx.size() > 0 && stackEIdx.size() > 0)
        {
        	sIdx = stackSIdx.peek();
        	eIdx = stackEIdx.peek();
        	
        	if(sIdx > eIdx)
        	{
        	   return -1;    	   
        	}
        	
        	if(sIdx < 0)
        	{
        	   sIdx = 0;
        	}
        	if(eIdx > tArray.length - 1)
        	{
        	   eIdx = tArray.length - 1;
        	}
        	
        	if(eIdx - sIdx <= 1)
        	{
               if(tArray[sIdx] == val)
               {
            	 return sIdx;
               }
               else if(tArray[eIdx] == val)
               {
            	 return eIdx;
               }
               else
               {
            	 return -1;
               }
        	}
            
            int midIdx = (sIdx + eIdx) / 2;
            if(val <= tArray[midIdx])
            {
               stackEIdx.pop();
               stackEIdx.push(midIdx);
            }
            else
            {
               stackSIdx.pop();
               stackSIdx.push(midIdx);
            }        	
        }
        return ret;
    }
    
    public static void main(String[] args){
    	testSolu1();
    	testSolu2();
    }
    
    public static void testSolu1()
    {
        int a1 = 8;
        int a2 = 3;
        int a[]={1,2,4,5,8,8,9,10,11,12,12,13,14};
        
        int idx1= RecursiveBiSearch(a, a1, 0, a.length - 1);
        int idx2= RecursiveBiSearch(a, a2, 0, a.length - 1);
        
        AlgoUtil.print(a);
        System.out.println(a1 + ":" + idx1);
        System.out.println(a2 + ":" + idx2);
    }
    
    public static void testSolu2()
    {

        int a1 = 8;
        int a2 = 3;
        int a[]={1,2,4,5,8,8,9,10,11,12,12,13,14};
        
        int idx1= NonRecursiveBiSearch(a,a1);
        int idx2= NonRecursiveBiSearch(a,a2);
        
        AlgoUtil.print(a);
        System.out.println(a1 + ":" + idx1);
        System.out.println(a2 + ":" + idx2);
    }
	
}
