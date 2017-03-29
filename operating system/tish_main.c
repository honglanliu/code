/* Written by Honglan Liu(NetID: sp6682). Course: CS 4560.*/

#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#define MAX_LEN 	1024    /* Maximum Length of the command alogwith the arguments */
#define ARG_LEN 	1024    /* Maximum Length of Argument */
#define ARG_COUNT	2    	/* Maximum number of arguments */

struct bglist_node *bglist_head, *bglist_end;
pid_t  fggroup_id;

/* background job list */
struct bglist_node {
	pid_t pid;
	char *job_name;
	char *job_status;
	struct bglist_node *next;
};

/* kill a job with ctrl+C */
void sigint_handler(int sig) {
	//kill(-getpgrp(), SIGINT);
	if (fggroup_id != 0) 
		kill(-fggroup_id, SIGINT);
    return;
}

void sigchld_handler(int sig) {
	pid_t pid;
	while ((pid = waitpid(-1, NULL, 0)) > 0) {
		struct bglist_node *current = bglist_head;
		while (current->next) {
			if (current->next->pid == pid) {
				current->next->job_status = "unactive";
				break;
			}
			current = current->next;
		}
		return;
	}
	//return;
}

void evaluate(char *command);
int parseline(char *buff, char **args);
int builtin_command(char **args);

int main() {
	char command[MAX_LEN];
	bglist_head = calloc(1, sizeof(struct bglist_node));
	bglist_end = bglist_head;
	/* catch SIGINT signal (ctrl+C) */
	signal(SIGINT, sigint_handler);   
	/* catch SIGCHLD signal */   
	signal(SIGCHLD, sigchld_handler);
	/* enter tish */   
	while(1) {
		printf("tish>>");
		/* read command */
		fgets(command, MAX_LEN, stdin); 
		/* if read a end of file, exit */
		if(feof(stdin)) {
			exit(0);
		}
		/* implement command */
		evaluate(command);
	}
}

void evaluate(char *command) {
	char   *args[ARG_LEN];
	char   buff[MAX_LEN];
	int    bg;
	pid_t  pid;
	int    status;


	strcpy(buff, command);
	/* parse buff array. return an int to see if the command is a background command */
	bg = parseline(buff, args);
	//command[strlen(command)-1]=' ';
    /* ignore white space or a null command. */
	if(args[0] == NULL) {
		return;
	}
	
	/* if it is a external command, create a child process and execute it. */
	if(!builtin_command(args)) {
		/* create a child process */
		if((pid=fork()) == 0) {
			if((execvp(args[0], args)) < 0) {
				perror("Exec failed: ");	
			}
			exit(0);
		}
		/* if it is a foreground command, parent process waits child process terminated. */
		if(!bg) {
			fggroup_id = pid;
			wait(&status);
		} else {
        /* if it is a background command, parent process doesn't wait child process terminated, and add this job into job list */
			command[strlen(command)-1]=' ';
			command[strlen(command)-2]=' ';
			struct bglist_node *new_bg_node = calloc(1, sizeof(struct bglist_node));
			new_bg_node->pid = pid;
			new_bg_node->job_name = malloc(MAX_LEN);
			strcpy(new_bg_node->job_name, command);
			new_bg_node->job_status = "active";
			bglist_end->next = new_bg_node;
			bglist_end = new_bg_node;

		}
	}
	return;
}	


/* internal command */
int builtin_command(char **args) {
	if(!strcmp(args[0],"bye")) {
		exit(0);
	}
	if(!strcmp(args[0],"kill")) {
		kill(atoi(args[1]), SIGTERM);
		return 1;
	}
	/* show all background commands and their pids, status. */
	if(!strcmp(args[0],"jobs")) {
		struct bglist_node *current = bglist_head->next;
		printf("%-15s %-15s %-15s\n","PID","JOBS","STATUS");
		while (current) {
			printf("%-15d %-15s %-15s\n", current->pid, current->job_name, current->job_status);
			current = current->next;
		}
		return 1;
	}
	/* ignore single & */
	if(!strcmp(args[0],"&")) {
		return 1;
	}
	return 0;
	
}

int parseline(char *buff, char **args){
	int     argc;
	int     bg;
    
    /* replace the last character '\n' */
    buff[strlen(buff) - 1] = '\0';

	argc = 0;
	/* put every parameter of command into args */
	args[argc] = strtok(buff, " ");
	while (args[argc] != NULL) {
		args[++argc] = strtok(NULL, " ");
	}
	/* to see if a command has '&' at last, if yes, replace '&' with NULL. */
	if((bg=(argc>0&&(*args[argc-1]=='&'))) != 0){
		args[--argc]=NULL;
	}
        
	return bg;
}


