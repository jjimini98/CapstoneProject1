package capstone;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.Random;


public class tokens {
	
	//token 100000�� �����ؼ� csv�� �����ϱ�~
	public static void main(String[] args) throws IOException {
		
		String creatfile = "C:\\Users\\user_\\Desktop\\tokens\\tokens.txt"; 
		FileWriter fw = new FileWriter(creatfile);
		
		String creatfile2 = "C:\\Users\\user_\\Desktop\\tokens\\search.txt"; 
		FileWriter fw2 = new FileWriter(creatfile2);
		//------------------------------------------------------
		String[] arr = new String[100000];  //1�����迭 �� n���� ����ϴ�.
		   String numStr = "";    //�������� ���� ������ ����ϴ�
        boolean cheak;    // ���ϱ� ���� boolean�� ������ ����ϴ�.
        for (int i=0; i<arr.length; i++) {    // �迭�� ũ�⸸ŭ for���� �����ϴ�.
            numStr = numberGen(12) ;
     	   cheak = true;    // i���� �� ������ cheak�� true�� ����ϴ�.

            for (int j=0; j<i; j++) {
                if(arr[j].equals(numStr)) {
             	   // for���� �����鼭  ��� ���� random���� �迭�� ����ִ� ������ ���Ͽ� ������ ������
             	   i--;    // i�� ���� �ϳ� �ٿ� �� �� �� ���� �մϴ�.
             	   cheak=false;    // �������� �ٸ��� ���� ���� �������Ƿ� cheak�� false�� ����ϴ�.
                }
            }
            if(cheak) {    // ���� if���� ������ �������� �ʾ����� �ڵ����� cheak�� true�Ƿ� ������ �˴ϴ�.
         	   arr[i] = numStr; // numStr�� ���� ���� arr[i]�濡 �ֽ��ϴ�.
                numStr = "";
            }
        }
        
        //-------------------------------------------------------
        
        //System.out.println(Arrays.toString(arr));
        
        //for(int i=0; i<arr.length; i++)    //����ϱ� ���� for��
     	  // System.out.println(arr[i]);    // arr�迭�� ����մϴ�.
        for (int i=0; i<arr.length; i++) {
        	if (i<50000) {
        		fw.append(arr[i]);
            	fw.append(',');
            	//fw.append('\n');        		
        	} else {
        		fw2.append(arr[i]);
            	fw2.append(',');
            	//fw2.append('\n');
        	}
        }
        fw.flush();
        fw.close();
        fw2.flush();
        fw2.close();
        //--------------------------------------------------------
 
	}
	
	public static String numberGen(int len) {
        
        Random rand = new Random();
        String numStr = ""; //������ ����� ����
        
        for(int i=0; i<len-11; i++) {
        	char ch = (char) ((Math.random() * 26) + 97);
        	numStr += ch;
        }
        for(int i=0;i<len-5;i++) {
            //0~9 ���� ���� ����
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        for(int i=0; i<len-6; i++) {
        	char ch = (char) ((Math.random() * 26) + 97);
        	numStr += ch;
        }
        return numStr;
        
   }




}


