package org.litespring.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 来自 org.springframework.
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月25日 下午1:48:29
 * @version 1.0
 * @description
 */
public abstract class NumberUtils
{
  public static <T extends Number> T convertNumberToTargetClass(Number number, Class<T> targetClass)
    throws IllegalArgumentException
  {
    Assert.notNull(number, "Number must not be null");
    Assert.notNull(targetClass, "Target class must not be null");
    if (targetClass.isInstance(number)) {
      return (T) number;
    }
    if (targetClass.equals(Byte.class))
    {
      long value = number.longValue();
      if ((value < -128L) || (value > 127L)) {
        raiseOverflowException(number, targetClass);
      }
      return (T) new Byte(number.byteValue());
    }
    if (targetClass.equals(Short.class))
    {
      long value = number.longValue();
      if ((value < -32768L) || (value > 32767L)) {
        raiseOverflowException(number, targetClass);
      }
      return (T) new Short(number.shortValue());
    }
    if (targetClass.equals(Integer.class))
    {
      long value = number.longValue();
      if ((value < -2147483648L) || (value > 2147483647L)) {
        raiseOverflowException(number, targetClass);
      }
      return (T) new Integer(number.intValue());
    }
    if (targetClass.equals(Long.class)) {
      return (T) new Long(number.longValue());
    }
    if (targetClass.equals(BigInteger.class))
    {
      if ((number instanceof BigDecimal)) {
        return (T) ((BigDecimal)number).toBigInteger();
      }
      return (T) BigInteger.valueOf(number.longValue());
    }
    if (targetClass.equals(Float.class)) {
      return (T) new Float(number.floatValue());
    }
    if (targetClass.equals(Double.class)) {
      return (T) new Double(number.doubleValue());
    }
    if (targetClass.equals(BigDecimal.class)) {
      return (T) new BigDecimal(number.toString());
    }
    throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to unknown target class [" + targetClass.getName() + "]");
  }
  
  private static void raiseOverflowException(Number number, Class targetClass)
  {
    throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to target class [" + targetClass.getName() + "]: overflow");
  }
  
  public static <T extends Number> T parseNumber(String text, Class<T> targetClass)
  {
    Assert.notNull(text, "Text must not be null");
    Assert.notNull(targetClass, "Target class must not be null");
    String trimmed = StringUtils.trimAllWhitespace(text);
    if (targetClass.equals(Byte.class)) {
      return (T) (isHexNumber(trimmed) ? Byte.decode(trimmed) : Byte.valueOf(trimmed));
    }
    if (targetClass.equals(Short.class)) {
      return (T) (isHexNumber(trimmed) ? Short.decode(trimmed) : Short.valueOf(trimmed));
    }
    if (targetClass.equals(Integer.class)) {
      return (T) (isHexNumber(trimmed) ? Integer.decode(trimmed) : Integer.valueOf(trimmed));
    }
    if (targetClass.equals(Long.class)) {
      return (T) (isHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
    }
    if (targetClass.equals(BigInteger.class)) {
      return (T) (isHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
    }
    if (targetClass.equals(Float.class)) {
      return (T) Float.valueOf(trimmed);
    }
    if (targetClass.equals(Double.class)) {
      return (T) Double.valueOf(trimmed);
    }
    if ((targetClass.equals(BigDecimal.class)) || (targetClass.equals(Number.class))) {
      return (T) new BigDecimal(trimmed);
    }
    throw new IllegalArgumentException("Cannot convert String [" + text + "] to target class [" + targetClass.getName() + "]");
  }
  
  public static <T extends Number> T parseNumber(String text, Class<T> targetClass, NumberFormat numberFormat)
  {
    if (numberFormat != null)
    {
      Assert.notNull(text, "Text must not be null");
      Assert.notNull(targetClass, "Target class must not be null");
      DecimalFormat decimalFormat = null;
      boolean resetBigDecimal = false;
      if ((numberFormat instanceof DecimalFormat))
      {
        decimalFormat = (DecimalFormat)numberFormat;
        if ((BigDecimal.class.equals(targetClass)) && (!decimalFormat.isParseBigDecimal()))
        {
          decimalFormat.setParseBigDecimal(true);
          resetBigDecimal = true;
        }
      }
      try
      {
        Number number = numberFormat.parse(StringUtils.trimAllWhitespace(text));
        return convertNumberToTargetClass(number, targetClass);
      }
      catch (ParseException ex)
      {
        throw new IllegalArgumentException("Could not parse number: " + ex.getMessage());
      }
      finally
      {
        if (resetBigDecimal) {
          decimalFormat.setParseBigDecimal(false);
        }
      }
    }
    return parseNumber(text, targetClass);
  }
  
  private static boolean isHexNumber(String value)
  {
    int index = value.startsWith("-") ? 1 : 0;
    return (value.startsWith("0x", index)) || (value.startsWith("0X", index)) || (value.startsWith("#", index));
  }
  
  private static BigInteger decodeBigInteger(String value)
  {
    int radix = 10;
    int index = 0;
    boolean negative = false;
    if (value.startsWith("-"))
    {
      negative = true;
      index++;
    }
    if ((value.startsWith("0x", index)) || (value.startsWith("0X", index)))
    {
      index += 2;
      radix = 16;
    }
    else if (value.startsWith("#", index))
    {
      index++;
      radix = 16;
    }
    else if ((value.startsWith("0", index)) && (value.length() > 1 + index))
    {
      index++;
      radix = 8;
    }
    BigInteger result = new BigInteger(value.substring(index), radix);
    return negative ? result.negate() : result;
  }
}
