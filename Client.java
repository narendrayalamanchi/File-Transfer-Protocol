//FtpClient.java

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
class Client extends JFrame implements ActionListener
{
 String fn,filenm;
 String fc;
 String dirn="c:/FTP CLIENT DIRECTORY";
 JPanel pnl;
 JLabel lbltle,lblud;
 Font fnt;
 JTextField txtfn;
 JButton btnu,btnd;
 Socket s;
 InputStreamReader in;
 OutputStream out;
 BufferedReader br;
 PrintWriter pw;
 public Client()
 {
  super("FTP CLIENT");

  pnl=new JPanel(null);

  fnt=new Font("Times New Roman",Font.BOLD,25);

  lbltle=new JLabel("FTP CLIENT");
  lbltle.setFont(fnt);
  lbltle.setBounds(225,35,200,30);
  pnl.add(lbltle);

  lblud=new JLabel("ENTER  FILE-NAME :");
  lblud.setBounds(100,100,150,35);
  pnl.add(lblud);

  txtfn=new JTextField();
  txtfn.setBounds(300,100,200,25);
  pnl.add(txtfn);

  btnu=new JButton("UPLOAD");
  btnu.setBounds(150,200,120,35);
  pnl.add(btnu);


  btnd=new JButton("DOWNLOAD");
  btnd.setBounds(320,200,120,35);

  pnl.add(btnd);

  btnu.addActionListener(this);
  btnd.addActionListener(this);
  getContentPane().add(pnl);

  try
  {
  s=new Socket("localhost",100);
  br=new BufferedReader(new InputStreamReader(s.getInputStream()));
  pw=new PrintWriter(s.getOutputStream(),true);
  out=s.getOutputStream();
  }
  catch(Exception e)
  {
   System.out.println("ExCEPTION :"+e.getMessage());
  }
 }

 private URLConnection client;

    private String host;

    private String username;

    private String password;

    private String remoteFile;

    public void setHost(String host) {
        this.host = "localhost";
    }

    public void setUser(String user) {
        this.username = "hello";
    }

    public void setPassword(String p) {
        this.password = "pass";
    }

    public void setRemoteFile(String d) {
        this.remoteFile = "hello.txt";
    }

 public void actionPerformed(ActionEvent e)
 {
  if(e.getSource()==btnu)
  {
    try {

            InputStream is = new FileInputStream(txtfn.getText());
            BufferedInputStream bis = new BufferedInputStream(is);
            OutputStream os = client.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            byte[] buffer = new byte[1024];
            int readCount;

            while ((readCount = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, readCount);
            }
            bos.close();

            System.out.println("Successfully uploaded");

            //return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            //return false;
        }
    }
  

  if(e.getSource()==btnd)
  {
   try
   {
   File dir=new File(dirn);
   if(!dir.exists())
   {
    dir.mkdir();
   }
   else{}
   boolean done=true;
   filenm=txtfn.getText();
   fn=new String("#"+filenm+"#");
   //System.out.println(filenm);
   pw.println(fn);
   File f=new File(dir,filenm);
   FileOutputStream fos=new FileOutputStream(f);
   DataOutputStream dops=new DataOutputStream(fos);
   while(done)
   {
     fc=br.readLine();
     if(fc==null)
     {
     done=false;
     }
    else
        {
          dops.writeChars(fc);
       //  System.out.println(fc);

        }
    }
   fos.close();
  }

  catch(Exception exx)
  {}

  }
 }
 public static void main(String args[])
 {
  FtpClient ftpc=new FtpClient();
  ftpc.setSize(600,300);
  ftpc.show();
 }
}