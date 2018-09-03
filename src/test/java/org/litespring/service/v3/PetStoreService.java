package org.litespring.service.v3;

import org.litespring.dao.v3.AccountDao;
import org.litespring.dao.v3.ItemDao;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年9月2日 下午8:18:25
 * @version 1.0
 * @description
 */
public class PetStoreService {
	private AccountDao accountDao;
	private ItemDao itemDao;
	private int version;

	public PetStoreService(AccountDao accountDao, ItemDao itemDao, int version) {
		super();
		this.accountDao = accountDao;
		this.itemDao = itemDao;
		this.version = version;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	

}
