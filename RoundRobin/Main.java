package RoundRobin;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter csv filename:");
        String FileName = scanner.nextLine();
        System.out.print("Please enter time quantum:");
        int TimeQuantum = scanner.nextInt();
        Scanner sc = new Scanner(new File(FileName));
        sc.useDelimiter("(,|\\v+)");
        sc.nextLine();
        ArrayList<RoundRobin.Process> csvFile = new ArrayList<>();
        while (sc.hasNext()) {
            RoundRobin.Process Process = new RoundRobin.Process(sc.nextInt(),
                    sc.nextInt(), sc.nextInt());
            csvFile.add(Process);
        }
        RoundRobin.RRScheduler MyScheduler = new RoundRobin.RRScheduler(csvFile, TimeQuantum);
        MyScheduler.RoundRobin();
        scanner.close();
        sc.close();
    }
}