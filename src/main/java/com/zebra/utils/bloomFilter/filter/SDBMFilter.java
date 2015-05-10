package com.zebra.utils.bloomFilter.filter;

import com.zebra.utils.Hashs;

public class SDBMFilter extends AbstractFilter {

	public SDBMFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public SDBMFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return Hashs.SDBMHash(str) % size;
	}

}
