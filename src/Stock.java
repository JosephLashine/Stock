 import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
public class Stock {
	
	    //Nested inner class
	    private static class Transaction 
	    {
	        private int shareAmount;
	        private int stockPrice;
	        Transaction (int shareAmount, int stockPrice) 
	        {
	            this.shareAmount = shareAmount;
	            this.stockPrice = stockPrice;
	        }
	    }
	    // implement queue interface that accepts Transaction objects
	    public static Queue<Transaction> queue = new LinkedList<Transaction>();
	    public static int totalShares = 0;
	    public static int capitalGain = 0;
	    /**
	     * creates Transaction object
	     * @param shareAmount: amount of shares bought on a single day
	     * @param stockPrice: how much the shares were bought for
	     */
	    public static void buy(int shareAmount, int stockPrice)
	    {
	        Transaction p = new Transaction (shareAmount, stockPrice);
	        totalShares += shareAmount;
	        queue.add(p); //adds the new Transaction object to the queue
	    }
	    /**
	     * sells shares in FIFO basis
	     * @param sahresForSale: amount of shares being sold
	     * @param stockPrice: how much the shares were sold for
	     */
	    public static void sell(int sharesForSale, int stockPrice)
	    {
	        //Checks to see that enough shares can be sold
	        if (sharesForSale > totalShares)
	        {
	            System.out.println("Error: Insufficient Shares");
	        }
	        //Continues to sell shares in FIFO until sell amount is reached
	        while (sharesForSale > 0)
	        {
	            Transaction ownedShares = queue.peek();
	            //sells all shares bought on earliest day and moves on to next day
	            if (ownedShares.shareAmount <= sharesForSale)
	            {
	                sharesForSale -= ownedShares.shareAmount;
	                capitalGain += ownedShares.shareAmount * (stockPrice - ownedShares.stockPrice);
	                ownedShares.shareAmount = 0;
	                queue.poll(); //move on to next day
	            }
	            else
	            {
	                capitalGain += sharesForSale * (stockPrice - ownedShares.stockPrice);
	                ownedShares.shareAmount -= sharesForSale;
	                sharesForSale = 0;
	            }
	        }
	    }
	    //method for retrieving the capital gain/loss
	    public static int calcGain()
	    {
	        return capitalGain;
	    }
	    
	    public static void main (String[]args)
	    {
	        System.out.println("Day 1: 100 shares bought at $20.");
	        buy(100, 20);
	        System.out.println("Day 2: 20 shares bought at $24.");
	        buy(20, 24);
	        System.out.println("Day 3: 200 shares bought at $36.");
	        buy(200, 36);
	        System.out.println("Day 4: 150 shares sold at $30.");
	        sell(150, 30);
	        
	        System.out.println("The total capital gain/loss is $" + calcGain() +".");
	    }
	}
