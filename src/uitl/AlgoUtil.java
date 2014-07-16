package uitl;

/**
 * 一些常用的工具函数
 * 
 * @author rayeaster
 *
 */
public class AlgoUtil 
{    
	// 打印函数
    public static void print(int b[])
    {
         for(int i=0; i< b.length; i++)
         {
             System.out.print(b[i] + (i%10 == 9 ? "\n":"\t"));
         }
         System.out.println();
    }
    
    //return index in tArray for val where tArray[index] is
    //the first element bigger than val in tArray 
    public static int getFirstBiggerIdx(int val, int[] tArray, int sIdx, int eIdx)
    {
    	int ret = -1;
    	
    	if(sIdx > eIdx)
    	{
    	   return ret;    	   
    	}
    	
    	if(sIdx < 0)
    	{
    	   return 0;
    	}
    	if(eIdx > tArray.length - 1)
    	{
    	   return tArray.length - 1;
    	}
    	
    	if(val < tArray[sIdx])
    	{
    	   return sIdx;
    	}
    	if(val > tArray[eIdx])
    	{
    	   return eIdx;
    	}
    	
    	while(sIdx < eIdx - 1)
    	{
        	ret = (eIdx + sIdx) / 2;
        	
    		if(val <= tArray[ret])
    		{
    		   eIdx = ret;
    		}
    		else
    		{
    		   sIdx = ret;    		   
    		}
    	}
    	
    	ret = eIdx;
    	
    	return ret;
    }
}