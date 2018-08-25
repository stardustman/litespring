package org.litespring.beans.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.litespring.utils.StringUtils;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月25日 下午5:21:04
 * @version 1.0
 * @description 处理<property  value="on"/> value为boolean值
 */
public class CustomBooleanEditor
extends PropertyEditorSupport
{
public static final String VALUE_TRUE = "true";
public static final String VALUE_FALSE = "false";
public static final String VALUE_ON = "on";
public static final String VALUE_OFF = "off";
public static final String VALUE_YES = "yes";
public static final String VALUE_NO = "no";
public static final String VALUE_1 = "1";
public static final String VALUE_0 = "0";
private final String trueString;
private final String falseString;
private final boolean allowEmpty;

public CustomBooleanEditor(boolean allowEmpty)
{
  this(null, null, allowEmpty);
}

public CustomBooleanEditor(String trueString, String falseString, boolean allowEmpty)
{
  this.trueString = trueString;
  this.falseString = falseString;
  this.allowEmpty = allowEmpty;
}

public void setAsText(String text)
  throws IllegalArgumentException
{
  String input = text != null ? text.trim() : null;
  if ((this.allowEmpty) && (!StringUtils.hasLength(input))) {
    setValue(null);
  } else if ((this.trueString != null) && (input.equalsIgnoreCase(this.trueString))) {
    setValue(Boolean.TRUE);
  } else if ((this.falseString != null) && (input.equalsIgnoreCase(this.falseString))) {
    setValue(Boolean.FALSE);
  } else if ((this.trueString == null) && ((input.equalsIgnoreCase("true")) || (input.equalsIgnoreCase("on")) || (input.equalsIgnoreCase("yes")) || (input.equals("1")))) {
    setValue(Boolean.TRUE);
  } else if ((this.falseString == null) && ((input.equalsIgnoreCase("false")) || (input.equalsIgnoreCase("off")) || (input.equalsIgnoreCase("no")) || (input.equals("0")))) {
    setValue(Boolean.FALSE);
  } else {
    throw new IllegalArgumentException("Invalid boolean value [" + text + "]");
  }
}

public String getAsText()
{
  if (Boolean.TRUE.equals(getValue())) {
    return this.trueString != null ? this.trueString : "true";
  }
  if (Boolean.FALSE.equals(getValue())) {
    return this.falseString != null ? this.falseString : "false";
  }
  return "";
}
}
