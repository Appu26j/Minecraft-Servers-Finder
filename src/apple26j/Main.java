package apple26j;

import br.com.azalim.mcserverping.MCPing;
import br.com.azalim.mcserverping.MCPingOptions;
import br.com.azalim.mcserverping.MCPingResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main
{
    private static final String americanNgrokIP = "0.tcp.ngrok.io";
    private static final String indianNgrokIP = "0.tcp.in.ngrok.io";
    private static final String asianNgrokIP = "0.tcp.ap.ngrok.io";
    private static final String europeanNgrokIP = "0.tcp.eu.ngrok.io";
    private static final String australianNgrokIP = "0.tcp.au.ngrok.io";
    private static final String japaneseNgrokIP = "0.tcp.jp.ngrok.io";
    private static final String southAmericanNgrokIP = "0.tcp.sa.ngrok.io";
    private static final String[] allNgrokIPs = new String[]{americanNgrokIP, indianNgrokIP, asianNgrokIP, europeanNgrokIP, australianNgrokIP, japaneseNgrokIP, southAmericanNgrokIP};

    public static void main(String[] arguments)
    {
        new Thread(() ->
        {
            try
            {
                MCPing.getPing("hypixel.net");
            }

            catch (Exception e)
            {
                ;
            }
        }).start();

        long timee = System.nanoTime() / 1000000;

        while (((System.nanoTime() / 1000000) - timee) < 250)
        {
            ;
        }

        try
        {
            (new ProcessBuilder("cmd", "/c", "cls")).inheritIO().start().waitFor();
        }

        catch (Exception e)
        {
            ;
        }

        try
        {
            System.out.print("By using this program, you agree that the creator of the program shall not be responsible for any of your actions." + System.lineSeparator());
            long time = System.nanoTime() / 1000000;

            while (((System.nanoTime() / 1000000) - time) < 4000)
            {
                ;
            }

            (new ProcessBuilder("cmd", "/c", "cls")).inheritIO().start().waitFor();
        }

        catch (Exception e)
        {
            ;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Should the servers be:" + System.lineSeparator() + "1: American" + System.lineSeparator() + "2: Indian" + System.lineSeparator() + "3: Asian" + System.lineSeparator() + "4: European" + System.lineSeparator() + "5: Australian" + System.lineSeparator() + "6: Japanese" + System.lineSeparator() + "7: South American" + System.lineSeparator() + "8: All of the Above" + System.lineSeparator() + "Type the number accordingly." + System.lineSeparator()+ "> ");
        int nextInt = Integer.parseInt(scanner.nextLine());
        String ngrokIP = "";

        switch (nextInt)
        {
            case 1:
            {
                ngrokIP = americanNgrokIP;
                break;
            }

            case 2:
            {
                ngrokIP = indianNgrokIP;
                break;
            }

            case 3:
            {
                ngrokIP = asianNgrokIP;
                break;
            }

            case 4:
            {
                ngrokIP = europeanNgrokIP;
                break;
            }

            case 5:
            {
                ngrokIP = australianNgrokIP;
                break;
            }

            case 6:
            {
                ngrokIP = japaneseNgrokIP;
                break;
            }

            case 7:
            {
                ngrokIP = southAmericanNgrokIP;
                break;
            }

            case 8:
            {
                ngrokIP = "All";
                break;
            }
        }

        String finalNgrokIP = ngrokIP;
        System.out.print(System.lineSeparator() + "Do you want to skip servers with 0 players? (Yes/No)" + System.lineSeparator() + "> ");
        boolean skipServersWithZeroPlayers = scanner.nextLine().equalsIgnoreCase("yes");
        System.out.print(System.lineSeparator() + "Finding servers..." + System.lineSeparator());
        System.out.print("If you wish to stop, you may close the program.");
        File hits = new File("hits.txt");

        if (hits.exists())
        {
            hits.delete();
        }

        try
        {
            hits.createNewFile();
        }

        catch (Exception e)
        {
            ;
        }

        System.out.print(System.lineSeparator() + System.lineSeparator());

        for (int i = 10000; i < 20000; i++)
        {
            int finalI = i;

            if (ngrokIP.equals("All"))
            {
                for (String IP : allNgrokIPs)
                {
                    new Thread(() ->
                    {
                        Socket socket = null;

                        try
                        {
                            socket = new Socket(IP, finalI);
                            MCPingOptions mcPingOptions = MCPingOptions.builder().hostname(IP).port(finalI).build();
                            MCPingResponse mcPingResponse = MCPing.getPing(mcPingOptions);

                            if (skipServersWithZeroPlayers && mcPingResponse.getPlayers().getOnline() == 0)
                            {
                                return;
                            }

                            String text = "Server \"" + IP + ":" + finalI + "\" is online!" + System.lineSeparator() + "MOTD: " + mcPingResponse.getDescription().getStrippedText().replaceAll("\n", " ") + System.lineSeparator() + "Ping: " + mcPingResponse.getPing() + "ms" + System.lineSeparator() + "Version: " + mcPingResponse.getVersion().getName() + System.lineSeparator() + "Players online: " + mcPingResponse.getPlayers().getOnline() + " out of " + mcPingResponse.getPlayers().getMax() + System.lineSeparator() + (mcPingResponse.getPlayers().getSample() == null ? System.lineSeparator() : "Their names: ");

                            if (mcPingResponse.getPlayers().getSample() != null)
                            {
                                for (MCPingResponse.Player player : mcPingResponse.getPlayers().getSample())
                                {
                                    text += player.equals(mcPingResponse.getPlayers().getSample().get(mcPingResponse.getPlayers().getSample().size() - 1)) ? player.getName() + System.lineSeparator() + System.lineSeparator() : player.getName() + ", ";
                                }
                            }

                            System.out.print(text);
                            FileWriter fileWriter = new FileWriter(hits, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            PrintWriter printWriter = new PrintWriter(bufferedWriter);
                            printWriter.print("Server \"" + IP + ":" + finalI + "\" is online!" + System.lineSeparator() + "MOTD: " + mcPingResponse.getDescription().getStrippedText().replaceAll("\n", " ") + System.lineSeparator() + "Ping: " + mcPingResponse.getPing() + "ms" + System.lineSeparator() + "Version: " + mcPingResponse.getVersion().getName() + System.lineSeparator() + "Players online: " + mcPingResponse.getPlayers().getOnline() + " out of " + mcPingResponse.getPlayers().getMax() + System.lineSeparator() + (mcPingResponse.getPlayers().getSample() == null ? System.lineSeparator() : "Their names: "));

                            if (mcPingResponse.getPlayers().getSample() != null)
                            {
                                for (MCPingResponse.Player player : mcPingResponse.getPlayers().getSample())
                                {
                                    printWriter.print(player.equals(mcPingResponse.getPlayers().getSample().get(mcPingResponse.getPlayers().getSample().size() - 1)) ? player.getName() + System.lineSeparator() + System.lineSeparator() : player.getName() + ", ");
                                }
                            }

                            printWriter.flush();
                            printWriter.close();
                            bufferedWriter.close();
                            fileWriter.close();
                        }

                        catch (Exception e)
                        {
                            ;
                        }

                        finally
                        {
                            if (socket != null)
                            {
                                try
                                {
                                    socket.close();
                                }

                                catch (Exception e)
                                {
                                    ;
                                }
                            }
                        }
                    }).start();
                }
            }

            else
            {
                new Thread(() ->
                {
                    Socket socket = null;

                    try
                    {
                        socket = new Socket(finalNgrokIP, finalI);
                        MCPingOptions mcPingOptions = MCPingOptions.builder().hostname(finalNgrokIP).port(finalI).build();
                        MCPingResponse mcPingResponse = MCPing.getPing(mcPingOptions);

                        if (skipServersWithZeroPlayers && mcPingResponse.getPlayers().getOnline() == 0)
                        {
                            return;
                        }

                        String text = "Server \"" + finalNgrokIP + ":" + finalI + "\" is online!" + System.lineSeparator() + "MOTD: " + mcPingResponse.getDescription().getStrippedText().replaceAll("\n", " ") + System.lineSeparator() + "Ping: " + mcPingResponse.getPing() + "ms" + System.lineSeparator() + "Version: " + mcPingResponse.getVersion().getName() + System.lineSeparator() + "Players online: " + mcPingResponse.getPlayers().getOnline() + " out of " + mcPingResponse.getPlayers().getMax() + System.lineSeparator() + (mcPingResponse.getPlayers().getSample() == null ? System.lineSeparator() : "Their names: ");

                        if (mcPingResponse.getPlayers().getSample() != null)
                        {
                            for (MCPingResponse.Player player : mcPingResponse.getPlayers().getSample())
                            {
                               text += player.equals(mcPingResponse.getPlayers().getSample().get(mcPingResponse.getPlayers().getSample().size() - 1)) ? player.getName() + System.lineSeparator() + System.lineSeparator() : player.getName() + ", ";
                            }
                        }

                        System.out.print(text);
                        FileWriter fileWriter = new FileWriter(hits, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        PrintWriter printWriter = new PrintWriter(bufferedWriter);
                        printWriter.print("Server \"" + finalNgrokIP + ":" + finalI + "\" is online!" + System.lineSeparator() + "MOTD: " + mcPingResponse.getDescription().getStrippedText().replaceAll("\n", " ") + System.lineSeparator() + "Ping: " + mcPingResponse.getPing() + "ms" + System.lineSeparator() + "Version: " + mcPingResponse.getVersion().getName() + System.lineSeparator() + "Players online: " + mcPingResponse.getPlayers().getOnline() + " out of " + mcPingResponse.getPlayers().getMax() + System.lineSeparator() + (mcPingResponse.getPlayers().getSample() == null ? System.lineSeparator() : "Their names: "));

                        if (mcPingResponse.getPlayers().getSample() != null)
                        {
                            for (MCPingResponse.Player player : mcPingResponse.getPlayers().getSample())
                            {
                                printWriter.print(player.equals(mcPingResponse.getPlayers().getSample().get(mcPingResponse.getPlayers().getSample().size() - 1)) ? player.getName() + System.lineSeparator() + System.lineSeparator() : player.getName() + ", ");
                            }
                        }

                        printWriter.flush();
                        printWriter.close();
                        bufferedWriter.close();
                        fileWriter.close();
                    }

                    catch (Exception e)
                    {
                        ;
                    }

                    finally
                    {
                        if (socket != null)
                        {
                            try
                            {
                                socket.close();
                            }

                            catch (Exception e)
                            {
                                ;
                            }
                        }
                    }
                }).start();
            }
        }

        scanner.close();
    }
}
