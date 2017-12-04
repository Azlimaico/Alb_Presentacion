/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.general.calculo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Desarrollador1
 */
public class Serialization {
    /**
   * Copiar un objeto a través de mecanismos de serialización.
   */
  @SuppressWarnings( "unchecked" )
  public static <T> T copy( T source )
  {
    try
    {
      ByteArrayOutputStream ostream = new ByteArrayOutputStream();
      ObjectOutputStream oostream = new ObjectOutputStream( ostream );
   
      oostream.writeObject( source );
      oostream.flush();
   
      byte[] bytes = ostream.toByteArray();
       
      InputStream istream = new ByteArrayInputStream( bytes );
      ObjectInputStream oistream = new ObjectInputStream( istream );
       
      return ( T )oistream.readObject();
    }
    catch( RuntimeException e )
      { throw e; }
    catch( Exception e )
      { throw new IllegalArgumentException( source.getClass().getName(), e ); }
  }
}
