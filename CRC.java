import java.util.*;
class CRC{
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter the number of bits for generator poly: ");
		int length = scanner.nextInt();
		int generator_poly[] = new int[length];
		
		System.out.println("\nEnter the divisor(generator-poly) in binary : ");
		for(int i=0; i<length; i++)
			generator_poly[i] = scanner.nextInt();

		System.out.println("\nEnter the number of bits for data: ");
		int dl = scanner.nextInt();
		int data[] = new int[dl+(length-1)];
		System.out.println("\nEnter the dividend(data) in binary : ");
		for(int i=0; i<dl; i++)
			data[i] = scanner.nextInt();

		System.out.println("\nThe genetator_poly  is: ");
		for(int i=0; i<length; i++)
			System.out.print(generator_poly[i]);

		System.out.println("\nThe data is: ");
		for(int i=0; i<dl; i++)
			System.out.print(data[i]);

		for(int i = dl; i<(dl+(length-1)); i++)
			data[i] = 0;

		int rem[] = divide(data,generator_poly);

		System.out.println("\nThe CRC data is: ");
		for(int i=0; i<dl; i++)
			System.out.print(data[i]);
		for(int i=0; i<rem.length-1; i++)
			System.out.print(rem[i]);

		int rec_data[] = new int[dl+(length-1)];
		System.out.println("\nEnter the crc data in binary : ");
		for(int i=0; i<dl+(length-1); i++)
			rec_data[i] = scanner.nextInt();

		int rec_rem[] = divide(rec_data,generator_poly);
		int flag = 0;
		for(int i=0; i<rec_rem.length; i++){
			if(!(rec_rem[i] == 0)){
				flag = 1;
				break;
			}
		}
		if( flag == 1 ){
			System.out.println("CRC Data contains error");
		}else{
			System.out.println("CRC Data doesnt contains error");
		}
	}
	
	static int[] divide(int old_data[],int gen[]){
		
		int i;
		int data[] = new int[old_data.length];
		System.arraycopy(old_data, 0, data, 0, old_data.length);

		int rem[] = new int[gen.length];
		System.arraycopy(data, 0, rem, 0, gen.length);
		
		for(i=0 ; i <= old_data.length-gen.length ; i++) {
			if(rem[0] == 1) {
				for(int j=1 ; j < gen.length ; j++)
					rem[j-1] = exor(rem[j], gen[j]);
			}
			else{
				for(int j=1 ; j < gen.length ; j++)
					rem[j-1] = exor(rem[j], 0);
			}
			if (i < old_data.length-gen.length)
				rem[gen.length-1] = old_data[i+gen.length];				
			else
				rem[gen.length-1] = 0;
		}
		return rem;
	}
	
	static int exor(int a, int b) {
		if(a == b) {
			return 0;
		}
		return 1;
	}
}