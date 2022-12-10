package org.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Client extends Thread {
    private static Socket player;
    private static BufferedReader reader;
    private static PrintWriter writer;
    private static BufferedReader consoleReader;
    private static Set<String> signs = Stream.of("x", "o").collect(Collectors.toSet());

    public static void main(String[] args) throws IOException {
        try {
            player = new Socket("localhost", 1111);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(player.getInputStream()));
            writer = new PrintWriter(player.getOutputStream(), true);
            System.out.println(reader.readLine());
            writer.println(consoleReader.readLine());
            while(!(reader.readLine()).equals("Good")) {
                System.out.println("Input correct sign like x or o");
                writer.println(consoleReader.readLine());
            }
            System.out.println("Good");
            while (true) {
                String line = reader.readLine();
                if(line.equals("Input coordinates for next move like 0 0")) {
                    System.out.println("Input coordinates for next move like 0 0");
                    for(int i = 0; i < 5; i++) {
                        System.out.println(reader.readLine());
                    }
                    while(!reader.readLine().equals("Good")) {
                        System.out.println("Input coordinates like 0 0");
                        writer.println(consoleReader.readLine());
                    }
                    System.out.println("Good");
                }
                else {
                    System.out.println(line);
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            player.close();
        }
    }
}
