#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>
 
int main(int argc, char* argv[])
{
    MPI_Init(&argc, &argv);
    
    int message = 1234;
    int size;
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    
    int myrank;
    MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
    
    switch(myrank)
    {
        case 0:
        {            
            int buffer_size = (MPI_BSEND_OVERHEAD + sizeof(int));
            int* buffer = malloc(buffer_size);
            buffer = (int*)malloc(buffer_size + MPI_BSEND_OVERHEAD);
 
            MPI_Buffer_attach(buffer, buffer_size);
 
            printf("[Process %d] I send value %d to process %d.\n", myrank, message, 1);
            MPI_Bsend(&message, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
 
            MPI_Buffer_detach(&buffer, &buffer_size);
            free(buffer);
            break;
        }
        case 1:
        {
            int buffer;
            MPI_Recv(&buffer, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            printf("[Process %d] I received value %d.\n", myrank, buffer);
            break;
        }
    }
 
    MPI_Finalize();
 
    return 0;
}

