/* Written by Honglan Liu(NetID: sp6682). Course: CS 4560.*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <malloc.h> 
#include <time.h> 

struct pcb 
{          
	int Process_ID;         
	int Priority;           
	int Process_Need_Time;     
	char *Status;  
	struct pcb *next;
};   
struct pcb *head;
struct pcb *head1;
struct pcb *head2;
int n;  

void createProcess(int n) 
{  
	int i = 1;    
	srand((int)time(0)); 
	head = calloc(1, sizeof(struct pcb));     
	head->next = NULL;  
	/* create processes and store them into q. */ 
	struct pcb *q = head;    
	printf("%-18s %-18s %-18s %18s\n","PROCESS_NUMS", "PRIORITY", "PROCESS_NEED_TIME", "STATUS"); 
	printf("--------------------------------------------------------------------------------------------------\n"); 
	while(i <= n)  
	{       
		struct pcb *p = calloc(1, sizeof(struct pcb));      
		p->Process_ID = i;     
		p->Status = "Ready";   
		p->Priority = rand()%10 + 1;     
		p->Process_Need_Time = rand()%40 + 2; 
		printf("%-18d %-18d %-18d %18s\n",p->Process_ID, p->Priority, p->Process_Need_Time, p->Status);
		p->next = NULL;   
		q->next = p;   
		q = q->next;   
		i++;   
	}
	printf("\n");
	/* copy the same processes queue to p0 */
	head1 = calloc(1, sizeof(struct pcb));
	head1->next = NULL;      
	struct pcb *p0 = head1; 	  
	for(q = head->next; q != NULL; q = q->next)  
	{   
		struct pcb *r = calloc(1,sizeof(struct pcb));   
		r->Process_ID = q->Process_ID;     
		r->Status = q->Status;   
		r->Priority = q->Priority; 
		r->Process_Need_Time = q->Process_Need_Time;   
		p0->next = r;   
		r->next = NULL;   
		p0 = p0->next;   
	}   
	/* copy the same processes queue to p1 */ 
	head2 = calloc(1, sizeof(struct pcb));
	head2->next = NULL;
	struct pcb *p1 = head2;  
	for(q = head->next; q != NULL; q = q->next)  
	{   
		struct pcb *k = calloc(1, sizeof(struct pcb));   
		k->Process_ID = q->Process_ID;    
		k->Status = q->Status;   
		//k->Arrive_Time = q->Arrive_Time;
		k->Priority = q->Priority;   
		k->Process_Need_Time = q->Process_Need_Time;   
		p1->next = k;   
		k->next = NULL;   
		p1 = p1->next;   
	}  
}
/* need to measure
(1) the CPU utilization
(2) the average job throughput per second
(3) the average job turnaround time. */
void SJF() 
{  
	struct pcb *p;  
	struct pcb *pmin;  
	double process_execute_time = 0.0; 
	double turnaround_time;
	double average_turnaround_time;
	double count = 0.0;
	double cpu_utlization;
	double average_throughput;
	while(head1->next != NULL)  
	{   
		pmin = head1->next;  
		/* find the shortest process. */
		for(p = head1->next; p != NULL; p = p->next)   
		{    
			if(pmin->Process_Need_Time > p->Process_Need_Time)
			{      
				pmin = p;  
			}
		}
		count++;
		process_execute_time += pmin->Process_Need_Time;
		printf("execute the remaining shortest process: %d\n", pmin->Process_ID);  
		printf("\n"); 
		   
		for(p = head1; p != NULL; p = p->next)   
		{    
			if(p->next == pmin)    
			{   
				p->next = p->next->next;        
				free(pmin);
			}    
		}   
	}   
	/* Assume context switch time is 1 second, equaling to (count - 1).*/
	turnaround_time = process_execute_time + count - 1;
	average_turnaround_time = turnaround_time / count;
	cpu_utlization = process_execute_time / turnaround_time * 100.0;
	average_throughput = count / turnaround_time;
	printf("All processes are executed.\n");
	printf("\n"); 
	printf("Context Switch is: %.2f times\n", (count-1)); 
	printf("Turnaround Time is: %.2f\n", turnaround_time); 
	printf("Average Turnaround Time is: %.2f\n", average_turnaround_time); 
	printf("CPU utilization is: %.2f%%\n", cpu_utlization); 
	printf("Average throughput is: %.2f\n", average_throughput);
	return;
}

/* For RR, turnaround time is the number of time slice every process takes times the sum of time slice plus context switch time.*/
void RR(int m) 
{  
	struct pcb *p;    
	double turnaround_time;
	double average_turnaround_time;
	double count = 0.0;
	double cpu_utlization;
	double average_throughput;
	printf("Time quantum is 4.\n"); 
	while(head2->next != NULL)
	{   
		p = head2->next;   
		struct pcb *prep=head2;    
		struct pcb *q;    
		printf("Round Robin: \n"); 
		while(p != NULL)   
		{    
			
			printf("Process %d executes one time slice.\n", p->Process_ID);   
			for(q = head2; q->next != NULL; q = q->next)    
			{     
				if(q->next == p)     
				{   
					/* time slice is 4*/
					p->Process_Need_Time -= 4;       
					count++;     
				}     
			}     
			if(p->Process_Need_Time <= 0)    
			{     
				printf("Process %d has already finished.\n", p->Process_ID);     
				prep->next = prep->next->next;     
				free(p);     
				p = prep->next;    
			}    
			else    
			{    
				prep = prep->next;     
				p = p->next;     
			}    
		} 
			printf("\n"); 
	}    
	/* Assume time slice is 4 seconds, and context switch time is 1 second. */
	turnaround_time = count * 4 + count - 1;
	average_turnaround_time = turnaround_time / m;
	cpu_utlization = (count * 4) / turnaround_time * 100.0;
	average_throughput = m / turnaround_time;
	printf("\n"); 
	printf("All processes have already finsihed.\n");
	printf("\n"); 
	printf("Context Switch is: %.2f times\n", count); 
	printf("Turnaround Time is: %.2f\n", turnaround_time); 
	printf("Average Turnaround Time is: %.2f\n", average_turnaround_time); 
	printf("CPU utilization is: %.2f%%\n", cpu_utlization); 
	printf("Average throughput is: %.2f\n", average_throughput);     
	printf("\n"); 
	return;
}


int main() 
{    
	int m;
	printf("Please enter the numbers of process: \n");
	scanf("%d", &m);
	n = m;   
	if(n == 0)      
		printf("No processes needed to run.");
	else  
	{   
		createProcess(n);     
		printf("---------------------------------Shortest Job First Scheduling--------------------------------\n");   
		printf("\n"); 
		SJF();      
		printf("\n");        
		printf("-------------------------------------Round-Robin Scheduling------------------------------------\n"); 
		printf("\n"); 
		RR(m); 
		printf("\n"); 
	}  
	return 0;
}