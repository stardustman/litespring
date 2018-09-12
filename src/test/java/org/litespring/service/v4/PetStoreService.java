package org.litespring.service.v4;

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.stereotype.Component;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年9月9日 下午12:58:05
 * @version 1.0
 * @description
 */
@Component(value="petStore")
public class PetStoreService {
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ItemDao itemDao;
	
	public AccountDao getAccountDao() {
		return accountDao;
	}
	
	public ItemDao getItemDao() {
		return itemDao;
	}
    
	
	

}
