#include "mpi.h"
#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[]) {
 int myrank, size, message;
 int TAG = 0;
 MPI_Status status;
 MPI_Init(&argc, &argv);
 MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
 MPI_Comm_size(MPI_COMM_WORLD, &size);
 message = myrank;
 if ((myrank % 2) == 0) {
  if ((myrank + 1) != size)
   MPI_Send(&message, 6, MPI_CHAR, myrank + 1, TAG, MPI_COMM_WORLD);
 }
 else {
  if ((myrank % 2) != 0) 
MPI_Recv(&message, 6, MPI_CHAR, myrank - 1, TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
  printf("received :%i\n", message);
 }
 MPI_Finalize();
 return 0;
}
