package com.zebra.utils.bloomFilter.filter;

import com.zebra.utils.Hashs;

public class PJWFilter extends AbstractFilter {

	public PJWFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public PJWFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return Hashs.PJWHash(str) % size;
	}

}
