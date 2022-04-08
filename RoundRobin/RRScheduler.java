package RoundRobin;

import java.util.ArrayList;
public class RRScheduler
{
    int Timer;
    ArrayList<RoundRobin.Process> ProcessesList;
    ArrayList<RoundRobin.Process> ReadyQueue;
    ArrayList<RoundRobin.Process> EndProcesses;

    int TimeQuantum;
    int ContextSwitch; // small number that should be less than half the TimeQuantum

    RoundRobin.Process CPU;
    int Counter;

    public RRScheduler(ArrayList<RoundRobin.Process> ProcessesList, int TimeQuantum)
    {
        this.TimeQuantum = TimeQuantum;
        this.ProcessesList = ProcessesList;
    }

    public void RoundRobin()
    {
        Timer = 0;
        ContextSwitch = 0;
        CPU = null;
        ReadyQueue = new ArrayList<>();
        EndProcesses = new ArrayList<>();

        while(!ReadyQueue.isEmpty() || !ProcessesList.isEmpty() || CPU != null)
        {
            //add to ReadyQueue
            for(int i = 0; i < ProcessesList.size(); i++)
            {
                if(ProcessesList.get(i).ArrivalTime == Timer)
                {
                    ReadyQueue.add(ProcessesList.remove(i));
                }
            }

            // Add to CPU
            if (CPU == null)
            {
                CPU = ReadyQueue.remove(0);
            }

            Counter++;
            CPU.ServiceTime++;

            if(CPU.BurstTime == CPU.ServiceTime)
            {
                // done
                CPU.CompletionTime = Timer; //Completion Time is set
                EndProcesses.add(CPU);
                CPU = null;
                ContextSwitch++;
                Counter = 0;
            }
            else if(Counter == TimeQuantum)
            {
                //Exceeds TimeQuantum
                ReadyQueue.add(CPU);
                CPU = null;
                ContextSwitch++;
                Counter = 0;
            }
            Timer++; // Real Time

            double SumTurnAroundTime = 0.0;
            double SumWaitingTime = 0.0;
            double SumUtil = 0.0;
            for(int j = 0; j < EndProcesses.size(); j++)
            {
                SumTurnAroundTime += EndProcesses.get(j).CompletionTime - EndProcesses.get(j).ArrivalTime;
                SumWaitingTime += (EndProcesses.get(j).CompletionTime - EndProcesses.get(j).ArrivalTime)
                        - EndProcesses.get(j).BurstTime;
                SumUtil += EndProcesses.get(j).BurstTime;
            }

            double AvgTurnAroundTime = SumTurnAroundTime / EndProcesses.size();
            double AvgWaitingTime = SumWaitingTime / EndProcesses.size();
            double CPUUtilization = (SumUtil - (ContextSwitch * 0.01)) / Timer;
            double Throughput = (double) EndProcesses.size() / Timer;

            System.out.println("");
            System.out.println("CPU Utilization: " + CPUUtilization);
            System.out.println("Throughput: " + Throughput);
            System.out.println("Average Waiting Time: " + AvgWaitingTime);
            System.out.println("Average Turnaround Time: " + AvgTurnAroundTime);

        }


    }

}
