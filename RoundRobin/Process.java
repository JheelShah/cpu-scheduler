package RoundRobin;

public class Process
{
    public int PID, ArrivalTime, ServiceTime, BurstTime, CompletionTime;

    public Process(int PID, int ArrivalTime, int BurstTime)
    {
        this.PID = PID; // Unique
        this.ArrivalTime = ArrivalTime;
        this.BurstTime = BurstTime;
        this. ServiceTime = 0;
    }
}
