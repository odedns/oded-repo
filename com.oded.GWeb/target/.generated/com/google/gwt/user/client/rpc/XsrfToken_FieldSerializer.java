package com.google.gwt.user.client.rpc;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class XsrfToken_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static java.lang.String getToken(com.google.gwt.user.client.rpc.XsrfToken instance) {
    return (java.lang.String) ReflectionHelper.getField(com.google.gwt.user.client.rpc.XsrfToken.class, instance, "token");
  }
  
  private static void setToken(com.google.gwt.user.client.rpc.XsrfToken instance, java.lang.String value) 
  {
    ReflectionHelper.setField(com.google.gwt.user.client.rpc.XsrfToken.class, instance, "token", value);
  }
  
  public static void deserialize(SerializationStreamReader streamReader, com.google.gwt.user.client.rpc.XsrfToken instance) throws SerializationException {
    setToken(instance, streamReader.readString());
    
  }
  
  public static com.google.gwt.user.client.rpc.XsrfToken instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.google.gwt.user.client.rpc.XsrfToken();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.google.gwt.user.client.rpc.XsrfToken instance) throws SerializationException {
    streamWriter.writeString(getToken(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.google.gwt.user.client.rpc.XsrfToken_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.google.gwt.user.client.rpc.XsrfToken_FieldSerializer.deserialize(reader, (com.google.gwt.user.client.rpc.XsrfToken)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.google.gwt.user.client.rpc.XsrfToken_FieldSerializer.serialize(writer, (com.google.gwt.user.client.rpc.XsrfToken)object);
  }
  
}
