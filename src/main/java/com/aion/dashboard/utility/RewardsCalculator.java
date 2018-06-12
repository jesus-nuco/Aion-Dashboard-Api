package com.aion.dashboard.utility;

import java.math.BigInteger;

public class RewardsCalculator {
	private static BigInteger m;
	private static final BigInteger BLOCK_REWARD = new BigInteger("1497989283243310185");
	private static final long RAMP_UP_LOWER_BOUND = 0;
	private static final long RAMP_UP_UPPER_BOUND = 259200; // 1 month
	private static final BigInteger RAMP_UP_START_VALUE = new BigInteger("748994641621655092");
	private static final BigInteger RAMP_UP_END_VALUE = BLOCK_REWARD;
	static {
		// pre-calculate the desired increment
		long delta = RAMP_UP_UPPER_BOUND - RAMP_UP_LOWER_BOUND;
		m = RAMP_UP_END_VALUE.subtract(RAMP_UP_START_VALUE).divide(BigInteger.valueOf(delta));
	}
	/**
	 * Linear ramp function that falls off after the upper bound
	 */
	public static BigInteger calculateReward(long number) {
		if(number < 1) {
			return BigInteger.ZERO;
		}
		if (number <= RAMP_UP_UPPER_BOUND) {
			return BigInteger.valueOf(number).multiply(m).add(RAMP_UP_START_VALUE);
		} else {
			return BLOCK_REWARD;
		}
	}
	public BigInteger getDelta() {
		return m;
	}

}
