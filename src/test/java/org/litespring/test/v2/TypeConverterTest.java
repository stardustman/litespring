package org.litespring.test.v2;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.TypeMismatchException;

public class TypeConverterTest {

	@Test
	public void testConvertStringToInt() throws TypeMismatchException {
		TypeConverter converter = new SimpleTypeConverter();
		Integer i = converter.convertIfNecessary("3", int.class);
		Assert.assertEquals(3, i.intValue());

		try {
			converter.convertIfNecessary("3.1", Integer.class);
		} catch (TypeMismatchException e) {
			return;
		}

	}
	

	@Test
	public void testConvertStringToBoolean() throws TypeMismatchException {
		TypeConverter converter = new SimpleTypeConverter();
		Boolean b = converter.convertIfNecessary("true", boolean.class);
		Assert.assertEquals(true, b.booleanValue());

		try {
			converter.convertIfNecessary("xxxyyyzzz", boolean.class);
		} catch (TypeMismatchException e) {
			return;
		}

	}

}