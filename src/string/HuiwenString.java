package string;

import java.util.Arrays;


/**
 * ������ִ�
 * 
 * ���ظ����ַ�����������ִ�, manacher�㷨�����ö�̬�滮˼��
 * ÿ�θ��ݵ�ǰ�����ַ�array[i]�Ļ��İ뾶radius[i]��
 * ������ൽ�����ַ�[i+1, radius[i] + i]��Χ���»��İ뾶
 * 
 * @author rayeaster
 *
 */
public class HuiwenString 
{
	
	/** 
     * �������� #a#c#b#c#a#a#c#b#c#d#��ʽ���ַ������� 
     *  
     * @param s 
     * @return 
     */  
    public static char[] init(String s)  
    {  
        char[] str = new char[s.length() * 2 + 1];  
  
        int i = 0;  
        for (; i < s.length(); i++)  
        {  
            str[2 * i] = '#';  
            str[2 * i + 1] = s.charAt(i);  
        }  
        str[2 * i] = '#';  
  
        return str;  
    }  
  
    /** 
     *  
     *  
     * @param str 
     */  
    public static void manacher(char[] s)  
    {  
        int rad[] = new int[s.length];  
  
        int i = 1, j = 0;  //i starts from 1
  
        // ��¼��Ļ��Ĵ��ĳ���  
        int maxLen = 0;  
        while (i < s.length)  
        {  
            // ɨ��ó�radֵ  
            while (i - j - 1 > -1 
            	   && i + j + 1 < s.length  
                   && s[i - j - 1] == s[i + j + 1])  
            {
                j++;
            }
            rad[i] = j;  
  
            maxLen = maxLen > j ? maxLen : j;  
  
            int k = 1;  
            while (k <= rad[i] && rad[i - k] != rad[i] - k)  
            {  
                rad[i + k] = Math.min(rad[i - k], rad[i] - k);  
                k++;  
            }   
            //update the next search center
            i = i + k;  
            //update the next search radius, if always 0, duplicate work is done
            j = Math.max(j - k, 0);  
        }  
  
        System.out.println(Arrays.toString(rad));  
        System.out.println("����Ĵ����ȣ� " + maxLen);  
    }  
		
	public static void main(String[] args)
	{
		String s = "acbcaacbcd";  
        char[] str = init(s);  
        System.out.println(Arrays.toString(str));  
        manacher(str);
    }
}
