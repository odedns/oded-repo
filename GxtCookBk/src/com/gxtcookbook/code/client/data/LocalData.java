package com.gxtcookbook.code.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;

public class LocalData {

	public static List<Stock> getStocks() {
		List<Stock> stocks = new ArrayList<Stock>();

		stocks.add(new Stock("Apple Inc.", "AAPL", 125.64, 123.43));
		stocks.add(new Stock("Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
		stocks.add(new Stock("Google Inc.", "GOOG", 516.2, 512.6));
		stocks.add(new Stock("Intel Corporation", "INTC", 21.36, 21.53));
		stocks.add(new Stock("Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
		stocks.add(new Stock("Microsoft Corporation", "MSFT", 29.56, 29.72));
		stocks.add(new Stock("Nokia Corporation (ADR)", "NOK", 27.83, 27.93));
		stocks.add(new Stock("Oracle Corporation", "ORCL", 18.73, 18.98));
		stocks.add(new Stock("Starbucks Corporation", "SBUX", 27.33, 27.36));
		stocks.add(new Stock("Yahoo! Inc.", "YHOO", 26.97, 27.29));
		stocks.add(new Stock("Applied Materials, Inc.", "AMAT", 18.4, 18.66));
		stocks.add(new Stock("Comcast Corporation", "CMCSA", 25.9, 26.4));
		stocks.add(new Stock("Sirius Satellite", "SIRI", 2.77, 2.74));

		stocks.add(new Stock("Tellabs, Inc.", "TLAB", 10.64, 10.75));
		stocks.add(new Stock("eBay Inc.", "EBAY", 30.43, 31.21));
		stocks.add(new Stock("Broadcom Corporation", "BRCM", 30.88, 30.48));
		stocks.add(new Stock("CMGI Inc.", "CMGI", 2.14, 2.13));
		stocks.add(new Stock("Amgen, Inc.", "AMGN", 56.22, 57.02));
		stocks.add(new Stock("Limelight Networks", "LLNW", 23, 22.11));
		stocks.add(new Stock("Amazon.com, Inc.", "AMZN", 72.47, 72.23));

		stocks.add(new Stock("E TRADE Financial Corporation", "ETFC", 24.32,
				24.58));
		stocks.add(new Stock("AVANIR Pharmaceuticals", "AVNR", 3.7, 3.52));
		stocks.add(new Stock("Gemstar-TV Guide, Inc.", "GMST", 4.41, 4.55));
		stocks.add(new Stock("Akamai Technologies, Inc.", "AKAM", 43.08, 45.32));
		stocks.add(new Stock("Motorola, Inc.", "MOT", 17.74, 17.69));
		stocks.add(new Stock("Advanced Micro Devices, Inc.", "AMD", 13.77,
				13.98));
		stocks.add(new Stock("General Electric Company", "GE", 36.8, 36.91));
		stocks.add(new Stock("Texas Instruments Incorporated", "TXN", 35.02,
				35.7));
		stocks.add(new Stock("Qwest Communications", "Q", 9.9, 10.03));
		stocks.add(new Stock("Tyco International Ltd.", "TYC", 33.48, 33.26));
		stocks.add(new Stock("Pfizer Inc.", "PFE", 26.21, 26.19));
		stocks.add(new Stock("Time Warner Inc.", "TWX", 20.3, 20.45));
		stocks.add(new Stock("Sprint Nextel Corporation", "S", 21.85, 21.76));
		stocks.add(new Stock("Bank of America Corporation", "BAC", 49.92, 49.73));
		stocks.add(new Stock("Taiwan Semiconductor", "TSM", 10.4, 10.52));
		stocks.add(new Stock("AT&T Inc.", "T", 39.7, 39.66));
		stocks.add(new Stock("United States Steel Corporation", "X", 115.81,
				114.62));
		stocks.add(new Stock("Exxon Mobil Corporation", "XOM", 81.77, 81.86));
		stocks.add(new Stock("Valero Energy Corporation", "VLO", 72.46, 72.6));
		stocks.add(new Stock("Micron Technology, Inc.", "MU", 12.02, 12.27));
		stocks.add(new Stock("Verizon Communications Inc.", "VZ", 42.5, 42.61));
		stocks.add(new Stock("Avaya Inc.", "AV", 16.96, 16.96));
		stocks.add(new Stock("The Home Depot, Inc.", "HD", 37.66, 37.79));

		stocks.add(new Stock("First Data Corporation", "FDC", 32.7, 32.65));
		return stocks;

	}

	  public static List<Stock> getCompanies() {
	    DateTimeFormat f = DateTimeFormat.getFormat("M/d h:mma");
	    List<Stock> stocks = new ArrayList<Stock>();
	    stocks.add(new Stock("3m Co", 71.72, 0.02, 0.03, f.parse("4/2 12:00am"),
	        "Manufacturing"));
	    stocks.add(new Stock("Alcoa Inc", 29.01, 0.42, 1.47, f.parse("4/1 12:00am"),
	        "Manufacturing"));
	    stocks.add(new Stock("Altria Group Inc", 83.81, 0.28, 0.34, f.parse("4/3 12:00am"),
	        "Manufacturing"));
	    stocks.add(new Stock("American Express Company", 52.55, 0.01, 0.02,
	        f.parse("4/8 12:00am"), "Finance"));
	    stocks.add(new Stock("American International Group, Inc.", 64.13, 0.31, 0.49,
	        f.parse("4/1 12:00am"), "Services"));
	    stocks.add(new Stock("AT&T Inc.", 31.61, -0.48, -1.54, f.parse("4/8 12:00am"),
	        "Services"));
	    stocks.add(new Stock("Boeing Co.", 75.43, 0.53, 0.71, f.parse("4/8 12:00am"),
	        "Manufacturing"));
	    stocks.add(new Stock("Caterpillar Inc.", 67.27, 0.92, 1.39, f.parse("4/1 12:00am"),
	        "Services"));
	    stocks.add(new Stock("Citigroup, Inc.", 49.37, 0.02, 0.04, f.parse("4/4 12:00am"),
	        "Finance"));
	    stocks.add(new Stock("E.I. du Pont de Nemours and Company", 40.48, 0.51, 1.28,
	        f.parse("4/1 12:00am"), "Manufacturing"));
	    stocks.add(new Stock("Exxon Mobil Corp", 68.1, -0.43, -0.64, f.parse("4/3 12:00am"),
	        "Manufacturing"));
	    stocks.add(new Stock("General Electric Company", 34.14, -0.08, -0.23,
	        f.parse("4/3 12:00am"), "Manufacturing"));
	    stocks.add(new Stock("General Motors Corporation", 30.27, 1.09, 3.74,
	        f.parse("4/3 12:00am"), "Automotive"));
	    stocks.add(new Stock("Hewlett-Packard Co.", 36.53, -0.03, -0.08,
	        f.parse("4/3 12:00am"), "Computer"));
	    stocks.add(new Stock("Honeywell Intl Inc", 38.77, 0.05, 0.13, f.parse("4/3 12:00am"),
	        "Manufacturing"));
	    stocks.add(new Stock("Intel Corporation", 19.88, 0.31, 1.58, f.parse("4/2 12:00am"),
	        "Computer"));
	    stocks.add(new Stock("International Business Machines", 81.41, 0.44, 0.54,
	        f.parse("4/1 12:00am"), "Computer"));
	    stocks.add(new Stock("Johnson & Johnson", 64.72, 0.06, 0.09, f.parse("4/2 12:00am"),
	        "Medical"));
	    stocks.add(new Stock("JP Morgan & Chase & Co", 45.73, 0.07, 0.15,
	        f.parse("4/2 12:00am"), "Finance"));
	    stocks.add(new Stock("McDonald\"s Corporation", 36.76, 0.86, 2.40,
	        f.parse("4/2 12:00am"), "Food"));
	    stocks.add(new Stock("Merck & Co., Inc.", 40.96, 0.41, 1.01, f.parse("4/2 12:00am"),
	        "Medical"));
	    stocks.add(new Stock("Microsoft Corporation", 25.84, 0.14, 0.54,
	        f.parse("4/2 12:00am"), "Computer"));
	    stocks.add(new Stock("Pfizer Inc", 27.96, 0.4, 1.45, f.parse("4/8 12:00am"),
	        "Services"));
	    stocks.add(new Stock("The Coca-Cola Company", 45.07, 0.26, 0.58,
	        f.parse("4/1 12:00am"), "Food"));
	    stocks.add(new Stock("The Home Depot, Inc.", 34.64, 0.35, 1.02,
	        f.parse("4/8 12:00am"), "Retail"));
	    stocks.add(new Stock("The Procter & Gamble Company", 61.91, 0.01, 0.02,
	        f.parse("4/1 12:00am"), "Manufacturing"));
	    stocks.add(new Stock("United Technologies Corporation", 63.26, 0.55, 0.88,
	        f.parse("4/1 12:00am"), "Computer"));
	    stocks.add(new Stock("Verizon Communications", 35.57, 0.39, 1.11,
	        f.parse("4/3 12:00am"), "Services"));
	    stocks.add(new Stock("Wal-Mart Stores, Inc.", 45.45, 0.73, 1.63,
	        f.parse("4/3 12:00am"), "Retail"));
	    stocks.add(new Stock("Walt Disney Company (The) (Holding Company)", 29.89, 0.24,
	        0.81, f.parse("4/1 12:00am"), "Services"));
	    return stocks;
	  }
}
