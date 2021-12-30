package com.example.demo.classload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ClassLoad {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ClassLoader classLoader = new MyClassLoader(new InetSocketAddress(InetAddress.getLocalHost(),8080));
        while (true){
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if( "STOP".equals(line) ){
                break;
            }
            String[] commands = line.split(" ");
            System.out.println("waiting...");
            if( "send".equals(commands[0]) ){
                Class clazz = classLoader.loadClass(commands[1]);
                Method ms = clazz.getDeclaredMethod("say");
                Object instance = clazz.newInstance();
                ms.invoke(instance);
            }
            System.out.println( "load finish." );
        }
    }
}

class MyClassLoader extends ClassLoader{
    private SocketAddress sa;
    public MyClassLoader(SocketAddress sa){
        this.sa = sa;
    }

    private Map<String,Class> cache = new ConcurrentHashMap<>();
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = cache.get(name);
        //因为由ClassLoader加载过了类会进行缓存，所以这里的缓存不是必要的
        if( clazz != null ){
            System.out.println("从缓存中加载");
            return clazz;
        }else{
            System.out.println("从远程服务器加载。");
            Socket socket = new Socket();
            try {
                socket.connect(sa);
                OutputStream out = socket.getOutputStream();
                out.write(name.getBytes(Charset.forName("utf-8")));
                out.flush();
                socket.shutdownOutput();
                InputStream in = socket.getInputStream();
                ByteArrayOutputStream bot = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for( int len = 0; (len = in.read(buffer)) != -1; ){
                    bot.write(buffer,0,len);
                }
                byte[] bytes = bot.toByteArray();
                Class cls = this.defineClass(name,bytes,0,bytes.length);
                cache.putIfAbsent(name,cls);
                socket.close();
                return cls;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}