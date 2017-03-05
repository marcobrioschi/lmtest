package biz.marcobrioschi.lmtest.util;


public class Money {

	long moneyCents;

	private Money(long moneyCents) {
		this.moneyCents = moneyCents;
	}
	
	public Money(double value) {
		moneyCents = Math.round( value * 100 );
	}

	public Money add(Money money) {
		return new Money(moneyCents + money.moneyCents);
	}
	
	public double getValueAsDouble() {
		return (double)moneyCents / 100.0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (moneyCents ^ (moneyCents >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (moneyCents != other.moneyCents)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[moneyCents=").append(moneyCents).append(" $]");
		return builder.toString();
	}

	public static final Money ZERO = new Money(0L);
	
}
