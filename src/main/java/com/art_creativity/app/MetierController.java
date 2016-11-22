
package com.art_creativity.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import UHF.Reader18;

@Service
public class MetierController implements IMetierController{

	public static String password = "00000000";
//	private Reader18 reader;
	
//	public MetierController(){
//	}
	
	/*public String readerRfid(int addressRfid, int port, String addressIp){
		
		int[] response  = new int[50];
		response = reader.OpenNetPort(addressRfid, port, addressIp);
		String ans = "";
		//String ans2 = "";
		if(response==null)
			return "no connect";
		ans += addressRfid+" "+port+" "+addressIp+"\n";
		int arr[] = new int[2];
		arr[0] = addressRfid;
		arr[1] = port;
		int [] recv = reader.GetReaderInformation(arr);
		int limit = recv[recv.length-1];
		
		for(int i=0; i<50/limit; i++){
			try {
				Thread.sleep(limit);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			recv = reader.Inventory_G2(arr);
	        List<Integer[]> list = new ArrayList();
	       if(recv.length>3){
	    	   int tail = recv[2];
	    	   int dep = 3;
	    	   
	    	   do{
	    		   int c = recv[dep];
	    		   //System.out.println("c = "+c);
	    		   Integer[] recvs = new Integer[c];
	    		   int e = 0;
	    		   for(int k = dep+1; k<c+dep+1; k++){
	    			   recvs[k-(dep+1)] = recv[k];
	    			   e = k+1;
	    		   }
	    		   dep =e;
	    		   list.add(recvs);
	    		   //dep += c;
	    		  // System.out.println("end"+tail+"="+dep);
	    	   }while(tail>dep);
	    	   System.out.println("end"+tail+"="+dep);
	       }
	       for(Integer[] integ : list){
	    	  
		        int[] arr1 = new int[(13+integ.length)];
		        arr1[0]  = addressRfid;
		        arr1[1] = integ.length;
		        //System.arraycopy(integ, 0, arr1, 2, integ.length);
		        ans+="\nEPC : ";
		        for(int h = 0; h< integ.length; h++){
		        	arr1[h+2] = integ[h];
		        	ans+=Integer.toHexString(integ[h]);
		        }
		        arr1[2+integ.length] =0x01;
		        arr1[3+integ.length] =0x00;
		        arr1[4+integ.length] = 6;
		        arr1[5+integ.length] = 0;
		        arr1[6+integ.length] = 0;
		        arr1[7+integ.length] = 0;
		        arr1[8+integ.length] = 0;
		        arr1[9+integ.length] = 0;
		        arr1[10+integ.length] = 0;
		        arr1[11+integ.length] = 0x01;
		        arr1[12+integ.length] = port;
		        
		        recv = reader.ReadCard_G2(arr1);
		        ans+="\n\tReader : ";
		        for(int k =2; k<recv.length; k++){
		        	ans+=(Integer.toHexString(recv[k])+"");
	    		}
	       }
	       // System.out.println(inventory);
	        
			
		}
		
		recv = reader.CheckEASAlarm_G2(arr);
		ans+=("\nAlarm\t");
		for(int k =0; k<recv.length; k++){
        	ans+=(Integer.toHexString(recv[k])+"");
		}
        ans+=("\n");
	       

		
		
        reader.CloseNetPort(port);
		return ans;//+"\n\tHexadecimal\t\n"+ans2;
	}*/
	
	@SuppressWarnings("unused")
	public DataRFid readerRfid(final int addressRfid, final int port, final String addressIp){
		Reader18 reader = new Reader18();
		int[] response  = new int[50];
		response = reader.OpenNetPort(addressRfid, port, addressIp);
		String ans = "";
		DataRFid dataRFid = new DataRFid();
		//String ans2 = "";
		if(response==null)
			return dataRFid;
		//ans += addressRfid+" "+port+" "+addressIp+"\n";
		int arr[] = new int[2];
		arr[0] = addressRfid;
		arr[1] = port;
		int [] recv = reader.GetReaderInformation(arr);
		/*for (int m=0;m<recv.length;m++)
        {
			System.out.println(recv[m]);
	        System.out.println("hexa : "+Integer.toHexString(recv[m]));
        }*/
		
		int limit = recv[recv.length-1];
		
		//for(int i=0; i<50/limit; i++){
			try {
				Thread.sleep(limit);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        System.out.println("inventory ");
	        String inventory = "";
	        recv = reader.Inventory_G2(arr);
	        for (int m=0;m<recv.length;m++)
	        {
	           //System.out.println(recv[m]);
	          inventory += toHexString(recv[m])+" ";
	        }
	        List<Integer[]> list = new ArrayList<Integer[]>();
	        
	       if(recv.length>3){
	    	   int tail = recv[2];
	    	   int dep = 3;
	    	   
	    	   do{
	    		   int c = recv[dep];
	    		   System.out.println("c = "+c);
	    		   Integer[] recvs = new Integer[c];
	    		   int e = 0;
	    		   for(int k = dep+1; k<c+dep+1; k++){
	    			   recvs[k-(dep+1)] = recv[k];
	    			   e = k+1;
	    		   }
	    		   dep =e;
	    		   list.add(recvs);
	    		   //dep += c;
	    		   System.out.println("end"+tail+"="+dep);
	    	   }while(tail>dep);
	    	   System.out.println("end"+tail+"="+dep);
	       }
	       List<EPC> epcs = new ArrayList<>();
	       for(Integer[] integ : list){
	    	   System.out.println("inventory plus");
	    	   for(int p : integ){
	    		   System.out.print(Integer.toHexString(p));
	    	   }
	    	   System.out.println("");
	    	   System.out.println("reader");
		        int[] arr1 = new int[(13+integ.length)];
		        arr1[0]  = addressRfid;
		        arr1[1] = integ.length;
		        //System.arraycopy(integ, 0, arr1, 2, integ.length);
		        ans+="\nEPC : ";
		        String tag = "";
		        for(int h = 0; h< integ.length; h++){
		        	arr1[h+2] = integ[h];
		        	ans+=toHexString(integ[h]);
		        	tag+=toHexString(integ[h]);
		        }
		        int[] pass = getPassWord();
		        arr1[2+integ.length] = 1;
		        arr1[3+integ.length] = 0x00;
		        arr1[4+integ.length] = 6;
		        arr1[5+integ.length] = pass[0];
		        arr1[6+integ.length] = pass[1];
		        arr1[7+integ.length] = pass[2];
		        arr1[8+integ.length] = pass[3];
		        arr1[9+integ.length] = 0;
		        arr1[10+integ.length] = 0;
		        arr1[11+integ.length] = 0;
		        arr1[12+integ.length] = port;
		        
		        recv = reader.ReadCard_G2(arr1);
		        ans+="\n\tReader : ";
		        String data = "";
		        for(int k =2; k<recv.length; k++){
		        	System.out.print(Integer.toHexString(recv[k])+"");
		        	ans+=(toHexString(recv[k])+"");
		        	data+=(toHexString(recv[k])+"");
	    		}
		        
		        try {
					Thread.sleep(limit);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        arr1[3+integ.length] = 0x20;
		        arr1[4+integ.length] = 1;
		        recv = reader.ReadCard_G2(arr1);
		        String tampered = "";
		        for(int k =2; k<recv.length; k++){
		        	System.out.print(toHexString(recv[k])+"");
		        	ans+=(toHexString(recv[k])+"");
		        	tampered+=(toHexString(recv[k])+"");
	    		}
		        epcs.add(new EPC(tag, data, tampered));
		        System.out.println("");
	       }
	        //System.out.println(inventory);	        
			
		//}	
	    dataRFid.setEpcs(epcs);
		recv = reader.CheckEASAlarm_G2(arr);
		ans+=("\nAlarm\n");
		String alarm = "";
		for(int k =0; k<recv.length; k++){
			//System.out.println(recv[k]);
        	ans+=(Integer.toHexString(recv[k])+" ");
        	alarm+=(Integer.toHexString(recv[k])+" ");
		}
		dataRFid.setAlarm(alarm);
        ans+=("\n");
	    
        reader.CloseNetPort(port);
		return dataRFid;//+"\n\tHexadecimal\t\n"+ans2;
	}
	
	public boolean send(String txt){
		if(txt.isEmpty())return false;
		
		byte[] file = txt.getBytes();
		
		URL url;
		DataOutputStream dos = null;
		HttpURLConnection http = null;
		String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String fileName = new Date().getTime()+".txt"; 
        int bytesRead = 0, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        
		try {
			url = new URL("");
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true); // Allow Inputs
			http.setDoOutput(true); // Allow Outputs
            http.setUseCaches(false); 
			http.setRequestMethod("POST");
			http.setRequestProperty("", "");
			http.setRequestProperty("ENCTYPE", "multipart/form-data");
			http.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			http.setRequestProperty("uploaded_file", fileName);
			
			dos = new DataOutputStream(http.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + fileName + "\"" + lineEnd);

            dos.writeBytes(lineEnd);
            
            do {
                bytesAvailable = file.length - bytesRead;
                // create a buffer of  maximum size
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                System.arraycopy(file, 0, buffer, 0, bufferSize);
                bytesRead += bufferSize;
                dos.write(buffer, 0, bufferSize);
            }while (bytesRead < file.length);

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            int serverResponseCode = http.getResponseCode();
            String serverResponseMessage = http.getResponseMessage();
            
            System.out.println("response code : "+serverResponseCode+"\nresponseMessage : "+serverResponseMessage);
            dos.flush();
            dos.close();
            if(serverResponseCode == 200){
            	return true;
            }

            //close the streams //
            
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public int[] getPassWord(){
		int[] pass = new int[4];
		pass[0] = Integer.parseInt(password.substring(0, 2), 16);
		pass[1] = Integer.parseInt(password.substring(2, 4), 16);
		pass[2] = Integer.parseInt(password.substring(4, 6), 16);
		pass[3] = Integer.parseInt(password.substring(6, 8), 16);
		return pass;
	}
	
	public static String toHexString(int i) {
    	String str = Integer.toHexString(i).toUpperCase();
    	if(str.length() == 1){
    		str = "0"+str;
    	}
    	return str;
    }
	
	public static void main(String[] enter){
		MetierController mateir = new MetierController();
		System.out.println(mateir.readerRfid(255, 6000, "192.168.1.141"));
	}
	
}
