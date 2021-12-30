package com.example.demo.classload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private static ServerSocket ss;
    private static ExecutorService ex = Executors.newCachedThreadPool();
    private static class SoktHandler implements Runnable {
        private Socket socket;

        public SoktHandler(Socket so){
            this.socket = so;
        }
        @Override
        public void run() {
            try{
                if( socket != null ){
                    InputStream in = socket.getInputStream();
                    Reader reader = new InputStreamReader(in, Charset.forName("utf-8"));
                    char[] buffer = new char[1024];
                    StringBuilder sb = new StringBuilder();
                    for( int len = 0; (len = reader.read(buffer)) != -1; ){
                        sb.append(buffer,0,len);
                    }
                    if( "STOP".equals( sb.toString() ) ){
                        synchronized (SoktHandler.class){
                            if( !ss.isClosed() ){
                                ss.close();
                            }
                        }
                    }else{
                        String resourceName = sb.toString();
                        resourceName = resourceName.replaceAll("\\.","/");
                        if(!resourceName.endsWith(".class")){
                            resourceName += ".class";
                            InputStream ins = SocketServer.class.getClassLoader().getResourceAsStream(resourceName);
                            OutputStream out = socket.getOutputStream();
                            byte[] bytes = new byte[1024];
                            for( int len = 0; ( len = ins.read(bytes) ) != -1;){
                                out.write(bytes,0,len);
                            }
                            out.flush();
                            out.close();
                            ins.close();
                        }
                    }

                }
            }catch (Exception e){
                System.out.println("IO异常，连接被关闭。");
            }
        }
    }
    public static void main(String[] args) {
        try{
            ss = new ServerSocket(8080);
            while (true){
                Socket socket = ss.accept();
                ex.execute(new SoktHandler(socket));
            }
        }catch (IOException e){
            if( e instanceof SocketException){
                System.out.println( "服务关闭！" );
                ex.shutdown();
            }
        }
    }
}