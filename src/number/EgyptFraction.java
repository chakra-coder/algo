package number;

import util.AlgoUtil;

public class EgyptFraction{

   private static final double EPSION = 1e-30;
   private static final int MAXSTEP = 1000;
   
   Fraction destFraction;
   double destValue;
   int bestStep;
   boolean success; //找到一个可行解
   double[] bestX; //最优解的分母
   double[] curX; //当前解的分母
   Fraction[] diff; //当前解对应的差值   

   public EgyptFraction(int numerator, int denominator){
	   
	    destFraction = new Fraction(numerator, denominator); 
	    
	    success = false;
		bestStep = MAXSTEP;
		
		int size = MAXSTEP + 1;
		
		bestX = new double[size];
		curX = new double[size];
		diff = new Fraction[size];
		
		for(int i = 0; i < size;i++){
			bestX[i] = -1;
			curX[i] = -1;
			diff[i] = new Fraction();
		}
   }
   
   public static void main(String[] args){ 
	   EgyptFraction ef = new EgyptFraction(19, 45);
	   ef.solve();
	   for (int i = 1; i < ef.bestStep; ++i){  
	        System.out.print(ef.bestX[i] + " ");  
	   }  
	   System.out.print(ef.bestX[ef.bestStep] + " ");  
   }
   
   private static class Fraction{
	   Fraction() {
		   
	   }	
	   
       Fraction(double n, double d){
    	   numerator = n;
    	   denominator = d; 
       }
       
	   double numerator;
	   double denominator;
	   
	   void normalize(){
		   if (Math.abs(numerator) == 0 || Math.abs(denominator) == 0){
				return;
		   }
		   int gcd = AlgoUtil.GCD((int)denominator, (int)numerator);
		   numerator /= gcd;
		   denominator /= gcd;
	   }
   }   

   public void solve(){
	   success = false;
	   bestStep = MAXSTEP;
	   destFraction.normalize();
	   if (destFraction.numerator == 1){
			bestStep = 1;
			bestX[0] = destFraction.denominator;
	   }else{
		    IdaStar();
	   }
   }

   private void IdaStar(){
	   int steps = 2;
	   while (!success){
			curX[0] = destFraction.denominator / destFraction.numerator;
			diff[0] = destFraction;// - Fraction(1, curX[0]);
			idaIterator(1, steps++);
	   }
   }

   //curStep must be >= 2
   //第curStep个数为v
   //当前差值为d (diff.get(curStep-1).toDouble())
   //显然1/v <= diff.get(curStep-1) => v >= 1 / d, 另外根据题意v >= x[curStep-1] + 1
   //如果还有n步，那么n * 1/v >= d => v <= n / d
   //另外根据题意v <= *bestx.rbegin() - 1
   //因此，max(x[curStep-1] + 1, 1/d) <= v <= min(n / d, *bestx.regin() - 1)
   private void idaIterator(int curStep, int totalStep)
   {
   	if (curStep > totalStep)
   	{
   		if (Math.abs(diff[curStep - 1].numerator) < EPSION)
   		{
   			success = true;
   			if (totalStep < bestStep || (totalStep == bestStep && curX[totalStep] < bestX[totalStep]))
   			{
   				bestStep = totalStep;
   				for (int i = 1; i <= totalStep; ++i)
   				{
   					bestX[i] = curX[i];
   				}
   			}
   		}
   		return;
   	}

   	assert(diff[curStep-1].numerator != 0);

   	int startPos = (int) (diff[curStep-1].denominator / diff[curStep-1].numerator); // 1/d
   	//显然如果还少c/d,那么增加的值t必须 < c/d, c/d < 1 / [d/c]
   	//所以t < 1 /[d/c]
   	if (startPos < curX[curStep - 1] + 1){
   		startPos = (int) (curX[curStep - 1] + 1);
   	}

   	
   	int lastPos = (int) ((totalStep - curStep + 1) * diff[curStep - 1].denominator / diff[curStep - 1].numerator);

   	if (success){
   		//要成为最优解，其最大的分母要小于最优解的最大分母
   		if (lastPos > bestX[bestStep]){
   			lastPos = (int) bestX[bestStep];
   		}
   	}

   	
   	for (int v = startPos; v <= lastPos; ++v)
   	{
   		assert(v >= 0);	
   		curX[curStep] = v;
   		if (diff[curStep - 1].numerator * v >= diff[curStep - 1].denominator)
   		{
   			Fraction fr = new Fraction();
   			fr.denominator = diff[curStep - 1].denominator * v;
   			fr.numerator = diff[curStep - 1].numerator * v - diff[curStep - 1].denominator;
   			//if (fr.denominator < 0)
   			//{
   			//	cout << diff[curStep - 1].denominator << " " << v;
   			//	system("PAUSE");
   			//}
   			//assert (fr.numerator >= 0);
   			//fr.normalize();//在此处不应该调用normalize，因为已经使用了浮点数，直接截断int数就会出现负数
   			diff[curStep] = fr;
   			idaIterator(curStep + 1, totalStep);
   		}
   	}
   }

 

}



