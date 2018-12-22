import java.util.*;
class Hamming 
{
	public static void main(String[] args) 
	{
		int n, pow_val, k=0;
		double j;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of hamming data");
		n = sc.nextInt();
		//2^4 >= 7+4
		for(j=0.00; j<n; j++)
			if(Math.pow(2.00, j) >= (n+j))
				break;
		int par_count = (int)j;
		System.out.println("Number of Parity Bits: " + par_count);
		int barr[] = new int[n];
		System.out.println("Enter the Hamming Data from MSB to LSB");
		for(int i=0; i<n; i++)
		{
			barr[i] = sc.nextInt();
		}
		System.out.println("The Entered hamming data is");
		for(int i =0; i<n; i++)
		{
			System.out.print(barr[i] + "\t");
		}
		int z = n-1;
		n += par_count; 
		int parr[] = new int[n];
		for(int i=1; i<=n; i++)
		{
			if((int)Math.pow(2,k) == i)
			{
				parr[i-1] = -1;
				k++;
			}
			else
			{
				parr[i-1] = barr[z];
				z--;
			}
		}
		int res[] = new int[par_count];
		for(int i=0; i<par_count; i++)
		{
			int par_bit, count=0, indc=0;
			int delimiter = (int)Math.pow(2,i);
			for(int c=delimiter-1; c<n; c++)
			{
				if(indc < delimiter)
				{
					if(parr[c] == 1)
						count++;	
					indc++;
				}
				else
				{
					indc = 0;
					c = c + delimiter - 1;
				}
			}
			if(count % 2 != 0)
				par_bit = 1;
			else
				par_bit = 0;
			res[i] = par_bit;
			System.out.print("\nParity Bit P" + delimiter+ " "+ par_bit);
		}
		for(int i=0; i<par_count; i++)
		{
			int pos = (int)Math.pow(2,i);
			parr[pos-1] = res[i];
		}
		System.out.println("\nHamming Code");
		for(int i =n-1; i>=0; i--)
			System.out.print(parr[i] + "\t");
	}
}