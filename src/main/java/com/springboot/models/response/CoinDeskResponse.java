package com.springboot.models.response;

public class CoinDeskResponse {
	private Time time;

	private String disclaimer;

	private String chartName;

	private bpi bpi;
	
	public class Time{
		private String updated;

		private String updatedISO;

		private String updateduk;

		public String getUpdated() {
			return updated;
		}

		public void setUpdated(String updated) {
			this.updated = updated;
		}

		public String getUpdatedISO() {
			return updatedISO;
		}

		public void setUpdatedISO(String updatedISO) {
			this.updatedISO = updatedISO;
		}

		public String getUpdateduk() {
			return updateduk;
		}

		public void setUpdateduk(String updateduk) {
			this.updateduk = updateduk;
		}
	}
	public class bpi{
		private CoinInfo USD;

		private CoinInfo GBP;

		private CoinInfo EUR;
		
		public CoinInfo getUSD() {
			return USD;
		}

		public void setUSD(CoinInfo uSD) {
			USD = uSD;
		}

		public CoinInfo getGBP() {
			return GBP;
		}

		public void setGBP(CoinInfo gBP) {
			GBP = gBP;
		}

		public CoinInfo getEUR() {
			return EUR;
		}

		public void setEUR(CoinInfo eUR) {
			EUR = eUR;
		}

		public class CoinInfo{
			private String code;

			private String symbol;

			private String rate;

			private String description;

			private String rate_float;

			public String getCode() {
				return code;
			}

			public void setCode(String code) {
				this.code = code;
			}

			public String getSymbol() {
				return symbol;
			}

			public void setSymbol(String symbol) {
				this.symbol = symbol;
			}

			public String getRate() {
				return rate;
			}

			public void setRate(String rate) {
				this.rate = rate;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getRate_float() {
				return rate_float;
			}

			public void setRate_float(String rate_float) {
				this.rate_float = rate_float;
			}
		}
	}

	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public bpi getBpi() {
		return bpi;
	}
	public void setBpi(bpi bpi) {
		this.bpi = bpi;
	}
}

