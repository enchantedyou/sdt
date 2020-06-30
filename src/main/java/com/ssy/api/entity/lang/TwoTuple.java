package com.ssy.api.entity.lang;

/**
 * <p>
 * 文件功能说明：
 *       	双元组		
 * </p>
 * 
 * @Author sunshaoyu
 *         <p>
 *         <li>2019年7月31日-上午9:31:54</li>
 *         <li>修改记录</li>
 *         <li>-----------------------------------------------------------</li>
 *         <li>标记：修订内容</li>
 *         <li>2019年7月31日-sunshaoyu：创建注释模板</li>
 *         <li>-----------------------------------------------------------</li>
 *         </p>
 */
public class TwoTuple<A,B> {
	
	private final A first;  
	private final B second;
    
	public TwoTuple(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "[<"+first+","+second+">]";
	}
}
