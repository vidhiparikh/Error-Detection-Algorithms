import java.util.*;
class CheckSum 
{
	int num,carry;
	public String hexAdd(int d1, int d2){
		return Integer.toHexString(d1+d2);
	}
	public String checkSum(String hexaData){
		//for hex addition 
		String res = "0";
		for(int i=0; i<hexaData.length(); i+=4)
		{
			String num1 = hexaData.substring(i,i+4);
			int datain = Integer.parseInt(num1,16);
			int resint = Integer.parseInt(res,16);
			res = hexAdd(resint, datain);
		}
		//For adding carry's
		System.out.println("The sum is: " + res);
		if (res.length() == 5) {
			carry = Integer.parseInt(res.substring(0,1), 16);
			num = Integer.parseInt(res.substring(1,5), 16);
		}
		else{
			carry = 0;
			num = Integer.parseInt(res, 16);
		}
		System.out.println("Carry is " + carry);
		//for one's complement
		String ones_comp = Integer.toHexString(~(Integer.parseInt(hexAdd(carry, num),16)));
		System.out.println("Final checkSum : " + ones_comp.substring(4));
		return (ones_comp.substring(4));

	}
	public static void main(String[] args){
		CheckSum cs = new CheckSum();
		String data;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Hexadecimal String");
		data = sc.nextLine();
		//for padding 0's
		for(int i= 0 ; i < (data.length() % 4 ); i++)
			data+=0;
		System.out.println("The CheckSum is: " + data + cs.checkSum(data));

		System.out.println("Enter the CheckSum String");
		data = sc.nextLine();

		if(cs.checkSum(data).equals("0000"))
			System.out.println("No error");
		else
			System.out.println("Error");
	}
}