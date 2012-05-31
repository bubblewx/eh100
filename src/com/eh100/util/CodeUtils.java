package com.eh100.util;

public class CodeUtils {

	    
	    public static void main(String[] args) {
	    
	     String QJstr="第２００章全军覆没";
	     
	     String result=SBCchange(QJstr);
	    
	     System.out.println(QJstr+"\n"+result);
	  
	 }
	 
	 
	 public static final String SBCchange(String QJstr)
	 {
	     String outStr="";
	     String Tstr="";
	     byte[] b=null;

	     for(int i=0;i<QJstr.length();i++)
	     {     
	      try
	      {
	       Tstr=QJstr.substring(i,i+1);
	       b=Tstr.getBytes("unicode");
	      }
	      catch(java.io.UnsupportedEncodingException e)
	      {
	       e.printStackTrace();
	      }     
	   
	      if (b[3]==-1)
	      {
	       b[2]=(byte)(b[2]+32);
	       b[3]=0;      
	        
	       try
	       {       
	        outStr=outStr+new String(b,"unicode");
	       }
	       catch(java.io.UnsupportedEncodingException e)
	       {
	        e.printStackTrace();
	       }      
	      }else outStr=outStr+Tstr;
	     }
	    
	     return outStr; 
	  }

	 
}
