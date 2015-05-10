package com.zebra.utils.bloomFilter.filter;

import com.zebra.utils.Hashs;

public class ELFFilter extends AbstractFilter {

	public ELFFilter(long maxValue, int MACHINENUM) {
		super(maxValue, MACHINENUM);
	}
	
	public ELFFilter(long maxValue) {
		super(maxValue);
	}
	
	@Override
	public long hash(String str) {
		return Hashs.ELFHash(str) % size;
	}

}
