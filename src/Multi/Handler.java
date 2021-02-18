/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multi;

import java.net.Socket;

/**
 *
 * @author ferdio
 */
public class Handler extends Thread{
    
    private final Socket socket;

    public Handler(Socket socket){
         this.socket=socket;
    }
}
