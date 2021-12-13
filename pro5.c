#include <stdio.h>
    #include <stdlib.h>
    #include <mpi.h>

        
 int main(int argc,char *argv[]) {
 
 	MPI_Init(&argc, &argv);
	int myid, numprocs, source, i;
	int myrank, data = 2002, TAG = 0;

        MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
        MPI_Status status;
        
        switch(myrank)
        {
            case 0:
            {
                MPI_Send(&data, 1, MPI_INT, 2, 0, MPI_COMM_WORLD);
                break;
            }
            case 1:
            {
            	int message[3] = {0, 1, 2};
                MPI_Send(message, 3, MPI_INT, 2, TAG, MPI_COMM_WORLD); 
            }
            
            case 2:
            {      
            	source = status.MPI_SOURCE;
               int *buffer[3];
               int count;
               
               MPI_Get_count(&status, MPI_INT, &count);
            	
            	for(i = 0; i < count; i++){

			buffer[i] = (int*)malloc(count * sizeof(int));

            	}
            	
               MPI_Recv(buffer, 3, MPI_INT, source, 0, MPI_COMM_WORLD, 				&status);
                
                for(i = 0; i < count; i++){
                	printf("received: %ls.\n", buffer[i]);
                }
                
                break;
            
            }
        }
     
        MPI_Finalize();
     
        return 0;
    }
    
    
