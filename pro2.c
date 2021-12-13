#include "mpi.h"
#include <stdio.h>
#include <string.h>

int main(int argc,char *argv[]) {
int myid, numprocs;
char message[30];
int myrank;
MPI_Status status;
int TAG = 0;
MPI_Init(&argc, &argv);
MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
if (myrank == 0) {
strcpy(message, "Hi, Second Processor!");
MPI_Send(&message, 30, MPI_CHAR, 1, TAG, MPI_COMM_WORLD);
}
else {
MPI_Recv(&message, 30, MPI_CHAR, 0, TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
printf("received: %s\n", message);
}
MPI_Finalize();
return 0;
}
