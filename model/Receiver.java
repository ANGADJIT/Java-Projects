package model;

//* Receiver enum for connection 
enum RECEIVER_TYPE{
    SERVER,
    CLIENT
}

class Receiver extends Thread{

    //* enum for initialzing connection
    final RECEIVER_TYPE conType;

    //* connection manager enum
    private enum CONNECTION_MANAGER{
        CLOSE_CONNECTION,
        START_CONNECTION
    }

    //* connection manager var
    CONNECTION_MANAGER manager = CONNECTION_MANAGER.CLOSE_CONNECTION;

    Receiver(RECEIVER_TYPE type){
       conType = type;
    }   

    @Override
    public void run(){
        //* start connection
        manager = CONNECTION_MANAGER.START_CONNECTION;

        if(conType == RECEIVER_TYPE.CLIENT){
            final Client client = Client.getInstance();
           
            while(manager != CONNECTION_MANAGER.CLOSE_CONNECTION){
                //* code for widget 
            }
        }
        else{
            final Server server = Server.getInstance();

            while(manager != CONNECTION_MANAGER.CLOSE_CONNECTION){
                //* code for widget 
            }
        }
    }

    void dispose(){
        manager = CONNECTION_MANAGER.CLOSE_CONNECTION;
    }
}


