package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomNumberEditor;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月25日 下午1:56:57
 * @version 1.0
 * @description <property value=""> 转换value
 */
public class CustomNumberEditorTest {

	@Test
	public void testConvertString() {
		//true 允许为空
		CustomNumberEditor editor = new CustomNumberEditor(Integer.class,true);
		
		editor.setAsText("3");
		Object value = editor.getValue();
		Assert.assertTrue(value instanceof Integer);
		Assert.assertEquals(3, ((Integer)editor.getValue()).intValue());
		
		editor.setAsText("");
		Assert.assertTrue(editor.getValue() == null);
		
		try {
			editor.setAsText("3.1");
		} catch (IllegalArgumentException e) {
			return;
		}
		Assert.fail();
		
	}

}
