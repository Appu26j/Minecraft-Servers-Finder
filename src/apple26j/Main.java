package apple26j;

import br.com.azalim.mcserverping.MCPing;
import br.com.azalim.mcserverping.MCPingOptions;
import br.com.azalim.mcserverping.MCPingResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
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

        while (((System.nanoTime() / 1000000) - timee) < 500)
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
        System.out.println("Should the servers be:");
        System.out.println("1: American");
        System.out.println("2: Indian");
        System.out.println("3: Asian");
        System.out.println("4: European");
        System.out.println("5: Australian");
        System.out.println("6: Japanese");
        System.out.println("7: South American");
        System.out.println("8: All of the above");
        System.out.println("Type the number accordingly.");
        System.out.print("> ");
        int number = Integer.parseInt(scanner.nextLine()) - 1;
        String IP = (number < 7 ? allNgrokIPs[number] : "All");
        System.out.println();
        System.out.println("Do you want to skip servers which have 0 players? (Yes/No)");
        System.out.print("> ");
        boolean skipServersWithZeroPlayers = containsYes(scanner.nextLine());
        System.out.println();
        System.out.println("Finding servers...");
        System.out.println();
        File hitsTxt = new File("hits.txt");

        if (hitsTxt.exists())
        {
            hitsTxt.delete();
        }

        try
        {
            hitsTxt.createNewFile();
        }

        catch (Exception e)
        {
            ;
        }

        for (int i = 10000; i < 20000; i++)
        {
            int finalI = i;

            if (number == 7)
            {
                for (String ngrokIP : allNgrokIPs)
                {
                    new Thread(() ->
                    {
                        try
                        {
                            MCPingOptions mcPingOptions = MCPingOptions.builder().hostname(ngrokIP).port(finalI).build();
                            MCPingResponse mcPingResponse = MCPing.getPing(mcPingOptions);

                            if (mcPingResponse.getPlayers().getOnline() == 0 && skipServersWithZeroPlayers)
                            {
                                return;
                            }

                            String lineOne = "Server \"" + ngrokIP + ":" + finalI + "\" is online!";
                            String lineTwo = "Description: " + mcPingResponse.getDescription().getStrippedText().replaceAll("\n", " ");
                            String lineThree = "Ping: " + mcPingResponse.getPing() + "ms";
                            String lineFour = "Version: " + mcPingResponse.getVersion().getName().replaceAll("Spigot", "").replaceAll("Paper", "").replaceAll("CraftBukkit", "").trim();
                            String lineFive = "Players online: " + mcPingResponse.getPlayers().getOnline() + " out of " + mcPingResponse.getPlayers().getMax();
                            String lineSix = "";
                            String lineSeven = "Server is probably a premium server";

                            if (mcPingResponse.getPlayers().getOnline() != 0 && mcPingResponse.getPlayers().getSample() != null)
                            {
                                lineSix = "Their names: ";

                                for (MCPingResponse.Player player : mcPingResponse.getPlayers().getSample())
                                {
                                    if (!isPlayerPremium(player.getName()))
                                    {
                                        lineSeven = "Server is probably a cracked server";
                                    }

                                    lineSix += player.equals(mcPingResponse.getPlayers().getSample().get(mcPingResponse.getPlayers().getSample().size() - 1)) ? player.getName() : player.getName() + ", ";
                                }
                            }

                            String[] allLines = new String[]{lineOne, lineTwo, lineThree, lineFour, lineFive, lineSix, lineSeven};
                            String finalLine = "";

                            for (String line : allLines)
                            {
                                if (line.isEmpty())
                                {
                                    continue;
                                }

                                finalLine += line + System.lineSeparator();
                            }

                            System.out.print(finalLine + System.lineSeparator());
                            PrintWriter printWriter = new PrintWriter(new FileWriter(hitsTxt, true));
                            printWriter.print(finalLine + System.lineSeparator());
                            printWriter.close();
                        }

                        catch (Exception e)
                        {
                            ;
                        }
                    }).start();
                }
            }

            else
            {
                new Thread(() ->
                {
                    try
                    {
                        MCPingOptions mcPingOptions = MCPingOptions.builder().hostname(IP).port(finalI).build();
                        MCPingResponse mcPingResponse = MCPing.getPing(mcPingOptions);

                        if (mcPingResponse.getPlayers().getOnline() == 0 && skipServersWithZeroPlayers)
                        {
                            return;
                        }

                        String lineOne = "Server \"" + IP + ":" + finalI + "\" is online!";
                        String lineTwo = "Description: " + mcPingResponse.getDescription().getStrippedText().replaceAll("\n", " ");
                        String lineThree = "Ping: " + mcPingResponse.getPing() + "ms";
                        String lineFour = "Version: " + mcPingResponse.getVersion().getName().replaceAll("Spigot", "").replaceAll("Paper", "").replaceAll("CraftBukkit", "").trim();
                        String lineFive = "Players online: " + mcPingResponse.getPlayers().getOnline() + " out of " + mcPingResponse.getPlayers().getMax();
                        String lineSix = "";
                        String lineSeven = "Server is probably a premium server";

                        if (mcPingResponse.getPlayers().getOnline() != 0 && mcPingResponse.getPlayers().getSample() != null)
                        {
                            lineSix = "Their names: ";

                            for (MCPingResponse.Player player : mcPingResponse.getPlayers().getSample())
                            {
                                if (!isPlayerPremium(player.getName()))
                                {
                                    lineSeven = "Server is probably a cracked server";
                                }

                                lineSix += player.equals(mcPingResponse.getPlayers().getSample().get(mcPingResponse.getPlayers().getSample().size() - 1)) ? player.getName() : player.getName() + ", ";
                            }
                        }

                        String[] allLines = new String[]{lineOne, lineTwo, lineThree, lineFour, lineFive, lineSix, lineSeven};
                        String finalLine = "";

                        for (String line : allLines)
                        {
                            if (line.isEmpty())
                            {
                                continue;
                            }

                            finalLine += line + System.lineSeparator();
                        }

                        System.out.print(finalLine + System.lineSeparator());
                        PrintWriter printWriter = new PrintWriter(new FileWriter(hitsTxt, true));
                        printWriter.print(finalLine + System.lineSeparator());
                        printWriter.close();
                    }

                    catch (Exception e)
                    {
                        ;
                    }
                }).start();
            }
        }
    }

    public static boolean containsYes(String line)
    {
        return line.toLowerCase().contains("yes") || line.toLowerCase().contains("yep") || line.toLowerCase().contains("ya") || line.toLowerCase().contains("yah") || line.toLowerCase().contains("yeah") || line.toLowerCase().contains("yeh") || line.toLowerCase().contains("yup");
    }

    public static boolean isPlayerPremium(String playerName)
    {
        try
        {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) (new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName)).openConnection();
            httpsURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            String line = bufferedReader.readLine();
            httpsURLConnection.disconnect();
            return line != null;
        }

        catch (Exception e)
        {
            return false;
        }
    }
}
