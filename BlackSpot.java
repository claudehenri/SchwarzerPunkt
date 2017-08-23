package mod.ValueObject;

import java.io.*;
import java.sql.*;
import java.util.*;
import mod.Model.*;
import mod.Utility.*;
import javax.swing.*;


public class BlackSpot implements java.io.Serializable {

    private int iX = 0;
    private int iY = 1;
    private int iZ = 2;
    private int max = 4;        
    private String blacktime = null;
    private Double blackQuote = null;
	private String blackSymbol = null;
	private Integer blackShares = null;  
    private Integer active = null;  	
    private String lastCommaQuote = null;
    private String[] commaQuoteConstituent = null;

    /* i.e.:
    "VRTX",86.42,"10/4/2016","4:00pm",-0.37,86.94,87.25,85.75,1081122
    */

	/**
     * Construct a BlackSpot using the supplied object instances.
     *
     * @param tick the first object to form the BlackSpot, may be null.
     * @param price the second object to form the BlackSpot, may be null.
     * @param time the third object to form the BlackSpot, may be null.
     */
    public BlackSpot() {
        this.commaQuoteConstituent = new String[4];
        /*
		 RAM: this
		 Cache: XML
		 Records: DB
		*/
		this.blacktime = null;
        this.blackQuote = null;
		this.blackSymbol = null;
		this.blackShares = null; 
        return;       
    }
	

    /**
     * Construct a BlackSpot using the supplied object instances.
     *
     * @param tick the first object to form the BlackSpot, may be null.
     * @param price the second object to form the BlackSpot, may be null.
     * @param time the third object to form the BlackSpot, may be null.
     */
     public BlackSpot(String ticker) {
        this.commaQuoteConstituent = new String[4];
		this.blackSymbol = ticker;		
		/*
		 RAM: this
		 Cache: XML
		 Records: DB
		*/
		this.blacktime = null;
        this.blackQuote = null;
        return;       
    }    
	
    
   /**
     * Construct a BlackSpot using the supplied object instances.
     *
     * @param tick the first object to form the BlackSpot, may be null.
     * @param price the second object to form the BlackSpot, may be null.
     * @param time the third object to form the BlackSpot, may be null.
     */
    public BlackSpot(String ticker, Integer shares) {
        this.commaQuoteConstituent = new String[4];
        this.blackShares = shares;
		this.blackSymbol = ticker;		
		/*
		 RAM: this
		 Cache: XML
		 Records: DB
		*/
		this.blacktime = null;
        this.blackQuote = null;
        return;       
    }


            
/**
     * Construct a BlackSpot using the supplied object instances.
     *
     * @param shares the number of shares bought and may not be null.	 
     * @param tick the first object to form the BlackSpot, may be null.
     * @param price the second object to form the BlackSpot, may be null.
     * @param time the third object to form the BlackSpot, may be null.
     */
    public BlackSpot(Integer shares, String tick, Double price, java.util.Date time) {
        this.commaQuoteConstituent = new String[4];
		this.blacktime = null;
        this.blackQuote = null;
		this.blackSymbol = null;
		this.blackShares = null; 
		/*
		 RAM: this
		 Cache: XML
		 Records: DB
		*/		
		if(shares!= null && tick!= null && price!= null && time!= null)
		{
			this.blacktime = time.toString();
			this.blackQuote = price;
			this.blackSymbol = tick;
			this.blackShares = shares; 
		}		
        return;       
    }
	

    private boolean updateSpotFactors( String[] csQuote )
    {
    	boolean updatedOkay = true;

    	if(csQuote==null) 
	{
		System.out.println("\tcsQuote is null"); 
		return false;
	}	
	
	if(csQuote.length < max)	
	{ 
		System.out.println("\tcsQuote length, "+csQuote.length+", is not valid");
		return  false;
	}
    	
    	if(csQuote.length==max)
    	{
          		this.blackSymbol = (String)csQuote[iX].replace("\"", "");;
          		this.blackQuote = new Double((String)csQuote[iY]);
          		this.blacktime = (String)csQuote[iZ] + " " +
				(String)csQuote[iZ+1];  
				System.out.println("\tblackSymbol, "+blackSymbol);
				System.out.println("\tblackQuote, " +blackQuote);
				System.out.println("\tblacktime, "  +blacktime);
				
    	}
    	
    	return updatedOkay;
    }


    public String[] setLastCommaQuote( String csquote )
    {
    	int c = 0;
    	int maxColumn = 3;
    	String commaDelim = ",";
    	System.out.println("\t|");
    	boolean hasQuoteSet = true;
    	String tick, spot, date, time;    	
		String[] columns = new String[4];    
		System.out.println("\t<setLastCommaQuote: "+csquote+">");	
    	
    	if(csquote==null||csquote.equals("")) 
	{
		System.out.println("\t[No CommaQuote]");
		return columns ;
	}
    	
    	java.util.StringTokenizer st = new java.util.
    		StringTokenizer(csquote, commaDelim);
    	
    	while (st.hasMoreTokens()) 
    	{
    		columns[c] = (String)st.nextToken();
         		System.out.println("\tColumn: "+ columns[c] );
         		if(c==maxColumn) break;
         		c++;
        	}
    	
        	System.out.println("\t|------------");
        	
        	System.arraycopy(columns, 0, this.commaQuoteConstituent, 0, maxColumn + 1);

	updateSpotFactors( this.commaQuoteConstituent );

	return this.commaQuoteConstituent;
    }
    
    
    /**
     * Return the ticker object in the BlackSpot, may be null.
     *
     * @return the ticker object in the BlackSpot.
 
    public X getTicker() {
        return this.ticker;
    }
   */

    /**
     * Return the price object in the BlackSpot, may be null.
     *
     * @return the price object in the BlackSpot.
    public Y getPrice() {
        return this.price;
    }
	*/
	
    /**
     * Return the time object in the BlackSpot, may be null.
     *
     * @return the time object in the BlackSpot.

    public Z getTime() {
        return this.time;
    }
	*/

    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BlackSpot");
        sb.append("{").append(this.blackSymbol);
        sb.append(", ").append(this.blackQuote );
		sb.append(", ").append(this.blackShares );
        sb.append(", ").append(this.blacktime );
        sb.append('}');
        
        return sb.toString();
    }


	public static String recordApproximateSpot(BlackSpot aSpot){
        String fileId = "";
        java.util.Properties spotProps = null;
		long sctm = System.currentTimeMillis();
        System.out.println("\t" + aSpot.toString());   
        Long bEasySpotTime = new java.lang.Long(sctm);         
        String tickerId = (String)aSpot.getBlackSymbol();
        
        if(spotProps == null)
        {
            spotProps = new java.util.Properties();    
        }
          
        fileId = tickerId+"-spot-"+bEasySpotTime+".xml";
        
        spotProps.setProperty((String)aSpot.getBlackSymbol(), aSpot.toString());
        
        try
        {
            spotProps.storeToXML(new java.io.FileOutputStream(fileId), tickerId, "UTF-8");
            
        }catch( Exception e )
        {
            e.printStackTrace();
        }
        
        return tickerId;
    }
	
	
    public String getBlackSymbol( ){
    	return this.blackSymbol;
    }
	
    public Integer getBlackShares( ){
    	return this.blackShares;
    }
	
    public Double getBlackQuote( ){
    	return this.blackQuote;
    }

    public String getBlacktime( ){
    	return this.blacktime;
    }

	/**
	 * Return the active object, may NOT be null.
	 *
	 * @return the active object in the ApproximatedDebt.
	 */
	public Integer getActivation() {
		return this.active ;
	}
	
//Close the "BlackSpot"
// Class;
};
