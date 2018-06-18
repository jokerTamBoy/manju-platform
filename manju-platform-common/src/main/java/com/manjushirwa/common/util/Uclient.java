package com.manjushirwa.common.util;
/*java中封装了大量的socket API,为编写网络通信程序提供了极大的方便.
        在计算机网络的学习中,大家都已熟练掌握了TCP/UDP的基本原理,在此不在赘述.仅给出接收端和发送端的源代码,供大家讨论和批评.
        发送端代码如下:*/
//只要稍加改进,即可发送数据
import java.io.*;
import java.lang.*;
import java.net.*;
public class Uclient {
 private DatagramSocket cli;
 private DatagramPacket pac;
 private byte sb[];
 private String sen;
 public Uclient()
 {
  Init();
 }
 public void Init()
 {
  try
  {
   //指定端口号，避免与其他应用程序发生冲突
   cli=new DatagramSocket(10002);
   sb=new byte[1024];
   sen="UDP方式发送数据";
   sb=sen.getBytes();
   pac=new DatagramPacket(sb,sb.length,InetAddress.getByName("localhost"),10005);
   cli.send(pac);
  }
  catch(SocketException se)
  {
   se.printStackTrace();
  }
  catch(IOException ie)
  {
   ie.printStackTrace();
  }
 }
 public static void main(String args[])
 {
  new Uclient();
 }
}
