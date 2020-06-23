import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author gydo194
 */
public class Server implements Runnable {

    private ServerSocket ssocket;
    private int port = 4445;

    public Server() {

    }

    public Server(int port) {
        this.port = port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public boolean start(int port) {
        //experimental function
        try {
            init(port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void init(int port) throws IOException {
        ssocket = new ServerSocket(port);

    }

    public void loop() throws IOException {
        Socket in;
        //create thread for connection
        while (true) {
            in = ssocket.accept();
            System.out.println("Got connection from: "+in.getInetAddress());
            ServerThread st = new ServerThread(in);
            Thread t = new Thread(st);
            t.start();
        }
    }

    public void run() {
        try {
             init(this.port);
             loop();
        } catch (Exception e) {
        }
    }

}

class ServerThread implements Runnable {

    private Socket sc;
    private static ArrayList<ServerThread> instances = new ArrayList<>();

    private synchronized void add() {
        this.instances.add(this);
    }

    private synchronized void remove() {
        this.instances.remove(this);
    }

    public ServerThread(Socket nsock) {
        sc = nsock;
        add();
        //add this to the arraylist, one instance/thread for every connection.
    }

    private synchronized void dispatchMessage(String message) {
        //send one message 

        try {
            OutputStream out = this.sc.getOutputStream();
            PrintWriter pw = new PrintWriter(out);
            //now send the message
            System.out.printf("dispatchMessage: Sending message '%s'\n", message); //visualize what's happening in the console
           // pw.print(message); //deliberately send no newline yet
            pw.println(message);
            pw.flush();

        } catch (IOException e) {
            System.out.println("dispatchMessage caught exception :(");
            e.printStackTrace();
        }

    }

    private static synchronized void dispatch(String message) {
        for (ServerThread st : instances) {
            //if(st == this) continue; //statics :/
            st.dispatchMessage(message);
        }
    }

    @Override
    public void run() {
        //listen for input, if received dispatch it
        InputStream inst;
        OutputStream outst;
        PrintWriter pw;

        try {
            //instantiate objects for reading and writing to the socket
            inst = sc.getInputStream();
            outst = sc.getOutputStream();
            pw = new PrintWriter(outst);
            Scanner in = new Scanner(inst);
            String inMessage;

            while (true) {
                inMessage = in.nextLine(); //maybe replace this with something less newline-dependant.
                //the above call *should* block until input is actually received.
                //that's rather fortunate for our CPU usage.
                System.out.printf("run(): received: '%s'.\n", inMessage);
                dispatch(inMessage);
            }

        } catch (IOException e) {
        }

    }
    
    public static void main(String[] args) throws IOException {
        Server s = new Server();
        Thread t = new Thread(s);
       s.run();
    }
}