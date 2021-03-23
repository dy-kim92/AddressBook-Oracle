package com.java.addressbook;

import java.util.List;

public interface AddressDao {
	
	public List<AddressVo> getList();
	public List<AddressVo> search(String keyword);
	public AddressVo get(Long id);
	public boolean insert(AddressVo vo);
	public boolean delete(Long id);

}
