import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendMes extends Thread {
    private Socket socket;

    public SendMes(Socket socket) {
        this.socket = socket;
    }
    private String msg;
    public void run() {
        DataOutputStream objOut=null;
        try {
            objOut = new DataOutputStream(socket.getOutputStream());
            while(true){
                if(msg!=null&&msg.length()>0){
                    objOut.writeUTF(msg);
                    msg=null;
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>"+e.getMessage()+"</font></h1></html>"), "错误", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                objOut.close();
            } catch (IOException e) {
            }
        }
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}